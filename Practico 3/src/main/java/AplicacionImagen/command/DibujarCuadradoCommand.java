package AplicacionImagen.command;

import AplicacionImagen.Imagen;
import AplicacionImagen.operaciones.DibujarCuadrado;

public class DibujarCuadradoCommand implements CommandExcecute {
    private DibujarCuadrado dibujarCuadrado;
    private Imagen imagen;
    private int x0, y0, x1, y1, color;
    private int[][] imagenAnterior;

    public DibujarCuadradoCommand(DibujarCuadrado dibujarCuadrado, Imagen imagen, int x0, int y0, int x1, int y1, int color) {
        this.dibujarCuadrado = dibujarCuadrado;
        this.imagen = imagen;
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.color = color;
        this.imagenAnterior = copiarImagen(imagen.getImagenPixeles());
    }

    @Override
    public void execute() {
        dibujarCuadrado.dibujarCuadrado(imagen, x0, y0, x1, y1, color);
    }

    @Override
    public void deshacer() {
        imagen.cambioDePixles(imagenAnterior);
    }

    private int[][] copiarImagen(int[][] imagen) {
        int[][] copia = new int[imagen.length][];
        for (int i = 0; i < imagen.length; i++) {
            copia[i] = imagen[i].clone();
        }
        return copia;
    }
}

