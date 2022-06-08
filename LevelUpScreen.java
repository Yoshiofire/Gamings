import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;


public class LevelUpScreen {

    public Rectangle wholeScreen;
    public static ArrayList <Integer> dontCallAbilities = new ArrayList <Integer>();
    public static boolean createMoreCards = true;
    static int tries = 0;

    //Creating New Swords Cap
    static int swordLimit = 5;
    static int currentAmountOfSwords = 1;

    
    public LevelUpScreen(int width, int height){
        wholeScreen = new Rectangle(0,0, width, height);
    }

    public void testCreateCards(){
        tries++;
        if(tries >= 15){
            tries = 0;
            int range = (int) (Math.random()*5)+1;
            if(createMoreCards){
                for(int x = 0; x < range; x++){
                    int randomNumber = (int) (Math.random()*2);
                    // int randomNumber = 0;
                    boolean canCreateCard = true;

                    for(int y = 0; y<dontCallAbilities.size(); y++){
                        if(dontCallAbilities.get(y).equals(Integer.valueOf(randomNumber))){
                            canCreateCard = false;
                        }
                    }
                    if(canCreateCard){
                        switch(randomNumber){
                            case 0:
                                new Card("Get a new Sword", 0);
                                break;
                            default:
                                new Card("You made a mistake with the randomNumber Variable", 777);
                                break;
    
    
                        }
                    }
                    else{//If you can't create the good cards because they are already used?
                        switch(randomNumber){
                            case 0:
                                new Card("Gain +10 Max HP", -1);
                                break;
                            case 1:
                                new Card("Remove All Enemy I-Frames", -2);
                                break;
                            default:
                                new Card("Unlucky", -666);
                                break;
                        }
                    }
                }
            }
            createMoreCards = false;
        }
    }

    public static void testCreateAbilities(int abilityID, PlayerData player){
        tries = 0;
        switch(abilityID){
            case-666:
                break;
            case -2: //Not implimented properly yet :(
                for(int x = 0; x < Spawner.spawnerList.size(); x++){
                    Spawner.spawnerList.get(x).entitySpawnedData.iFrameTime = 0;
                }
                break;
            case -1:
                player.healthMax += 10;
                player.health += 10;
                break;
            case 0: //Gain a new Sword.
                new Sword(new int[] {player.posX, player.posX + player.sizeX+400, player.posX + player.sizeX+400, player.posX}, new int[] {player.posY+30, player.posY+30, player.posY + player.sizeY-30, player.posY + player.sizeY-30} );
                currentAmountOfSwords++;
                if(currentAmountOfSwords == swordLimit){
                    dontCallAbilities.add(abilityID);
                }
                break;
            case 777:
                break;
            
        }

        
    }

    public void draw(Graphics2D g3){
        g3.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
        g3.setColor(Color.GRAY);
        g3.fill(wholeScreen);
        g3.draw(wholeScreen);
        g3.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }
}
