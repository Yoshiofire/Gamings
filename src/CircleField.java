
import java.awt.Color;
import java.awt.Graphics2D;

public class CircleField extends Item{

    public CircleField(int[] x, int[] y){ // we will send in
        super(x, y);
        cooldownSeconds = cooldownFrames * Game.FPS; 

        
    }

public void expandCircleField(int size){


}
    




    public void draw(Graphics2D g3){
        if(animationHitbox != null){
            g3.setColor(Color.YELLOW);
            g3.fill(animationHitbox);
            g3.drawImage(sprite, at, null);
            // g3.setClip();
        }
    }


}
