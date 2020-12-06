/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metaheuristicas_Practica_3;

import java.util.HashSet;

/**
 *
 * @author David
 */
public class ColoniaHormigas {

    
    ///Atributos de la clase:
    private final Archivo archivoDatos;///<Contiene los datos del problema
    private final GestorLog gestor;///<Gestor encargado de la creación del Log
    private double[][] matrizFeromonas;
    private Hormiga mejorHormiga;
    
    
    public ColoniaHormigas(Archivo _archivoDatos, GestorLog _gestor, int _iteraciones, int _numeroCromosomas) {
        int tam = _archivoDatos.getTama_Solucion();
        this.matrizFeromonas = new double[tam][tam];
        this.archivoDatos = _archivoDatos;
        this.gestor = _gestor;
        this.mejorHormiga = new Hormiga(new HashSet<>(), 0.0f);
        
    }
    
    
    
    public void colonia(){
        
        
        
        
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
