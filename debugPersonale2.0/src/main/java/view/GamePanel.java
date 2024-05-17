package view;

import controller.ButtonsController;
import controller.CardsController;
import model.ButtonsLogic;
import org.example.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel
{
    private MainPanel mainPanel;
    private int selectedRectangleIndex = -1;
    private int selectedRectangleOffsetX;
    private int selectedRectangleOffsetY;
    private static final JButton back = new JButton("←");

    private final Rectangle[] rectangles;
    private final BufferedImage[] cardImages; // Array per memorizzare le immagini delle carte

    public GamePanel(MainPanel _mainPanel) {
        mainPanel = _mainPanel;
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Imposta il layout a sinistra con margine di 10 pixel

        // Aggiungta del bottone al pannello + Controller + Controllo uscita partita
        back.addActionListener(new ButtonsController(mainPanel));
        add(back);

        // Inizializza i rettangoli delle carte del giocatore
        rectangles = new Rectangle[3];
        for (int i = 0; i < rectangles.length; i++) {
            rectangles[i] = new Rectangle(i * 200 + 150, Settings.FRAME_HEIGHT / 2 + 40, Settings.CARD_HEIGHT, Settings.CARD_WIDTH);
        }
        cardImages = new BufferedImage[3]; // Considerando che ci sono 3 carte del giocatore
        try {
            cardImages[0] = ImageIO.read(new File("src/main/resources/carte_napoletane/bastoni/1_B.png"));
            cardImages[1] = ImageIO.read(new File("src/main/resources/carte_napoletane/bastoni/2_B.png"));
            cardImages[2] = ImageIO.read(new File("src/main/resources/carte_napoletane/bastoni/3_B.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Aggiungi il mouse listener al pannello
        addMouseListener(new CardsController(this));
        addMouseMotionListener(new CardsController(this));
    }
    private void drawCardImages(Graphics g) {
        // Disegna le immagini delle carte all'interno dei rettangoli
        for (int i = 0; i < rectangles.length; i++) {
            if (cardImages[i] != null) {
                g.drawImage(cardImages[i], rectangles[i].x, rectangles[i].y, this);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCardImages(g); // Disegna le immagini delle carte
        drag(g); // Disegna i rettangoli
    }

    private void drag(Graphics g) {
        // Disegna i rettangoli relativi alle carte del giocatore, tranne se uno è selezionato
        //non serve andare a disegnare i rettangoli delle carte perche i rettangoli vengono creati sopra le carte
//        for (int i = 0; i < rectangles.length; i++) {
//            if (i != selectedRectangleIndex) {
//                g.setColor(Color.red);
//                g.fillRect(rectangles[i].x, rectangles[i].y, rectangles[i].width, rectangles[i].height);
//            }
//        }
        g.setColor(Color.red);
        // Disegna l'immagine della carta selezionata sopra il rettangolo
        if (selectedRectangleIndex != -1 && cardImages[selectedRectangleIndex] != null) {
            g.drawImage(cardImages[selectedRectangleIndex], rectangles[selectedRectangleIndex].x, rectangles[selectedRectangleIndex].y, this);
        }

        // Disegna le altre carte e gli altri elementi
        for (int positionX = 0; positionX<3 ; positionX++) {
            //carte del bot coperte
            g.fillRect(positionX * 200 + 150, 50, Settings.CARD_HEIGHT, Settings.CARD_WIDTH);
        }

        // Disegna la carta della briscola e il mazzo delle carte restanti
        g.fillRect(750, Settings.FRAME_WIDTH / 4 + 50, Settings.CARD_WIDTH, Settings.CARD_HEIGHT);
        g.fillRect(850, Settings.FRAME_WIDTH / 4 + 10, Settings.CARD_HEIGHT, Settings.CARD_WIDTH);
    }

    public int getSelectedRectangleIndex() {
        return selectedRectangleIndex;
    }

    public int getSelectedRectangleOffsetX() {
        return selectedRectangleOffsetX;
    }

    public int getSelectedRectangleOffsetY() {
        return selectedRectangleOffsetY;
    }

    public Rectangle[] getRectangles() {
        return rectangles;
    }

    public void setSelectedRectangleIndex(int selectedRectangleIndex) {
        this.selectedRectangleIndex = selectedRectangleIndex;
    }

    public void setSelectedRectangleOffsetX(int selectedRectangleOffsetX) {
        this.selectedRectangleOffsetX = selectedRectangleOffsetX;
    }

    public void setSelectedRectangleOffsetY(int selectedRectangleOffsetY) {
        this.selectedRectangleOffsetY = selectedRectangleOffsetY;
    }
}
