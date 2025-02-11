package tablasDeHash;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestTablaHash {
    @Test
    public void testPalabraMesas() {
        TablaHashString tabla = new TablaHashString();
        int hash = tabla.miFuncionHash("Mesas");

        assertEquals(180, hash);
    }
}
