package view;

import controller.ButtonsController;
import org.example.Settings;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel
{
    private static ButtonsController bc;
    private static final JLabel gameText = new JLabel("Briscoliamo");
    private static final JPanel buttonPanel = new JPanel(new GridBagLayout()); // Pannello per i bottoni
    private static final JButton play = new JButton("Play Now!");
    private static final JButton stats = new JButton("Statistics");
    private static final JButton rules = new JButton("Rules");

    public MainPanel()
    {
        setLayout(new BorderLayout());

        bc = new ButtonsController(this);

        gameText.setHorizontalAlignment(SwingConstants.CENTER);
        gameText.setVerticalAlignment(SwingConstants.CENTER);
        gameText.setFont(new Font("Arial", Font.BOLD, 24)); // Imposta il font e le dimensioni
        add(gameText, BorderLayout.NORTH);

        // Aggiunge spazio tra il testo e gli altri componenti
        setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Aggiungi spazio tra i bottoni
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridy = 0; // Imposta la riga iniziale per il primo bottone

        // Crea i bottoni, li aggiunge al pannello e implementa l'ActionListener
        add(play);  play.addActionListener(bc);
        add(stats); stats.addActionListener(bc);
        add(rules); rules.addActionListener(bc);

        // Imposta le dimensioni dei bottoni
        Dimension buttonSize = new Dimension(200, 100);
        play.setPreferredSize(buttonSize);
        stats.setPreferredSize(buttonSize);
        rules.setPreferredSize(buttonSize);

        buttonPanel.add(play, gbc); gbc.gridy++; // Incrementa la riga per il prossimo bottone
        buttonPanel.add(stats, gbc); gbc.gridy++; // Incrementa la riga per il prossimo bottone
        buttonPanel.add(rules, gbc);

        // Aggiungi il pannello dei bottoni al pannello principale
        add(buttonPanel, BorderLayout.CENTER);
    }

    public JPanel getMainPanel()
    {
        return this;
    }

}
