import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class Game extends JPanel implements Runnable{
  // will i need to setup a whole tile system? If so what is a good size?
  

  //Windows Height, probably won't be changable.
  public static final int screenWidth = 1366; // if we do a settings, can be changed
  public static final int screenHeight = 768;




  Thread gameThread; // why we need a thread is because of the fact that if we dont use a thread then it will become more akin to a turn based rpg where we do our thing then another person will do their thing.
  // With a thread what happens is that is runs through it top to bottom while we have another thing also running I think?
  KeyHandler keyChecker = new KeyHandler(this);
  

  int playerSizeX = 100;
  int playerSizeY = 100;
  int playerStartingX = (int) (screenWidth/2) - playerSizeX/2;
  int playerStartingY = (int) (screenHeight/2) - playerSizeY/2;
  PlayerData player = new PlayerData(keyChecker, this, playerStartingX ,playerStartingY, 15, playerSizeX, playerSizeY);
  Sword sword = new Sword(new int[] {player.posX, player.posX + playerSizeX+400, player.posX + playerSizeX+400, player.posX}, new int[] {player.posY+30, player.posY+30, player.posY + playerSizeY-30, player.posY + playerSizeY-30} );
  // Sword sword2 = new Sword(new int[] {player.posX, player.posX + playerSizeX+400, player.posX + playerSizeX+400, player.posX}, new int[] {player.posY+30, player.posY+30, player.posY + playerSizeY-30, player.posY + playerSizeY-30} );
  // Sword sword3 = new Sword(new int[] {player.posX, player.posX + playerSizeX+400, player.posX + playerSizeX+400, player.posX}, new int[] {player.posY+30, player.posY+30, player.posY + playerSizeY-30, player.posY + playerSizeY-30} );
  // Sword sword4 = new Sword(new int[] {player.posX, player.posX + playerSizeX+400, player.posX + playerSizeX+400, player.posX}, new int[] {player.posY+30, player.posY+30, player.posY + playerSizeY-30, player.posY + playerSizeY-30} );
  // Sword sword5 = new Sword(new int[] {player.posX, player.posX + playerSizeX+400, player.posX + playerSizeX+400, player.posX}, new int[] {player.posY+30, player.posY+30, player.posY + playerSizeY-30, player.posY + playerSizeY-30} );
  // Sword sword6 = new Sword(new int[] {player.posX, player.posX + playerSizeX+400, player.posX + playerSizeX+400, player.posX}, new int[] {player.posY+30, player.posY+30, player.posY + playerSizeY-30, player.posY + playerSizeY-30} );




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
  People people = new People("people_images/people.jpg");
  People people2 = new People("people_images/shirt.jpg");
  People people3 = new People("people_images/people.jpg");

//Spawner Types, need to be before the different enemies.  
  Spawner peopleSpawner = new Spawner(people, player);
  // Spawner peopleSpawner2 = new Spawner(people, player);
  // Spawner peopleSpawne3r = new Spawner(people, player);
  // Spawner peopleSpawner4 = new Spawner(people, player);
  // Spawner people2Spawner = new Spawner(people2, player);


  //Game states
  public int gameState = 4; // for now we can say that. 
  public final int playState = 1;
  public final int pauseState = 2;
  public final int deathState = 3;
  public final int levelUpState = 4;
  LevelUpScreen onlyLevelUpScreen = new LevelUpScreen(screenWidth, screenHeight);
  int count;
  int secondOnes;
  int secondsTens;
  int minutesOnes;
  int minutesTens;
  // boolean isStillPlaying = true;







  public Game(){
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setDoubleBuffered(true);
    this.setBackground(Color.BLACK);
    this.addKeyListener(keyChecker);
    this.setFocusable(true);
  }

  

    public void startGameThread(){
      gameThread = new Thread(this);
      gameThread.start();

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
      int FPScount = 0;
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



        //CHECK COLLISION BETWEEN PLAYER AND CURRENT OBJECTS (INVISIBLE WALLS, PEOPLE) 
        if(Game.frameCount % (FPS * 5) == 0 && Game.frameCount != 0){
          player.healHP(500);
        }
        if(Game.frameCount % (FPS * 10) == 0 && Game.frameCount != 0){ //Every 3 minutes we add another spawner?
          if(Spawner.spawnerList.size() < 7){//Because we only have one type of enemy, I think setting the limit to 7 spawners is good enough for now.
            new Spawner(peopleSpawner.entitySpawnedData, player);
          }
        }
        if(Game.frameCount % (FPS * 30) == 0 && Game.frameCount != 0){ //Every 3 minutes we decrease the spawning cooldown of everything by 1?
          for(int x = Spawner.spawnerList.size()-1; x >= 0; x--){//What happens is that the new spawner will be at the default cooldown.
            /*
             * Actually, at 3:00, the first spawner will have -3 seconds removed from the cooldown, then a new spawner will be created.
             * That spawner will have the default cooldown of (lets say the defualt is 3).
             * Therefore we have one spawner at -3 seconds, to another at 0 seconds removed.
             */
            Spawner currentSpawner = Spawner.spawnerList.get(x);
            currentSpawner.setSpawnerCooldown(currentSpawner.getSpawnerCooldown()-1);
          }
        }
        
        player.collides = false;
        for(int x = People.peopleList.size()-1; x >= 0 ;x-- ){
        People peoples = People.peopleList.get(x);  
        if(!player.iFrame && !peoples.iFrame){        
            CD.checkPlay(peoples, player);
            doDeath(peoples.isDead, x);
          }
        }

        //Always check this.
        // for(InvisWall walls: InvisWall.wallList){
        //   CD.checkPlayWall(walls, player);
        // }

        int pSpeed = player.playerMove();
        for(int z = Sword.swordList.size()-1; z >= 0; z--){
          Sword swords = Sword.swordList.get(z); 
          swords.swingSword(player);
        }
        

      for(int z = Sword.swordList.size()-1; z >= 0; z--){
        Sword swords = Sword.swordList.get(z);
        for(int x = People.peopleList.size()-1; x >= 0 ;x-- ){
          People peoples = People.peopleList.get(x);
            if(CD.checkItem(peoples, swords)){
              doDeath(peoples.isDead, x);
            }
        }
      }
        

        for(Spawner spawner: Spawner.spawnerList){
          spawner.independentSpawnerMovement(pSpeed, keyChecker); // <-- ALWAYS NEEDED
          if(People.peopleList.size() + Spawner.spawnerList.size() < 1000){
            spawner.basicSpawnPeople();
          }
        }

          // CHECKS COLLISION BETWEEN PEOPLE AND OTHER OBJECTS CURRENTLY CREATED (INVISIBLE WALLS, PLAYER)
        for(int x = People.peopleList.size()-1; x >= 0 ;x-- ){
            People peoples = People.peopleList.get(x);
            peoples.collides = false;
            if(!peoples.iFrame){
              CD.checkObj(player, peoples);
              doDeath(peoples.isDead, x);
            }
            for(InvisWall walls: InvisWall.wallList){
              CD.checkWalls(walls, peoples); //People vs walls
            }
            // CD.checkPeopleVSPeople(People.peopleList, peoples);

            // peoples.entityMove(peoples.entityRandomDirection());
            peoples.entityMove(peoples.entityPlayerDrivenDirection(player));
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
          onlyLevelUpScreen.testCreateCards();
          // new Card("HELP");




          
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
      System.out.println("YES");
      player.gainEXP(People.peopleList.get(index).expWorth);
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

          // test.drawPolyHitbox(g2);
          for(Item items: Item.itemList){
            items.drawAniHitbox(g2);
          }
          for(int x = People.peopleList.size()-1; x >= 0 ;x-- ){
            People peoples = People.peopleList.get(x);
            peoples.drawHitboxes(g2);
          }

          player.drawHitboxes(g2);

          for(InvisWall walls: InvisWall.wallList){
            walls.drawWalls(g2);
          }
          drawTime(g2);
          for(Spawner spawner: Spawner.spawnerList){
            spawner.drawAllSpawnerHitboxes(g2); 
          }



          



          break;
        case playState: // pause
          playStateDrawMethod(g2);
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
    }

      g2.dispose();
      
    }// so what it does, we dispose at the end because if you don't it does.
    // What dispose actually does is it removes the reasources being used to keep the thing alive, therefore if there isn't any dispose the game will have unesscerry data.



  //Added pause() method to allow keystrokes to slow down!
	public static void pause(final int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (final Exception e) {
			// ignore
		}
	}



  
}