package Test;

import AplicacionImagen.Imagen;
import AplicacionImagen.command.DibujarPuntoCommand;
import AplicacionImagen.command.Invocador;
import AplicacionImagen.excepcion.ExepcionImagen;
import AplicacionImagen.operaciones.DibujarCuadrado;
import AplicacionImagen.operaciones.DibujarLinea;
import AplicacionImagen.operaciones.DibujarPunto;
import AplicacionImagen.operaciones.ParametrosOperacion;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class AplicacionImagenTest {


    @Test
    public void testDibujarPunto() throws ExepcionImagen {
        // Arrange
        int ancho = 10;
        int alto = 10;
        JTabbedPane tabbedPane = new JTabbedPane();
        Invocador invocador = new Invocador(tabbedPane);
        Imagen imagen = new Imagen(ancho, alto, invocador); // Crear imagen vacía
        ParametrosOperacion parametros = new ParametrosOperacion(5, 5, 1);  // Pincel en (5,5) con color 1
        DibujarPunto dibujarPunto = new DibujarPunto();

        // Act
        dibujarPunto.hacer(imagen, parametros);  // Dibujar el punto
        int[][] resultado = dibujarPunto.getImagenPintada();  // Obtener imagen después de dibujar


        // Crear el resultado esperado
        int[][] esperado = new int[ancho][alto];  // Imagen vacía
        int radio = 3;
        for (int i = -radio; i <= radio; i++) {
            for (int j = -radio; j <= radio; j++) {
                if (i * i + j * j <= radio * radio) {
                    int nuevoX = 5 + i;
                    int nuevoY = 5 + j;
                    if (nuevoX >= 0 && nuevoX < ancho && nuevoY >= 0 && nuevoY < alto) {
                        esperado[nuevoX][nuevoY] = 1;  // Color 1 en los puntos dibujados
                    }
                }
            }
        }

        // Assert
        assertArrayEquals(esperado, resultado);

    }
    @Test
    public void testDibujarCuadrado() {
        // Arrange
        int anchoImagen = 10;
        int altoImagen = 10;
        JTabbedPane tabbedPane = new JTabbedPane();
        Invocador invocador = new Invocador(tabbedPane);
        Imagen imagen = new Imagen(anchoImagen, altoImagen, invocador);

        int x0 = 2, y0 = 2, x1 = 5, y1 = 5;
        int color = 1;

        DibujarCuadrado dibujarCuadrado = new DibujarCuadrado();

        // Act
        dibujarCuadrado.dibujarCuadrado(imagen, x0, y0, x1, y1, color);  // Dibujar el cuadrado
        int[][] resultado = dibujarCuadrado.getImagenPintada();  // Obtener la imagen tras la operación

        // Crear el resultado esperado
        int[][] esperado = new int[anchoImagen][altoImagen];

        for (int x = 2; x <= 5; x++) {
            for (int y = 2; y <= 5; y++) {
                esperado[x][y] = color;
            }
        }

        // Assert
        assertArrayEquals(esperado, resultado);  // Comparar las matrices de píxeles
    }
    @Test
    public void testDibujarLineaConRadio() {
            // Arrange
            int anchoImagen = 10;
            int altoImagen = 10;
            JTabbedPane tabbedPane = new JTabbedPane();
            Invocador invocador = new Invocador(tabbedPane);
            Imagen imagen = new Imagen(anchoImagen, altoImagen, invocador);  // Instancia de la imagen

            // Coordenadas de la línea, color y radio
            int x0 = 1, y0 = 1, x1 = 8, y1 = 8;
            int color = 1;
            int radio = 3;

            DibujarLinea dibujarLinea = new DibujarLinea();  // Instancia de la clase a probar

            // Act
            dibujarLinea.dibujarLinea(imagen, x0, y0, x1, y1, color);  // Dibujar la línea
            int[][] resultado = dibujarLinea.getImagenPintada();  // Obtener la imagen tras la operación

            // Crear el resultado esperado
            int[][] esperado = new int[anchoImagen][altoImagen];  // Imagen vacía

            // Simular el trazado de la línea considerando el radio
            for (int i = 0; i <= anchoImagen - 1; i++) {
                for (int j = 0; j <= altoImagen - 1; j++) {
                    // Comprobar si la línea pasa cerca de este punto
                    double distance = Math.abs((y1 - y0) * i - (x1 - x0) * j + x1 * y0 - y1 * x0) / Math.sqrt(Math.pow(y1 - y0, 2) + Math.pow(x1 - x0, 2));
                    if (distance <= radio) {
                        esperado[i][j] = color;  // Pinta el color si está dentro del radio
                    }
                }
            }

            // Assert
            assertArrayEquals(esperado, resultado);
        }




    }
