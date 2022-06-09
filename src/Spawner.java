import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D;


public class Spawner {
    public Rectangle spawningArea;
    private Rectangle spawningHitbox;
    private Rectangle dontSpawnHitbox;
    public Entity entitySpawnedData;// WE DONT EVEN USE THE ENTITY, WE ONLY USE THE FILE PATH THING FOR IT <<<<<<<<<<<<<
    private int nextSpawn = -1;
    private int spawningCooldown;
    private int type;
    public static ArrayList <Spawner> spawnerList = new ArrayList<>();




    public Spawner(Entity entity, PlayerData player, int type){

        this.type = type;

        
        spawningArea = new Rectangle
        (
        (entity.hitbox.width + 30 + (int) InvisWall.wallList.get(0).hitbox.getX()),
        (entity.hitbox.height + 30 + (int) InvisWall.wallList.get(0).hitbox.getY()),
        (Game.leftBounds - entity.hitbox.width*4),
        (Game.leftBounds - entity.hitbox.height*4)
        );


        spawningHitbox = new Rectangle
            (
            ((int) InvisWall.wallList.get(0).hitbox.getX()),
            ((int) InvisWall.wallList.get(0).hitbox.getX()),
            (entity.hitbox.width),
            (entity.hitbox.height)
            );

        entitySpawnedData = new Entity
        (
        (int) entity.hitbox.getX(),
        (int) entity.hitbox.getY(),
        entity.eSpeed,
        (int) entity.hitbox.getWidth(),
        (int) entity.hitbox.getHeight()
        );
        entitySpawnedData.setSprite(entity.defaultFilePath);



        dontSpawnHitbox = new Rectangle
            (
            (player.hitbox.x - 120),
            (player.hitbox.y - 120),
            (player.hitbox.width + 240),
            (player.hitbox.height + 240)
            );
            spawningCooldown = -1;
        switch(type){
            case 1:
                spawningCooldown = 3; //<-- in seconds.
                break;
            case 2:
                spawningCooldown = 20;//<-- in seconds.
                break;
        }
        spawnerList.add(this);

        
    }
    //Entities playerInfluencedMovement, but only for the spawning Area, because.

    public void setSpawnerCooldown(int newCooldownTime){
        if(newCooldownTime <= 0){
            newCooldownTime = 1; //It can't go beyond 0, because it it goes to like -1 or 0 the funny happens.
        }
        spawningCooldown = newCooldownTime;
    }
    public int getSpawnerCooldown(){
        return spawningCooldown;
    }

    public void independentSpawnerMovement(int playerSpeed, KeyHandler key){
            if(key.upKey){
                spawningArea.y += playerSpeed;
            }
            if(key.downKey){
                spawningArea.y -= playerSpeed;
            }
            if(key.rightKey){
                spawningArea.x -= playerSpeed;
            }
            if(key.leftKey){
                spawningArea.x += playerSpeed;
            }
    }



    public void basicSpawnPeople(){
        if(Game.seconds >= nextSpawn){
            nextSpawn = Game.seconds + spawningCooldown;
            boolean spawned = false;
            int tries = 0;
            while(!spawned){
                // System.out.println("Tries: " + tries + "\n" + this.toString());
                // System.out.println();
                boolean spawnedCheck = true;
                int randomWidth = (int) ((Math.random() * (spawningArea.getMaxX()-spawningArea.getMinX())) + spawningArea.getMinX());
                int randomHeight = (int) ((Math.random() * (spawningArea.getMaxY()-spawningArea.getMinY())) + spawningArea.getMinY());
                spawningHitbox.x = randomWidth;
                spawningHitbox.y = randomHeight;
                // System.out.println("X-Coord: " + randomWidth);
                // System.out.println("Y-Coord: " + randomHeight);
                // System.out.println("HitboxX-Coord: " + spawningHitbox.x);
                // System.out.println("HitboxY-Coord: " + spawningHitbox.y);


                for(int x = People.peopleList.size()-1; x >= 0 ;x-- ){
                    // System.out.println(x);
                    if(spawningHitbox.intersects(People.peopleList.get(x).hitbox)){
                        spawnedCheck = false;
                        // System.out.println("SpawnCheck = false");

                    }
                }

                if(spawnedCheck && !(spawningHitbox.intersects(dontSpawnHitbox))){
                    People tempPeople = new People(entitySpawnedData.defaultFilePath, (int) spawningHitbox.getMinX(), (int) spawningHitbox.getMinY(), type);
                    tempPeople.addStatChanges();
                    

                    tempPeople.setSprite(entitySpawnedData.defaultFilePath);
                    // System.out.println("Spawning: " + type);
                    // System.out.println(tempPeople.iFrameTime);
                    // System.out.println(entitySpawnedData.iFrameTime);
                    spawned = true;
                    break;
                }

                tries++;
                if(tries >= 2){ //meaning that if it still hasn't gotten a spawn in after 20 tries it'll jsut pass, so it doesn't take anymore time.
                    spawned = true;
                }
            }
            }// Entity.entityList
        }// People.peopleList




    public void drawAllSpawnerHitboxes(Graphics2D g4){
        g4.setColor(Color.BLUE);
        g4.fill(spawningArea);
        g4.draw(spawningArea);
        g4.draw(dontSpawnHitbox);


    }

}
