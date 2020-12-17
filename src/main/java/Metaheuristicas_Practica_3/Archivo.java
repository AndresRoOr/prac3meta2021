/**
 * @file    Archivo.java
 * @author Andrés Rojas Ortega
 * @author David Díaz Jiménez
 * @version 1.0
 * @date 27/09/2020
 */
package Metaheuristicas_Practica_3;

import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @brief Clase que almacena toda la informaci�n que se encuentra dentro de un
 * archivo
 * @class Archivo
 * @author Andrés Rojas Ortega
 * @author David Díaz Jiménez
 * @date 27/09/2020
 */
public final class Archivo {

    ///Atributos de la clase:
    private String _nombre;///<Nombre del objeto                                        
    private String _ruta;///<Ruta completa del archivo de datos
    private Integer _tama_Matriz;///<Tama�o de la matriz de datos
    private Integer _tama_Solucion;///<Tama�o de la soluci�n             
    private float[][] _matriz;///<Matriz que almacena los datos del archivo
    

    /**
     * @brief Constructor parametrizado de la clase Archivo
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     * @param nombre String Nombre del objeto de la clase Archivo
     * @param ruta String Ruta completa del archivo que contiene los datos
     * @param semilla long Semilla para generar n�meros aleatorios
     * @throws FileNotFoundException
     * @throws IOException
     */
    Archivo(String nombre, String ruta)
            throws FileNotFoundException, IOException {

        _nombre = nombre;
        _ruta = ruta;
        _tama_Matriz = 0;
        _tama_Solucion = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(_ruta));
            String currentRecord = br.readLine();
            int num_linea = 0;

            while (currentRecord != null) {
                if (!currentRecord.isEmpty()) {

                    String[] linea = StringUtils.split(currentRecord, " ");

                    if (num_linea == 0) {
                        _tama_Matriz = Integer.parseInt(linea[0]);
                        _tama_Solucion = Integer.parseInt(linea[1]);
                        _matriz = new float[_tama_Matriz][_tama_Matriz];
                    } else {
                        Integer i = (Integer.parseInt(linea[0]));
                        Integer j = (Integer.parseInt(linea[1]));
                        _matriz[i][j] = (Float.parseFloat(linea[2]));
                        _matriz[j][i] = (Float.parseFloat(linea[2]));
                        //_matrizHeuristica[j][i] = 
                                //(1/(Double.parseDouble(linea[2])));
                        //_matrizHeuristica[i][j] = 
                          //      (1/(Double.parseDouble(linea[2])));
                    }
                    num_linea++;
                }
                currentRecord = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * @brief M�todo getter para el parmetro _nombre
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     * @return _nombre String
     */
    public String getNombre() {
        return _nombre;
    }

    /**
     * @brief M�todo getter para el par�metro _ruta
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     * @return _ruta String
     */
    public String getRuta() {
        return _ruta;
    }

    /**
     * @brief M�todo getter para el par�metro _tama_Matriz
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     * @return _tama_Matriz Integer
     */
    public Integer getTama_Matriz() {
        return _tama_Matriz;
    }

    /**
     * @brief M�todo getter para el par�metro _tama_Solucion
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     * @return _tama_Solucion Integer
     */
    public Integer getTama_Solucion() {
        return _tama_Solucion;
    }

    public float [][] getMatrizCostes() {
        return _matriz;
    }
    
    /**
     * @brief M�todo setter para el par�metro _matriz
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     * @param _matriz double[][] Nuevo valor de _matriz
     */
    /*public void setMatriz(float[][] _matriz) {
        this._matriz = _matriz;
    }
    */
    /**
     * @brief Muestra por pantalla los datos del Archivo y el contenido de
     * _matriz
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     */
    /*void presentarDatos() {
        System.out.println(_nombre);
        System.out.println("Datos matriz");
        for (int i = 0; i < _tama_Matriz; i++) {
            for (int j = 0; j < _tama_Matriz; j++) {
                System.out.print(_matriz[i][j] + " ");
            }
            System.out.println("");
        }

    }*/

}
