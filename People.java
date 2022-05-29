import java.util.ArrayList;

// import java.io.IOException;
// import java.awt.image.BufferedImage;
// import javax.imageio.ImageIO;
// import java.awt.Graphics2D;


public class People extends Entity{
    int direction = 0;
    public static ArrayList <People> peopleList = new ArrayList<>();
    // public BufferedImage sprite; // this one for the "character frames" ig
    
    
    public People(String filePath, KeyHandler k){ //DOESNT NEED TO BE A STRING, I AM JUST LAZY SO INSTEAD OF SENDING A BUFFEREDIMAGE I FORCE PEOPLE TO SEND IN STRING
        super(
            500 + (int) (Math.random() * 100),
            500 + (int) (Math.random() * 100),
            10,
            100,
            100);
        this.setSprite(filePath);
        this.type = "enemy1";
        peopleList.add(this);
    }


    public void peopleMove(){
        if(Game.frameCount % (Game.FPS/6) == 0){ // <- moves around 1/6 times per frame.
            direction = (int) (Math.random() * 4);
        }
        
        switch(direction){
            case 0:
                direction = 83;
                break;
            case 1:
                direction = 87;
                break;
            case 2:
                direction = 68;
                break;
            case 3:
                direction = 65;
                break;
        }
        if(!collides){
            switch(direction){
                    case 87: // W 1
                        posY -= eSpeed;
                        break;
                    case 83: //S 0
                        posY += eSpeed;
                        break;
                    case 65: // A 3
                        posX -= eSpeed;
                        break;
                    case 68: //D 2
                        posX += eSpeed;
                        break;
                }
        }
        hitbox.setLocation(posX, posY);
        movement = direction;
    
    }






}
