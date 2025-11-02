package Entités;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Joueur extends Entite {
    GamePanel gp;
    KeyHandler kh;

    public Joueur(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;

        setDefaultValues();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        vitesse = 4;
    }
    public void update(){
        if(kh.upPressed){
            y -= vitesse;
        }
        if (kh.downPressed){
            y += vitesse;
        }
        if (kh.leftPressed){
            x -= vitesse;
        }
        if (kh.rightPressed){
            x += vitesse;
        }
    }
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.white);
        g2d.fillRect(x, y, gp.getTileSize(), gp.getTileSize());
    }
}
