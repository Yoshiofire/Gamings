import java.awt.image.AffineTransformOp;
import java.util.ArrayList;


public class Sword extends Item{
    public static ArrayList <Sword> swordList = new ArrayList<>();
    static int amount = 0;
    int thisNumber;
    boolean doRotate = false;
    int startFrame;
    int endFrame = 0;
    int rotationS;
    int rotationE = 11;
    int cooldownTime;

    public Sword(int[] x, int[] y){
        super(x, y);
        amount++;
        thisNumber = amount+1;
        this.setSprite("player_images/sword.png");
        cooldownSeconds = cooldownFrames * Game.FPS;
        playerHas = true; 
        //currently sword is better rectangle, as it can rotate 360 in incrments of 1 compared to rectangles and their 90 only.
        /*
        The sword is built from left to right, up to down.

        0-------------1
                      |
                      |
        3------------ 2

        */
        

        /* I AM THE WORLDS SMARTEST MAN TO EVER EXIST ON THIS PLANET
        The problem with the code was that when initalizing 'sprite' it put its starting coords at (0,0).
        Then when we used at to rotate it or something, it was rotating it correctly, but was using the distance from the pivot point to (0,0).
        Which was not what we wanted but rather something. So after years and eons of reasearching I figured out that if we were to translate the BufferedImage onto the polygon
        Then we'd recreate what we currently have for the drawPolyHitbox.*/
        spriteAt.translate(hitbox.xpoints[0], hitbox.ypoints[0]);//set this to the top left coord of polygon
        

        // double spriteHeightAfterScalingAgainstHitbox = hitbox.getBounds().getHeight()/ hitbox.getBounds().getWidth();
        double spriteHeightAfterScalingAgainstHitbox = hitbox.getBounds().getHeight()/sprite.getHeight();

        double spriteWidthAfterScalingAgainstHitbox = hitbox.getBounds().getWidth()/sprite.getWidth(); //Because swords are going to be scaled x-coordinate wise, we really don't need a y one

        spriteAt.scale(spriteWidthAfterScalingAgainstHitbox, spriteHeightAfterScalingAgainstHitbox);
        //^^ spriteAt is what transformations you need to do onto the sprite of the item.

        AffineTransformOp a = new AffineTransformOp(spriteAt, AffineTransformOp.TYPE_BILINEAR);
        sprite = a.filter(sprite, null);
        a = null; //< don't know if this removes the amount of memory it'd be using?
        swordList.add(this);
    }

    public void swingSword(PlayerData player){ //not awake enough to fix this, but I need this to change when FPS changes.
        int sFrame = Game.FPS/15; // 2 at 30 FPS and 4 at 60FPS
        startFrame = Game.frameCount;

        if(player.key.attackKey && startFrame > endFrame && cooldownTime < Game.frameCount){ //1. if button is pressed, 2. if animation is done 3. if its off CD
            switch(player.movement){
                case 87: // W
                    rotationS = 90;
                    break;
                case 83: //S
                    rotationS = 270;
                    break;
                 case 65: // A
                    rotationS = 0;
                    break;
                case 68: //D
                    rotationS = 180;  
                    break;
            }
            rotationS += thisNumber*(360/amount);
            doRotate = true;
            endFrame = startFrame + 5*sFrame; //<- the 5 is the waiting time after each rotation
            cooldownTime = endFrame + cooldownSeconds;
            at.setToRotation(Math.toRadians(rotationS + 150), player.hitbox.getCenterX(), player.hitbox.getCenterY());
            animationHitbox = at.createTransformedShape(this.hitbox);
        }
        if(doRotate){ //if its in animation
            if(Game.frameCount % sFrame == 0){
                at.rotate(Math.toRadians(rotationE), player.hitbox.getCenterX(), player.hitbox.getCenterY());
                animationHitbox = at.createTransformedShape(this.hitbox);
            }
        }

        if(Game.frameCount > endFrame){ //checks if animation is done
            doRotate = false;
            // at.setToRotation(Math.toRadians(rotation), hitbox.xpoints[0], hitbox.ypoints[0] + (p.hitbox.getHeight()/2));
            animationHitbox = null;
        }
    }

    
}
