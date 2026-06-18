package entity;


public class Docente extends Utente {

    public Docente(String nome, String cognome, String email) {
        //forza il ruolo docente
        super(nome, cognome, email, "Docente");
    }
}