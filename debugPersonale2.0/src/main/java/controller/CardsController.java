package controller;

import view.GamePanel;
import view.MainPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class CardsController implements MouseListener, MouseMotionListener
{

    private static GamePanel gamePanel;

    public CardsController(GamePanel _gamePanel)
    {
        gamePanel = _gamePanel;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        // Controlla se il mouse è stato premuto sopra uno dei rettangoli
        for (int i = 0; i < gamePanel.getRectangles().length; i++) {
            if (gamePanel.getRectangles()[i].contains(e.getPoint())) {
                gamePanel.setSelectedRectangleIndex(i);
                gamePanel.setSelectedRectangleOffsetX(e.getX() - gamePanel.getRectangles()[i].x);
                gamePanel.setSelectedRectangleOffsetY(e.getY() - gamePanel.getRectangles()[i].y);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        // Resetta l'indice del rettangolo selezionato
        gamePanel.setSelectedRectangleIndex(-1);
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        // Se un rettangolo è stato selezionato, spostalo con il mouse
        if (gamePanel.getSelectedRectangleIndex()!= -1)
        {
            gamePanel.getRectangles()[gamePanel.getSelectedRectangleIndex()].x = e.getX() - gamePanel.getSelectedRectangleOffsetX();
            gamePanel.getRectangles()[gamePanel.getSelectedRectangleIndex()].y = e.getY() - gamePanel.getSelectedRectangleOffsetY();
            gamePanel.repaint(); // Ridisegna il pannello per mostrare il rettangolo spostato
        }
    }

    //  Non necessarie per questa applicazione
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
}
