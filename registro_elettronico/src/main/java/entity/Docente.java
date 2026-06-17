package entity;


public class Docente extends Utente {

    public Docente(String nome, String cognome, String email) {
        // Richiama il costruttore della classe padre (Utente) forzando il ruolo a "Docente"
        super(nome, cognome, email, "Docente");
    }
}