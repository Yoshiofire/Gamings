
public class CollisionDetect{ // this is going t

    Game game;

    public CollisionDetect(Game g){

        game = g;

    }

    public void checkObj(Entity entity, Entity entity2){ // needs to be turned into an arraylist ig
        //First entity needs to be stationary second is the moving one
        // System.out.println(entity.movement);


        //So now what we have to do is depending on what way the player is moving we check that direction, then we move the hitbox closer just to check if they would collide then
        //set espeed to 0, reset that during the update loop then whatever ig
            // entity.collides = false;
            entity2.collides = false;
            System.out.println(entity2.movement);
                switch(entity2.movement){
                    case 87: //up
                        entity2.hitbox.y -= entity2.eSpeed;
                        if(entity2.hitbox.intersects(entity.hitbox)){
                            // entity.posY -= entity.eSpeed;
                            // entity.collides = true;
                            entity2.collides = true;
                        }
                        entity2.hitbox.y += entity2.eSpeed;
                        break;
                    case 83: //down
                        entity2.hitbox.y += entity2.eSpeed;
                        if(entity2.hitbox.intersects(entity.hitbox)){
                            // entity.posY += entity.eSpeed;
                            // entity.collides = true;
                            entity2.collides = true;
                        }
                        entity2.hitbox.y -= entity2.eSpeed;
                        break;
                    case 65: //left
                        entity2.hitbox.x -= entity2.eSpeed;
                        if(entity2.hitbox.intersects(entity.hitbox)){
                            // entity.posX -= entity.eSpeed;
                            // entity.collides = true;
                            entity2.collides = true;
                        }
                        entity2.hitbox.x += entity2.eSpeed;
                        break;
                    case 68: //right
                        entity2.hitbox.x += entity2.eSpeed;
                        if(entity2.hitbox.intersects(entity.hitbox)){
                            // entity.posX += entity.eSpeed;
                            // entity.collides = true;
                            entity2.collides = true;
                        }
                        entity2.hitbox.x -= entity2.eSpeed;
                        break;
            }
            
        }
        
         
    }//We want to check the parameter rather than the area of the hit box?

