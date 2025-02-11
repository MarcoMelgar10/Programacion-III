package recursividad;

import org.junit.Test;
import recursividad.hanoi.exceptions.HanoiException;
import recursividad.hanoi.modelo.HanoiModel;

import static org.junit.Assert.assertEquals;

public class HanoiTest {
    @Test
    public void testFuncionamientoNormal3Anillos() {
        // Arrange
        HanoiModel hanoi = null;
        try {
            hanoi = new HanoiModel(3, 0, 2, 1, 0);
        } catch (HanoiException e) {
            throw new RuntimeException(e);
        }

        // Act
        hanoi.resolver();

        // Assert
        // Verificar que la torre 3 tenga los 3 anillos
        assertEquals(3, hanoi.getTorres()[2].getAnillos().size());
    }

    @Test
    public void testParametrosNegativos() {
        // Arrange
        HanoiModel hanoi;
        String result;

        // Act
        try {
            hanoi = new HanoiModel(0, 0, 2, 1, 0);
            result = "No error";
        } catch(HanoiException e) {
            result = e.getMessage();
        }

        // Assert
        assertEquals("El número de anillos debe ser mayor a 0", result);
    }

    @Test
    public void testParametrosTorresNegativos() {
        // Arrange
        HanoiModel hanoi;
        String result;

        // Act
        try {
            hanoi = new HanoiModel(2, -1, 2, 1, 0);
            result = "No error";
        } catch(HanoiException e) {
            result = e.getMessage();
        }

        // Assert
        assertEquals("El número de torre debe ser positivo", result);
    }

    @Test
    public void testParametrosTorresCorrecto() {
        // Arrange
        HanoiModel hanoi;
        String result;

        // Act
        try {
            hanoi = new HanoiModel(2, 0, 2, 2, 0);
            result = "No error";
        } catch(HanoiException e) {
            result = e.getMessage();
        }

        // Assert
        assertEquals("Los parametros de torre deben ser diferentes y entre 0 y 2", result);
    }
}
