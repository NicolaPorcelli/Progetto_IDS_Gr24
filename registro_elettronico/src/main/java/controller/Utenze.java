package controller;

import database.UtenteDAO;

public class Utenze {
    private UtenteDAO utenteDAO;

    public Utenze() {
        this.utenteDAO = new UtenteDAO();
    }

    // Rispetta la firma del tuo diagramma UML
    public boolean registrazioneUtente(String nome, String cognome, String email, String password, String ruolo) {
        // Qui potresti aggiungere validazioni (es. controlli su email vuote) per garantire la "User error protection" [cite: 346]
        return utenteDAO.insertUtente(nome, cognome, email, password, ruolo);
    }

    // Rispetta la firma del tuo diagramma UML
    public boolean accessoUtente(String email, String password) {
        return utenteDAO.verificaCredenziali(email, password);
    }
}