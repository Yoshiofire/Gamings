import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.AlphaComposite;
import java.util.ArrayList;


public class CircleZone extends Item{

    public int currentSize = 300;
    public Arc2D circleZone;
    public int centerPosX;
    public int centerPosY;
    public static ArrayList <CircleZone> circleZoneList = new ArrayList<>();


    

    public CircleZone(PlayerData player){
        super();
        dmg = player.contactDMG;
        centerPosX = (int) player.hitbox.getCenterX()-(currentSize/2);
        centerPosY = (int) player.hitbox.getCenterY()-(currentSize/2);
        circleZone = new Arc2D.Double
        (
            centerPosX,
            centerPosY,
            currentSize,
            currentSize,
            0,
            360,
            Arc2D.CHORD
        );
        animationHitbox = (Shape) circleZone;
        circleZoneList.add(this);
    }

    public void increaseSize(int increaseDiameter){
        currentSize += increaseDiameter;
        centerPosX -= increaseDiameter/2;
        centerPosY -= increaseDiameter/2;
        circleZone.setArc
        (
            centerPosX,
            centerPosY,
            currentSize,
            currentSize,
            0,
            360,
            Arc2D.CHORD
        );
        animationHitbox = (Shape) circleZone;
    }

    public void draw(Graphics2D g3){
        g3.setColor(Color.YELLOW);
        g3.draw(circleZone);
        // g3.draw(animationHitbox);
        g3.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .1f));
        g3.fill(animationHitbox);
        g3.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    
}
