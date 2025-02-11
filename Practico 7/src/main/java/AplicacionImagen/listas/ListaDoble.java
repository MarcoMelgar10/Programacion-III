package AplicacionImagen.listas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class ListaDoble<A> {
    private static final Logger logger = LogManager.getRootLogger();
    protected Nodo<A> raiz;
    protected Nodo<A> cola;
    protected int tam;
    private JTabbedPane tabbedPane;

    public ListaDoble(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
        raiz = null;
        cola = null;
        tam = 0;
    }

    public void eliminar(int posicion) {
        if (posicion < 0 || posicion >= tam) {
            logger.warn("Intento de eliminar en una posición fuera de rango: " + posicion);
            return;
        }
        Nodo<A> actual;
        if (posicion == 0) {
            raiz = raiz.getSiguiente();
            if (raiz != null) {
                raiz.setAnterior(null);
            } else {
                cola = null;
            }
        } else if (posicion+1 == tam - 1) {
            cola = cola.getAnterior();
            if (cola != null) {
                cola.setSiguiente(null);
            } else {
                raiz = null;
            }
        } else {
            if (posicion < tam / 2) {
                actual = raiz;
                for (int i = 0; i < (posicion+1); i++) {
                    actual = actual.getSiguiente();
                }
            } else {
                actual = cola;
                for (int i = tam - 1; i > (posicion+1); i--) {
                    actual = actual.getAnterior();
                }
            }
            Nodo<A> anterior = actual.getAnterior();
            Nodo<A> siguiente = actual.getSiguiente();
            anterior.setSiguiente(siguiente);
            if (siguiente != null) {
                siguiente.setAnterior(anterior);
            }
        }
        tam--;
        logger.info("Imagen eliminada de la lista en la posición " + posicion);
    }

    public void adicionar(A valor) {
        Nodo<A> nuevo = new Nodo<>(valor);
        nuevo.setAnterior(cola);
        if (cola != null) {
            cola.setSiguiente(nuevo);
        } else {
            raiz = nuevo;
        }
        cola = nuevo;
        tam++;
        tabbedPane.addTab("Imagen" + (tabbedPane.getTabCount() + 1), (Component) valor);
        logger.info("Imagen adicionada a la lista");
    }
}