package Tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

import Entités.Joueur;

public class TileManager {

    GamePanel gp;
    Tile[] tiles;
    int mapTuileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tiles = new Tile[94];
        mapTuileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap();
    }
    public void getTileImage(){


        try {
            for (int i = 1; i <= 47; i++) {

                tiles[i-1] = new Tile();
                InputStream is =getClass().getResourceAsStream("/Tuiles/Tile Grass/grass"+ i +".png");
                if (is == null) {
                    System.out.println("Image not found: grass" + i + ".png");
                }
                tiles[i-1].image = ImageIO.read(is);
            }
            for (int j = 48; j <= 94; j++) {
                tiles[j-1] = new Tile();
                InputStream is = getClass().getResourceAsStream("/Tuiles/Tile Rocks/tile" + (j-47) + ".png");
                if (is == null) {
                    System.out.println("Image not found: tile" + (j-47) + ".png");
                }
                tiles[j-1].image = ImageIO.read(is);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadMap(){
        try {
            InputStream is = getClass().getResourceAsStream("/Map/map_50x50.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col=0,row=0;

            while (col <gp.maxWorldCol && row <gp.maxWorldRow){

                String line = br.readLine();

                while (col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTuileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;

                }
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2d){

     //Test Tuiles
      /*  if(tiles[0].image!=null){
            g2d.drawImage(tiles[0].image, 0, 0,gp.getTileSize(),gp.getTileSize(), null);
        }
      */

        int WorldCol =0;
        int WorldRow =0;

        while (WorldCol <gp.maxWorldCol && WorldRow <gp.maxWorldRow) {
            int tilenum =  mapTuileNum[WorldCol][WorldRow];

            int x = WorldCol * gp.getTileSize();
            int y = WorldRow * gp.getTileSize();
            int screenX = x - gp.getJoueur().WorldX + gp.getJoueur().screenX;
            int screenY = y - gp.getJoueur().WorldY + gp.getJoueur().screenY;

            if(x + gp.getTileSize() > gp.getJoueur().WorldX - gp.getJoueur().screenX &&
               x - gp.getTileSize() < gp.getJoueur().WorldX + gp.getJoueur().screenX &&
               y + gp.getTileSize() > gp.getJoueur().WorldY - gp.getJoueur().screenY &&
               y - gp.getTileSize() < gp.getJoueur().WorldY + gp.getJoueur().screenY)
            g2d.drawImage(tiles[tilenum].image,screenX,screenY,gp.getTileSize(),gp.getTileSize(),null);
            WorldCol++;

            if (WorldCol == gp.maxWorldCol) {
                WorldCol = 0;
                WorldRow++;

            }


        }

    }
}
