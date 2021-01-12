/**
 * @file    AlgConfigurador_Clase02_Grupo06.java
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
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

/**
 * @brief Clase que almacena todos los parámetros principales del programa
 * @class AlgConfigurador_Clase02_Grupo06
 * @author Andrés Rojas Ortega
 * @author David Díaz Jiménez
 * @date 27/09/2020
 */
public class AlgConfigurador_Clase02_Grupo06 {

    ///Atributos de la clase:
    ArrayList<String> directoriosDatos;///<Almacena los directorios donde se 
    ///encuentran los archivos con la información del problema
    private Long semilla;///<Semilla utilizada para generar números aleatorios
    private int iteraciones;///<Número de iteraciones
    private long recuperarSemilla;///<Almacena el valor inicial de la semilla
    /// en dos puntos
    private float phi;
    private float beta;
    private float alfa;
    private int numeroHormigas;///<Número de hormigas
    private float q0;
    private float rho;
    private float delta;

    /**
     * @brief Constructor parametrizado de la clase Configurador
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     * @param ruta String Contiene la ruta completa del archivo que contiene la
     * información de los parámetros
     */
    public AlgConfigurador_Clase02_Grupo06(String ruta) {

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
                        directoriosDatos.addAll(Arrays.asList(v));
                        break;

                    case "Semilla":
                        semilla = Long.parseLong(split[1]);
                        recuperarSemilla = semilla;
                        break;
                    case "Iteraciones":
                        iteraciones = Integer.parseInt(split[1]);
                        break;

                    case "Q0":
                        q0 = Float.parseFloat(split[1]);
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

                    case "Phi":
                        phi = Float.parseFloat(split[1]);
                        break;
                    case "Delta":
                        delta = Float.parseFloat(split[1]);
                        break;
                    case "Rho":
                        rho = Float.parseFloat(split[1]);
                        break;
                }
            }
            b.close();
            AlgMain_Clase02_Grupo06.console.borrarTexto();
            AlgMain_Clase02_Grupo06.console.presentarSalida(
                    "Archivo de configuración cargado");
            AlgMain_Clase02_Grupo06.console.presentarSalida("Directorios de datos: "
                    + directoriosDatos);

        } catch (IOException e) {
            AlgMain_Clase02_Grupo06.console.borrarTexto();
            AlgMain_Clase02_Grupo06.console.presentarSalida(
                    "No se ha encontrado el archivo de configuración");
        } finally {
            try {
                if (null != f) {
                    f.close();
                }
            } catch (IOException e) {
            }
        }
    }

    /**
     * @brief Función getter del atributo directoriosDatos
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     * @return directoriosDatos ArrayList
     */
    public ArrayList<String> getDirectoriosDatos() {
        return directoriosDatos;
    }

    /**
     * @brief Función getter del atributo semilla
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     * @return semilla Long
     */
    public Long getSemilla() {
        return semilla;
    }

    /**
     * @brief Función getter del atributo iteraciones
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 27/09/2020
     * @return iteraciones Integer
     */
    public Integer getIteraciones() {
        return iteraciones;
    }

    /**
     * @brief Función getter del atributo q0
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 22/12/2020
     * @return q0 float
     */
    public Float getQ0() {
        return q0;
    }

    /**
     * @brief Función getter del atributo beta
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 22/12/2020
     * @return beta float
     */
    public Float getBeta() {
        return beta;
    }

    /**
     * @brief Función getter del atributo alfa
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 22/12/2020
     * @return alfa float
     */
    public Float getAlfa() {
        return alfa;
    }

    /**
     * @brief Función getter del atributo phi
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 22/12/2020
     * @return phi float
     */
    public Float getPhi() {
        return phi;
    }

    /**
     * @brief Función getter del atributo rho
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 22/12/2020
     * @return rho float
     */
    public Float getRho() {
        return rho;
    }

    /**
     * @brief Función getter del atributo delta
     * @author Andrés Rojas Ortega
     * @author David Díaz Jiménez
     * @date 22/12/2020
     * @return delta float
     */
    public Float getDelta() {
        return delta;
    }

    /**
     * @brief Función getter del atributo numeroHormigas
     * @author David Díaz Jiménez
     * @author Andrés Rojas Ortega
     * @date 22/11/2020
     * @return numeroHormigas Integer
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
