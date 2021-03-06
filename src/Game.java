import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
// import java.awt.AlphaComposite;

public class Game extends JPanel implements Runnable{
  // will i need to setup a whole tile system? If so what is a good size?
  

  //Windows Height, probably won't be changable.
  public static final int screenWidth = 1366; // if we do a settings, can be changed
  public static final int screenHeight = 768;




  public Thread gameThread; // why we need a thread is because of the fact that if we dont use a thread then it will become more akin to a turn based rpg where we do our thing then another person will do their thing.
  // With a thread what happens is that is runs through it top to bottom while we have another thing also running I think?
  KeyHandler keyChecker = new KeyHandler(this);
  

  int playerSizeX = 100;
  int playerSizeY = 100;
  int playerStartingX = (int) (screenWidth/2) - playerSizeX/2;
  int playerStartingY = (int) (screenHeight/2) - playerSizeY/2;
  PlayerData player = new PlayerData(keyChecker, this, playerStartingX ,playerStartingY, 15, playerSizeX, playerSizeY);
  int pSpeed = 0;
  // Sword sword = new Sword(new int[] {player.posX, player.posX + playerSizeX+400, player.posX + playerSizeX+400, player.posX}, new int[] {player.posY+30, player.posY+30, player.posY + playerSizeY-30, player.posY + playerSizeY-30} );
  // Sword sword2 = new Sword(new int[] {player.posX, player.posX + playerSizeX+400, player.posX + playerSizeX+400, player.posX}, new int[] {player.posY+30, player.posY+30, player.posY + playerSizeY-30, player.posY + playerSizeY-30} );
  // Sword sword3 = new Sword(new int[] {player.posX, player.posX + playerSizeX+400, player.posX + playerSizeX+400, player.posX}, new int[] {player.posY+30, player.posY+30, player.posY + playerSizeY-30, player.posY + playerSizeY-30} );
  // Sword sword4 = new Sword(new int[] {player.posX, player.posX + playerSizeX+400, player.posX + playerSizeX+400, player.posX}, new int[] {player.posY+30, player.posY+30, player.posY + playerSizeY-30, player.posY + playerSizeY-30} );
  // Sword sword5 = new Sword(new int[] {player.posX, player.posX + playerSizeX+400, player.posX + playerSizeX+400, player.posX}, new int[] {player.posY+30, player.posY+30, player.posY + playerSizeY-30, player.posY + playerSizeY-30} );
  // Sword sword6 = new Sword(new int[] {player.posX, player.posX + playerSizeX+400, player.posX + playerSizeX+400, player.posX}, new int[] {player.posY+30, player.posY+30, player.posY + playerSizeY-30, player.posY + playerSizeY-30} );
  // CircleZone test = new CircleZone(player);



  CollisionDetect CD = new CollisionDetect(this);

  //Garbage names, but they are the equivalent of uh height and width.
  public static final int topBounds = screenHeight * 3;
  public static final int leftBounds = screenWidth * 3;


//Borders (dont touch order ty)
  public InvisWall top = new InvisWall(0, 0, leftBounds, 0);
  public InvisWall bottom = new InvisWall(0, leftBounds, leftBounds, 0);
  public InvisWall left = new InvisWall(0, 0, 0, leftBounds);
  public InvisWall right = new InvisWall(leftBounds, 0, 0, leftBounds);
  

//Enemy types
  People people = new People("people_images/people.jpg", 500, 500, 1);
  People people2 = new People("people_images/redshirt.png", 500, 500, 2);


//Spawner Types, need to be before the different enemies.  
  public Spawner peopleSpawner = new Spawner(people, player, 1);
  public Spawner peopleSpawner2 = new Spawner(people2, player, 2);

  // Spawner peopleSpawner2 = new Spawner(people, player);
  // Spawner peopleSpawne3r = new Spawner(people, player);
  // Spawner peopleSpawner4 = new Spawner(people, player);
  // Spawner people2Spawner = new Spawner(people2, player);


  //Game states
  public int gameState = 5; // for now we can say that. 
  public final int playState = 1;
  public final int pauseState = 2;
  public final int deathState = 3;
  public final int levelUpState = 4;
  public final int titleScreenState = 5;

  LevelUpScreen onlyLevelUpScreen = new LevelUpScreen(screenWidth, screenHeight);
  int count;
  int secondOnes;
  int secondsTens;
  int minutesOnes;
  int minutesTens;


  SpawnerController onlySpawnerController = new SpawnerController(this, player, keyChecker);








  public Game(){
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setDoubleBuffered(true);
    this.setBackground(Color.BLACK);
    this.addKeyListener(keyChecker);
    this.setFocusable(true);
  }

  

    public void startGameThread(){


      for(int x = People.peopleList.size()-1; x >= 0; x--){
        People.peopleList.set(x, null);
        People.peopleList.remove(x);
      }
      gameThread = new Thread(this);
      gameThread.start();
      onlySpawnerController.startSpawnerControllerThread();    

    }

      //Changable Variables, mostly consisting of player stuff and setting data?
      public static int FPS = 30;// Only be 30 or 60 or 90 frames, ACTUALLY its better if we cap it at 30, ALSO CANT BE LOWER THAN 15, BECAUSE I DO 15/ MATHS

      public static int seconds = 0;
      public static int frameCount = 0;
      public static int FPStimer = 0;
      public static int FPScount = 0;


      
    @Override
    public void run(){



      double bootlegFPS = 1000000000/FPS; // the 10e9? means that it is equvilant to 1 second. Therefore if we divide 1 seconds by the frames(60) we are refreshing the thing 60 times every second.
      long lastTime = System.nanoTime();
      long currentTime;
      double delta = 0;
      
      //Wavplayer.play("music_zone/tune.wav");
      // new Card("Add +1 DMG");
      // new Card("Get another sword");
      // new Card("More speed");
      // new Card("Jerma bahablast");
      // new Card("HELP");
      // new Card("HELP");









      
        while(gameThread != null){
          //meaning that while the thread is still active we will be running what ever is in here?
          // x,y is 0,0 at top left and max at bottom left.
          // System.out.println("Holder");

          currentTime = System.nanoTime();
          delta += (currentTime - lastTime) / bootlegFPS;
          // seconds += (currentTime - lastTime); //This is because I used an old system AND I suck.
          FPStimer += (currentTime - lastTime);
          lastTime = currentTime;

          if(delta >= 1){
            update();
            repaint();
            if(gameState == playState){
              frameCount++;
              seconds = frameCount/FPS;
            }
            FPScount++;



            delta--;
          }

          if(FPStimer >= 1000000000){ //every second
            System.out.println("FPS: " + FPScount);
            System.out.println("Seconds: " + seconds);
            System.out.println(People.peopleList.size());
            System.out.println(Entity.entityList.size());
            FPScount = 0;
            FPStimer = 0;
          }
      }
    }

  
    public void update(){

      switch(gameState){
        case playState: // default playing thing

        //Check SpawnerController for entity stat changes and other things 
        onlySpawnerController.canRun = true;

        //Can't run this in the other thread because it doesn't 'make it' properly, unless I really don't care.
        // if(Game.frameCount % (Game.FPS * 60) == 0 && Game.frameCount != 0){ //Every 3 minutes we add another spawner?
        //   if(Spawner.spawnerList.size() < 15){//Because we only have one type of enemy, I think setting the limit to 7 spawners is good enough for now.
        //       new Spawner(peopleSpawner.entitySpawnedData, player, 1); //1 being people/ basic enemy
        //   }
        // }
        
        // if(Game.frameCount % (Game.FPS * 120) == 0 && Game.frameCount != 0){
        //   if(Spawner.spawnerList.size() < 15){//Because we only have one type of enemy, I think setting the limit to 7 spawners is good enough for now.
        //     new Spawner(peopleSpawner2.entitySpawnedData, player, 2); //1 being people/ basic enemy
        //   }
        // }
        
        player.collides = false;
        for(int x = People.peopleList.size()-1; x >= 0 ;x-- ){
        People peoples = People.peopleList.get(x);  
        if(!player.iFrame && !peoples.iFrame){        
            CD.checkPlay(peoples, player);
            doDeath(peoples.isDead, x);
          }
        }

        //Always check this.
        for(InvisWall walls: InvisWall.wallList){
          CD.checkPlayWall(walls, player);
        }

        pSpeed = player.playerMove();

        //Check all types of children items here.
        for(int z = Sword.swordList.size()-1; z >= 0; z--){
          Sword swords = Sword.swordList.get(z); 
          swords.swingSword(player);
        }
        

      for(int z = Item.itemList.size()-1; z >= 0; z--){
        Item items = Item.itemList.get(z);
        for(int x = People.peopleList.size()-1; x >= 0 ;x-- ){
          People peoples = People.peopleList.get(x);
            if(CD.checkItem(peoples, items)){
              doDeath(peoples.isDead, x);
            }
        }
      }

      for(int x = Spawner.spawnerList.size()-1; x >= 0; x--){
        Spawner currentSpawner = Spawner.spawnerList.get(x);
        currentSpawner.independentSpawnerMovement(pSpeed, keyChecker); // <-- ALWAYS NEEDED
      }
        

          // CHECKS COLLISION BETWEEN PEOPLE AND OTHER OBJECTS CURRENTLY CREATED (INVISIBLE WALLS, PLAYER)
        for(int x = People.peopleList.size()-1; x >= 0 ;x-- ){
            People peoples = People.peopleList.get(x);
            peoples.collides = false;
            // CD.checkIFrame(peoples); //normally needed, becasue most methods that reset I-frame are behind an I-Frame conditional, but I just added a checkIFrame on the walls method.
            if(!peoples.iFrame){
              CD.checkObj(player, peoples);
              doDeath(peoples.isDead, x);
            }
            for(InvisWall walls: InvisWall.wallList){
              CD.checkWalls(walls, peoples); //People vs walls
            }
            // CD.checkPeopleVSPeople(People.peopleList, peoples);

            switch(peoples.type){
              case 1:
                peoples.entityMove(peoples.entityPlayerDrivenDirection(player));
                break;
              case 2:
                peoples.entityMove(peoples.entityRandomDirection());
                break;
              
            }
            peoples.playerInfluencedMovement(pSpeed, keyChecker);
          }
        


        for(InvisWall walls: InvisWall.wallList){
          // walls.collides = false;
          // CD.checkObj(player, walls); //walls currently does not have a speed so i think it is useless AND has no movement direction!!! <- Made a defualt case. <- bugged
          // for(int x = People.peopleList.size()-1; x >= 0 ;x-- ){
          //   People peoples = People.peopleList.get(x);
          //   CD.checkWalls(walls, peoples); //changed it uh oh.
          // }
          walls.playerInfluencedMovement(pSpeed, keyChecker);
        }


  




        //Insert if player is dead then we switch the gamestate to end screen.
        if(player.isDead || player.health <= 0){
          gameState = deathState;
        }
          break;

        case pauseState: // pause

          // People help = new People("people_images/People.jpg", keyChecker);


          
          break;

        case deathState:




          
          break;

        case levelUpState:
          Card.changeIsSelected(keyChecker, this, player);
          Card.createCards();
          // new Card("HELP");




          
          break;

        case titleScreenState:
        if(keyChecker.enterKey){
          gameState = levelUpState;
        }





          
          break;
      } 

  }


  public void drawTime(Graphics2D g2){ 
    int size =  50;
    int tempSeconds = seconds%60;
    secondOnes = tempSeconds%10;
    secondsTens = tempSeconds/10;
    minutesOnes = seconds/60;
    minutesOnes %= 10;
    minutesTens = seconds/600;


    g2.setColor(Color.GRAY);
    g2.setFont(new Font("impact", Font.BOLD, size));
    g2.drawString("" + minutesTens + minutesOnes + ":" + secondsTens + secondOnes, (int) ((screenWidth/2) - size*(( 4 + Integer.toString(minutesTens).length() ) * .272)), size);

  }

  public void doDeath(boolean dead, int index){
    if(dead){
      // System.out.println("YES");
      player.gainEXP(People.peopleList.get(index).expWorth);
      People.peopleList.get(index).sprite.flush();
      People.peopleList.remove(index);
    }
  }

  public void playStateDrawMethod(Graphics2D g2){
    for(int x = People.peopleList.size()-1; x >= 0 ;x-- ){
      People peoples = People.peopleList.get(x);
      peoples.draw(g2);
    }
       //need this to move less, moving 60 times per second
       for(Item items: Item.itemList){
         items.draw(g2);
       }
      // sword.draw(g2);
      player.draw(g2);

      for(InvisWall walls: InvisWall.wallList){
        walls.drawWalls(g2);
      }
      // peopleSpawner.drawAllSpawnerHitboxes(g2);
      drawTime(g2);
  }

  
    
    public void paintComponent(Graphics g){
  
      super.paintComponent(g);
  
      Graphics2D g2 = (Graphics2D) g;

      switch(gameState){
        case pauseState: // default playing thing

          // // test.drawPolyHitbox(g2);

          // for(int x = People.peopleList.size()-1; x >= 0 ;x-- ){
          //   People peoples = People.peopleList.get(x);
          //   peoples.drawHitboxes(g2);
          // }

          // player.drawHitboxes(g2);

          // for(InvisWall walls: InvisWall.wallList){
          //   walls.drawWalls(g2);
          // }
          // drawTime(g2);


          //Everything above is for debugging hitboxes
          playStateDrawMethod(g2);
          onlyLevelUpScreen.draw(g2);
          g2.setColor(Color.WHITE);
          g2.setFont(new Font("impact", Font.PLAIN, 300));
          g2.drawString("PAUSED", (screenWidth/2) - (int) (300*1.5) , (screenHeight/2) + 300/3);





          



          break;

        case playState: // pause
        for(Item items: Item.itemList){
          items.drawAniHitbox(g2);
        }
          playStateDrawMethod(g2);
          for(int x = Spawner.spawnerList.size()-1; x >= 0; x--){
            Spawner spawners = Spawner.spawnerList.get(x);
            spawners.drawAllSpawnerHitboxes(g2); 
          }
          break;


      
      case deathState:
        int size = 300;
        setBackground(Color.RED);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("impact", Font.BOLD, size));
        g2.drawString("YOU SUCK", (screenWidth/2) - size*2 , (screenHeight/2) + size/3);
        g2.setFont(new Font("impact", Font.PLAIN, size/2));
        g2.drawString("" + minutesTens + minutesOnes + ":" + secondsTens + secondOnes, (int) ((screenWidth/2) - size/2), (screenHeight/2) + size);
        //Add the time here as it is the score be like your final time is:
        break;

      case levelUpState:
        playStateDrawMethod(g2);
        onlyLevelUpScreen.draw(g2);
        // System.out.println(Card.cardList.size());
        for(int x = Card.cardList.size()-1; x >= 0 ; x--){
          Card currentCard = Card.cardList.get(x);
          // System.out.println(currentCard.abilityCard.getX());
          currentCard.draw(g2);
        }



        break;

      case titleScreenState:

        onlyLevelUpScreen.drawStartScreen(g2);





          
        break;
    }

      g2.dispose();
      
    }// so what it does, we dispose at the end because if you don't it does.
    // What dispose actually does is it removes the reasources being used to keep the thing alive, therefore if there isn't any dispose the game will have unesscerry data.






  
}