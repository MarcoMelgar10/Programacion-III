package arbolBinario;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class binarySearchTree {
    private Nodo raiz;
    private static final Logger logger = LogManager.getRootLogger();

    public binarySearchTree() {
        this.raiz = null;
    }

    public void insertar(String palabra) {
        raiz = insertarRecursivo(raiz, palabra.toLowerCase());

    }

    private Nodo insertarRecursivo(Nodo actual, String palabra) {
        if (actual == null) {
            return new Nodo(palabra);
        }
        if (palabra.compareTo(actual.palabra) < 0) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, palabra);
        } else if (palabra.compareTo(actual.palabra) > 0) {
            actual.derecho = insertarRecursivo(actual.derecho, palabra);
        }
        return actual;
    }


    public ResultadoBusqueda buscar(String palabra) {
        return buscarRecursivo(raiz, palabra.toLowerCase(), 0);
    }

    private ResultadoBusqueda buscarRecursivo(Nodo actual, String palabra, int saltos) {
        if (actual == null) {
            return new ResultadoBusqueda(false, saltos, null);
        }
        if (palabra.equals(actual.palabra)) {
            return new ResultadoBusqueda(true, saltos, actual.palabra);
        }
        if (palabra.compareTo(actual.palabra) < 0) {
            ResultadoBusqueda resultado = buscarRecursivo(actual.izquierdo, palabra, saltos + 1);

            if (!resultado.isEncontrada() && resultado.getPalabra() == null) {
                resultado.setPalabra(actual.palabra);
            }
            return resultado;
        } else {
            ResultadoBusqueda resultado = buscarRecursivo(actual.derecho, palabra, saltos + 1);
            if (!resultado.isEncontrada() && resultado.getPalabra() == null) {
                resultado.setPalabra(actual.palabra); // La palabra mas cercana
            }
            return resultado;
        }
    }




}




