package entity;

public class Studente extends Utente {
    private String matricola;

    public Studente(String nome, String cognome, String email, String matricola) {
        super(nome, cognome, email, "Studente");
        this.matricola = matricola;
    }
    public String getMatricola() { return matricola; }
    // Setter specifico per Studente

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }
}