package AplicacionImagen.command;

import AplicacionImagen.Imagen;
import AplicacionImagen.excepcion.ExepcionImagen;
import AplicacionImagen.operaciones.DibujarPunto;
import AplicacionImagen.operaciones.ParametrosOperacion;

    public class DibujarPuntoCommand  implements CommandExcecute {
    private DibujarPunto dibujarPunto;
    private Imagen imagen;
    private ParametrosOperacion parametros;
    private int[][] imagenAnterior;

    public DibujarPuntoCommand(DibujarPunto dibujarPunto, Imagen imagen, ParametrosOperacion parametros) {
        this.dibujarPunto = dibujarPunto;
        this.imagen = imagen;
        this.parametros = parametros;
        this.imagenAnterior = copiarImagen(imagen.getImagenPixeles());
    }

    @Override
    public void execute() {
        try {
            dibujarPunto.hacer(imagen, parametros);
        } catch (ExepcionImagen e) {
            System.out.println(e.getMessage());
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
