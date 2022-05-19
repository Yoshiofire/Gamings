import java.util.ArrayList;

// import java.io.IOException;
// import java.awt.image.BufferedImage;
// import javax.imageio.ImageIO;
// import java.awt.Graphics2D;


public class People extends Entity{
    int count = 0;
    int direction = 0;
    public static ArrayList <People> peopleList = new ArrayList<>();
    // public BufferedImage sprite; // this one for the "character frames" ig
    
    
    public People(String filePath){ //DOESNT NEED TO BE A STRING, I AM JUST LAZY SO INSTEAD OF SENDING A BUFFEREDIMAGE I FORCE PEOPLE TO SEND IN STRING
        super(
            500 + (int) (Math.random() * 100),
            500 + (int) (Math.random() * 100),
            0,
            100,
            100);
        this.setSprite(filePath);
        peopleList.add(this);
    }


    public void peopleMove(){
        if(count == 5){
            direction = (int) (Math.random() * 4);
            count = 0;
        }
        
        int amount = (int) (Math.random() * 5);
        // int amount = 0;
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
        eSpeed = amount;
        count++;
        
        
            /*            switch(direction){
                case 87: // W
                    sprite = ImageIO.read(getClass().getResourceAsStream("/Player_Images/JermaUp.png"));
                    break;
                case 83: //S
                    sprite = ImageIO.read(getClass().getResourceAsStream("/Player_Images/JermaDown.png"));
                    break;
                 case 65: // A
                    sprite = ImageIO.read(getClass().getResourceAsStream("/Player_Images/JermaLeft.png"));
                    break;
                case 68: //D
                    sprite = ImageIO.read(getClass().getResourceAsStream("/Player_Images/JermaRight.png"));
                    break;

            }*/


        // DONT NEED THIS BECAUSE THE IMAGE WE ARE USING IS STATIC THEREFORE WE CAN JSUT INITALIZE IT WHEN WE CREATE THE OBJECT
        // try{

        //     sprite = ImageIO.read(getClass().getResourceAsStream("/People_Images/People.jpg"));

        // }catch(IOException e){

        //     e.getStackTrace();

        // }
    }

    // public void draw(Graphics2D g1){
    //     super.draw(g1, sprite);
    // }


}
