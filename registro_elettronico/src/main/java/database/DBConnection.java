package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;

    // connessione
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/gestione_scuola?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Nicola29!";

    // Nessun altro può fare "new DBConnection()"
    private DBConnection() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connessione al database fallita.");
            throw e;
        }
    }

    // ottenere l'istanza
    public static DBConnection getInstance() throws SQLException {
        // Crea l'istanza solo se non esiste ancora o se la connessione è chiusa
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }
        return instance;
    }

    // Getter per usare la connessione
    public Connection getConnection() {
        return connection;
    }
}