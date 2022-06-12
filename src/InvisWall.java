// import java.awt.Rectangle;
import java.util.ArrayList;

public class InvisWall extends Entity {
    static int count = 0;

    public static ArrayList <InvisWall> wallList = new ArrayList<>();
    
    public InvisWall(int x, int y, int sizeX, int sizeY){
        super(x-30, y-30, 0, sizeX+30, sizeY+30);
        // hitbox = new Rectangle(posX, posY, sizeX, sizeY);
        // collides = false;

        //Curently can't make InvisWall its own thing, because I'd have to change up the way player is made up too, and they practically work on
        //Entity's methods, and sort of is in a' is a' relationship.

        switch(count){
            case 0:
                this.type = "Top_Wall";
                break;
            case 1:
                this.type = "Bottom_Wall";
                break;
            case 2:
                this.type = "Left_Wall";
                break;
            case 3:
                this.type = "Right_Wall";
                break;
        }
        count++;
        wallList.add(this);
    }
}
