
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



            // int originalEntity2HitboxX = entity2.hitbox.x;
            // int originalEntity2HitboxY = entity2.hitbox.y;

                switch(entity2.movement){
                    case 87: //up
                        entity2.hitbox.y -= entity2.eSpeed;
                        if(entity2.hitbox.intersects(entity.hitbox)){
                            // entity.collides = true;
                            entity2.collides = true;
                        }
                        entity2.hitbox.y += entity2.eSpeed;
                        break;
                    case 83: //down
                        entity2.hitbox.y += entity2.eSpeed;
                        if(entity2.hitbox.intersects(entity.hitbox)){
                            // entity.collides = true;
                            entity2.collides = true;
                        }
                        entity2.hitbox.y -= entity2.eSpeed;
                        break;
                    case 65: //left
                        entity2.hitbox.x -= entity2.eSpeed;
                        if(entity2.hitbox.intersects(entity.hitbox)){
                            // entity.collides = true;
                            entity2.collides = true;
                        }
                        entity2.hitbox.x += entity2.eSpeed;
                        break;
                    case 68: //right
                        entity2.hitbox.x += entity2.eSpeed;
                        if(entity2.hitbox.intersects(entity.hitbox)){
                            // entity.collides = true;
                            entity2.collides = true;
                        }
                        entity2.hitbox.x -= entity2.eSpeed;
                        break;
                    // default:
                        // if(entity2.hitbox.intersects(entity.hitbox)){
                        //     entity2.collides = true;
                        // }
                        // break;
            }
            // entity2.hitbox.setLocation(originalEntity2HitboxX, originalEntity2HitboxY);
        }
        public void checkPlay(Entity entity, PlayerData player){

            int originalPlayerHitboxX = player.hitbox.x;
            int originalPlayerHitboxY = player.hitbox.y;

            if(player.key.upKey == true){
                player.hitbox.y -= player.eSpeed;
                if(player.hitbox.intersects(entity.hitbox)){
                    player.collides = true;
                    // entity.collides = true;
                }
                player.hitbox.y += player.eSpeed;
            }
            if(player.key.downKey == true){
                player.hitbox.y += player.eSpeed;
                if(player.hitbox.intersects(entity.hitbox)){
                    player.collides = true;
                    // entity.collides = true;
                }
                player.hitbox.y -= player.eSpeed;
            }
            if(player.key.leftKey == true ){
                player.hitbox.x -= player.eSpeed;
                if(player.hitbox.intersects(entity.hitbox)){
                    player.collides = true;
                    // entity.collides = true;
                }
                player.hitbox.x += player.eSpeed;
            }
            if(player.key.rightKey == true ){
                player.hitbox.x += player.eSpeed;
                if(player.hitbox.intersects(entity.hitbox)){
                    player.collides = true;
                    // entity.collides = true;
                }
                player.hitbox.x -= player.eSpeed;
            } 
            player.hitbox.setLocation(originalPlayerHitboxX, originalPlayerHitboxY);

        }
        public boolean checkItem(Entity entity, Item item){
            if(item.animationHitbox != null && item.animationHitbox.intersects(entity.hitbox.getBounds2D())){
                return true;
            }
            else{
                return false;
            }
        }
        
         
    }//We want to check the parameter rather than the area of the hit box?

