package entity;


public abstract class Utente {
    protected String nome;
    protected String cognome;
    protected String email;
    protected String ruolo;

    public Utente(String nome, String cognome, String email, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.ruolo = ruolo;
    }

    public String getEmail() { return email; }
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getRuolo() { return ruolo; }

    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}