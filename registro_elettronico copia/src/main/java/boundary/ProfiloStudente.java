package boundary;

import javax.swing.*;

public class ProfiloStudente extends JFrame {
    public ProfiloStudente() {
        setTitle("Registro Elettronico - Profilo Studente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Benvenuto nella Schermata Studente"));
        setContentPane(panel);
    }
}