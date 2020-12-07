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
 * @brief Clase que almacena la información de cada elemento que forma parte de
 * una población
 * @class Hormiga
 * @author Andrés Rojas Ortega
 * @author David Díaz Jiménez
 * @date 22/11/2020
 */
public final class Hormiga implements Comparable<Hormiga> {

    ///Atributos de la clase:
    private Set<Integer> cromosoma;///<Conjunte de genes solución del problema
    private double _contribucion;///<Coste que aporta a la solución
    private boolean recalcular;///<Indica si es necesario recalcular el coste

    /**
     * @brief Constructor parametrizado de la clase Hormiga
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 22/11/2020
     * @param cromo Set<Integer>
     * @param _contribucion Float
     */
    public Hormiga(Set<Integer> cromo, float _contribucion) {
        this.cromosoma = cromo;
        this._contribucion = _contribucion;
        this.recalcular = false;

    }

    /**
     * @brief Constructor parametrizado de la clase Hormiga
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 22/11/2020
     * @param cromo Set<Integer>
     * @param _contribucion float
     * @param recal boolean
     */
    public Hormiga(Set<Integer> cromo, float _contribucion, boolean recal) {
        this.cromosoma = cromo;
        this._contribucion = _contribucion;
        this.recalcular = recal;

    }

    /**
     * @brief Constructor por copia de la clase Cromosoma
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/11/2020
     * @param otro Hormiga
     */
    public Hormiga(Hormiga otro) {
        this.cromosoma = new HashSet<>(otro.getCromosoma());
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
        
        return this.cromosoma.contains(elemento);
        
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
     * @brief Método getter del atributo cromosoma
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/11/2020
     * @return cromosoma Set<Integer>
     */
    public Set<Integer> getCromosoma() {
        return cromosoma;
    }

    /**
     * @brief Método setter del atributo cromosoma
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/11/2020
     * @param cromosoma Set<Integer> 
     */
    public void setCromosoma(Set<Integer> cromosoma) {
        this.cromosoma = cromosoma;
    }

    /**
     * @brief Método getter del atributo recalcular
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/11/2020
     * @return recalcular boolean
     */
    public boolean isRecalcular() {
        return recalcular;
    }

    /**
     * @brief Método setter del atributo recalcular
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/11/2020
     * @param recalcular boolean 
     */
    public void setRecalcular(boolean recalcular) {
        this.recalcular = recalcular;
    }

}
