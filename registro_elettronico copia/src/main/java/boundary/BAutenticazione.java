package boundary;

import controller.ControllerUtenze;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BAutenticazione extends JFrame {
    // ... Variabili del form (txtEmailLogin, btnAccedi, ecc.) identiche a prima ...
    private JPanel mainPanel;
    private JTextField txtEmailLogin;
    private JPasswordField txtPassLogin;
    private JButton btnAccedi;
    private JTextField txtNomeReg;
    private JTextField txtCognomeReg;
    private JTextField txtEmailReg;
    private JPasswordField txtPassReg;
    private JComboBox<String> comboRuoloReg;
    private JButton btnRegistrati;

    public BAutenticazione() {
        setContentPane(mainPanel);
        setTitle("Sistema Scolastico - Autenticazione");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btnAccedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmailLogin.getText();
                String pass = new String(txtPassLogin.getPassword());

                if (email.trim().isEmpty() || pass.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Attenzione: Tutti i campi sono obbligatori! Compila tutto prima di procedere.",
                            "Campi mancanti",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Chiama il metodo statico del controller (come fa ControllerRimessaggio.salvaGommone)
                String ruolo = ControllerUtenze.accessoUtente(email, pass);

                if (ruolo != null) {
                    JOptionPane.showMessageDialog(mainPanel, "Accesso effettuato!");
                    dispose();
                    if (ruolo.equalsIgnoreCase("studente")) {
                        new ProfiloStudente().setVisible(true);
                    } else if (ruolo.equalsIgnoreCase("docente")) {
                        new ProfiloDocente().setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Credenziali errate!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnRegistrati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Recupero campi...
                String nome = txtNomeReg.getText();
                String cognome = txtCognomeReg.getText();
                String email = txtEmailReg.getText();
                String pass = new String(txtPassReg.getPassword());
                String ruolo = comboRuoloReg.getSelectedItem().toString();

                // 1. Controllo se i campi sono vuoti
                if (nome.trim().isEmpty() || cognome.trim().isEmpty() || email.trim().isEmpty() || pass.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Attenzione: tutti i campi sono obbligatori e non possono essere vuoti!",
                            "Campi Mancanti",
                            JOptionPane.WARNING_MESSAGE);
                    return; // Blocca la funzione qui
                }

                // Rimuoviamo gli spazi vuoti iniziali e finali per sicurezza
                nome = nome.trim();
                cognome = cognome.trim();
                email = email.trim();
                pass = pass.trim();

                // 2. Controllo lunghezza Nome e Cognome (tra 2 e 20 caratteri)
                if (nome.length() < 2 || nome.length() > 20) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Il nome deve contenere tra i 2 e i 20 caratteri.",
                            "Errore Nome",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (cognome.length() < 2 || cognome.length() > 20) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Il cognome deve contenere tra i 2 e i 20 caratteri.",
                            "Errore Cognome",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // 3. Controllo caratteri speciali in Nome e Cognome (solo lettere e spazi consentiti)
                String regexLettere = "^[a-zA-AàèìòùÀÈÌÒÙáéíóúÁÉÍÓÚ' ]+$";
                if (!nome.matches(regexLettere)) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Il nome può contenere solo lettere e spazi.",
                            "Caratteri Non Validi",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!cognome.matches(regexLettere)) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Il cognome può contenere solo lettere e spazi.",
                            "Caratteri Non Validi",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // 4. Controllo dominio dell'email (@istituto.it)
                if (!email.endsWith("@istituto.it")) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "L'indirizzo email inserito non è valido. Deve terminare con @istituto.it",
                            "Email Errata",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // 5. Controllo lunghezza della Password (tra 8 e 25 caratteri)
                if (pass.length() < 8 || pass.length() > 25) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "La password deve avere una lunghezza compresa tra 8 e 25 caratteri.",
                            "Password Non Sicura",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Delega al Controller
                boolean success = ControllerUtenze.registrazioneUtente(nome, cognome, email, pass, ruolo);

                if (success) {
                    JOptionPane.showMessageDialog(mainPanel, "Registrazione completata con successo!");
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Errore nella registrazione. Mail già usata?", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}