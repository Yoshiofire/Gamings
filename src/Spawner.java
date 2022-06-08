import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics2D;


public class Spawner {
    private Rectangle spawningArea;
    private Rectangle spawningHitbox;
    private Rectangle dontSpawnHitbox;
    public Entity entitySpawnedData;
    private int nextSpawn = -1;
    private int spawningCooldown;
    public static ArrayList <Spawner> spawnerList = new ArrayList<>();



    public Spawner(Entity entity, PlayerData player){
        
        spawningArea = new Rectangle
            (
            (entity.hitbox.width + 30),
            (entity.hitbox.height + 30),
            (Game.leftBounds - entity.hitbox.width*2 - 180),
            (Game.leftBounds - entity.hitbox.height*2 - 180)
            );

        spawningHitbox = new Rectangle
            (
            (entity.hitbox.x),
            (entity.hitbox.y),
            (entity.hitbox.width),
            (entity.hitbox.height)
            );

        entitySpawnedData = entity;

        dontSpawnHitbox = new Rectangle
            (
            (player.hitbox.x - 120),
            (player.hitbox.y - 120),
            (player.hitbox.width + 240),
            (player.hitbox.height + 240)
            );
        spawningCooldown = 1; //<-- in seconds.
        spawnerList.add(this);

        
    }
    //Entities playerInfluencedMovement, but only for the spawning Area, because.

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
                    new People(entitySpawnedData.defaultFilePath, (int) spawningHitbox.getMinX(), (int) spawningHitbox.getMinY());
                    spawned = true;
                    // for(People peoples: People.peopleList){
                    //     People.peopleList.remove(peoples);
                    //     People.peopleList.add(peoples);
                    // }
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
        g4.draw(spawningArea);
        g4.draw(dontSpawnHitbox);


    }

}
