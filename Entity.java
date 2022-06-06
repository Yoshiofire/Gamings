// import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
// import java.awt.Graphics;
// import java.util.ArrayList;
import java.awt.Color;



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
    // public int startIFrame;
    public int endIFrame;
    public boolean isDead;
    public String defaultFilePath;
    public static ArrayList <Entity> entityList = new ArrayList<>();


    //Below needs implimentation in the constructors
    public String type;
    public int health;
    public boolean iFrame;
    public int iFrameTime;
    public int contactDMG;
    public int movementCooldown;


    // public String direction; 

    public Entity(){
        posX = 100;
        posY = 100;
        sizeX = 200;
        sizeY = 200;
        hitbox = new Rectangle(posX, posY, sizeX, sizeY);
        collides = false;
        isDead = false;
        
        iFrame = false; // <-
        iFrameTime = 1; // <-

        eSpeed = 10;
        movementCooldown = 6; // <-
        health = 10; // <-
        contactDMG = 1;
        


        //FILE PATH TO DEFUALT SPRITE TEXTURE
        //WHAT NEEDS TO BE ADDED IS DEFAULT HITBOX
        try{
            defaultFilePath = "/download.jpg";
            sprite = ImageIO.read(getClass().getResourceAsStream(defaultFilePath));

        }catch(IOException e){

            e.getStackTrace();

        }
        // entityList.add(this);
    }


    public Entity(String filePath){
        this();
        try{
            defaultFilePath = filePath;
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
            // if(!collides){// doesn't really matetr because in PlayerData if it collides it returns a speed of 0; <--- adding this if statement made OTHER ENTITIES MOVE THROUGH THE INVISIBLE WALL WHY?
            /* I think that this is because of the fact that when it collides it stays sill (the entity), but everything else moves therefore at a certain point it escapes due to the fact that
                it is no longer colliding because everything is moving due to the player??

                Also you APE, How the hell does the collisions against people vs walls work when this if statement is out???????
                IT WORKS WHY?
                */

                //That is because everything SHOULD MOVE if the player is not colliding with anything. All movements are equal
                if(key.upKey){
                    posY += playerSpeed;
                    hitbox.y += playerSpeed;
                }
                if(key.downKey){
                    posY -= playerSpeed;
                    hitbox.y -= playerSpeed;
                }
                if(key.rightKey){
                    posX -= playerSpeed;
                    hitbox.x -= playerSpeed;
                }
                if(key.leftKey){
                    posX += playerSpeed;
                    hitbox.x += playerSpeed;

                }
            // }
        }

        public void entityListRefresh(){
            for(int x = entityList.size()-1; x >= 0 ; x--){
                Entity tempE = entityList.get(x);
                if(tempE == null){
                    entityList.remove(x);
                }
            }

        }

        public int entityPlayerDrivenDirection(PlayerData player){
                double playerPosY = player.hitbox.getCenterY();
                double playerPosX = player.hitbox.getCenterX();
                double entityPosY = this.hitbox.getCenterY();
                double entityPosX = this.hitbox.getCenterX();
                // double entityPosY = this.posY;
                // double entityPosX = this.posX;
                // double playerPosY = player.posY;
                // double playerPosX = player.posX;
                /* the order is Up, Down, Left, Right */
                // give bias
                if((entityPosX < playerPosX)){ //East (Enttiy vs Player)
                    if((entityPosY < playerPosY)){ //South
                        if(Math.abs(entityPosY - playerPosY) > Math.abs(entityPosX - playerPosX)){// If the entity Ypos is greater than entity Xpos
                            movement = 83; //down
                        }
                        else{
                            movement = 68; //left
                        }
                    }
                    else if((entityPosY > playerPosY)){ //North
                        if(Math.abs(entityPosY - playerPosY) > Math.abs(entityPosX - playerPosX)){// If the entity Ypos is greater than entity Xpos
                            movement = 87; //up
                        }
                        else{
                            movement = 68; //left
                        }
                    }
                }
                else if(entityPosX > playerPosX){//West
                    if((entityPosY < playerPosY)){ //South
                        if(Math.abs(entityPosY - playerPosY) > Math.abs(entityPosX - playerPosX)){// If the entity Ypos is greater than entity Xpos
                            movement = 83; //down
                        }
                        else{
                            movement = 65; //right
                        }
                    }
                    else if((entityPosY > playerPosY)){ //North
                        if(Math.abs(entityPosY - playerPosY) > Math.abs(entityPosX - playerPosX)){// If the entity Ypos is greater than entity Xpos
                            movement = 87; //up
                        }
                        else{
                            movement = 65; //right
                        }
                    }
                }
            return movement;
        }

        public int entityRandomDirection(){
            // int direction = (int) (Math.random() * 4); 
            int direction = movement;
            if(Game.frameCount % (Game.FPS/movementCooldown) == 0){ // <- moves around 1/6 times per frame.
                direction = (int) (Math.random() * 4);
            }//Need to add new move method, which is like the checkPlay(), for People direction its based on the location of the player. So the player will always get surrounded.
            
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
            movement = direction;
            return movement;
        }

        public void entityMove(int direction){
            if(!collides){
                switch(direction){
                        case 87: // W 1
                            posY -= eSpeed;
                            hitbox.y -= eSpeed;
                            break;
                        case 83: //S 0
                            posY += eSpeed;
                            hitbox.y += eSpeed;
                            break;
                        case 65: // A 3
                            posX -= eSpeed;
                            hitbox.x -= eSpeed;
                            break;
                        case 68: //D 2
                            posX += eSpeed;
                            hitbox.x += eSpeed;
                            break;
                    }
            }

        }









    public void draw(Graphics2D g3){
        if(this.iFrame){
            g3.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
            g3.drawImage(this.sprite, posX, posY, sizeX, sizeY, null);
            g3.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)); //needs this or else everything is opacity 50%.
        }
        else{
            g3.drawImage(this.sprite, posX, posY, sizeX, sizeY, null);
        }

    }


    
    public void drawHitboxes(Graphics2D g3){
    if(!this.iFrame){
        g3.setColor(Color.RED);
        g3.draw(this.hitbox);
        }
    }

    public void drawWalls(Graphics2D g3){
        g3.setColor(Color.WHITE);
        g3.fill(this.hitbox);
    }






}