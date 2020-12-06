/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metaheuristicas_Practica_3;

import com.formdev.flatlaf.FlatLightLaf;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 * @brief Clase Main del programa
 * @class Main
 * @author Andr�s Rojas Ortega
 * @author David D�az Jim�nez
 * @date 27/09/2020
 */
public class Main {

    
    public static Consola console = new Consola();
    public static GestorLog gestor = new GestorLog("");
    public static final ExecutorService exec = Executors.newFixedThreadPool(4);

    /**
     * @brief Funci�n principal del programa
     * @author Andr�s Rojas Ortega
     * @author David D�az Jim�nez
     * @date 27/09/2020
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        Configurador config = new Configurador("./config.txt");

        ArrayList<File> directorios = new ArrayList<>();
        directorios.add(new File("./archivos"));
        directorios.add(new File("./archivos/Datos"));
        directorios.add(new File("./archivos/Log"));
        directorios.add(new File("./archivos/Log/colonia"));

        directorios.stream().filter(directorio -> (!directorio.exists())).forEachOrdered((File directorio) -> {
            if (directorio.mkdirs()) {
            }
        });

        console.presentarSalida("");

        while (console.getEleccion() != 4) {

            while (console.getEleccion() == 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (console.getEleccion() == 4) {
                System.exit(0);
            }

            Metaheuristicas M1 = new Metaheuristicas(config.getDirectoriosDatos().get(0),
            config.getDirectoriosDatos().get(0), config);
            M1.lector_Archivos();

            switch (console.getEleccion()) {

                case 1:
                    
                    M1.coloniaHormigas();
                    break;
                    
                case 2:
                    config = null;
                    config = new Configurador("./config.txt");
                    console.restaurarEleccion();
                    break;
                   
            }
            M1 = null;
            console.restaurarEleccion();
        }
        exec.shutdownNow();
        System.exit(0);
    }
}
