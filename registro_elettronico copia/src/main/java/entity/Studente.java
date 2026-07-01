package entity;

import jakarta.persistence.Entity;

@Entity
public class Studente extends Utente {
    private String matricola;

    public Studente() {}

    public Studente(String nome, String cognome, String email, String password, String matricola) {
        super(nome, cognome, email, password, "studente");
        this.matricola = matricola;
    }
    public String getMatricola() { return matricola; }
}