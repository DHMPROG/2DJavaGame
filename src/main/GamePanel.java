package main;

import javax.swing.*;
import java.awt.*;
import Entités.Joueur;
import Tile.Tile;
import Tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    //Paramètres Écran
    final int originalTileSize = 16; //16x16 tile
    final int echelle = 3;

    public int getTileSize() {
        return tileSize;
    }

    final int tileSize = originalTileSize * echelle; //  48x48 tile

    //Paramètres Monde
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //Ratio 4x3
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    //Taille Écran
    public final int screenWidth = tileSize * maxScreenCol;  // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //Thread
    Thread gameThread;

    //FPS max
    int FPS = 60;

    //KeyHandler
    KeyHandler keyH = new KeyHandler();

    //Joueur
    Joueur joueur = new Joueur(this,keyH);

    //Collisions
    CollisionChecker cChecker = new CollisionChecker(this);

    public Joueur getJoueur() {
        return joueur;
    }

    //Tuiles
    TileManager tuileM = new TileManager(this);


    //Position par défaut du Joueur (tests)
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);               //Améliore le rendu du jeu
        this.addKeyListener(keyH);
        this.setFocusable(true);    //Game Panel peut presentement être focus pour recevoir le keyInput
    }


    //Démarrer le thread
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    //Faire tourner le thread
    @Override
    public void run() {

        double IntervalleDessin = (double) 1000000000 /FPS; //0.01666 secondes
        double nextDrawTime = System.nanoTime() + IntervalleDessin;

        while(gameThread != null){

            //Testing
            //System.out.println("La boucle de jeu tourne");

            // 1 Update: Mets à jour les informations qu'on reçoit comme le mouvement des personnages
            update();
            // 2 Draw: 'Dessine' l'écran avec les informations mis à jour
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0)remainingTime = 0;

                Thread.sleep((long)remainingTime);

                nextDrawTime +=IntervalleDessin;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }


    //Fonction des update
    public void update() {
            joueur.update();

    }

    //Fonction de dessin
    public void paint(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        tuileM.draw(g2d);
        joueur.draw(g2d);

        g2d.dispose();
    }


    //Getters
    public CollisionChecker getcChecker() {
        return cChecker;
    }
}
