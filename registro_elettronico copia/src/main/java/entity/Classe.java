package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "classi")
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true, length = 5, name = "codice_univoco")
    private String codiceUnivoco;

    // Costruttore richiesto da JPA
    public Classe() {
    }

    public Classe(String nome, String codiceUnivoco) {
        this.nome = nome;
        this.codiceUnivoco = codiceUnivoco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCodiceUnivoco() {
        return codiceUnivoco;
    }
}