package entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Genera tabelle separate ma collegate
public abstract class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String nome;
    protected String cognome;

    @Column(unique = true)
    protected String email;
    protected String password;
    protected String ruolo;

    public Utente() {}

    public Utente(String nome, String cognome, String email, String passwordInChiaro, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = PasswordHasher.hashPassword(passwordInChiaro);
        this.ruolo = ruolo;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getPassword() { return password; }
    public String getRuolo() { return ruolo; }
}