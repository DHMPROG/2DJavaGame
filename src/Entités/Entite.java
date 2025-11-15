package Entités;

import java.awt.image.BufferedImage;

public class Entite {
    public int WorldX, WorldY;
    public int vitesse;

    public BufferedImage haut1, haut2, gauche1, gauche2,
            droite1,droite2, bas1,bas2;
    public String direction;

    public int compteurSprite =0;
    public int nombreSprite = 1;


}
