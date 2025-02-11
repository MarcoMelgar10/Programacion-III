package AplicacionImagen.operaciones;

import AplicacionImagen.Imagen;
import AplicacionImagen.excepcion.ExepcionImagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DibujarCuadrado implements OperacionImagen{
    private static final Logger logger = LogManager.getRootLogger();
    private int imagenPintada[][];


    @Override
    public void hacer(Imagen imagen, ParametrosOperacion parametros) throws ExepcionImagen {

    }

    public void dibujarCuadrado(Imagen imagen, int x0, int y0, int x1, int y1, int color) {

        int ancho = Math.abs(x1 - x0);
        int alto = Math.abs(y1 - y0);

        int xRect = (x1 < x0) ? x0 - ancho : x0;
        int yRect = (y1 < y0) ? y0 - alto : y0;

        int maxX = imagen.getImagenPixeles().length;
        int maxY = imagen.getImagenPixeles()[0].length;

        for (int x = xRect; x <= xRect + ancho && x < maxX; x++) {
            for (int y = yRect; y <= yRect + alto && y < maxY; y++) {
                imagen.getImagenPixeles()[x][y] = color;
            }
        }
        logger.debug("Cuadrado dibujado desde: x " + x0 + ", y " + y0 + ", hasta: x " + x1 + ", y " + y1);
        imagenPintada = imagen.getImagenPixeles();
    }



    public int[][] getImagenPintada() {
        return imagenPintada;
    }
}
