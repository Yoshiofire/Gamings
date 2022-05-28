import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class Game extends JPanel implements Runnable{
  // will i need to setup a whole tile system? If so what is a good size?
  

  //Windows Height, probably won't be changable.
  private final int screenWidth = 1366; // if we do a settings, can be changed
  private final int screenHeight = 768;

  //Changable Variables, mostly consisting of player stuff and setting data?
  int FPS = 30;// can also be changed

  public static int seconds = 0;
  public static int frameCount = 0;


  Thread gameThread; // why we need a thread is because of the fact that if we dont use a thread then it will become more akin to a turn based rpg where we do our thing then another person will do their thing.
  // With a thread what happens is that is runs through it top to bottom while we have another thing also running I think?
  KeyHandler keyChecker = new KeyHandler(this);
  

  int playerSizeX = 100;
  int playerSizeY = 100;
  PlayerData player = new PlayerData(keyChecker, this, (int) (screenWidth/1.9) - playerSizeX, (int) (screenHeight/1.77) - playerSizeY, 15, playerSizeX, playerSizeY);
  Item test = new Item(new int[] {player.posX, player.posX + playerSizeX+100, player.posX + playerSizeX+100, player.posX}, new int[] {player.posY, player.posY, player.posY + playerSizeY, player.posY + playerSizeY} );


  CollisionDetect CD = new CollisionDetect(this);

  //Garbage names, but they are the equivalent of uh height and width.
  final int topBounds = screenHeight * 4;
  final int leftBounds = screenWidth * 4;



  InvisWall top = new InvisWall(0, 0, topBounds, 0);
  InvisWall bottom = new InvisWall(0, topBounds+50, leftBounds, 0);
  InvisWall left = new InvisWall(0, 0, 0, leftBounds);
  InvisWall right = new InvisWall(topBounds+45, 0, 0, leftBounds);


  


  People people = new People("/People_Images/People.jpg", keyChecker);
  People people2 = new People("/People_Images/People.jpg", keyChecker);
  People people3 = new People("/People_Images/People.jpg", keyChecker);

  


  //Game states
  public int gameState = 1; // for now we can say that. 
  public final int playState = 1;
  public final int pauseState = 2;
  int count;







  public Game(){
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setDoubleBuffered(true);
    this.setBackground(Color.BLUE);
    this.addKeyListener(keyChecker);
    this.setFocusable(true);
  }

  

    public void startGameThread(){
      gameThread = new Thread(this);
      gameThread.start();
    }

  
    @Override
    public void run(){

      double bootlegFPS = 1000000000/FPS; // the 10e9? means that it is equvilant to 1 second. Therefore if we divide 1 seconds by the frames(60) we are refreshing the thing 60 times every second.
      double nextRefresh = System.nanoTime() + bootlegFPS;
      
      
        while(gameThread != null){
          //meaning that while the thread is still active we will be running what ever is in here?
          // x,y is 0,0 at top left and max at bottom left.
          // System.out.println("Holder");



          update();
          
          
          repaint();


          double remainingTime = nextRefresh - System.nanoTime(); // meaning we would be left with something below 60fps/ .016 seconds?
          remainingTime /= 1000000;

          if(remainingTime < 0){
            remainingTime = 0;
            count ++;
            System.out.println("Stutter " + count);
          }


          
          try{
            
            Thread.sleep((long) remainingTime); 
            // why this works is becasue of the fact, that if we don't sleep the thread, what will happen is that the amount of times checked is dependant on the persons computer.
            // Therefore, by sleeping after we done the check, it might take like 0.1 seconds, it sleeps the difference off (.016 - .1). Meaning that it will only check every .16 seconds.
            // THEN if it the total is equalling 0, it just imiidetly checks because it already used up it's sleeping time trying to do something. If this happens, it tells us there is something wrong with the code for it to be stuttering.
            
          } catch(InterruptedException e){

            e.printStackTrace();
            
          }

          nextRefresh += bootlegFPS;
          frameCount ++;
          seconds = frameCount/FPS;
          if(frameCount % FPS == 0)
            System.out.println(seconds);
        }
      }

  
    public void update(){

      switch(gameState){
        case 1: // default playing thing


        //CHECK COLLISION BETWEEN PLAYER AND CURRENT OBJECTS (INVISIBLE WALLS, PEOPLE)
        
        
        player.collides = false;
        for(People peoples: People.peopleList){ 
          CD.checkObj(peoples, player);
        }
        for(InvisWall walls: InvisWall.wallList){
          CD.checkObj(walls, player);
        }
        int pSpeed = player.playerMove();


        for(int x = People.peopleList.size()-1; x >= 0 ;x-- ){
          for(Item a: Item.itemList){
            if(CD.checkItem(People.peopleList.get(x), a)){
              System.out.println("YES");
              People.peopleList.remove(x);
            }
          }
        }
        test.testHitboxRotate(player);




          // CHECKS COLLISION BETWEEN PEOPLE AND OTHER OBJECTS CURRENTLY CREATED (INVISIBLE WALLS, PLAYER)

        for(People peoples: People.peopleList){
          peoples.collides = false;
          CD.checkObj(player, peoples);
          for(InvisWall walls: InvisWall.wallList){
            CD.checkObj(walls, peoples); //People vs walls
          }
          peoples.peopleMove();
          peoples.playerInfluencedMovement(pSpeed, keyChecker);
        }


        for(InvisWall walls: InvisWall.wallList){
          walls.collides = false;
          // CD.checkObj(player, walls); //walls currently does not have a speed so i think it is useless AND has no movement direction!!! <- Made a defualt case. <- bugged
          for(People peoples: People.peopleList){
            CD.checkObj(peoples, walls);
          }
          walls.playerInfluencedMovement(pSpeed, keyChecker);
        }


          

          
          break;
        case 2: // pause
          
          break;


      }

  }




  
    
    public void paintComponent(Graphics g){
  
      super.paintComponent(g);
  
      Graphics2D g2 = (Graphics2D) g;

      switch(gameState){
        case 1: // default playing thing

          // test.drawPolyHitbox(g2);
          test.drawAniHitbox(g2);
          for(People peoples: People.peopleList){
            peoples.drawHitboxes(g2);
          }
          player.drawHitboxes(g2);

          for(InvisWall walls: InvisWall.wallList){
            walls.drawHitboxes(g2);
          }
          // test.draw(g2);
          



          break;
        case 2: // pause
        for(People peoples: People.peopleList){
          peoples.draw(g2);
        }
           //need this to move less, moving 60 times per second
          player.draw(g2);




          break;


      }

      g2.dispose();
      
    }// so what it does, we dispose at the end because if you don't it does.
    // What dispose actually does is it removes the reasources being used to keep the thing alive, therefore if there isn't any dispose the game will have unesscerry data.




  
}