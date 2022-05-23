// import java.awt.Graphics2D;
import java.io.IOException;
// import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
// import java.awt.Image;

public class PlayerData extends Entity{


    private Game game;
    private KeyHandler key;
    // public BufferedImage sprite; // this one for the "character frames" ig

    public PlayerData(KeyHandler k, Game g){
        super();
        game = g;
        key = k;
        this.setSprite("/Player_Images/JermaUp.png");
    }

    public PlayerData(KeyHandler k, Game g, int startingX, int startingY, int playerS){
        super(startingX, startingY, playerS);
        game = g;
        key = k;
        this.setSprite("/Player_Images/JermaUp.png");
    }

    public PlayerData(KeyHandler k, Game g, int startingX, int startingY, int playerS, int sizeX, int sizeY){
        super(startingX, startingY, playerS, sizeX, sizeY);
        game = g;
        key = k;
        this.setSprite("/Player_Images/JermaUp.png");
    }

    public void playerMove(){



        //Instead of checking for collison in the game.java, we check inside of the move methods of the entites' subclasses
        if(!collides){
            if(key.upKey == true){
                // getPlayerImage("up");
                posY -= eSpeed;
                movement = 87;
            }
            if(key.downKey == true){
                // getPlayerImage("down");
                posY += eSpeed;
                movement = 83;
            }
            if(key.leftKey == true){
                // getPlayerImage("left");
                posX -= eSpeed;
                movement = 65;
            }
            if(key.rightKey == true){
                // getPlayerImage("right");
                posX += eSpeed;
                movement = 68;
            }
        }
        else if(collides){
            if(key.upKey == true){
                // getPlayerImage("up");
                movement = 87;
            }
            if(key.downKey == true){
                // getPlayerImage("down");
                movement = 83;
            }
            if(key.leftKey == true){
                // getPlayerImage("left");
                movement = 65;
            }
            if(key.rightKey == true){
                // getPlayerImage("right");
                movement = 68;
            } 







        }




        getPlayerImage(key.lastPressed);
        hitbox.setLocation(posX, posY);
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
