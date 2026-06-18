package boundary;

import controller.Utenze;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BAutenticazione extends JFrame {


    // Variabili create dal form

    private JPanel mainPanel;

    // Componenti Accesso
    private JTextField txtEmailLogin;
    private JPasswordField txtPassLogin;
    private JButton btnAccedi;

    // Componenti Registrazione
    private JTextField txtNomeReg;
    private JTextField txtCognomeReg;
    private JTextField txtEmailReg;
    private JPasswordField txtPassReg;
    private JComboBox<String> comboRuoloReg;
    private JButton btnRegistrati;

    // controller
    private Utenze controllerUtenze;

    public BAutenticazione() {
        controllerUtenze = new Utenze();

        // Carica l'interfaccia
        setContentPane(mainPanel);
        setTitle("Sistema Scolastico - Autenticazione");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Esecuzione bottone Accesso
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
                if(controllerUtenze.accessoUtente(email, pass)) {
                    JOptionPane.showMessageDialog(mainPanel, "Accesso effettuato con successo!");

                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Credenziali errate!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Esecuzione bottone registrazione
        btnRegistrati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNomeReg.getText();
                String cognome = txtCognomeReg.getText();
                String email = txtEmailReg.getText();
                String pass = new String(txtPassReg.getPassword());
                String ruolo = comboRuoloReg.getSelectedItem().toString();

                // Controlli di validazione

                // Controllo campi vuoti
                // .trim() taglia gli spazi bianchi
                if (nome.trim().isEmpty() || cognome.trim().isEmpty() || email.trim().isEmpty() || pass.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Attenzione: Tutti i campi sono obbligatori! Compila tutto prima di procedere.",
                            "Campi mancanti",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                //Controllo lunghezza password
                if (pass.length() < 8 || pass.length() > 25) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Sicurezza: La password deve contenere almeno 8 caratteri e non più di 25 caratteri.",
                            "Password troppo corta o lunga",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }



                // Controlli superati
                boolean success = controllerUtenze.registrazioneUtente(nome, cognome, email, pass, ruolo);

                if (success) {
                    JOptionPane.showMessageDialog(mainPanel, "Registrazione completata con successo!");

                    // Svuota campi dopo registrazione
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