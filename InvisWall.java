import java.util.ArrayList;

public class InvisWall extends Entity {

    public static ArrayList <InvisWall> wallList = new ArrayList<>();
    
    public InvisWall(int x, int y, int sizeX, int sizeY){
        super(x-10, y-10, 0, sizeX+10, sizeY+10);
        wallList.add(this);
    }




}
