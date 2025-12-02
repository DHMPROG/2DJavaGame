// java
        package Entités;

        import main.GamePanel;
        import main.KeyHandler;

        import javax.imageio.ImageIO;
        import java.awt.*;
        import java.awt.image.BufferedImage;
        import java.io.IOException;

        /*
         Classe Joueur : représente le joueur contrôlé par l'utilisateur.
         Hérite de Entite (position, sprites, collisions, etc.).
         Gère l'initialisation, le chargement des images, la mise à jour (input) et le rendu.
        */
        public class Joueur extends Entite {
            // Référence au panneau de jeu pour accéder aux tailles et au contexte global
            GamePanel gp;
            // Gestionnaire d'input clavier
            KeyHandler kh;

            // Position du joueur sur l'écran (centrée)
            public final int screenX;
            public final int screenY;

            /*
             Constructeur :
             - conserve les références au GamePanel et KeyHandler,
             - calcule la position écran centrale du joueur,
             - initialise la zone de collision,
             - définit les valeurs par défaut (position monde, vitesse, direction),
             - charge les images/sprites du joueur.
            */
            public Joueur(GamePanel gp, KeyHandler kh) {
                this.gp = gp;
                this.kh = kh;

                // Calculer la position du joueur au centre de l'écran (en pixels)
                screenX = gp.screenWidth/2 - (gp.getTileSize()/2);
                screenY = gp.screenHeight/2- (gp.getTileSize()/2);

                // Zone de collision relative (x, y, largeur, hauteur)
                aireCollision = new Rectangle(32,32,16,16);

                setDefaultValues();
                getImageJoueur();
            }

            /*
             setDefaultValues :
             Définit la position initiale dans le monde (WorldX / WorldY),
             la vitesse de déplacement et la direction initiale.
            */
            public void setDefaultValues(){
                WorldX = gp.getTileSize()*23;
                WorldY = gp.getTileSize()*21;
                vitesse = 4;
                direction = "bas";
            }

            /*
             getImageJoueur :
             Charge les images des différentes directions et frames depuis les ressources.
             Utilise ImageIO et getResourceAsStream pour être compatible avec les JAR.
             Un message est affiché si toutes les images sont correctement chargées.
            */
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
                    if(haut1!=null && haut2!=null &&
                            gauche1!=null && gauche2!=null &&
                                droite1!=null && droite2!=null &&
                                    bas1!=null && bas2!=null)
                        System.out.println("Images joueurs sucessefully loaded");

                } catch (IOException e) {
                    // Propagation d'une RuntimeException si une image ne peut être chargée.
                    throw new RuntimeException(e);
                }

            }

            /*
             update :
             - Vérifie les touches appuyées via KeyHandler,
             - Met à jour la direction et la position monde (WorldX/WorldY) par rapport à la vitesse,
             - Gère l'animation de marche en changeant la frame toutes les X mises à jour.
             Note : la gestion des collisions avec le monde (tiles, entités) devrait se faire
             ici ou dans Entite si ajoutée ultérieurement.
            */
            public void update(){
               if(kh.upPressed || kh.downPressed || kh.leftPressed || kh.rightPressed){
                   if(kh.upPressed){
                       direction = "haut";
                   }
                   if (kh.downPressed){
                       direction = "bas";
                   }
                   if (kh.leftPressed){
                       direction = "gauche";
                   }
                   if (kh.rightPressed){
                       direction = "droite";
                   }

                   //Regarder si il y'a collision
                   CollisionObj = false;
                   gp.getcChecker().checkTile(this);

                   //Si collision est false joueur peut bouger
                   if(CollisionObj == false){
                       switch (direction){
                           case "haut":
                               WorldY -= vitesse;
                               break;
                           case "bas":
                               WorldY += vitesse;
                               break;
                           case "gauche":
                               WorldX -= vitesse;
                               break;
                           case "droite":
                               WorldX += vitesse;
                               break;
                       }
                   }



                   // Comptage pour alterner les sprites (animation de marche)
                   compteurSprite++;
                   if (compteurSprite >12){
                       if(nombreSprite == 1) nombreSprite =2;
                       else if(nombreSprite == 2) nombreSprite =1;
                       compteurSprite = 0;
                   }
               }
            }

            // Calcul de la position caméra X bornée
            public int getCameraX() {
                int camX = WorldX - screenX;

                if (camX < 0) camX = 0;

                int maxCamX = gp.maxWorldCol * gp.getTileSize() - gp.screenWidth;
                if (maxCamX < 0) maxCamX = 0;
                if (camX > maxCamX) camX = maxCamX;

                return camX;
            }

            // Calcul de la position caméra Y bornée
            public int getCameraY() {
                int camY = WorldY - screenY;

                if (camY < 0) camY = 0;

                int maxCamY = gp.maxWorldRow * gp.getTileSize() - gp.screenHeight;
                if (maxCamY < 0) maxCamY = 0;
                if (camY > maxCamY) camY = maxCamY;

                return camY;
            }

            /*
             draw :
             - Sélectionne l'image correspondant à la direction et à la frame actuelle,
             - Dessine l'image centrée sur les coordonnées écran calculées.
             Les dimensions de rendu utilisent la taille de tuile pour coller à la grille du jeu.
            */
            public void draw(Graphics2D g2d){
               // Préparer l'image à dessiner selon la direction et la frame
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

                int cameraX = getCameraX();
                int cameraY = getCameraY();

                int drawX = WorldX - cameraX;
                int drawY = WorldY - cameraY;
                // Dessin de l'image à l'écran (possibilité que image soit null si échec de chargement)
                g2d.drawImage(image, drawX, drawY, gp.getTileSize(), gp.getTileSize(), null);

            }


        }