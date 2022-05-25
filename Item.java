import java.util.ArrayList;
import java.awt.Shape;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class Item extends Entity{
    

    public int cooldown;
    public static ArrayList <Item> itemList = new ArrayList<>();
    //Add stats like size and dmg later


    public Item(int x, int y){
        super(x, y, 0, 100, 50);
        cooldown = 1;
        itemList.add(this);
    }

//DOESNT WORK, ONLY WORKS IN 90 DEGS
/* I guess what we can do is only show 90 degs, but do the intersection tests with a rectangle2d and the degs != deg % 90 == 0;
*/
    public void rotate(){
        if(Game.frameCount % 30 == 0){
            AffineTransform at = new AffineTransform();

            // at.setToRotation(Math.toRadians(40), posX, posY);Math.toRadians(40), posX, posY
            at.rotate(Math.toRadians(1),posX, posY);
            // at.scale(.99, .99);
            Shape temp = at.createTransformedShape(hitbox);
            hitbox = temp.getBounds();
        }


    }





}
