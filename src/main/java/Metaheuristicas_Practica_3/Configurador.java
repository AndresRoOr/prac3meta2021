/**
 * @file    Configurador.java
 * @author Andrés Rojas Ortega
 * @author David Díaz Jiménez
 * @version 1.0
 * @date 27/09/2020
 */
package Metaheuristicas_Practica_3;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import org.apache.commons.lang3.StringUtils;

/**
 * @brief Clase que almacena todos los parámetros principales del programa
 * @class Configurador
 * @author Andrés Rojas Ortega
 * @author David Díaz Jiménez
 * @date 27/09/2020
 */
public class Configurador {

    ///Atributos de la clase:
    ArrayList<String> directoriosDatos;///<Almacena los directorios donde se 
    ///encuentran los archivos con la información del problema
    private Long semilla;///<Semilla utilizada para generar número aleatorios
    private int iteraciones;///<Número de iteraciones
    private long recuperarSemilla;///<Almacena el valor inicial de la semilla
    /// en dos puntos
    private float fi;///<Probabilidad de que un par de cromosomas
    ///se crucen
    private float beta;///<Probabilidad de que un gen mute
    private float alfa;///<Probabilidad de que un gen del padre se incluya en
    ///el hijo
    private int numeroHormigas;///<N�mero de hormigas
    private float q0;

    /**
     * @brief Constructor parametrizado de la clase Configurador
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     * @param ruta String Contiene la ruta completa del archivo que contiene la
     * información de los parámetros
     */
    public Configurador(String ruta) {

        directoriosDatos = new ArrayList<>();

        String linea;
        FileReader f = null;
        try {
            f = new FileReader(ruta);
            BufferedReader b = new BufferedReader(f);

            while ((linea = b.readLine()) != null) {
                String[] split = StringUtils.split(linea, "=");
                switch (split[0]) {
                    case "Datos":
                        String[] v = split[1].split(" ");
                        for (int i = 0; i < v.length; i++) {
                            directoriosDatos.add(v[i]);
                        }
                        break;

                    case "Semilla":
                        semilla = Long.parseLong(split[1]);
                        recuperarSemilla = semilla;
                        break;
                    case "Iteraciones":
                        iteraciones = Integer.parseInt(split[1]);
                        break;

                    case "Q0":
                        q0 =  Float.parseFloat(split[1]);
                        break;

                    case "Beta":
                        beta = Float.parseFloat(split[1]);
                        break;

                    case "Alfa":
                        alfa = Float.parseFloat(split[1]);
                        break;

                    case "Numero de hormigas":
                        numeroHormigas = Integer.parseInt(split[1]);
                        break;

                    case "Fi":
                        fi = Float.parseFloat(split[1]);
                        break;
                }
            }
            b.close();

        } catch (IOException e) {
            Main.console.presentarSalida("No se ha encontrado el archivo de configuraci�n");
        } finally {
            try {
                if (null != f) {
                    f.close();
                }
            } catch (IOException e2) {
            }
        }
    }

    /**
     * @brief Funcion getter del atributo directoriosDatos
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     * @return directoriosDatos ArrayList
     */
    public ArrayList<String> getDirectoriosDatos() {
        return directoriosDatos;
    }

    /**
     * @brief Funcion getter del atributo semilla
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     * @return semilla Long
     */
    public Long getSemilla() {
        return semilla;
    }

    /**
     * @brief Funcion getter del atributo iteraciones
     * @author Andr�s Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     * @return iteraciones Integer
     */
    public Integer getIteraciones() {
        return iteraciones;
    }

   
    /**
     * @brief Función getter del atributo cruceMpx
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/11/2020
     * @return cruceMpx Boolean
     */
    public Float getQ0() {
        return q0;
    }

    /**
     * @brief Función getter del atributo probReproduccion
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/11/2020
     * @return probReproduccion Float
     */
    public Float getBeta() {
        return beta;
    }

    /**
     * @brief Función getter del atributo probMutacion
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/11/2020
     * @return probMutacion Float
     */
    public Float getAlfa() {
        return alfa;
    }

    /**
     * @brief Función getter del atributo pobMpx
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/11/2020
     * @return probMpx Float
     */
    public Float getFi() {
        return fi;
    }

    /**
     * @brief Función getter del atributo numeroCromosomas
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/11/2020
     * @return numeroCromosomas Integer
     */
    public Integer getNumeroHormigas() {
        return numeroHormigas;
    }

    /**
     * @brief Rota las posiciones de la semilla una posición a la derecha
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 06/10/2020
     */
    void rotarSemilla() {

        char[] cadenaSemilla = semilla.toString().toCharArray();
        char[] cadenaRotada = new char[cadenaSemilla.length];

        cadenaRotada[cadenaSemilla.length - 1] = cadenaSemilla[0];

        for (int i = 0; i < cadenaSemilla.length - 1; i++) {
            cadenaRotada[i] = cadenaSemilla[i + 1];
        }

        while (cadenaRotada[0] == '0') {
            char[] cadenaAux = cadenaRotada;

            cadenaRotada[cadenaSemilla.length - 1] = cadenaAux[0];

            for (int i = 0; i < cadenaAux.length - 1; i++) {
                cadenaRotada[i] = cadenaAux[i + 1];
            }
        }

        semilla = Long.parseLong(String.valueOf(cadenaRotada));
    }

    /**
     * @brief Restaura la semilla a su estado original
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 06/10/2020
     */
    void RecuperarSemilla() {
        semilla = recuperarSemilla;
    }
}
