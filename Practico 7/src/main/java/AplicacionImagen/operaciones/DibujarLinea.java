package AplicacionImagen.operaciones;

import AplicacionImagen.Imagen;
import AplicacionImagen.Postgres.DAO.ConexionDB;
import AplicacionImagen.Postgres.DAO.Evento;
import AplicacionImagen.Postgres.DAO.EventosDao;
import AplicacionImagen.excepcion.ExepcionImagen;

import java.sql.SQLException;

public class DibujarLinea implements OperacionImagen{
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getRootLogger();
    private int [][]imagenPintada;

    @Override
    public void hacer(Imagen imagen, ParametrosOperacion parametros) throws ExepcionImagen {

    }
   public void dibujarLinea(Imagen imagen, int x0, int y0, int x1, int y1, int color) throws SQLException {
       int dx = Math.abs(x1 - x0);//Variables de diferencia entre los puntos
       int dy = Math.abs(y1 - y0);
       int sx = x0 < x1 ? 1 : -1;
       int sy = y0 < y1 ? 1 : -1;//Direccion del eje
       int err = dx - dy;//Variable de error del algoritmo
       int radio = 3;

       while (true) {
           for (int i = -radio; i <= radio; i++) {
               for (int j = -radio; j <= radio; j++) {
                   if (i * i + j * j <= radio * radio) {
                       int nuevoX = x0 + i;
                       int nuevoY = y0 + j;
                       if (nuevoX >= 0 && nuevoX < imagen.getAncho() && nuevoY >= 0 && nuevoY < imagen.getAlto()) {
                           if (imagen.getImagenPixeles()[nuevoX][nuevoY] != color) {
                               imagen.getImagenPixeles()[nuevoX][nuevoY] = color;
                           }
                       }
                   }
               }
           }

           if (x0 == x1 && y0 == y1) {
               break;//Llega al punto final y se rompe el bucle
           }

           // Actualiza la posición
           int e2 = 2 * err;
           if (e2 > -dy) {
               err -= dy;
               x0 += sx;
           }
           if (e2 < dx) {
               err += dx;
               y0 += sy;
           }
       }

       imagenPintada = imagen.getImagenPixeles();
       logger.info("Línea dibujada desde el punto inicial x: " + x0 + ", y: " + y0 + " hasta el punto final x: " + dx + ", y: " + dy);
       EventosDao eventosDao = new EventosDao();
       eventosDao.llamdaProcedimiento(new Evento("Dibujar linea", "Línea dibujada desde el punto inicial x: " + x0 + ", y: " + y0 + " hasta el punto final x: " + dx + ", y: " + dy));
   }

    public int[][] getImagenPintada() {
        return imagenPintada;
    }
}
