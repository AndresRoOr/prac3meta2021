/**
 * @file    GestorLog.java
 * @author Andrés Rojas Ortega
 * @author David Díaz Jiménez
 * @version 1.0
 * @date 02/11/2020
 */
package Metaheuristicas_Practica_3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @brief Clase encargada de crear y escribir los archivos log del programa
 * @class GestorLog
 * @author Andrés Rojas Ortega
 * @author David Díaz Jiménez
 * @date 02/11/2020
 */
public final class GestorCSV {

    ///Atributos de la clase:
    private String _archiveName;
    private FileWriter fichero = null;
    private PrintWriter pw = null;

    /**
     * @brief Constructor parametrizado de la clase GestorLog
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 02/11/2020
     * @param name String
     */
    public GestorCSV(String name) {
        _archiveName = name;

    }

    /**
     * @brief Metodo setter del atributo _archiveName
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 02/11/2020
     * @param nombre String
     */
    void cambiarNombre(String nombre) {
        _archiveName = nombre;
    }

    /**
     * @brief Abre el archivo _archiveName
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 02/11/2020
     */
    void abrirArchivo() {
        try {
            fichero = new FileWriter("./archivos/CSV/" + _archiveName+".csv");
            pw = new PrintWriter(fichero);

        } catch (IOException e) {
        }
    }

    /**
     * @brief Escribe la informaci�n guardada en line en el archivo
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 02/11/2020
     * @param linea String Cadena con la informaci�n para guardar
     */
    void escribirArchivo(String linea) {
        pw.println(linea);
    }

    /**
     * @brief Cierra el archivo.
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 02/11/2020
     */
    void cerrarArchivo() {
        try {
            // Nuevamente aprovechamos el finally para 
            // asegurarnos que se cierra el fichero.
            if (null != fichero) {
                fichero.close();
            }
        } catch (IOException e2) {
        }
    }
}
