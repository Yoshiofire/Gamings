import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.image.BufferedImage;
import java.io.IOException;



public class LevelUpScreen {

    public Rectangle wholeScreen;
    BufferedImage tempTitleScreen;


    
    public LevelUpScreen(int width, int height){
        wholeScreen = new Rectangle(0,0, width, height);
        try {

            tempTitleScreen = ImageIO.read(getClass().getResourceAsStream("extra_images/dasas.PNG"));

        } 
        catch (IOException e) {
            
            e.printStackTrace();

        }
    }





    public void draw(Graphics2D g3){
        g3.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
        g3.setColor(Color.GRAY);
        g3.fill(wholeScreen);
        g3.draw(wholeScreen);
        g3.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }

    public void drawStartScreen(Graphics2D g3){
        g3.drawImage(tempTitleScreen, 0, 0, (int) wholeScreen.getWidth(), (int) wholeScreen.getHeight(), null);

    }
}
