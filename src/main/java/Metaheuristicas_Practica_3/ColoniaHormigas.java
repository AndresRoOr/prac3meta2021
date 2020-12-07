/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metaheuristicas_Practica_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author David
 */
public class ColoniaHormigas {

    
    ///Atributos de la clase:
    private final Archivo archivoDatos;///<Contiene los datos del problema
    private final GestorLog gestor;///<Gestor encargado de la creaci�n del Log
    private double[][] matrizFeromonas;
    private Random_p generadorAleatorio;
    private Hormiga mejorHormiga;
    private ArrayList<Hormiga> colonia;
    private final int tamColonia;
    private final int tamHormiga;
    private final int tamMatriz;
    private final int maxItereaciones;
    private int iteraciones;
    private float q0;
    private float phi;
    private float beta;
    private float rho;
    private float delta;
    private float alfa;
    private int costeGreedy;
    
    
    
    public ColoniaHormigas(Archivo _archivoDatos, GestorLog _gestor, 
            int _iteraciones, int _numeroHormigas, long _semilla) {
        int tam = _archivoDatos.getTama_Solucion();
        this.matrizFeromonas = new double[tam][tam];
        this.archivoDatos = _archivoDatos;
        this.gestor = _gestor;
        this.mejorHormiga = new Hormiga(new HashSet<>(), 0.0f);
        this.colonia = new ArrayList<>();
        this.tamColonia = _numeroHormigas;
        this.tamHormiga = archivoDatos.getTama_Solucion();
        this.generadorAleatorio = new Random_p();
        this.generadorAleatorio.Set_random(_semilla);
        this.tamMatriz = archivoDatos.getTama_Matriz();
        this.maxItereaciones = _iteraciones;
        iteraciones = 1;
        
    }
    
    
    
    public void colonia(){
        
        while(iteraciones <= maxItereaciones){
            
            inicializarColonia();
            
            for(int i = 1; i<= tamHormiga; i++ ){
                
                for( int j = 0; j < tamColonia; j++){
                    
                    ArrayList<Integer> LRC = new ArrayList<>();
                    
                    HashMap<Integer,Double> noSeleccionados = new HashMap<>();
                    for(int a =0; a <= tamMatriz; a++){
                        if(!colonia.get(j).contains(a)){
                            
                            double aporte = 0.0f;
                            
                            for (int b : colonia.get(j).getCromosoma()) {
                                
                                //aporte += 
                                
                            }
                            
                            
                            noSeleccionados.put(a,Double.NaN);
                        }
                    }
                    
                 actualizarFeromonaLocal();
                    
                    
                }
                
            }

            colonia.clear();
        }
        
        
    }
    
    private void inicializarColonia(){
        
        while(colonia.size()<tamColonia){
            int primerElemento = generadorAleatorio.Randint(0, tamMatriz-1);
            Set<Integer> aux = new HashSet<>(primerElemento);
            Hormiga hormiga = new Hormiga(aux, 0.0f);
            colonia.add(hormiga);
        }
        
    }
    
    private void inicializarFeromona(){
        for(int i=0; i < tamColonia; i++){
            for(int j=0; j < tamColonia; j++){
                matrizFeromonas[i][j] = (1/(tamColonia*costeGreedy));
            }
        }
    }
    
    private void actualizarFeromonaLocal(){
        
    }
            
    
    /*void PresentarResultados() {

        _gestor.escribirArchivo("");
        _gestor.escribirArchivo("Resultados");

        float coste = calcularCoste(_mejorCromosoma.getCromosoma());
        _gestor.escribirArchivo("");
        _gestor.escribirArchivo("Mejor coste: " + coste);
        _gestor.escribirArchivo("Mejor cromosoma: " + _mejorCromosoma.getCromosoma());
        
        Main.console.presentarSalida("Mejor Coste:  " + _mejorCromosoma.getContribucion());
        Main.console.presentarSalida("");

    }
*/
    
}
