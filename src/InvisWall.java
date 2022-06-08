package src;
import java.util.ArrayList;

public class InvisWall extends Entity {
    static int count = 0;

    public static ArrayList <InvisWall> wallList = new ArrayList<>();
    
    public InvisWall(int x, int y, int sizeX, int sizeY){
        super(x-30, y-30, 0, sizeX+30, sizeY+30);
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
