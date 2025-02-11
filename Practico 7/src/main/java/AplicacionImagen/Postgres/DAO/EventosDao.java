package AplicacionImagen.Postgres.DAO;

import AplicacionImagen.listas.Lista;
import AplicacionImagen.listas.ListaDoble;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;


public class EventosDao {
private String sql;
private ConexionDB conexionDB;
private static final Logger logger = LogManager.getRootLogger();
public EventosDao(){
    try {
         conexionDB = new ConexionDB();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

    public void llamdaProcedimiento(Evento evento) throws SQLException {
        sql = "CALL add_event(?, ?)";

        try (PreparedStatement stmt =conexionDB.getConexion().prepareStatement(sql)) {
            stmt.setString(1, evento.getNombre());
            stmt.setString(2, evento.getInfo());
            stmt.execute();
            logger.info("Informacion cargada a la base de datos",evento.getNombre());
        } catch (SQLException e) {
            // Manejo de error cuando el correo ya existe o cualquier otra excepción
            if (e.getSQLState().equals("P0001")) { // Código SQL para una excepción RAISE EXCEPTION
                logger.warn("Error: {}", e.getMessage());
            } else {
                logger.error("Error mientras se ejecutaba el procedimiento: {}", e.getMessage());
            }
        }

    }

    public Lista<String[]> getDatos() {
        String sql = "SELECT * FROM Eventos"; // Corrige la consulta, faltaba seleccionar columnas
        Lista<String[]> lista = new Lista<>(); // Asegúrate de que tu clase Lista esté correctamente implementada

        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            int columnCount = rs.getMetaData().getColumnCount(); // Número de columnas en la tabla

            while (rs.next()) {
                String[] fila = new String[columnCount]; // Arreglo para almacenar una fila
                for (int i = 1; i <= columnCount; i++) { // Recorre las columnas (base 1)
                    fila[i - 1] = rs.getString(i); // Obtiene el valor de cada columna
                }
                lista.adicionar(fila); // Agrega la fila a la lista
            }
            return lista; // Devuelve la lista llena
        } catch (SQLException e) {
            if ("P0001".equals(e.getSQLState())) { // Código SQL específico para excepciones personalizadas
                logger.warn("Error: {}", e.getMessage());
            } else {
                logger.error("Error mientras se ejecutaba el procedimiento: {}", e.getMessage());
            }
        }
        return null;
    }
    public Lista<String[]> geEventoPorNombre(String nombre) {
        String sql = "SELECT * FROM Eventos WHERE snombre = ?"; // Consulta con parámetro
        Lista<String[]> lista = new Lista<>();

        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(sql)) {
            stmt.setString(1, nombre); // Asigna el valor al parámetro `?`

            try (ResultSet rs = stmt.executeQuery()) {
                int columnCount = rs.getMetaData().getColumnCount();

                while (rs.next()) {
                    String[] fila = new String[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        fila[i - 1] = rs.getString(i);
                    }
                    lista.adicionar(fila);
                }
            }
            return lista;
        } catch (SQLException e) {
            if ("P0001".equals(e.getSQLState())) {
                logger.warn("Error: {}", e.getMessage());
            } else {
                logger.error("Error mientras se ejecutaba el procedimiento: {}", e.getMessage());
            }
        }
        return null;
    }
}
