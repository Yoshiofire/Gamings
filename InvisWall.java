import java.util.ArrayList;

public class InvisWall extends Entity {
    static int count = 0;

    public static ArrayList <InvisWall> wallList = new ArrayList<>();
    
    public InvisWall(int x, int y, int sizeX, int sizeY){
        super(x-30, y-30, 0, sizeX+30, sizeY+30);
        this.type = "wall";
        wallList.add(this);
    }

    public void allTrue(){
        for(InvisWall walls: InvisWall.wallList){
            walls.collides = true;
        }
    }
}
