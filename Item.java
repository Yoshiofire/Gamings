import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Item{
    

    public int cooldownFrames;
    public int cooldownSeconds;
    public Polygon hitbox;
    public static ArrayList <Item> itemList = new ArrayList<>();
    public BufferedImage sprite;
    public Shape animationHitbox;
    public int dmg;


    public AffineTransform at = new AffineTransform();
    public AffineTransform spriteAt = new AffineTransform();

    public Item(int[] x, int[] y){
        hitbox = new Polygon(x, y, x.length);
        cooldownFrames = 10;
        cooldownSeconds = cooldownFrames * Game.FPS;
        dmg = 5;
        try{

            sprite = ImageIO.read(getClass().getResourceAsStream("/download.jpg"));
            

        }catch(IOException e){

            e.getStackTrace();

        }


        itemList.add(this);
    }






  


    public void draw(Graphics2D g3){
        if(animationHitbox != null){
            g3.drawImage(sprite, at, null);
        }
    }


    public void drawPolyHitbox(Graphics2D g3){
        g3.drawPolygon(this.hitbox);

    }

    public void drawAniHitbox(Graphics2D g3){
        if(animationHitbox != null){
            g3.draw(this.animationHitbox);
        }

    }




}
