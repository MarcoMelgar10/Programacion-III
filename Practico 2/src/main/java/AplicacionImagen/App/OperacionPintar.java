package AplicacionImagen.App;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.Stack;

public class OperacionPintar {
    private static final Logger logger = LogManager.getRootLogger();

    private int colorDeReferencia;
    private int rangoColor;

    public OperacionPintar() {
    }

    public int[][] floodFill(int[][] imagen, int x, int y, int colorNuevo, int rangoColor) {
        colorDeReferencia = imagen[x][y];
        this.rangoColor = rangoColor;
        if (colorDeReferencia == colorNuevo) {
            return imagen;
        }
        pintarPixeles(imagen, x, y, colorNuevo);
        logger.debug("Cambio de color " + colorDeReferencia + " a " + colorNuevo);
        return imagen;
    }

    public void pintarPixeles(int[][] imagen, int x, int y, int colorNuevo) {
        if (x < 0 || y < 0 || x >= imagen.length || y >= imagen[0].length) {
            return;
        }if(estaDentroDelRango(imagen[x][y])){
            imagen[x][y] = colorNuevo;
            pintarPixeles(imagen, x + 1, y,  colorNuevo);
            pintarPixeles(imagen, x - 1, y,  colorNuevo);
            pintarPixeles(imagen, x, y + 1,  colorNuevo);
            pintarPixeles(imagen, x, y - 1,  colorNuevo);
        }
    }
    public boolean estaDentroDelRango(int colorActual) {
        int rojoActual = (colorActual >> 16) & 0xFF;
        int verdeActual = (colorActual >> 8) & 0xFF;
        int azulActual = colorActual & 0xFF;
        int rojoDeReferencia = (colorDeReferencia >> 16) & 0xFF;
        int verdeDeReferencia = (colorDeReferencia >> 8) & 0xFF;
        int azulDeReferencia = colorDeReferencia & 0xFF; //60
        int[] coloresActuales = {rojoActual, verdeActual, azulActual};
        int[] coloresDeReferencia = {rojoDeReferencia, verdeDeReferencia, azulDeReferencia};
        for(int i=0; i < 3;i++){
            if(!(coloresActuales[i] < (coloresDeReferencia[i] + rangoColor)
                    && coloresActuales[i] > (coloresDeReferencia[i] - rangoColor))){
                return false;
            }
        }
        return true;


    }



}
