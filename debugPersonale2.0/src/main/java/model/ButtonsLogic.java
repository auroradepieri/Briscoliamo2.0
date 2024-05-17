package model;

import controller.ButtonsController;
import org.example.Main;
import view.GamePanel;
import view.MainFrame;
import view.MainPanel;

import javax.swing.*;
import java.awt.*;

public class ButtonsLogic {

    private static MainPanel mainPanel;
    private static JPanel playPanel;
    private static JPanel statsPanel;
    private static JPanel rulesPanel;
    private static final JButton back = new JButton("←");

    public ButtonsLogic(MainPanel _mainPanel) { mainPanel = _mainPanel; }

    public void play()
    {
        playPanel = createPlayPanel();

        mainPanel.removeAll();
        mainPanel.add(playPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();

        AuriGame game = new AuriGame();
        game.StartGame();

    }

    public void stats()
    {
        statsPanel = createStatsPanel();

        mainPanel.removeAll();
        mainPanel.add(statsPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void rules()
    {
        rulesPanel = createRulesPanel();

        mainPanel.removeAll();
        mainPanel.add(rulesPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();

    }

    public void home()
    {
        mainPanel.removeAll();
        mainPanel.add(new MainPanel().getMainPanel(), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel createPlayPanel()
    {
        JPanel tempPlay = new GamePanel(mainPanel);
        return tempPlay;
    }

    private JPanel createStatsPanel()
    {
        JPanel tempStats = new JPanel();

        back.addActionListener(new ButtonsController(mainPanel));
        tempStats.add(back);

        return tempStats;
    }

    private JPanel createRulesPanel()
    {
        JPanel tempRules = new JPanel();
        JButton back = new JButton("←");

        back.addActionListener(new ButtonsController(mainPanel));

        tempRules.add(back);

        return tempRules;
    }
}
