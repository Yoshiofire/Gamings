import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Item{
    

    public int cooldown;
    public Polygon hitbox;
    public static ArrayList <Item> itemList = new ArrayList<>();
    public BufferedImage sprite;
    public Shape animationHitbox;
    private AffineTransform at = new AffineTransform();
    private AffineTransform spriteAt = new AffineTransform();
    //Add stats like size and dmg later


    public Item(int[] x, int[] y){
        hitbox = new Polygon(x, y, x.length);
        cooldown = 5;
        try{

            sprite = ImageIO.read(getClass().getResourceAsStream("/download.jpg"));
            

        }catch(IOException e){

            e.getStackTrace();

        }

        
        itemList.add(this);
        /* I AM THE WORLDS SMARTEST MAN TO EVER EXIST ON THIS PLANET
        The problem with the code was that when initalizing 'sprite' it put its starting coords at (0,0).
        Then when we used at to rotate it or something, it was rotating it correctly, but was using the distance from the pivot point to (0,0).
        Which was not what we wanted but rather something. So after years and eons of reasearching I figured out that if we were to translate the BufferedImage onto the polygon
        Then we'd recreate what we currently have for the drawPolyHitbox.*/



        spriteAt.translate(hitbox.xpoints[0], hitbox.ypoints[0]);//set this to the top left coord of polygon
        spriteAt.scale(1, .5);
        //^^ spriteAt is what transformations you need to do onto the sprite of the item.

        AffineTransformOp a = new AffineTransformOp(spriteAt, AffineTransformOp.TYPE_BILINEAR);
        sprite = a.filter(sprite, null);
    }


    public void testHitboxRotate(PlayerData p){
        int sFrame = 3;
        if(animationHitbox == null || Game.frameCount % (cooldown*sFrame) == 0){ //Resets the rotation to the hitbox
            at.setToRotation(Math.toRadians(90), hitbox.xpoints[0], hitbox.ypoints[0] + (p.hitbox.getHeight()/2));
        }
        else{//Rotates it 45 deg until cooldown is over.
            if(Game.frameCount % sFrame == 0){
                at.rotate(Math.toRadians(45), hitbox.xpoints[0], hitbox.ypoints[0] + (p.hitbox.getHeight()/2));
            }
        }
        animationHitbox = at.createTransformedShape(this.hitbox);

    }

    public void draw(Graphics2D g3){

        g3.drawImage(sprite, at, null);
    }


    public void drawPolyHitbox(Graphics2D g3){
        g3.drawPolygon(this.hitbox);

    }

    public void drawAniHitbox(Graphics2D g3){
        g3.draw(this.animationHitbox);

    }




}
