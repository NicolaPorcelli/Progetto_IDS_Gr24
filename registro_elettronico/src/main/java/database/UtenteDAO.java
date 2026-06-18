package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO {

    public boolean insertUtente(String nome, String cognome, String email, String password, String ruolo) {
        String query = "INSERT INTO utenti (nome, cognome, email, password, ruolo) VALUES (?, ?, ?, ?, ?)";

        // Otteniamo la connessione unica dal Singleton.
        // Nel Try-with-resources mettiamo  il PreparedStatement, così Java chiude solo quello
        try (PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(query)) {

            stmt.setString(1, nome);
            stmt.setString(2, cognome);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.setString(5, ruolo);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verificaCredenziali(String email, String password) {
        String query = "SELECT id FROM utenti WHERE email = ? AND password = ?";

        try (PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}