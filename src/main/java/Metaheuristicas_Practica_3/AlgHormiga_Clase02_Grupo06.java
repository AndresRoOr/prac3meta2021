/** @file    AlgHormiga_Clase02_Grupo06.java
 * @author Andrés Rojas Ortega
 * @author David Díaz Jiménez
 * @version 1.0
 * @date 22/12/2020
 */
package Metaheuristicas_Practica_3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @brief Clase que almacena la información de cada hormiga que forma parte de
 * una colonia
 * @class AlgHormiga_Clase02_Grupo06
 * @author Andrés Rojas Ortega
 * @author David Díaz Jiménez
 * @date 22/12/2020
 */
public final class AlgHormiga_Clase02_Grupo06 implements Comparable<AlgHormiga_Clase02_Grupo06> {

    ///Atributos de la clase:
    private ArrayList<Integer> elementos;///<Conjunte de elementos solución del 
    //problema
    private Set<Integer> aux;
    private double _contribucion;///<Coste que aporta a la solución

    /**
     * @brief Constructor parametrizado de la clase Hormiga
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 22/12/2020
     * @param _elementos ArrayList<Integer>
     * @param _contribucion Float
     */
    public AlgHormiga_Clase02_Grupo06(ArrayList<Integer> _elementos, float _contribucion) {
        this.elementos = _elementos;
        this._contribucion = _contribucion;
        this.aux = new HashSet<>(elementos);

    }

    /**
     * @brief Constructor parametrizado de la clase Hormiga
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 22/12/2020
     * @param _elementos ArrayList<Integer>
     * @param _contribucion float
     * @param recal boolean
     */
    public AlgHormiga_Clase02_Grupo06(ArrayList<Integer> _elementos, float _contribucion, boolean recal) {
        this.elementos = _elementos;
        this._contribucion = _contribucion;
        this.aux = new HashSet<>(elementos);

    }

    /**
     * @brief Constructor por copia de la clase Hormiga
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/12/2020
     * @param otro Hormiga
     */
    public AlgHormiga_Clase02_Grupo06(AlgHormiga_Clase02_Grupo06 otro) {
        this.elementos = new ArrayList<>(otro.getElementos());
        this._contribucion = otro.getContribucion();
        this.aux = new HashSet<>(elementos);

    }

    @Override
    /**
     * @brief Función compareTo sobrecargada
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/12/2020
     * @return int
     */
    public int compareTo(AlgHormiga_Clase02_Grupo06 otro) {
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

    /**
     * @brief Función contains sobrecargada
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/12/2020
     * @param elemento int
     * @return boolean
     */
    public boolean contains(int elemento) {

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

    /**
     * @brief Función de adición de un elemento nuevo
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/12/2020
     * @param elemento int
     */
    public void add(int elemento) {

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
     * @brief Método getter del atributo elementos
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/11/2020
     * @return elementos ArrayList<Integer>
     */
    public ArrayList<Integer> getElementos() {
        return elementos;
    }

    /**
     * @brief Método setter del atributo elementos
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/11/2020
     * @param _elementos ArrayList<Integer>
     */
    public void setEloementos(ArrayList<Integer> _elementos) {
        this.elementos = _elementos;
    }

}
