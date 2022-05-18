import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;

public class KeyHandler implements KeyListener{
  
  public Game game;
  public boolean upKey, downKey, leftKey, rightKey, pauseKey;
  public int lastPressed;

  public KeyHandler(Game g){
    game = g;
  }

  

  @Override
  public void keyTyped(KeyEvent e){
    // System.out.println();
    
  }
  
  @Override
  public void keyPressed(KeyEvent e){
    // imo we use if statements because of the fact if we were to use switch it would only detect one key and not two keys pressed at once????

    int code = e.getKeyCode();
    lastPressed = code;
    // System.out.print(lastPressed);

    // if(code == KeyEvent.VK_W){
    //   upKey = true;
    // }
    // if(code == KeyEvent.VK_A){
    //   leftKey = true;
    // }
    // if(code == KeyEvent.VK_S){
    //   downKey = true;
    // }
    // if(code == KeyEvent.VK_D){
    //   rightKey = true;
    // }

    switch(code){
      case KeyEvent.VK_W:
        upKey = true;
        break;
      case KeyEvent.VK_A:
        leftKey = true;
        break;
      case KeyEvent.VK_S:
        downKey = true;
        break;
      case KeyEvent.VK_D:
        rightKey = true;
        break;
      case KeyEvent.VK_ESCAPE:
        if(game.gameState == game.playState){ //if its on play state then pause it
          game.gameState = game.pauseState;
        }
        else if(game.gameState == game.pauseState){// opposite of the first thing
          game.gameState = game.playState;
          game.setBackground(Color.blue);
        }
        break;
      
    }
    
    //pause buttom ig
    // if(code == KeyEvent.VK_ESCAPE){
    //   if(game.gameState == game.playState){ //if its on play state then pause it
    //     game.gameState = game.pauseState;
    //   }
    //   else if(game.gameState == game.pauseState){// opposite of the first thing
    //     game.gameState = game.playState;
    //     game.setBackground(Color.blue);
    //   }
 
      
    // }
    
    
  }

  @Override
  public void keyReleased(KeyEvent e){

    int code = e.getKeyCode();

    // if(code == KeyEvent.VK_W){
    //   upKey = false;
    // }
    // if(code == KeyEvent.VK_A){
    //   leftKey = false;
    // }
    // if(code == KeyEvent.VK_S){
    //   downKey = false;
    // }
    // if(code == KeyEvent.VK_D){
    //   rightKey = false;
    // }
    switch(code){
      case KeyEvent.VK_W:
        upKey = false;
        break;
      case KeyEvent.VK_A:
        leftKey = false;
        break;
      case KeyEvent.VK_S:
        downKey = false;
        break;
      case KeyEvent.VK_D:
        rightKey = false;
        break;
    }
    
  }


  





  
}