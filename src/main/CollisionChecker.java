package main;

import Entités.Entite;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entite entite){
        int entiteLeftWorldX = entite.WorldX + entite.aireCollision.x;
        int entiteRightWorldX = entite.WorldX + entite.aireCollision.x + entite.aireCollision.width;
        int entiteTopWorldY = entite.WorldY + entite.aireCollision.y;
        int entiteBottomWorldY = entite.WorldY + entite.aireCollision.y + entite.aireCollision.height;

        int entiteLeftCol = entiteLeftWorldX / gp.getTileSize();
        int entiteRightCol = entiteRightWorldX / gp.getTileSize();
        int entiteTopRow = entiteTopWorldY / gp.getTileSize();
        int entiteBottomRow = entiteBottomWorldY / gp.getTileSize();

        int tileNum1, tileNum2;

        switch (entite.direction){
            case "haut":
                entiteTopRow = (entiteTopWorldY - entite.vitesse) / gp.getTileSize();
                tileNum1 = gp.tuileM.getMapTuileNum()[entiteLeftCol][entiteTopRow];
                tileNum2 = gp.tuileM.getMapTuileNum()[entiteRightCol][entiteTopRow];
                if(gp.tuileM.getTiles()[tileNum1].collision == true || gp.tuileM.getTiles()[tileNum2].collision == true){
                    entite.CollisionObj = true;
                }
                break;
            case "bas":
                entiteBottomRow = (entiteBottomWorldY + entite.vitesse)/gp.getTileSize();
                tileNum1 = gp.tuileM.getMapTuileNum()[entiteLeftCol][entiteBottomRow];
                tileNum2 = gp.tuileM.getMapTuileNum()[entiteRightCol][entiteBottomRow];
                if(gp.tuileM.getTiles()[tileNum1].collision == true || gp.tuileM.getTiles()[tileNum2].collision == true){
                    entite.CollisionObj = true;
                }
                break;
            case "gauche":
                entiteLeftCol = (entiteLeftWorldX - entite.vitesse)/gp.getTileSize();
                tileNum1 = gp.tuileM.getMapTuileNum()[entiteLeftCol][entiteTopRow];
                tileNum2 = gp.tuileM.getMapTuileNum()[entiteLeftCol][entiteBottomRow];
                if(gp.tuileM.getTiles()[tileNum1].collision == true || gp.tuileM.getTiles()[tileNum2].collision == true) {
                    entite.CollisionObj = true;
                }
                break;
            case "droite":
                entiteRightCol = (entiteRightWorldX)/gp.getTileSize();
                tileNum1 = gp.tuileM.getMapTuileNum()[entiteRightCol][entiteTopRow];
                tileNum2 = gp.tuileM.getMapTuileNum()[entiteRightCol][entiteBottomRow];
                if(gp.tuileM.getTiles()[tileNum1].collision == true || gp.tuileM.getTiles()[tileNum2].collision == true) {
                    entite.CollisionObj = true;
                }
                break;
        }
    }
}
