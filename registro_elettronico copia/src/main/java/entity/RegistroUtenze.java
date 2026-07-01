package entity;

import database.GestorePersistenza;
import java.util.Map;

public class RegistroUtenze {
    private GestorePersistenza gestorePersistenza;

    public RegistroUtenze() {
        // Il Registro istanzia il gestore della persistenza, come nell'esempio del docente.
        this.gestorePersistenza = new GestorePersistenza();
    }

    public boolean registraUtente(String nome, String cognome, String email, String passwordInChiaro, String ruolo) {
        // 1. Controlla se la mail esiste già
        Utente esistente = gestorePersistenza.cercaPrimoPerCampi(Utente.class, Map.of("email", email));
        if (esistente != null) {
            return false; // Email già in uso
        }

        // 2. Crea l'oggetto specifico
        Utente nuovoUtente;
        if (ruolo.equalsIgnoreCase("studente")) {
            nuovoUtente = new Studente(nome, cognome, email, passwordInChiaro, "Matricola-Gen");
        } else if (ruolo.equalsIgnoreCase("docente")) {
            nuovoUtente = new Docente(nome, cognome, email, passwordInChiaro);
        } else {
            return false;
        }

        // 3. Salva l'oggetto
        return gestorePersistenza.salva(nuovoUtente);
    }

    public String autentica(String email, String passwordInChiaro) {
        String hash = PasswordHasher.hashPassword(passwordInChiaro);

        Utente utenteTrovato = gestorePersistenza.cercaPrimoPerCampi(
                Utente.class,
                Map.of("email", email, "password", hash)
        );

        if (utenteTrovato != null) {
            return utenteTrovato.getRuolo();
        }
        return null;
    }
}