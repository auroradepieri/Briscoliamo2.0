package view;

import org.example.Settings;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    public MainFrame()
    {
        setTitle("Briscoliamo");
        setSize(Settings.FRAME_WIDTH, Settings.FRAME_HEIGHT);

        MainPanel mainPanel = new MainPanel();
        add(mainPanel);

        setFocusable(true);
        requestFocus();

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
