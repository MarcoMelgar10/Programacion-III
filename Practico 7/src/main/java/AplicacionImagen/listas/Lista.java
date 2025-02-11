package AplicacionImagen.listas;

import java.beans.PropertyChangeSupport;

public class Lista<E>  {
    protected Nodo<E> raiz;
    protected int tam = 0;


    public Lista() {

    }

    public void adicionar(E valor) {
        if (raiz == null) {
            raiz = new Nodo<>(valor);
            tam++;
        }
        Nodo<E> actual = raiz;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        Nodo<E> nuevo = new Nodo<>(valor);
        actual.setSiguiente(nuevo);
        tam++;
    }

    public Nodo<E> getNodo(int posicion) {
        if (posicion >= tam) {
            throw new IndexOutOfBoundsException(
                    "La posición " + posicion + " está fuera del tamaño de la lista (" + tam + ")"
            );
        }
        int count = 0;
        Nodo<E> actual = raiz;
        while (actual.getSiguiente() != null && count != posicion) {
            count++;
            actual = actual.getSiguiente();
        }
        return actual;
    }


   @Override
    public String toString() {
        if (raiz == null) {
            return "Lista vacía";
        }

        StringBuilder builder = new StringBuilder();

        Nodo<E> actual = raiz;
        int[] maxColumnWidths = null;

        // Calcular los anchos máximos de cada columna
        while (actual != null) {
            String[] fila = (String[]) actual.getDato();
            if (maxColumnWidths == null) {
                maxColumnWidths = new int[fila.length];
            }
            for (int i = 0; i < fila.length; i++) {
                if (fila[i] != null) {
                    maxColumnWidths[i] = Math.max(maxColumnWidths[i], fila[i].length());
                }
            }
            actual = actual.getSiguiente();
        }

        // Reiniciar nodo para recorrer los datos
        actual = raiz;

        // Crear línea separadora
        StringBuilder separador = new StringBuilder("+");
        for (int width : maxColumnWidths) {
            separador.append("-".repeat(width + 2)).append("+");
        }
        separador.append("\n");

        // Agregar filas formateadas
        builder.append(separador);
        while (actual != null) {
            String[] fila = (String[]) actual.getDato();
            builder.append("|");
            for (int i = 0; i < fila.length; i++) {
                builder.append(String.format(" %-"+ maxColumnWidths[i] +"s ", fila[i])).append("|");
            }
            builder.append("\n");
            builder.append(separador);
            actual = actual.getSiguiente();
        }

        return builder.toString();
    }


    }