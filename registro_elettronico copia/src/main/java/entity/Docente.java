package entity;

import jakarta.persistence.Entity;

@Entity
public class Docente extends Utente {
    public Docente() {}

    public Docente(String nome, String cognome, String email, String password) {
        super(nome, cognome, email, password, "docente");
    }
}