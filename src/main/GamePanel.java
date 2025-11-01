package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Paramètres Écran
    final int originalTileSize = 16; //16x16 tile
    final int echelle = 3;

    final int tileSize = originalTileSize * echelle; //  48x48 tile

    //Ratio 4x3
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    //Taille Écran
    final int screenWidth = tileSize * maxScreenCol;  // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    Thread gameThread;
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);               //Améliore le rendu du jeu


    }


    //Démarrer le thread
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    //Faire tourner le thread
    @Override
    public void run() {
        while(gameThread != null){

            //Testing
            //System.out.println("La boucle de jeu tourne");


            // 1 Update: Mets à jour les informations qu'on reçoit comme le mouvement des personnages
            update();
            // 2 Draw: 'Dessine' l'écran avec les informations mis à jour
            repaint();

        }

    }


    //Fonction des update
    public void update() {

    }

    //Fonction de dessin
    public void paint(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.white);
        g2d.fillRect(100, 100, tileSize, tileSize);
        g2d.dispose();

    }


}
