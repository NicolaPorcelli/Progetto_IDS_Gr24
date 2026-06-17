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