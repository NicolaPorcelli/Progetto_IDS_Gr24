package boundary;

import controller.GestioneClasse;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormCreazioneClasse extends JFrame {

    private JPanel panelContenitore;
    private JTextField txtNomeClasse;
    private JButton btnConferma;
    private ProfiloDocente finestraPadre;

    public FormCreazioneClasse(ProfiloDocente padre) {
        this.finestraPadre = padre; // Riferimento memorizzato per aggiornare la tabella

        setTitle("Creazione Classe");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Chiude solo questa finestra
        setLocationRelativeTo(null);
        setResizable(false);

        panelContenitore = new JPanel();
        panelContenitore.setLayout(new BoxLayout(panelContenitore, BoxLayout.Y_AXIS));
        panelContenitore.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelContenitore.add(new JLabel("Inserisci il nome della classe virtuale:"));
        panelContenitore.add(Box.createVerticalStrut(5));

        txtNomeClasse = new JTextField(20);
        panelContenitore.add(txtNomeClasse);
        panelContenitore.add(Box.createVerticalStrut(15));

        btnConferma = new JButton("Conferma");
        panelContenitore.add(btnConferma);

        setContentPane(panelContenitore);

        btnConferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNomeClasse.getText();

                if (nome.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(panelContenitore, "Il nome non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Invocazione del Controller
                String codice = GestioneClasse.creaClasseVirtuale(nome);

                if (codice != null) {
                    JOptionPane.showMessageDialog(panelContenitore, "Classe creata con successo!\nCodice generato: " + codice, "Successo", JOptionPane.INFORMATION_MESSAGE);

                    // Forza l'aggiornamento istantaneo dei dati sulla finestra padre prima di chiudersi
                    finestraPadre.aggiornaElencoClassi();

                    dispose(); // Chiude il form corrente
                } else {
                    JOptionPane.showMessageDialog(panelContenitore, "Impossibile salvare la classe.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
