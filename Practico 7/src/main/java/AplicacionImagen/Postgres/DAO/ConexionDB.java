package AplicacionImagen.Postgres.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static ConexionDB instancia;
    private final String url = "jdbc:postgresql://localhost:5432/registro_eventos";
    private static final Logger logger = LogManager.getRootLogger();
    private final String user = "user1";
    private final String password = "password1";
    private Connection conexion;
    public static ConexionDB getOrCreate() throws SQLException {
        if (instancia == null) {
            instancia = new ConexionDB();
        }
        return instancia;
    }

    public ConexionDB() throws SQLException {
        conexion = DriverManager.getConnection(url , user, password);
        logger.info("Database connected!");
    }

    public void desconectar() throws SQLException {
        conexion.close();
        logger.info("Database disconnected!");
    }

    public Connection getConexion() {
        return conexion;
    }
}
