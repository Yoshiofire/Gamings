
import java.util.ArrayList;

public class CollisionDetect{ // this is going t

    Game game;

    public CollisionDetect(Game g){

        game = g;

    }

    public void checkEntityCollisionWithoutContactDMG(Entity entity, Entity entity2){
        switch(entity2.movement){
            case 87: //up
                entity2.hitbox.y -= entity2.eSpeed;
                if(entity2.hitbox.intersects(entity.hitbox)){
                    entity2.collides = true;
                }
                entity2.hitbox.y += entity2.eSpeed;

                break;
            case 83: //down
                entity2.hitbox.y += entity2.eSpeed;
                if(entity2.hitbox.intersects(entity.hitbox)){
                    entity2.collides = true;
                }
                entity2.hitbox.y -= entity2.eSpeed;

                break;
            case 65: //left
                entity2.hitbox.x -= entity2.eSpeed;
                if(entity2.hitbox.intersects(entity.hitbox)){
                    entity2.collides = true;
                }
                entity2.hitbox.x += entity2.eSpeed;

                break;
            case 68: //right
                entity2.hitbox.x += entity2.eSpeed;
                if(entity2.hitbox.intersects(entity.hitbox)){
                    entity2.collides = true;
                }
                entity2.hitbox.x -= entity2.eSpeed;
                break;
         }
    }

    public void checkObj(Entity entity, Entity entity2){
        switch(entity2.movement){
            case 87: //up
                entity2.hitbox.y -= entity2.eSpeed;
                if(entity2.hitbox.intersects(entity.hitbox)){
                    // entity.collides = true;
                    entity2.collides = true;
                    checkDMGAgainstEntities(entity, entity2);
                }
                entity2.hitbox.y += entity2.eSpeed;
                break;
            case 83: //down
                entity2.hitbox.y += entity2.eSpeed;
                if(entity2.hitbox.intersects(entity.hitbox)){
                    // entity.collides = true;
                    entity2.collides = true;
                    checkDMGAgainstEntities(entity, entity2);
                }
                entity2.hitbox.y -= entity2.eSpeed;
                break;
            case 65: //left
                entity2.hitbox.x -= entity2.eSpeed;
                if(entity2.hitbox.intersects(entity.hitbox)){
                    // entity.collides = true;
                    entity2.collides = true;
                    checkDMGAgainstEntities(entity, entity2);
                }
                entity2.hitbox.x += entity2.eSpeed;
                break;
            case 68: //right
                entity2.hitbox.x += entity2.eSpeed;
                if(entity2.hitbox.intersects(entity.hitbox)){
                    // entity.collides = true;
                    entity2.collides = true;
                    checkDMGAgainstEntities(entity, entity2);
                }
                entity2.hitbox.x -= entity2.eSpeed;
                break;
            }
            checkIFrame(entity);
            checkIFrame(entity2);

        }

        public void checkWalls(InvisWall wall, Entity entity2){
            // boolean doesCollideWithWall = false;


            switch(entity2.movement){
                case 87: //up
                    entity2.hitbox.y -= entity2.eSpeed;
                    if(entity2.hitbox.intersects(wall.hitbox)){
                        entity2.collides = true;
                        // doesCollideWithWall = true;
                    }
                    entity2.hitbox.y += entity2.eSpeed;

                    break;
                case 83: //down
                    entity2.hitbox.y += entity2.eSpeed;
                    if(entity2.hitbox.intersects(wall.hitbox)){
                        entity2.collides = true;
                        // doesCollideWithWall = true;

                    }
                    entity2.hitbox.y -= entity2.eSpeed;

                    break;
                case 65: //left
                    entity2.hitbox.x -= entity2.eSpeed;
                    if(entity2.hitbox.intersects(wall.hitbox)){
                        entity2.collides = true;


                        // doesCollideWithWall = true;

                    }
                    entity2.hitbox.x += entity2.eSpeed;

                    break;
                case 68: //right
                    entity2.hitbox.x += entity2.eSpeed;
                    if(entity2.hitbox.intersects(wall.hitbox)){
                        entity2.collides = true;

                        // doesCollideWithWall = true;

                    }
                    entity2.hitbox.x -= entity2.eSpeed;
                    break;
                }
                //IDEA
                // if(doesCollideWithWall){
                //     if(!entity2.type.equals("Player")){
                //         switch(wall.type){
                //             case "Top_Wall": //we want it to go to the bottom
                //                 System.out.println("Top_Wall");
                //                 entity2.hitbox.y = (int) (game.bottom.hitbox.y - (entity2.hitbox.getHeight()));
                //                 break;//replace game.leftbounds
                //         }
                //     }
                //     else if(entity2.type.equals("Player")){
                //         switch(wall.type){
                //             case "Top_Wall": //we want it to go to the bottom
                //                 System.out.println("Top_Wall");
                //                 for(int x = 0; x < Entity.entityList.size(); x++){
                //                     Entity currentEntity = Entity.entityList.get(x);
                //                     if(currentEntity != entity2){
                //                         currentEntity.hitbox.y = (int) (game.bottom.hitbox.y - (entity2.hitbox.getHeight()));

                //                     }
                //                 }
                //                 break;//replace game.leftbounds
                //         }
                //     }

                // }
            }


            /*
            OKAY YOU APE. YOU GOT THE RIGHT IDEA BY NOT DOING A SORT ITTERATION, WHERE THE INNER LOOP IS LIKE FOR(INT Y = X-1...) AND STUFF.
            BUT YOU DONT DO A IF(X != y)... ACTUAL PRIMATE BEHAVIOR.
            so in the end I fixed it but I am the most retarded human being to ever be concieved on this planet. 
            */
        public void checkPeopleVSPeople(ArrayList<People> peoplesList, People people){
                for(int x = 0; x < peoplesList.size(); x++){
                    People currentPeople = peoplesList.get(x);
                    if(people != currentPeople){
                        checkEntityCollisionWithoutContactDMG(currentPeople, people);
                    }
                    //using this is an easy solution as it doesn't move anymore and will move if the player intervenes
                    // if(people != currentPeople && people.hitbox.intersects(currentPeople.hitbox)){
                    //     people.collides = true; 
                    //     break;
                    }
                }



            // }



        public void checkPlay(Entity entity, PlayerData player){

            switch(player.movement){
                case 87: //up
                    player.hitbox.y -= player.eSpeed;
                    if(player.hitbox.intersects(entity.hitbox)){
                        player.collides = true;
                    }
                    player.hitbox.y += player.eSpeed;

                    break;
                case 83: //down
                    player.hitbox.y += player.eSpeed;
                    if(player.hitbox.intersects(entity.hitbox)){
                        player.collides = true;
                    }
                    player.hitbox.y -= player.eSpeed;

                    break;
                case 65: //left
                    player.hitbox.x -= player.eSpeed;
                    if(player.hitbox.intersects(entity.hitbox)){
                        player.collides = true;
                    }
                    player.hitbox.x += player.eSpeed;

                    break;
                case 68: //right
                    player.hitbox.x += player.eSpeed;
                    if(player.hitbox.intersects(entity.hitbox)){
                        player.collides = true;
                    }
                    player.hitbox.x -= player.eSpeed;
                    break;
                }

                //And checks whatever
                if(player.key.upKey == true){
                    player.hitbox.y -= player.eSpeed;
                    if(player.hitbox.intersects(entity.hitbox)){
                        player.collides = true;
                        checkDMGAgainstEntities(entity, player);
                    }
                    player.hitbox.y += player.eSpeed;
                }
    
                if(player.key.downKey == true){
                    player.hitbox.y += player.eSpeed;
                    if(player.hitbox.intersects(entity.hitbox)){
                        player.collides = true;
                        checkDMGAgainstEntities(entity, player);
                    }
                    player.hitbox.y -= player.eSpeed;
                }
    
                if(player.key.leftKey == true ){
                    player.hitbox.x -= player.eSpeed;
                    if(player.hitbox.intersects(entity.hitbox)){
                        player.collides = true;
                        checkDMGAgainstEntities(entity, player);
                    }
                    player.hitbox.x += player.eSpeed;
                }
    
                if(player.key.rightKey == true ){
                    player.hitbox.x += player.eSpeed;
                    if(player.hitbox.intersects(entity.hitbox)){
                        player.collides = true;
                        checkDMGAgainstEntities(entity, player);
                    }
                    player.hitbox.x -= player.eSpeed;
                } 
                checkIFrame(entity);
                checkIFrame(player);
        }

        public void checkPlayWall(InvisWall wall, PlayerData player){
            if(player.key.upKey == true){
                player.hitbox.y -= player.eSpeed;
                if(player.hitbox.intersects(wall.hitbox)){
                    player.collides = true;
                }
                player.hitbox.y += player.eSpeed;
            }

            if(player.key.downKey == true){
                player.hitbox.y += player.eSpeed;
                if(player.hitbox.intersects(wall.hitbox)){
                    player.collides = true;
                }
                player.hitbox.y -= player.eSpeed;
            }

            if(player.key.leftKey == true ){
                player.hitbox.x -= player.eSpeed;
                if(player.hitbox.intersects(wall.hitbox)){
                    player.collides = true;
                }
                player.hitbox.x += player.eSpeed;
            }

            if(player.key.rightKey == true ){
                player.hitbox.x += player.eSpeed;
                if(player.hitbox.intersects(wall.hitbox)){
                    player.collides = true;
                }
                player.hitbox.x -= player.eSpeed;
            } 


            if(player.movement == 87){
                player.hitbox.y -= player.eSpeed;
                if(player.hitbox.intersects(wall.hitbox)){
                    player.collides = true;
                }
                player.hitbox.y += player.eSpeed;
            }
            if(player.movement == 83){
                player.hitbox.y += player.eSpeed;
                if(player.hitbox.intersects(wall.hitbox)){
                    player.collides = true;
                }
                player.hitbox.y -= player.eSpeed;
            }
            if(player.movement == 65){
                player.hitbox.x -= player.eSpeed;
                if(player.hitbox.intersects(wall.hitbox)){
                    player.collides = true;
                }
                player.hitbox.x += player.eSpeed;
            }
            if(player.movement == 68){
                player.hitbox.x += player.eSpeed;
                if(player.hitbox.intersects(wall.hitbox)){
                    player.collides = true;
                }
                player.hitbox.x -= player.eSpeed;
            }

        }

        public boolean checkItem(Entity entity, Item item){
            if(!entity.iFrame){
                if(item.animationHitbox != null && item.animationHitbox.intersects(entity.hitbox.getBounds2D())){
                    entity.takeDMG(item.dmg);
                    // entity.healHP(item.dmg);

                    if(entity.health <= 0){
                        entity.isDead = true;
                        return true;// <- outside the method it has to have a remove from list thing.
                    }
                    entity.endIFrame = Game.frameCount + (entity.iFrameTime * Game.FPS);
                    entity.iFrame = true;
                }
            }
            checkIFrame(entity);
            return false;
        }

        public void checkIFrame(Entity entity){
            if(Game.frameCount >= entity.endIFrame){
                entity.iFrame = false;
            }
        }


        public void checkDMGAgainstEntities(Entity entity, Entity entity2){
            if((entity2.collides)){
                if(!entity.iFrame && !entity2.iFrame){
                    
                    entity2.takeDMG(entity.contactDMG);
                    entity.takeDMG(entity2.contactDMG);
    
                    if(entity.health <= 0){
                        entity.isDead = true;
                        // entity = null;
                    }
                    else{
                        // System.out.println("Entity 1: " + entity);
                        entity.endIFrame = Game.frameCount + (entity.iFrameTime * Game.FPS);
                        entity.iFrame = true;
                    }
    
                    if(entity2.health <= 0){
                        entity2.isDead = true;
                        // entity2 = null;

                    }
                    else{
                        // System.out.println("Entity 2: " + entity2);
                        entity2.endIFrame = Game.frameCount + (entity2.iFrameTime * Game.FPS);
                        entity2.iFrame = true;
                    }
                }
            }
            checkIFrame(entity);
            checkIFrame(entity2);
        }




        
         
    }

