import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Graphics2D;


public class Spawner {
    private Rectangle spawningArea;
    private Rectangle spawningHitbox;
    private Rectangle dontSpawnHitbox;
    private Entity entitySpawnedData;
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

        // spawningHitbox = new Rectangle
        //     (
        //     (entity.hitbox.x),
        //     (entity.hitbox.y),
        //     (entity.hitbox.width),
        //     (entity.hitbox.height)
        //     );
        spawningHitbox = entity.hitbox;

        entitySpawnedData = entity;

        dontSpawnHitbox = new Rectangle
            (
            (player.hitbox.x - 90),
            (player.hitbox.y - 90),
            (player.hitbox.width + 180),
            (player.hitbox.height + 180)
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
            nextSpawn += spawningCooldown;
            boolean spawned = false;
            int tries = 0;
            while(!spawned){
                int randomWidth = (int) ((Math.random() * (spawningArea.getMaxX()-spawningArea.getMinX())) + spawningArea.getMinX());
                int randomHeight = (int) ((Math.random() * (spawningArea.getMaxY()-spawningArea.getMinY())) + spawningArea.getMinY());
                spawningHitbox.setLocation(randomWidth, randomHeight);
                for(int x = 0; x < People.peopleList.size(); x++){
                    if(!(spawningHitbox.intersects(People.peopleList.get(x).hitbox.getBounds())) && !(spawningHitbox.intersects(dontSpawnHitbox))){
                        new People(entitySpawnedData.defaultFilePath, spawningHitbox.x, spawningHitbox.y);
                        spawned = true;
                        break;
                    }
                }
                tries++;
                if(tries >= 2){ //meaning that if it still hasn't gotten a spawn in after 20 tries it'll jsut pass, so it doesn't take anymore time.
                    spawned = true;
                }
            }// Entity.entityList
        }// People.peopleList



    }

    public void drawAllSpawnerHitboxes(Graphics2D g4){
        g4.draw(spawningArea);
        g4.draw(dontSpawnHitbox);
        g4.draw(spawningArea);


    }

}
