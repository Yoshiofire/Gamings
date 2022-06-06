// import java.awt.Graphics2D;
import java.io.IOException;
// import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
// import java.awt.Image;

public class PlayerData extends Entity{


    private Game game;
    public KeyHandler key;
    // public BufferedImage sprite; // this one for the "character frames" ig
    public String imageFilePathUp, imageFilePathDown, imageFilePathLeft, imageFilePathRight;

    // public PlayerData(KeyHandler k, Game g){
    //     super();
    //     game = g;
    //     key = k;
    //     this.setSprite("/Player_Images/JermaUp.png");
    //     this.type = "player";
    // }

    // public PlayerData(KeyHandler k, Game g, int startingX, int startingY, int playerS){
    //     super(startingX, startingY, playerS);
    //     game = g;
    //     key = k;
    //     this.setSprite("/Player_Images/JermaUp.png");
    //     this.type = "player";
    // }

    public PlayerData(KeyHandler k, Game g, int startingX, int startingY, int playerS, int sizeX, int sizeY){
        super(startingX, startingY, playerS, sizeX, sizeY);
        imageFilePathUp = "/Player_Images/JermaUp.png";
        imageFilePathDown = "/Player_Images/JermaDown.png";
        imageFilePathLeft = "/Player_Images/JermaLeft.png";
        imageFilePathRight = "/Player_Images/JermaRight.png";
        this.health = 10;
        healthMax = health;
        this.eSpeed = 20;
        game = g;
        key = k;
        this.setSprite(imageFilePathUp);
        this.type = "Player";
        this.moveHPBars();
    }

    public int playerMove(){

        if(key.upKey == true){
            movement = 87;
        }
        if(key.downKey == true){
            movement = 83;
        }
        if(key.leftKey == true){
            movement = 65;
        }
        if(key.rightKey == true){
            movement = 68;
        } 
        getPlayerImage(movement);
        if(!collides){
            return this.eSpeed;
        }
        return 0;






    }

    public void getPlayerImage(int direction){
        try{
            // System.out.println(direction);
            switch(direction){
                case 87: // W
                    sprite = ImageIO.read(getClass().getResourceAsStream(imageFilePathUp));
                    break;
                case 83: //S
                    sprite = ImageIO.read(getClass().getResourceAsStream(imageFilePathDown));
                    break;
                 case 65: // A
                    sprite = ImageIO.read(getClass().getResourceAsStream(imageFilePathLeft));
                    break;
                case 68: //D
                    sprite = ImageIO.read(getClass().getResourceAsStream(imageFilePathRight));
                    break;
            }

        }catch(IOException e){

            e.getStackTrace();

        }
    }
}
