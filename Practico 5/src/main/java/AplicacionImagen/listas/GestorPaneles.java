package AplicacionImagen.listas;

import javax.swing.*;

public class GestorPaneles<A> {
    private ListaDoble<A> imagenListaDoble;
    private A valor;
    public GestorPaneles(JTabbedPane tabbedPane){
        imagenListaDoble = new ListaDoble<>(tabbedPane);
    }
    public void agregarPanel(A panel){
        valor = panel;
        imagenListaDoble.adicionar(valor);
    }
    public void eliminarPanel(int index){//Avisar que se elimino la imagen para que el TabbedPane se elimine
        imagenListaDoble.eliminar(index);
    }

}
