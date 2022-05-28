// import java.awt.Graphics2D;
import java.io.IOException;
// import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
// import java.awt.Image;

public class PlayerData extends Entity{


    private Game game;
    public KeyHandler key;
    // public BufferedImage sprite; // this one for the "character frames" ig

    public PlayerData(KeyHandler k, Game g){
        super();
        game = g;
        key = k;
        this.setSprite("/Player_Images/JermaUp.png");
        this.type = "player";
    }

    public PlayerData(KeyHandler k, Game g, int startingX, int startingY, int playerS){
        super(startingX, startingY, playerS);
        game = g;
        key = k;
        this.setSprite("/Player_Images/JermaUp.png");
        this.type = "player";
    }

    public PlayerData(KeyHandler k, Game g, int startingX, int startingY, int playerS, int sizeX, int sizeY){
        super(startingX, startingY, playerS, sizeX, sizeY);
        game = g;
        key = k;
        this.setSprite("/Player_Images/JermaUp.png");
        this.type = "player";
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
        // hitbox.setLocation(posX, posY);
        if(!collides){
            //turn into invincibility frames? When it collides,
            return this.eSpeed;
        }
        return 0;






    }

    public void getPlayerImage(int direction){
        try{
            // System.out.println(direction);
            switch(direction){
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
            }

        }catch(IOException e){

            e.getStackTrace();

        }
    }



    // DRAW METHOD REWRITTEN, ALWAYS ON THE BOTTOM OR ELSE MY EYES WILL HURT LOOKING FOR IT
    // public void draw(Graphics2D g1){
    //     super.draw(g1, sprite);
    // }




}
