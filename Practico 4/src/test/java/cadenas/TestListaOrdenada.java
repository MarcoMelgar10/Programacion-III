package cadenas;

import cadena.models.ListaDoble;
import cadena.models.ListaOrdenada;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestListaOrdenada {

    @Test
    public void testInsertar7Letras() {
        String[] letras = { "B", "N" , "E", "S", "C", "A", "P"};

        ListaOrdenada<String> lista = new ListaOrdenada<>();
        for (int i = 0; i < letras.length; i++) {
            lista.insertar(letras[i]);
        }

        String result = lista.toString();
        assertEquals("A <-> B <-> C <-> E <-> N <-> P <-> S <-> ",result);
    }
}
