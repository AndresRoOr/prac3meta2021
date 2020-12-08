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

import java.util.HashSet;
import java.util.Set;

/**
 * @brief Clase que almacena la informaci�n de cada elemento que forma parte de
 * una poblaci�n
 * @class Hormiga
 * @author Andr�s Rojas Ortega
 * @author David D�az Jim�nez
 * @date 22/11/2020
 */
public final class Hormiga implements Comparable<Hormiga> {

    ///Atributos de la clase:
    private Set<Integer> elementos;///<Conjunte de genes soluci�n del problema
    private double _contribucion;///<Coste que aporta a la soluci�n
    private boolean recalcular;///<Indica si es necesario recalcular el coste

    /**
     * @brief Constructor parametrizado de la clase Hormiga
     * @author Andr�s Rojas Ortega
     * @author David D�az Jim�nez
     * @date 22/11/2020
     * @param _elementos  Set<Integer>
     * @param _contribucion Float
     */
    public Hormiga(Set<Integer> _elementos, float _contribucion) {
        this.elementos = _elementos;
        this._contribucion = _contribucion;
        this.recalcular = false;

    }

    /**
     * @brief Constructor parametrizado de la clase Hormiga
     * @author Andr�s Rojas Ortega
     * @author David D�az Jim�nez
     * @date 22/11/2020
     * @param _elementos Set<Integer>
     * @param _contribucion float
     * @param recal boolean
     */
    public Hormiga(Set<Integer> _elementos, float _contribucion, boolean recal) {
        this.elementos = _elementos;
        this._contribucion = _contribucion;
        this.recalcular = recal;

    }

    /**
     * @brief Constructor por copia de la clase Cromosoma
     * @author David D�az Jim�nez
     * @author Andr�s Rojas Ortega
     * @date 22/11/2020
     * @param otro Hormiga
     */
    public Hormiga(Hormiga otro) {
        this.elementos = new HashSet<>(otro.getElementos());
        this._contribucion = otro.getContribucion();
        this.recalcular = false;

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
        
        return this.elementos.contains(elemento); 
    }

    /**
     * @brief Metodo getter del atributo _contribucion
     * @author Andr�s Rojas Ortega
     * @author David D�az Jim�nez
     * @date 02/11/2020
     * @return _contribucion float
     */
    public double getContribucion() {
        return this._contribucion;
    }

    /**
     * @brief Metodo setter del atributo _contribucion
     * @author Andr�s Rojas Ortega
     * @author David D�az Jim�nez
     * @date 22/11/2020
     * @param cont float
     */
    public void setContribucion(float cont) {
        this._contribucion = cont;
    }

    /**
     * @brief M�todo getter del atributo cromosoma
     * @author David D�az Jim�nez
     * @author Andr�s Rojas Ortega
     * @date 22/11/2020
     * @return elementos Set<Integer>
     */
    public Set<Integer> getElementos() {
        return elementos;
    }

    /**
     * @brief M�todo setter del atributo cromosoma
     * @author David D�az Jim�nez
     * @author Andr�s Rojas Ortega
     * @date 22/11/2020
     * @param _elementos  Set<Integer> 
     */
    public void setEloementos(Set<Integer> _elementos) {
        this.elementos = _elementos;
    }

    /**
     * @brief M�todo getter del atributo recalcular
     * @author David D�az Jim�nez
     * @author Andr�s Rojas Ortega
     * @date 22/11/2020
     * @return recalcular boolean
     */
    public boolean isRecalcular() {
        return recalcular;
    }

    /**
     * @brief M�todo setter del atributo recalcular
     * @author David D�az Jim�nez
     * @author Andr�s Rojas Ortega
     * @date 22/11/2020
     * @param recalcular boolean 
     */
    public void setRecalcular(boolean recalcular) {
        this.recalcular = recalcular;
    }

}
