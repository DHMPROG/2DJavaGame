package Entités;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Joueur extends Entite {
    GamePanel gp;
    KeyHandler kh;

    public Joueur(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;

        setDefaultValues();
        getImageJoueur();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        vitesse = 4;
        direction = "bas";
    }
    public void getImageJoueur(){
        try {
            haut1 = ImageIO.read(getClass().getResourceAsStream("/Joueur/haut 1.png"));
            haut2 = ImageIO.read(getClass().getResourceAsStream("/Joueur/haut 2.png"));
            gauche1 = ImageIO.read(getClass().getResourceAsStream("/Joueur/gauche 1.png"));
            gauche2 = ImageIO.read(getClass().getResourceAsStream("/Joueur/gauche 2.png"));
            droite1 = ImageIO.read(getClass().getResourceAsStream("/Joueur/droite 1.png"));
            droite2 = ImageIO.read(getClass().getResourceAsStream("/Joueur/droite 2.png"));
            bas1 = ImageIO.read(getClass().getResourceAsStream("/Joueur/bas 1.png"));
            bas2 = ImageIO.read(getClass().getResourceAsStream("/Joueur/bas 2.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void update(){
       if(kh.upPressed || kh.downPressed || kh.leftPressed || kh.rightPressed){
           if(kh.upPressed){
               direction = "haut";
               y -= vitesse;
           }
           if (kh.downPressed){
               direction = "bas";
               y += vitesse;
           }
           if (kh.leftPressed){
               direction = "gauche";
               x -= vitesse;
           }
           if (kh.rightPressed){
               direction = "droite";
               x += vitesse;
           }
           compteurSprite++;
           if (compteurSprite >12){
               if(nombreSprite == 1) nombreSprite =2;
               else if(nombreSprite == 2) nombreSprite =1;
               compteurSprite = 0;
           }
       }


    }
    public void draw(Graphics2D g2d){
       //g2d.setColor(Color.white);
        // g2d.fillRect(x, y, gp.getTileSize(), gp.getTileSize());

        BufferedImage image = null;

        switch (direction){
            case "haut":
                if(nombreSprite == 1) image = haut1;
                if(nombreSprite == 2) image = haut2;
                break;
            case "bas":
                if(nombreSprite == 1) image = bas1;
                if(nombreSprite == 2) image = bas2;
                break;
            case "gauche":
                if(nombreSprite == 1) image = gauche1;
                if(nombreSprite == 2) image = gauche2;
                break;
            case "droite":
                if(nombreSprite == 1) image = droite1;
                if(nombreSprite == 2) image = droite2;
                break;
        }
        g2d.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);

    }
}
