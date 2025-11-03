package Tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    GamePanel gp;
    Tile[] tiles;
    int mapTuileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tiles = new Tile[94];
        mapTuileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
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
            InputStream is = getClass().getResourceAsStream("/Map/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col=0,row=0;

            while (col <gp.maxScreenCol && row <gp.maxScreenRow){

                String line = br.readLine();

                while (col < gp.maxScreenCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTuileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
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

        int col =0;
        int row =0;
        int x=0,y=0;

        while (col <gp.maxScreenCol && row <gp.maxScreenRow) {
            int tilenum =  mapTuileNum[col][row];

            g2d.drawImage(tiles[tilenum].image,x,y,gp.getTileSize(),gp.getTileSize(),null);
            col++;
            x+= gp.getTileSize();

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y+= gp.getTileSize();

            }


        }

    }
}
