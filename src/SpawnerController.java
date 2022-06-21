public class SpawnerController extends Thread{

    volatile Game currentGame;
    volatile boolean canRun;
    volatile PlayerData currentPlayer;
    KeyHandler keyChecker;
    Thread spawnerControllerThread;

    

    public SpawnerController(Game game, PlayerData player, KeyHandler key){
        currentGame = game;
        currentPlayer = player;
        keyChecker = key;
        canRun = true;
    }

    public void startSpawnerControllerThread(){
        spawnerControllerThread = new Thread(this);
        spawnerControllerThread.start();
    }


    @Override
    public void run(){
        while(currentGame.gameThread != null && spawnerControllerThread != null){

            if(canRun && currentGame.gameState == currentGame.playState){//So its doubled checked, because you need somethings to be idiot proof.
                //START OF DYNAMIC CHALLENGE THING
                if(Game.frameCount % (Game.FPS * 5) == 0 && Game.frameCount != 0){
                    currentPlayer.healHP(5);
                }
                if(Game.frameCount % (Game.FPS * 30) == 0 && Game.frameCount != 0){ //Every 3 minutes we decrease the spawning cooldown of everything by 1?
                for(int x = Spawner.spawnerList.size()-1; x >= 0; x--){//What happens is that the new spawner will be at the default cooldown.
                   Spawner currentSpawner = Spawner.spawnerList.get(x);
                   currentSpawner.setSpawnerCooldown(currentSpawner.getSpawnerCooldown()-1);
                   System.out.println(currentSpawner.getSpawnerCooldown());
                }
                Entity.contactDMGStatAddition += 4;
                }


              if(Game.frameCount % (Game.FPS * 60) == 0 && Game.frameCount != 0){ //Every 3 minutes we add another spawner?
                // if(Spawner.spawnerList.size() < 15){//Because we only have one type of enemy, I think setting the limit to 7 spawners is good enough for now.
                //     new Spawner(currentGame.peopleSpawner.entitySpawnedData, currentPlayer, 1); //1 being people/ basic enemy
                // }
                Entity.healthStatAddition += 30;
                Entity.expWorthStatAddition += 2;
            }

              if(Game.frameCount % (Game.FPS * 120) == 0 && Game.frameCount != 0){
                Entity.eSpeedStatAddition += 2;
            }

            // if(Game.frameCount % (Game.FPS * 60) == 0 && Game.frameCount != 0){ //Every 3 minutes we add another spawner?
            //     if(Spawner.spawnerList.size() < 15){//Because we only have one type of enemy, I think setting the limit to 7 spawners is good enough for now.
            //         new Spawner(currentGame.peopleSpawner.entitySpawnedData, currentPlayer, 1); //1 being people/ basic enemy
            //     }
            //   }
              
            //   if(Game.frameCount % (Game.FPS * 120) == 0 && Game.frameCount != 0){
            //     if(Spawner.spawnerList.size() < 15){//Because we only have one type of enemy, I think setting the limit to 7 spawners is good enough for now.
            //       new Spawner(currentGame.peopleSpawner2.entitySpawnedData, currentPlayer, 2); //1 being people/ basic enemy
            //     }
            //   }
                
                  
                //END OF DYNAMIC CHALLENGE THING

                for(int x = Spawner.spawnerList.size()-1; x >= 0; x--){
                    if(People.peopleList.size() + Spawner.spawnerList.size() < 1000){
                        Spawner currentSpawner = Spawner.spawnerList.get(x);
                        currentSpawner.basicSpawnPeople();
                    }
                }


                canRun = false;
            }
        }

    }
    
}
