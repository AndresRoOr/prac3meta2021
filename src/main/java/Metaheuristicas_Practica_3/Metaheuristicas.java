/**
 * @file    Metaheuristicas.java
 * @author Andrés Rojas Ortega
 * @author David Díaz Jiménez
 * @version 1.0
 * @date 27/09/2020
 */
package Metaheuristicas_Practica_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @brief Clase que calcula todos los resultados con los algoritmos solicitados
 * sobre todos los datos facilitados
 * @class Metaheuristicas
 * @author Andrés Rojas Ortega
 * @author David Díaz Jiménez
 * @date 27/09/2020
 */
public final class Metaheuristicas {

    ///Atributos de la clase:
    private final Configurador config;///<Contiene los par�metros principales del programa
    private final String nombre;///<Nombre del objeto Metaheuristicas
    private ArrayList<Archivo> archivos;///<Contiene el nombre de los archivos que 
    ///contienen los datos sobre los que hacer los c�lculos
    private String ruta_Carpeta_Archivos;///<Directorio que contiene los archivos

    /**
     * @brief Constructor parametrizado de la clase Metaheuristicas
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     * @param nombre String Nombre de la nueva instancia
     * @param ruta String Ruta del directorio que contiene los archivos
     * @param config Configurador Objeto con los parámetros del programa
     */
    Metaheuristicas(String _nombre, String _ruta, Configurador _config) {
        this.config = _config;
        this.nombre = _nombre;
        this.ruta_Carpeta_Archivos = _ruta;
        this.archivos = new ArrayList<>();
    }

    /**
     * @brief Realiza la lectura de todos los datos de todos los archivos
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     * @throws FileNotFoundException
     * @throws IOException
     */
    void lector_Archivos() throws FileNotFoundException, IOException {
        final File carpeta = new File(ruta_Carpeta_Archivos);
        if(carpeta.exists()){
            for (final File fichero_Entrada : carpeta.listFiles()) {

                    if (fichero_Entrada.isFile()) {
                        Archivo ar = new Archivo(fichero_Entrada.getName(),
                                ruta_Carpeta_Archivos + "/"
                                + fichero_Entrada.getName());
                        archivos.add(ar);
                    }
            
            }
        }else{
                Main.console.presentarSalida("No existe el directorio: " 
                        + carpeta.getPath());
            }

    }

    /**
     * @brief Muestra por pantalla los datos de todos los archivos le�dos
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     */
    /*void mostrar_Datos() {
        for (Archivo ar : _archivos) {
            ar.presentarDatos();
        }
    }*/

    /**
     * @brief Calcula la soluci�n para todos los archivos utilizando el
     * algoritmo Greedy y muestra el resultado por pantalla
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     */
    void coloniaHormigas() {

        if (archivos.size() > 0) {

            int aumento = (1000 / (archivos.size() * 5));

            for (Archivo ar : archivos) {

                int ite = 1;

                while (ite <= 5) {
                    Main.console.setValue(aumento / 2);

                    Timer t = new Timer();

                    Main.gestor.cambiarNombre("colonia/MPX_"+"SEM_" +
                            config.getSemilla()+"_" + ar.getNombre());
                    Main.gestor.abrirArchivo();

                    Random_p sem = new Random_p();
                    sem.Set_random(config.getSemilla());

                    
                    ColoniaHormigas ch = new ColoniaHormigas(ar, Main.gestor, 
                            config.getIteraciones(), 
                            config.getNumeroHormigas(), sem, 
                            config.getQ0(),config.getPhi(), config.getBeta(),
                            config.getRho(), config.getDelta(),
                            config.getAlfa(), 19396);

                    t.startTimer();

                    ch.colonia();

                    double tiempo = t.stopTimer();

                    Main.console.presentarSalida(
                            "Datos de la soluci�n al problema: " 
                                    + ar.getNombre() + ", con SEMILLA: "
                                    + config.getSemilla());
                    Main.console.presentarSalida(
                            "Tiempo de ejecuci�n del algoritmo: " + tiempo
                                    + " milisegundos");

                    ch.PresentarResultados();

                    Main.gestor.cerrarArchivo();

                    Main.console.setValue(aumento / 2);

                    ite++;

                    config.rotarSemilla();
                }

                config.RecuperarSemilla();
            }
        } else {
            Main.console.presentarSalida("No hay datos en el directorio: " 
                    + config.directoriosDatos.get(0));
        }
    }
}
