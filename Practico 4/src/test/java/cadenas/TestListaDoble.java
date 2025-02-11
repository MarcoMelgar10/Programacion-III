package cadenas;

import cadena.models.ListaDoble;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TestListaDoble {
    @Test
    public void testInsertar2Elementos() {
        // Arrange
        ListaDoble<String> lista = new ListaDoble<>();

        // Act
        lista.insertar("M");
        lista.insertar("R");
        String result = lista.toString();

        // Assert
        String esperado = "R <-> M <-> ";
        assertEquals(esperado, result);
    }

    @Test
    public void testAdicionar2Elementos() {
        // Arrange
        ListaDoble<String> lista = new ListaDoble<>();

        // Act
        lista.adicionar("M");
        lista.adicionar("R");
        String result = lista.toString();

        // Assert
        String esperado = "M <-> R <-> ";
        assertEquals(esperado, result);
    }

    @Test
    public void testAdicionar2Insertar1Elementos() {
        // Arrange
        ListaDoble<String> lista = new ListaDoble<>();

        // Act
        lista.adicionar("M");
        lista.adicionar("R");
        lista.insertar("K");
        String result = lista.toString();

        // Assert
        String esperado = "K <-> M <-> R <-> ";
        assertEquals(esperado, result);
    }

    @Test
    public void testEliminarEnIZquierda() {
        // Arrange
        ListaDoble<String> lista = new ListaDoble<>();
        lista.adicionar("M");
        lista.adicionar("R");
        lista.adicionar("K");
        lista.adicionar("E");
        lista.adicionar("T");

        // Act
        lista.eliminar(1);
        String result = lista.toString();

        // Assert
        String esperado = "M <-> K <-> E <-> T <-> ";
        assertEquals(esperado, result);
    }

    @Test
    public void testEliminarEnIDerecha() {
        // Arrange
        ListaDoble<String> lista = new ListaDoble<>();
        lista.adicionar("M");
        lista.adicionar("R");
        lista.adicionar("K");
        lista.adicionar("E");
        lista.adicionar("T");

        // Act
        lista.eliminar(3);
        String result = lista.toString();

        // Assert
        String esperado = "M <-> R <-> K <-> T <-> ";
        assertEquals(esperado, result);
    }
}
