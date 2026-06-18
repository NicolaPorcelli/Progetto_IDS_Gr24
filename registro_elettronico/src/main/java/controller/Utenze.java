package controller;

import database.UtenteDAO;

public class Utenze {
    private UtenteDAO utenteDAO;

    public Utenze() {
        this.utenteDAO = new UtenteDAO();
    }

    //Registra Utente
    public boolean registrazioneUtente(String nome, String cognome, String email, String password, String ruolo) {
        return utenteDAO.insertUtente(nome, cognome, email, password, ruolo);
    }

    //Accesso Utente
    public boolean accessoUtente(String email, String password) {
        return utenteDAO.verificaCredenziali(email, password);
    }
}