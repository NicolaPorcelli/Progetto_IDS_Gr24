package boundary;

import controller.GestioneClasse;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProfiloDocente extends JFrame {

    private JPanel mainPanel;
    private JButton btnApriCreaClasse;
    private JTable tabellaClassi;
    private JScrollPane scrollPane;

    public ProfiloDocente() {
        setTitle("Registro Elettronico - Profilo Docente");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurazione Layout Grafico
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel.add(new JLabel("<html><h2>Pannello Docente - Le tue Classi Virtuali</h2></html>"));
        mainPanel.add(Box.createVerticalStrut(15));

        btnApriCreaClasse = new JButton("Crea classe");
        mainPanel.add(btnApriCreaClasse);
        mainPanel.add(Box.createVerticalStrut(15));

        // Tabella per mostrare le classi (Modellata sulla JTable del docente)
        tabellaClassi = new JTable();
        scrollPane = new JScrollPane(tabellaClassi);
        mainPanel.add(scrollPane);

        setContentPane(mainPanel);

        // Caricamento iniziale dei dati
        aggiornaElencoClassi();

        // Evento del bottone "Crea classe" che apre la seconda schermata
        btnApriCreaClasse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Passiamo 'ProfiloDocente.this' come parametro per poter fare il refresh successivo
                SwingUtilities.invokeLater(() -> new FormCreazioneClasse(ProfiloDocente.this).setVisible(true));
            }
        });
    }

    /**
     * Metodo pubblico invocato per aggiornare la JTable in modo dinamico
     */
    public void aggiornaElencoClassi() {
        // Chiediamo al controller i dati convertiti in String[]
        List<String[]> righe = GestioneClasse.visualizzaClassi();

        String[] colonne = {"Nome Classe", "Codice Univoco"};
        DefaultTableModel model = new DefaultTableModel(colonne, 0);

        for (String[] riga : righe) {
            model.addRow(riga);
        }

        tabellaClassi.setModel(model);
    }
}