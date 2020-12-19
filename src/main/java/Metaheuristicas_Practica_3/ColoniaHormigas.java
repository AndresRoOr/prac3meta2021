/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metaheuristicas_Practica_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class ColoniaHormigas {
    
    private class costesTask implements Callable<Float>{
        private int j;

        public costesTask(int j) {
            this.j = j;
        }

        @Override
        public Float call() throws Exception {
            return calcularCoste(colonia.get(j).getElementos());
        }
        
        
    }

    private class ApliclarRTTask implements Callable<Boolean> {

        private final int j;

        public ApliclarRTTask( int j) {

            this.j = j;
        }

        @Override
        public Boolean call() throws Exception {
            
            // Invocamos a la función de cálculo de las LRC  
            
            ArrayList<Integer> LRC = generarLRC(j);
            
            ArrayList<Double> ProbabilidadTransicion = new ArrayList<>();

            double sumatoria = 0;
            
            ArrayList<Integer> elementosHormiga = 
                    new ArrayList<>(colonia.get(j).getElementos());

            // Comprobamos que regla aplicar para la transición, en función del
            // valor de q
            
            double q = aleatoriosq.get(j);

            if (q0 <= q) {

                int elemento = 0;
                double mayorValor = Double.MAX_VALUE;

                // Calculamos el elemento de la LRC que más aporta
                
                for (Integer eleLrc : LRC) {
                    
                    sumatoria = 0;
                    
                    for (Integer eleHormiga : elementosHormiga) {

                        sumatoria
                            += Math.pow(matrizFeromonas[eleHormiga][eleLrc],
                                        alfa)
                            * Math.pow(archivoDatos.getMatrizCostes()
                                    [eleHormiga][eleLrc], beta);
                    }

                    if (mayorValor == Double.MAX_VALUE) {
                        mayorValor = sumatoria;
                    }

                    if (sumatoria >= mayorValor) {
                        mayorValor = sumatoria;
                        elemento = eleLrc;
                    }
                }
                colonia.get(j).add(elemento);

            } else {
                
                Hormiga aux = new Hormiga(colonia.get(j));
                for (Integer eleLrc : LRC) {

                    // Calculamos el valor superior de la formula de transición,
                    // para cada elemento de la LRC
                    
                    double valorSuperior = 0.0;
                   
                    for (Integer eleHormiga : aux.getElementos()) {
                        valorSuperior
                                += Math.pow(archivoDatos.getMatrizCostes()
                                        [eleHormiga][eleLrc], beta)
                                * Math.pow(matrizFeromonas[eleHormiga][eleLrc],
                                        alfa);
                    }

                    // Calculamos la sumatoria
                    sumatoria += valorSuperior;
                    ProbabilidadTransicion.add(valorSuperior);

                }

                double total = 0.0;
                int index = 0;
                
                // Obtemos las probabilidades
                
                for (Double valor : ProbabilidadTransicion) {

                    Double prob = (valor / sumatoria);
                    ProbabilidadTransicion.set(index, prob);
                    total += prob;
                    index++;
                }

                double uniforme = aleatorios.get(j);
                index = 0;
                double probabilidadAcumulada = 0.0;

                // Calculamos el rango al que pertenece el uniforme
                
                for (Double ProDouble : ProbabilidadTransicion) {

                    probabilidadAcumulada += ProDouble;

                    if (uniforme <= probabilidadAcumulada) {
                        colonia.get(j).add(LRC.get(index));
                        break;
                    }
                    index++;
                }
            }
            LRC.clear();
            return true;
        }
    }

    ///Atributos de la clase:
    private final Archivo archivoDatos;///<Contiene los datos del problema
    private final GestorLog gestor;///<Gestor encargado de la creación del Log
    private final double[][] matrizFeromonas;
    private final Random_p generadorAleatorio;
    private Hormiga mejorHormiga;
    private final ArrayList<Hormiga> colonia;
    private final ArrayList<Double> aleatorios;
    private final ArrayList<Double> aleatoriosq;
    private final int tamColonia;
    private final int tamHormiga;
    private final int tamMatriz;
    private final int maxItereaciones;
    private int iteraciones;
    private final float q0;
    private final float phi;
    private final float beta;
    private final float rho;
    private final float delta;
    private final float alfa;
    private final float costeGreedy;

    public ColoniaHormigas(Archivo _archivoDatos, GestorLog _gestor,
            int _iteraciones, int _numeroHormigas, Random_p _semilla, float _q0,
            float _phi, float _beta, float _rho, float _delta, float _alfa,
            float _coste) {

        int tam = _archivoDatos.getTama_Matriz();
        this.matrizFeromonas = new double[tam][tam];
        this.archivoDatos = _archivoDatos;
        this.gestor = _gestor;
        this.mejorHormiga = new Hormiga(new ArrayList<>(), 0.0f);
        this.colonia = new ArrayList<>();
        this.tamColonia = _numeroHormigas;
        this.tamHormiga = archivoDatos.getTama_Solucion();
        this.generadorAleatorio = _semilla;
        this.tamMatriz = archivoDatos.getTama_Matriz();
        this.maxItereaciones = _iteraciones;
        this.iteraciones = 0;
        this.alfa = _alfa;
        this.beta = _beta;
        this.rho = _rho;
        this.delta = _delta;
        this.phi = _phi;
        this.q0 = _q0;
        this.costeGreedy = 1000;
        this.aleatorios = new ArrayList<>();
        this.aleatoriosq = new ArrayList<>();
    }

    public void colonia() {

        inicializarFeromona();

        double time = System.currentTimeMillis();
        while (iteraciones <= maxItereaciones && System.currentTimeMillis() -
                time<= 600000) {

            inicializarColonia();

            for (int i = 1; i < tamHormiga; i++) {
                
                //Creamos los números aleatorios, para que el orden de ejecución
                // de los hilos no altere el resultado
                
                for(int b = 0; b < tamColonia; b++){
                    aleatorios.add(generadorAleatorio.Randfloat(0,1));
                    aleatoriosq.add(generadorAleatorio.Randfloat(0, 1));
                }
                
                ArrayList<Future<Boolean>> futures = new ArrayList<>();
                
                //Lanzamos el cálculo de las LRC y la regla de Transición en un
                //hilo independiente para cada hormiga
                
                for (int a = 0; a < tamColonia; a++) {
                    
                    futures.add(Main.exec.submit(new ApliclarRTTask(a)));
                    
                }
                
                //Esperamos a que finalizen todos los hilos
                
                for (int j = 0; j < 10; j++) {
                    try {
                        futures.get(j).get();
                    } catch (InterruptedException | ExecutionException ex) {
                        Logger.getLogger(ColoniaHormigas.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }
                }

                actualizarFeromonaLocal();
                
                aleatorios.clear();
                aleatoriosq.clear();

            }

            tareasDemonio();

            colonia.clear();

            iteraciones++;
            
            System.out.println(mejorHormiga.getContribucion());
        }
    }

    private void inicializarColonia() {

        while (colonia.size() < tamColonia) {
            int primerElemento = generadorAleatorio.Randint(0, tamMatriz - 1);
            ArrayList<Integer> aux = new ArrayList<>();
            aux.add(primerElemento);
            Hormiga hormiga = new Hormiga(aux, 0.0f);
            colonia.add(hormiga);
        }
    }

    private void inicializarFeromona() {
        double feromonaInicial = (costeGreedy);
        for (int i = 0; i < tamMatriz; i++) {
            for (int j = i; j < tamMatriz; j++) {
                matrizFeromonas[i][j] = feromonaInicial;
                matrizFeromonas[j][i] = feromonaInicial;
            }
        }
    }

    private ArrayList<Integer> generarLRC(int j) {

        ArrayList<Integer> LRC = new ArrayList<>();
        HashMap<Integer, Float> noSeleccionados = new HashMap<>();

        float max = 0;
        float min = Float.MAX_VALUE;
        
        ArrayList<Integer> elementosHormiga = colonia.get(j).getElementos();

        for (int a = 0; a < tamMatriz; a++) {
            if (!colonia.get(j).contains(a)) {

                float aporte = 0.0f;

                for (int b : elementosHormiga) {

                    aporte += archivoDatos.getMatrizCostes()[b][a];

                }
                if (min == Double.MAX_VALUE) {
                    max = aporte;
                    min = aporte;
                }

                if (aporte > max) {
                    max = aporte;
                } else if (aporte < min) {
                    min = aporte;
                }

                noSeleccionados.put(a, aporte);
            }
        }

        Iterator<Map.Entry<Integer, Float>> iterator
                = noSeleccionados.entrySet().iterator();

        double valorCorte = min + delta * (max - min);
        while (iterator.hasNext()) {

            Map.Entry<Integer, Float> next = iterator.next();
            if (next.getValue() >= valorCorte) {
                LRC.add(next.getKey());
            }
        }

        return LRC;
    }

    private void actualizarFeromonaLocal() {
        
        ArrayList<Future<Float>> future = new ArrayList<>();
        
        for(int i = 0; i< colonia.size();i++){
            future.add(Main.exec.submit(new costesTask(i)));
        }
        
        for(int i = 0; i< colonia.size();i++){
            try {
                future.get(i).get();
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(ColoniaHormigas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for (int i = 0; i < colonia.size(); i++) {
            int size = colonia.get(i).getElementos().size();
            ArrayList<Integer> aux = colonia.get(i).getElementos();
            for (int j = 0; j <= size-1; j++){
                
                int a = aux.get(j);
                
                int b = aux.get(size - 1);

                double valorAnterior = matrizFeromonas[a][b];

                try {
                    matrizFeromonas[a][b] = (1 - rho) * valorAnterior
                            + rho * (1/(tamColonia*future.get(i).get()));
                    matrizFeromonas[b][a] = (1 - rho) * valorAnterior
                            + rho * (1/(tamColonia*future.get(i).get()));
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(ColoniaHormigas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void tareasDemonio() {

        int mejorHormigaLocal = evaluarMejorHormiga();

        actualizaFeromonaGlobal(mejorHormigaLocal);

    }

    private float calcularCoste(ArrayList<Integer> elementos) {

        float coste = 0.0f;
        Object[] sol = elementos.toArray();
        int length = sol.length;

        for (int i = 0; i < length - 1; i++) {
            int a = (int) sol[i];
            for (int j = i + 1; j < length; j++) {
                int b = (int) sol[j];
                coste += archivoDatos.getMatrizCostes()[a][b];
            }
        }
        return coste;
    }

    private void actualizaFeromonaGlobal(int posMHL) {

        //Evaporacion
        for (int i = 0; i < tamMatriz - 1; i++) {
            for (int j = i+1 ; j < tamMatriz; j++) {
                double valorAnterior
                        = matrizFeromonas[i][j];

                matrizFeromonas[i][j] = (1 - phi) * valorAnterior;

                matrizFeromonas[j][i] = (1 - phi) * valorAnterior;
            }
        }

        Object[] elementos
                = colonia.get(posMHL).getElementos().toArray();

        int length = elementos.length;
        
        double contribucion = colonia.get(posMHL).getContribucion();

        for (int i = 0; i < length - 1; i++) {
            int a = (int) elementos[i];
            for (int j = i + 1; j < length; j++) {
                int b = (int) elementos[j];
                matrizFeromonas[a][b] += phi
                        * (contribucion);
                matrizFeromonas[b][a] += phi
                        * (contribucion);
            }
        }
    }

    private int evaluarMejorHormiga() {

        int indexMejorH = 0;
        double valorMejor = 0;

        for (int i = 0; i < colonia.size(); i++) {

            float coste = calcularCoste(colonia.get(i).getElementos());
            colonia.get(i).setContribucion(coste);

            if (coste >= valorMejor) {

                indexMejorH = i;
                valorMejor = coste;
            }
        }
        
        if(valorMejor >= mejorHormiga.getContribucion()){
            mejorHormiga = new Hormiga(colonia.get(indexMejorH));
        }
        return indexMejorH;

    }

    void PresentarResultados() {

        gestor.escribirArchivo("");
        gestor.escribirArchivo("Resultados");

        float coste = calcularCoste(mejorHormiga.getElementos());
        gestor.escribirArchivo("");
        gestor.escribirArchivo("Mejor coste: " + coste);
        gestor.escribirArchivo("Mejor hormiga: "
                + mejorHormiga.getElementos());

        Main.console.presentarSalida("Mejor Coste:  "
                + mejorHormiga.getContribucion());
        Main.console.presentarSalida("Iteraciones completadas: " + iteraciones);
        Main.console.presentarSalida("");

    }
}
