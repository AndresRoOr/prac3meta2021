/**
 * @file    AlgMetaheuristicas_Clase02_Grupo06.java
 * @author Andrés Rojas Ortega
 * @author David Díaz Jiménez
 * @version 1.0
 * @date 22/12/2020
 */
package Metaheuristicas_Practica_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @brief Clase que calcula todos los resultados con los algoritmos solicitados
 * sobre todos los datos facilitados
 * @class AlgMetaheuristicas_Clase02_Grupo06
 * @author Andrés Rojas Ortega
 * @author David Díaz Jiménez
 * @date 22/12/2020
 */
public final class AlgMetaheuristicas_Clase02_Grupo06 {

    ///Atributos de la clase:
    private final AlgConfigurador_Clase02_Grupo06 config;///<Contiene los parámetros principales del programa
    private final String nombre;///<Nombre del objeto AlgMetaheuristicas_Clase02_Grupo06
    private ArrayList<AlgArchivo_Clase02_Grupo06> archivos;///<Contiene el nombre de los archivos que 
    ///contienen los datos sobre los que hacer los cálculos
    private String ruta_Carpeta_Archivos;///<Directorio que contiene los archivos

    /**
     * @brief Constructor parametrizado de la clase Metaheuristicas
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 22/12/2020
     * @param nombre String Nombre de la nueva instancia
     * @param ruta String Ruta del directorio que contiene los archivos
     * @param config AlgConfigurador_Clase02_Grupo06 Objeto con los parámetros del programa
     */
    AlgMetaheuristicas_Clase02_Grupo06(String _nombre, String _ruta, AlgConfigurador_Clase02_Grupo06 _config) {
        this.config = _config;
        this.nombre = _nombre;
        this.ruta_Carpeta_Archivos = _ruta;
        this.archivos = new ArrayList<>();
    }

    /**
     * @brief Realiza la lectura de todos los datos de todos los archivos
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 22/12/2020
     * @throws FileNotFoundException
     * @throws IOException
     */
    void lector_Archivos() throws FileNotFoundException, IOException {
        final File carpeta = new File(ruta_Carpeta_Archivos);
        if (carpeta.exists()) {
            for (final File fichero_Entrada : carpeta.listFiles()) {

                if (fichero_Entrada.isFile()) {
                    AlgArchivo_Clase02_Grupo06 ar = new AlgArchivo_Clase02_Grupo06(fichero_Entrada.getName(),
                            ruta_Carpeta_Archivos + "/"
                            + fichero_Entrada.getName());
                    archivos.add(ar);
                }

            }
        } else {
            AlgMain_Clase02_Grupo06.console.presentarSalida("No existe el directorio: "
                    + carpeta.getPath());
        }

    }

    /**
     * @brief Calcula la solución para todos los archivos utilizando un sistema
     * de colonias de hormigas y muestra el resultado por pantalla
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 22/12/2020
     */
    void coloniaHormigas() {

        if (archivos.size() > 0) {

            int aumento = (1000 / (archivos.size() * 5));

            for (AlgArchivo_Clase02_Grupo06 ar : archivos) {

                int ite = 1;

                AlgGreedy_Clase02_Grupo06 gredy = new AlgGreedy_Clase02_Grupo06(ar);
                AlgRandom_p_Clase02_Grupo06 semGre = new AlgRandom_p_Clase02_Grupo06();
                semGre.Set_random(config.getSemilla());
                float greedy = gredy.greedy(semGre);

                while (ite <= 5) {
                    AlgMain_Clase02_Grupo06.console.setValue(aumento / 2);

                    AlgTimer_Clase02_Grupo06 t = new AlgTimer_Clase02_Grupo06();

                    String name = ar.getNombre();

                    AlgMain_Clase02_Grupo06.gestor.cambiarNombre("Alfa_" + config.getAlfa() + ",Beta_"
                            + config.getBeta() + "SEM_" + config.getSemilla()
                            + "_" + name.replaceFirst(".txt", ".log"));
                    AlgMain_Clase02_Grupo06.gestor.abrirArchivo();

                    AlgRandom_p_Clase02_Grupo06 sem = new AlgRandom_p_Clase02_Grupo06();
                    sem.Set_random(config.getSemilla());

                    AlgColoniasHormigas_Clase02_Grupo06 ch = new AlgColoniasHormigas_Clase02_Grupo06(ar, AlgMain_Clase02_Grupo06.gestor,
                            config.getIteraciones(),
                            config.getNumeroHormigas(), sem,
                            config.getQ0(), config.getPhi(), config.getBeta(),
                            config.getRho(), config.getDelta(),
                            config.getAlfa(), greedy);

                    t.startTimer();

                    ch.colonia();

                    double tiempo = t.stopTimer();

                    AlgMain_Clase02_Grupo06.console.presentarSalida(
                            "Datos de la solución al problema: "
                            + ar.getNombre() + ", con SEMILLA: "
                            + config.getSemilla());
                    AlgMain_Clase02_Grupo06.console.presentarSalida(
                            "Tiempo de ejecución del algoritmo: " + tiempo
                            + " milisegundos");

                    ch.PresentarResultados();

                    AlgMain_Clase02_Grupo06.gestor.cerrarArchivo();

                    AlgMain_Clase02_Grupo06.console.setValue(aumento / 2);

                    ite++;

                    config.rotarSemilla();
                }

                config.RecuperarSemilla();
            }
        } else {
            AlgMain_Clase02_Grupo06.console.presentarSalida("No hay datos en el directorio: "
                    + config.directoriosDatos.get(0));
        }
    }
}
