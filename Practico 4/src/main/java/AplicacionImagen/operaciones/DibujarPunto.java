package AplicacionImagen.operaciones;

import AplicacionImagen.Imagen;
import AplicacionImagen.excepcion.ExepcionImagen;

public class DibujarPunto implements OperacionImagen{
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getRootLogger();
    private int [][] imagenPintada;
    private Imagen imagen;

    @Override
          public void hacer(Imagen imagen, ParametrosOperacion parametros) throws ExepcionImagen {
              this.imagen = imagen;
              if(imagen.getImagenPixeles() == null){return;}
              int x = parametros.getX1();
              int y = parametros.getY1();
              int colorPincel = parametros.getColor();
              if(x<0 || y<0 || x>imagen.getAncho() || y > imagen.getAlto()){return;}
              this.imagenPintada = imagen.getImagenPixeles();
              if (imagenPintada[x][y] == colorPincel) {
                  return;
              }
              int radio = 3;
              for (int i = -radio; i <= radio; i++) {
                  for (int j = -radio; j <= radio; j++) {
                      if (i * i + j * j <= radio * radio) {
                          int nuevoX = x + i;
                          int nuevoY = y + j;
                          if (nuevoX >= 0 && nuevoX < imagen.getAncho() && nuevoY >= 0 && nuevoY < imagen.getAlto()) {
                              if (imagenPintada[nuevoX][nuevoY] != colorPincel) {
                                  imagenPintada[nuevoX][nuevoY] = colorPincel;
                                  String msg = String.format("Dibujado en x: %d, y: %d", nuevoX, nuevoY);
                                 logger.info(msg);
                              }
                          }
                      }
                  }
              }
          }
          public void dibujarLineaEntrePuntos(int x1, int y1, int x2, int y2, int color) throws ExepcionImagen {//Algoritmo Bresenham
              int dx = Math.abs(x2 - x1);
              int dy = Math.abs(y2 - y1);
              int sx = (x1 < x2) ? 1 : -1;
              int sy = (y1 < y2) ? 1 : -1;
              int err = dx - dy;
              ParametrosOperacion parametros = new ParametrosOperacion(x1, y1, color);
              while (true) {
                  parametros.setX1(x1);
                  parametros.setY1(y1);
                  hacer(imagen,parametros);
                  if (x1 == x2 && y1 == y2) {
                      break;
                  }
                  int e2 = err * 2;
                  if (e2 > -dy) {
                      err -= dy;
                      x1 += sx;
                 }
                  if (e2 < dx) {
                      err += dx;
                      y1 += sy;
                  }
              }
          }
          public int[][]getImagenPintada(){
              return imagenPintada;
          }
     }
