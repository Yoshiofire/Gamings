import java.awt.Graphics2D;
import java.io.IOException;
// import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
// import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;

public class PlayerData extends Entity{


    private Game game;
    public KeyHandler key;
    // public BufferedImage sprite; // this one for the "character frames" ig
    public String imageFilePathUp, imageFilePathDown, imageFilePathLeft, imageFilePathRight;
    public int exp;
    public int levelUpEXP;
    public Rectangle expBar;

    // public PlayerData(KeyHandler k, Game g){
    //     super();
    //     game = g;
    //     key = k;
    //     this.setSprite("player_images/JermaUp.png");
    //     this.type = "player";
    // }

    // public PlayerData(KeyHandler k, Game g, int startingX, int startingY, int playerS){
    //     super(startingX, startingY, playerS);
    //     game = g;
    //     key = k;
    //     this.setSprite("player_images/JermaUp.png");
    //     this.type = "player";
    // }

    public PlayerData(KeyHandler k, Game g, int startingX, int startingY, int playerS, int sizeX, int sizeY){
        super(startingX, startingY, playerS, sizeX, sizeY);
        imageFilePathUp = "player_images/JermaUp.png";
        imageFilePathDown = "player_images/JermaDown.png";
        imageFilePathLeft = "player_images/JermaLeft.png";
        imageFilePathRight = "player_images/JermaRight.png";
        this.health = 100;
        healthMax = health;
        this.eSpeed = 20;
        this.iFrameTime = 1;
        exp = 0;
        levelUpEXP = 100;


        game = g;
        key = k;
        this.setSprite(imageFilePathUp);
        this.type = "Player";
        expBar = new Rectangle(0,0,0,(int) greenHPBar.getHeight()/2);
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

    public void moveHPBars(){
        super.moveHPBars();
        System.out.println("didwork");
        expBar.x = greenHPBar.x;
        expBar.y = greenHPBar.y - 10;
    }

    public void gainEXP(int expGained){
        exp += expGained;
        if(exp >= levelUpEXP){
            exp = 0;
            // new Sword(new int[] {posX, posX + sizeX+400, posX + sizeX+400, posX}, new int[] {posY+30, posY+30, posY + sizeY-30, posY +sizeY-30} );
            game.gameState = game.levelUpState;
        }
        expBar.width = (int) (hitbox.getWidth() * (1.0 *exp/levelUpEXP));
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

    public void draw(Graphics2D g3){
        super.draw(g3);
        if(expBar.width != 0){
            g3.setColor(Color.CYAN);
            g3.fill(this.expBar);
            g3.draw(this.expBar);
        }
    }
}
