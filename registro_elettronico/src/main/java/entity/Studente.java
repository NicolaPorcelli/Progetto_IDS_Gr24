package entity;

public class Studente extends Utente {
    private String matricola;

    public Studente(String nome, String cognome, String email, String matricola) {
        //Forza il ruolo studente
        super(nome, cognome, email, "Studente");
        this.matricola = matricola;
    }
    public String getMatricola() { return matricola; }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }
}