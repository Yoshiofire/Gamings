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
    //temp variable
    boolean doRotate = false;
    int startFrame;
    int endFrame = 0;
    int rotation;

    public Item(int[] x, int[] y){
        hitbox = new Polygon(x, y, x.length);
        cooldown = 4;
        try{

            sprite = ImageIO.read(getClass().getResourceAsStream("/download.jpg"));
            

        }catch(IOException e){

            e.getStackTrace();

        }

        

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
        itemList.add(this);
    }


    public void testHitboxRotate(PlayerData p){
        int sFrame = 5;
        startFrame = Game.frameCount;
        if(p.key.attackKey && startFrame > endFrame){
            doRotate = true;
            endFrame = startFrame + cooldown*sFrame;
            at.setToRotation(Math.toRadians(rotation + 90), hitbox.xpoints[0], hitbox.ypoints[0] + (p.hitbox.getHeight()/2));
            animationHitbox = at.createTransformedShape(this.hitbox);
        }
        if(doRotate){
            if(Game.frameCount % sFrame == 0){
                at.rotate(Math.toRadians(45), hitbox.xpoints[0], hitbox.ypoints[0] + (p.hitbox.getHeight()/2));
                animationHitbox = at.createTransformedShape(this.hitbox);
            }
        }
        if(Game.frameCount > endFrame){
            doRotate = false;
            // at.setToRotation(Math.toRadians(rotation), hitbox.xpoints[0], hitbox.ypoints[0] + (p.hitbox.getHeight()/2));
            animationHitbox = null;
        }
    }




    //RATHER THAN DO THIS, ON THE ROTATE IT NEEDS TO ROTATE DEPENDING ON WHAT DIRECTION THE PLAYER IS MOVING?
    // public void directionMovement(PlayerData p){

    //     if(p.key.upKey == true){
    //         rotation = 90;
    //     }
    //     if(p.key.downKey == true){
    //         rotation = 270;
    //     }
    //     if(p.key.leftKey == true){
    //         rotation = 0;
    //     }
    //     if(p.key.rightKey == true){
    //         rotation = 180;
    //     } 
    // }
        


    public void draw(Graphics2D g3){

        g3.drawImage(sprite, at, null);
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
