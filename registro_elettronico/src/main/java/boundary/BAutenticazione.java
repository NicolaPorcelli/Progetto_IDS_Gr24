package boundary;

import controller.Utenze;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BAutenticazione extends JFrame {

    // =========================================================
    // VARIABILI CREATE DAL FORM (Devono chiamarsi esattamente
    // come i "field name" che hai messo nel designer grafico)
    // =========================================================
    private JPanel mainPanel;

    // Componenti Login
    private JTextField txtEmailLogin;
    private JPasswordField txtPassLogin;
    private JButton btnAccedi;

    // Componenti Registrazione
    private JTextField txtNomeReg;
    private JTextField txtCognomeReg;
    private JTextField txtEmailReg;
    private JPasswordField txtPassReg;
    private JComboBox<String> comboRuoloReg; // Nel form, vai su 'model' nelle proprietà e scrivi: Studente, Docente
    private JButton btnRegistrati;

    // Il nostro controller
    private Utenze controllerUtenze;

    public BAutenticazione() {
        controllerUtenze = new Utenze();

        // 1. CARICA L'INTERFACCIA GRAFICA DISEGNATA
        setContentPane(mainPanel); // mainPanel è il JPanel radice del tuo .form
        setTitle("Sistema Scolastico - Autenticazione");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la finestra nello schermo

        // 2. AZIONE BOTTONE LOGIN
        btnAccedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmailLogin.getText();
                String pass = new String(txtPassLogin.getPassword());

                if(controllerUtenze.accessoUtente(email, pass)) {
                    JOptionPane.showMessageDialog(mainPanel, "Login effettuato con successo!");
                    // Qui aprirai la finestra del profilo (es: nuova BProfiloStudente().setVisible(true); )
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Credenziali errate!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

// 3. AZIONE BOTTONE REGISTRAZIONE
        btnRegistrati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNomeReg.getText();
                String cognome = txtCognomeReg.getText();
                String email = txtEmailReg.getText();
                String pass = new String(txtPassReg.getPassword());
                String ruolo = comboRuoloReg.getSelectedItem().toString();

                // --- INIZIO CONTROLLI DI VALIDAZIONE (User Error Protection) ---

                // 1. Controllo campi vuoti
                // Usiamo il metodo .trim() che taglia via gli spazi bianchi (così un utente non può imbrogliare inserendo solo "spazi")
                if (nome.trim().isEmpty() || cognome.trim().isEmpty() || email.trim().isEmpty() || pass.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Attenzione: Tutti i campi sono obbligatori! Compila tutto prima di procedere.",
                            "Campi mancanti",
                            JOptionPane.WARNING_MESSAGE);
                    return; // Il "return" è fondamentale: blocca l'esecuzione del codice qui. Non andrà al database.
                }

                // 2. Controllo lunghezza password
                if (pass.length() < 8) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Sicurezza: La password deve contenere almeno 8 caratteri.",
                            "Password troppo corta",
                            JOptionPane.WARNING_MESSAGE);
                    return; // Anche qui, blocchiamo l'esecuzione.
                }

                // --- FINE CONTROLLI ---

                // Se il codice arriva a questa riga, significa che tutti i controlli sono stati superati!
                boolean success = controllerUtenze.registrazioneUtente(nome, cognome, email, pass, ruolo);

                if (success) {
                    JOptionPane.showMessageDialog(mainPanel, "Registrazione completata con successo!");

                    // Opzionale ma consigliato: svuotiamo i campi dopo una registrazione riuscita
                    txtNomeReg.setText("");
                    txtCognomeReg.setText("");
                    txtEmailReg.setText("");
                    txtPassReg.setText("");
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Errore nella registrazione. Mail già usata?", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}