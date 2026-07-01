package entity;

import database.GestorePersistenza;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RegistroClassi {

    private GestorePersistenza gestorePersistenza;

    public RegistroClassi() {
        this.gestorePersistenza = new GestorePersistenza();
    }

    public String creaERegistraClasse(String nomeClasse) {
        String codiceGenerato = "";
        boolean isUnivoco = false;

        while (!isUnivoco) {
            codiceGenerato = generaCodiceAlfanumerico();
            Classe esistente = gestorePersistenza.cercaPrimoPerCampi(Classe.class, Map.of("codiceUnivoco", codiceGenerato));
            if (esistente == null) {
                isUnivoco = true;
            }
        }

        Classe nuovaClasse = new Classe(nomeClasse, codiceGenerato);
        boolean salvato = gestorePersistenza.salva(nuovaClasse);

        if (salvato) {
            return codiceGenerato;
        }
        return null;
    }

    /**
     * Recupera tutte le classi presenti nel database.
     * Passando una mappa vuota a cercaPerCampi, il GestorePersistenza esegue una SELECT totale.
     */
    public List<Classe> ottieniTutteLeClassi() {
        return gestorePersistenza.cercaPerCampi(Classe.class, Map.of());
    }

    private String generaCodiceAlfanumerico() {
        String lettere = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeri = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        sb.append(lettere.charAt(random.nextInt(lettere.length())));
        sb.append(lettere.charAt(random.nextInt(lettere.length())));
        sb.append(numeri.charAt(random.nextInt(numeri.length())));
        sb.append(numeri.charAt(random.nextInt(numeri.length())));
        sb.append(numeri.charAt(random.nextInt(numeri.length())));

        return sb.toString();
    }
}
