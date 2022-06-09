import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;
// import java.io.IOException;
// import java.awt.image.BufferedImage;
// import javax.imageio.ImageIO;
// import java.awt.Graphics2D;


public class People extends Entity{
    int direction = 0;
    public int type;
    public static ArrayList <People> peopleList = new ArrayList<>();
    // public static List<People> peopleList = Collections.synchronizedList(new ArrayList<>());

    // public People(String filePath){ //DOESNT NEED TO BE A STRING, I AM JUST LAZY SO INSTEAD OF SENDING A BUFFEREDIMAGE I FORCE PEOPLE TO SEND IN STRING
    //     super(
    //         500,
    //         500,
    //         7,
    //         60,
    //         60);
    //     defaultFilePath = filePath;
    //     this.setSprite(filePath);
    //     this.moveHPBars();
    //     this.expWorth = 5;
    //     peopleList.add(this);
    // }

    

    public People(String filePath, int x, int y, int type){ //DOESNT NEED TO BE A STRING, I AM JUST LAZY SO INSTEAD OF SENDING A BUFFEREDIMAGE I FORCE PEOPLE TO SEND IN STRING
        super(
            x,
            y,
            7,
            60,
            60);
        this.defaultFilePath = filePath;
        this.setSprite(filePath);
        this.type = type;
        // this.type = "enemy1";
        switch(type){
            case 1:
                expWorth = 5;
                break;
            case 2:
                eSpeed *= 2;
                expWorth = 20;
                health = 40;
                healthMax = health;
                contactDMG *= 2;
                break;
        }
        this.moveHPBars();
        peopleList.add(this);

    }





}
