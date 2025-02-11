package arbolBinario;

import org.apache.logging.log4j.LogManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class LectorArchivo {
    private static org.apache.logging.log4j.Logger logger = LogManager.getRootLogger();
        public static void cargarPalabras(binarySearchTree arbol, String rutaArchivo) {
            try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] palabras = linea.split("\\s+");
                    for (String palabra : palabras) {
                        if(!arbol.buscar(palabra).isEncontrada()){
                            arbol.insertar(palabra);
                        }
                    }
                }
                logger.info("Archivo cargado");
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }


