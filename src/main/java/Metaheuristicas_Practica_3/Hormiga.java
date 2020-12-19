/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metaheuristicas_Practica_3;

/**
 *
 * @author David
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @brief Clase que almacena la informaci�n de cada elemento que forma parte de
 * una poblaci�n
 * @class Hormiga
 * @author Andrés Rojas Ortega
 * @author David Díaz Jiménez
 * @date 22/11/2020
 */
public final class Hormiga implements Comparable<Hormiga> {

    ///Atributos de la clase:
    private ArrayList<Integer> elementos;///<Conjunte de genes soluci�n del 
    //problema
    private Set<Integer> aux;
    private double _contribucion;///<Coste que aporta a la soluci�n

    /**
     * @brief Constructor parametrizado de la clase Hormiga
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 22/11/2020
     * @param _elementos  Set<Integer>
     * @param _contribucion Float
     */
    public Hormiga(ArrayList<Integer> _elementos, float _contribucion) {
        this.elementos = _elementos;
        this._contribucion = _contribucion;
        this.aux = new HashSet<>(elementos);

    }

    /**
     * @brief Constructor parametrizado de la clase Hormiga
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 22/11/2020
     * @param _elementos Set<Integer>
     * @param _contribucion float
     * @param recal boolean
     */
    public Hormiga(ArrayList<Integer> _elementos, float _contribucion, boolean recal) {
        this.elementos = _elementos;
        this._contribucion = _contribucion;
        this.aux = new HashSet<>(elementos);

    }

    /**
     * @brief Constructor por copia de la clase Cromosoma
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/11/2020
     * @param otro Hormiga
     */
    public Hormiga(Hormiga otro) {
        this.elementos = new ArrayList<>(otro.getElementos());
        this._contribucion = otro.getContribucion();
        this.aux = new HashSet<>(elementos);

    }

    @Override
    public int compareTo(Hormiga otro) {
        Double ele1 = this.getContribucion();
        Double ele2 = otro.getContribucion();
        int comparativa = ele1.compareTo(ele2);

        if (comparativa < 0) {
            return -1;
        } else if (comparativa > 0) {
            return 1;
        } else {
            return 0;
        }

    }
    
    public boolean contains(int elemento){
        
        return this.aux.contains(elemento); 
    }

    /**
     * @brief Metodo getter del atributo _contribucion
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 02/11/2020
     * @return _contribucion float
     */
    public double getContribucion() {
        return this._contribucion;
    }

    public void add(int elemento){
        
        this.aux.add(elemento);
        this.elementos.add(elemento);
        
    }
    
    /**
     * @brief Metodo setter del atributo _contribucion
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 22/11/2020
     * @param cont float
     */
    public void setContribucion(float cont) {
        this._contribucion = cont;
    }

    /**
     * @brief M�todo getter del atributo cromosoma
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/11/2020
     * @return elementos Set<Integer>
     */
    public ArrayList<Integer> getElementos() {
        return elementos;
    }

    /**
     * @brief M�todo setter del atributo cromosoma
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/11/2020
     * @param _elementos  Set<Integer> 
     */
    public void setEloementos(ArrayList<Integer> _elementos) {
        this.elementos = _elementos;
    }

}
