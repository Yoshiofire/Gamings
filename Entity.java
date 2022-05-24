// import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
// import java.awt.Graphics;
// import java.util.ArrayList;



public class Entity{

    public int posX;
    public int posY;
    public int eSpeed;
    public int sizeX;
    public int sizeY;
    public BufferedImage sprite;
    public Rectangle hitbox;
    public boolean collides;
    public int movement;
    public boolean moved;
    public String type;

    // public String direction; 

    public Entity(){
        posX = 100;
        posY = 100;
        eSpeed = 10;
        sizeX = 200;
        sizeY = 200;
        hitbox = new Rectangle(posX-1, posY-1, sizeX-1, sizeY-1);
        collides = false;
        //FILE PATH TO DEFUALT SPRITE TEXTURE
        //WHAT NEEDS TO BE ADDED IS DEFAULT HITBOX
        try{

            sprite = ImageIO.read(getClass().getResourceAsStream("/download.jpg"));

        }catch(IOException e){

            e.getStackTrace();

        }

    }


    public Entity(String filePath){
        this();
        try{

            sprite = ImageIO.read(getClass().getResourceAsStream(filePath));

        }catch(IOException e){

            e.getStackTrace();

        }

    }





    public Entity(int x, int y, int speed){
        this();
        posX = x;
        posY = y;
        eSpeed = speed;
        hitbox.setLocation(posX, posY);
    }

    public Entity(int x, int y, int speed, int sizeX, int sizeY){
        this();
        posX = x;
        posY = y;
        eSpeed = speed;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        hitbox.setBounds(posX, posY, sizeX, sizeY);
    }

    //DEFUNCT
    // public void draw(Graphics2D g3, BufferedImage i){

    //     // g3.setColor(Color.red);

    //     // g3.fillRect(Posx, Posy, 10, 10); //location x, location y, size x, size y

    //     g3.drawImage(i, posX, posY, sizeX, sizeY, null);



    // }

        public void setSprite(String filePath){

            try{

                sprite = ImageIO.read(getClass().getResourceAsStream(filePath));
    
            }catch(IOException e){
    
                e.getStackTrace();
    
            }


        }



        public String numToStringDirection(int num){
            String temp = "";
            switch(num){
                case 87:
                    temp = "up";
                    break;
                case 83:
                    temp = "down";
                    break;
                case 65:
                    temp = "left";
                    break;
                case 68:
                    temp = "right";
                    break;
            }
            return temp;


        }
        public void playerInfluencedMovement(int playerSpeed, KeyHandler key){
            if(!this.collides){// doesn't really matetr because in PlayerData if it collides it returns a speed of 0;
                if(key.upKey){
                    posY += playerSpeed;
                }
                if(key.downKey){
                    posY -= playerSpeed;
                }
                if(key.rightKey){
                    posX -= playerSpeed;
                }
                if(key.leftKey){
                    posX += playerSpeed;
                }
                hitbox.setLocation(posX, posY);
            }
        }








    public void draw(Graphics2D g3){

        BufferedImage i = this.sprite;
        g3.drawImage(i, posX, posY, sizeX, sizeY, null);
        g3.draw(this.hitbox);

    }
    
    public void drawHitboxes(Graphics2D g3){

        // BufferedImage i = this.sprite;
        // g3.drawImage(i, posX, posY, sizeX, sizeY, null);
        g3.draw(this.hitbox);

    }

    // public int getX() { return x};
    // public int getY() { return y};
    // public int getSpeed() { return speed};





}