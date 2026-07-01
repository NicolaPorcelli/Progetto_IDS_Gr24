package controller;

import entity.Classe;
import entity.RegistroClassi;
import java.util.ArrayList;
import java.util.List;

public class GestioneClasse {

    public static String creaClasseVirtuale(String nomeClasse) {
        if (nomeClasse == null || nomeClasse.trim().isEmpty()) {
            return null;
        }
        RegistroClassi registro = new RegistroClassi();
        return registro.creaERegistraClasse(nomeClasse.trim());
    }

    /**
     * Adapter che converte gli oggetti Entity "Classe" in array di String
     * per non violare il pattern ed evitare che la GUI importi il pacchetto Entity.
     */
    public static List<String[]> visualizzaClassi() {
        RegistroClassi registro = new RegistroClassi();
        List<Classe> elencoClassi = registro.ottieniTutteLeClassi();

        List<String[]> righeTabella = new ArrayList<>();

        for (Classe c : elencoClassi) {
            String[] riga = new String[] {
                    c.getNome(),
                    c.getCodiceUnivoco()
            };
            righeTabella.add(riga);
        }

        return righeTabella;
    }
}