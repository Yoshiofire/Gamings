import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;


public class LevelUpScreen {

    public Rectangle wholeScreen;
    public int amountOfAbilitiesCalled;
    public static ArrayList <Integer> dontCallAbilities = new ArrayList <Integer>();

    
    public LevelUpScreen(int width, int height){
        wholeScreen = new Rectangle(0,0, width, height);
        amountOfAbilitiesCalled = 0;
    }

    public void testCreateCards(){
        
    }

    public void testCreateAbilities(){
        int index = (int) Math.random();
        boolean checked = true;
        for(int x = 0; x < dontCallAbilities.size(); x++){
            if(Integer.valueOf(index).equals(dontCallAbilities.get(x))){
                checked = false;
            }
        }


        if(checked){
            switch(index){
                case 0:
                    System.out.println("Get another sword");
                    testCreateCards();

            }
        }
        amountOfAbilitiesCalled = 0;
    }

    public void draw(Graphics2D g3){
        g3.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
        g3.setColor(Color.BLACK);
        g3.fill(wholeScreen);
        g3.draw(wholeScreen);
        g3.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }
}
