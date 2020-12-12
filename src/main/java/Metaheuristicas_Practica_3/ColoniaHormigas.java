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
/**
 *
 * @author David
 */
public class ColoniaHormigas {

    
    ///Atributos de la clase:
    private final Archivo archivoDatos;///<Contiene los datos del problema
    private final GestorLog gestor;///<Gestor encargado de la creaci�n del Log
    private final double [][] matrizFeromonas;
    private final Random_p generadorAleatorio;
    private Hormiga mejorHormiga;
    private final ArrayList<Hormiga> colonia;
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
            float _coste ) {
        
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
        this.iteraciones = 1;
        this.alfa = _alfa;
        this.beta = _beta;
        this.rho = _rho;
        this.delta = _delta;
        this.phi = _phi;
        this.q0 = _q0;
        this.costeGreedy = _coste;
        
    }
    
    
    
    public void colonia(){
        
        inicializarFeromona();
        
        while(iteraciones <= maxItereaciones){
            
            inicializarColonia();
            
            for(int i = 1; i< tamHormiga; i++ ){
                
                for( int j = 0; j < tamColonia; j++){
                    
                    ArrayList<Integer> LRC = generarLRC(j);
                    
                    aplicarReglaTransicion(LRC,j);

                }
                
                actualizarFeromonaLocal();
                
            }

            tareasDemonio();

            colonia.clear();

            iteraciones++;
            
            System.out.println(mejorHormiga.getContribucion());
        }
        
    }
    
    private void inicializarColonia(){
        
        while(colonia.size()<tamColonia){
            int primerElemento = generadorAleatorio.Randint(0, tamMatriz-1);
            ArrayList<Integer> aux = new ArrayList<>();
            aux.add(primerElemento);
            Hormiga hormiga = new Hormiga(aux, 0.0f);
            colonia.add(hormiga);
        }
        
    }
    
    private void inicializarFeromona(){
        double feromonaInicial = 1/(tamColonia*costeGreedy);
        for(int i = 0; i < tamMatriz; i++){
            for (int j = i; j < tamMatriz; j++) {
                matrizFeromonas[i][j] = feromonaInicial;
                matrizFeromonas[j][i] = feromonaInicial;
            }
        }
    }
    
    private ArrayList<Integer> generarLRC(int j){
        
        ArrayList<Integer> LRC = new ArrayList<>();
        HashMap<Integer,Float> noSeleccionados = new HashMap<>();
        
        float max = 0;
        float min = Float.MAX_VALUE;
        
        for(int a =0; a < tamMatriz; a++){
            if(!colonia.get(j).contains(a)){

                float aporte = 0.0f;

                for (int b : colonia.get(j).getElementos()) {

                    aporte += archivoDatos.getMatrizCostes()[b][a];

                }
                if(min == Double.MAX_VALUE){
                    max = aporte;
                    min = aporte;
                }
                
                if(aporte > max) max = aporte;
                else if(aporte < min) min = aporte;

                noSeleccionados.put(a,aporte);
            }
        }

        Iterator<Map.Entry<Integer, Float>> iterator = 
                noSeleccionados.entrySet().iterator();

        double valorCorte = min + 0.8*(max-min);
        while(iterator.hasNext()){

            Map.Entry<Integer, Float> next = iterator.next();
            if(next.getValue() >= valorCorte){
                LRC.add(next.getKey());
            }
        }
        
        return LRC;
    }
    
    private void aplicarReglaTransicion(ArrayList<Integer> LRC, int j){
        
        ArrayList<Double> ProbabilidadTransicion = new ArrayList<>();
        
        double sumatoria = 0;
        
        ArrayList<Integer> elementosHormiga = colonia.get(j).getElementos();
        
        int ultimoElemento = elementosHormiga.get(elementosHormiga.size()-1);
        
        double q = generadorAleatorio.Randfloat(0, 1);
        
        if(q0 <= q){
            
            int elemento = 0;
            double mayorValor = Double.MAX_VALUE;
            
            for(Integer eleLrc : LRC){

                sumatoria = 
                    Math.pow(matrizFeromonas[ultimoElemento][eleLrc],alfa)*
                    Math.pow(archivoDatos.getMatriz()[ultimoElemento][eleLrc]
                            ,beta);    
                
                if(mayorValor == Double.MAX_VALUE){
                    mayorValor = sumatoria;
                }
                
                if(sumatoria >= mayorValor){
                    sumatoria = mayorValor;
                    elemento = eleLrc;
                }
            }
            
            colonia.get(j).add(elemento);
            
        }else{
                 
            for(Integer eleLrc : LRC){

                sumatoria += 
                    Math.pow(matrizFeromonas[ultimoElemento][eleLrc],alfa)*
                    Math.pow(archivoDatos.getMatriz()[ultimoElemento][eleLrc]
                            ,beta);

            }

            for(Integer elemetoLrc : LRC){

                double prob = 
                    (Math.pow(matrizFeromonas[ultimoElemento][elemetoLrc]
                        ,alfa)*
                    Math.pow(archivoDatos.getMatriz()
                            [ultimoElemento][elemetoLrc]
                        ,beta))/(sumatoria);

                ProbabilidadTransicion.add(prob);

            }

            double aleatorio = generadorAleatorio.Randfloat(0,1);
        
            int index = 0;
            double probabilidadAcumulada = ProbabilidadTransicion.get(0);

            for(Double ProDouble : ProbabilidadTransicion){

                probabilidadAcumulada += ProDouble;

                if(aleatorio<probabilidadAcumulada){

                    colonia.get(j).add(LRC.get(index));
                    break;

                }    
                index++;
            }

        }
        
    }
    
    private void actualizarFeromonaLocal(){
        
        for (int i = 0; i < colonia.size(); i++) {
           int size = colonia.get(i).getElementos().size();
           for (int a  = 0; a < colonia.get(i).getElementos().size()-2; a++){
                //int a = colonia.get(i).getElementos().get(size-2);
                int b = colonia.get(i).getElementos().get(size-1);

                double valorAnterior = matrizFeromonas[a][b];

                matrizFeromonas[a][b] = (1-rho)*valorAnterior 
                        + rho*(1/(tamColonia*costeGreedy));
                matrizFeromonas[b][a] = (1-rho)*valorAnterior 
                        + rho*(1/(tamColonia*costeGreedy));
           }
        }
          
    }
    
    private void tareasDemonio(){
        
        int mejorHormigaLocal = evaluarMejorHormiga();
        
        anadirFeromona( mejorHormigaLocal);
        
        evaporacionFeronomaGlobal();
        
    }
    
    private float calcularCoste(ArrayList<Integer> elementos) {

        float coste = 0.0f;
        Object[] sol = elementos.toArray();
        int length = sol.length;

        for (int i = 0; i < length - 1; i++) {
            int a = (int) sol[i];
            for (int j = i+1; j < length; j++) {
                int b = (int) sol[j];
                coste += archivoDatos.getMatrizCostes()[a][b];
            }
        }
        
        return coste;
        
    }
            
    private void evaporacionFeronomaGlobal(){
        
        for(int i =0; i < tamColonia; i++){
            int size = colonia.get(i).getElementos().size();
            for (int a = 0; a < size -1; a++) {
                
                int elemento1 = colonia.get(i).getElementos().get(a);
                
                for (int b = a+1; size < size; b++) {
                    
                    int elemento2 = colonia.get(i).getElementos().get(b);

                    double valorAnterior = 
                            matrizFeromonas[elemento1][elemento2];

                    matrizFeromonas[a][b] = (1-phi)*valorAnterior 
                            + 100/(1/colonia.get(i).getContribucion());
                            
                    matrizFeromonas[b][a] = (1-phi)*valorAnterior 
                            + 100/(1/colonia.get(i).getContribucion());

                }  
            }
        }
    }
    
    private int evaluarMejorHormiga(){
        
        int indexMejorH= 0;
        double valorMejor = 0;
        
        for(int i = 0; i < colonia.size(); i++){

            float coste = calcularCoste(colonia.get(i).getElementos());
            colonia.get(i).setContribucion(coste);

            if(coste > valorMejor){
                
                indexMejorH = i;
                valorMejor = coste;
            }
        }
        
        if(valorMejor > mejorHormiga.getContribucion()){
            mejorHormiga = new Hormiga(colonia.get(indexMejorH));
        }
        
        return indexMejorH;    
        
    }
    
    private void anadirFeromona( int mejorHormigaLocal ){
        
        int size = colonia.get(mejorHormigaLocal).getElementos().size();
        for (int i = 0; i < size; i++) {
                 
            for (int a = 0; a < size-1; a++) {
                for (int b = a+1; b < size; b++) {
                    
                    double valorAnterior = matrizFeromonas[a][b];
                    
                    matrizFeromonas[a][b] = (1-rho)*valorAnterior 
                        + (costeGreedy);
                    
                     matrizFeromonas[b][a] = (1-rho)*valorAnterior 
                        + (costeGreedy);
                
                }  
            }
        }   
    }
    
    /*void PresentarResultados() {

        _gestor.escribirArchivo("");
        _gestor.escribirArchivo("Resultados");

        float coste = calcularCoste(_mejorCromosoma.getCromosoma());
        _gestor.escribirArchivo("");
        _gestor.escribirArchivo("Mejor coste: " + coste);
        _gestor.escribirArchivo("Mejor cromosoma: " 
            + _mejorCromosoma.getCromosoma());
        
        Main.console.presentarSalida("Mejor Coste:  " 
            + _mejorCromosoma.getContribucion());
        Main.console.presentarSalida("");

    }
*/
    
}
