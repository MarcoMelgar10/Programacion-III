package AplicacionImagen.command;

import AplicacionImagen.Imagen;
import AplicacionImagen.Postgres.DAO.Evento;
import AplicacionImagen.Postgres.DAO.EventosDao;
import AplicacionImagen.operaciones.DibujarLinea;

import java.sql.SQLException;

public class DibujarLineaCommand implements CommandExcecute {
    private DibujarLinea dibujarLinea;
    private Imagen imagen;
    private int x0, y0, x1, y1, color;
    private int[][] imagenAnterior;

    public DibujarLineaCommand(DibujarLinea dibujarLinea, Imagen imagen, int x0, int y0, int x1, int y1, int color) {
        this.dibujarLinea = dibujarLinea;
        this.imagen = imagen;
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.color = color;
        this.imagenAnterior = copiarImagen(imagen.getImagenPixeles()); // Guardar la imagen original
    }

    @Override
    public void execute() {
        try {
            dibujarLinea.dibujarLinea(imagen, x0, y0, x1, y1, color);  // Ejecutar la acción de pintar línea
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private int[][] copiarImagen(int[][] imagen) {
        int[][] copia = new int[imagen.length][];
        for (int i = 0; i < imagen.length; i++) {
            copia[i] = imagen[i].clone();
        }
        return copia;
    } public int [][] getImagenAnterior(){
            return imagenAnterior;
        }
}
