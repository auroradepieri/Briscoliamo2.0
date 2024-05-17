package controller;

import model.ButtonsLogic;
import view.MainFrame;
import view.MainPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonsController implements ActionListener{

    private static MainPanel mainPanel;
    private static ButtonsLogic logic;

    public ButtonsController(MainPanel _mainPanel)
    {
        mainPanel = _mainPanel;
        logic = new ButtonsLogic(mainPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton tempButton)
        {
            String buttonText = tempButton.getText();

            switch (buttonText)
            {
                case "Play Now!" -> logic.play();
                case "Statistics" -> logic.stats();
                case "Rules" -> logic.rules();
                case "←" -> logic.home();
            }
        }
    }

}
