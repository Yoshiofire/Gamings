import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;
// import java.io.IOException;
// import java.awt.image.BufferedImage;
// import javax.imageio.ImageIO;
// import java.awt.Graphics2D;


public class People extends Entity{
    int direction = 0;
    public static ArrayList <People> peopleList = new ArrayList<>();
    // public static List<People> peopleList = Collections.synchronizedList(new ArrayList<>());
    // public BufferedImage sprite; // this one for the "character frames" ig
    
    
    public People(String filePath){ //DOESNT NEED TO BE A STRING, I AM JUST LAZY SO INSTEAD OF SENDING A BUFFEREDIMAGE I FORCE PEOPLE TO SEND IN STRING
        super(
            500 + (int) (Math.random() * 100),
            500 + (int) (Math.random() * 100),
            5,
            50,
            50);
        defaultFilePath = filePath;
        this.setSprite(filePath);
        this.type = "enemy1";
        peopleList.add(this);
    }

    public People(String filePath, int x, int y){ //DOESNT NEED TO BE A STRING, I AM JUST LAZY SO INSTEAD OF SENDING A BUFFEREDIMAGE I FORCE PEOPLE TO SEND IN STRING
        super(
            x,
            y,
            5,
            50,
            50);
        defaultFilePath = filePath;
        this.setSprite(filePath);
        this.type = "enemy1";
        peopleList.add(this);
    }









}
