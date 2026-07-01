package org.example;


import boundary.BAutenticazione;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Avvia l'interfaccia grafica in modo thread-safe
        SwingUtilities.invokeLater(() -> {
            BAutenticazione form = new BAutenticazione();
            form.setVisible(true);
        });
    }
}

//progetto IDS 2026 di Porcelli Nicola (Leader), Ambrosanio Lidia