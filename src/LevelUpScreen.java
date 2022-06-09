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
    static boolean firstAbilities = true;

    //Creating New Swords Cap
    static int swordLimit = 5;
    static int currentAmountOfSwords = 0;

    
    public LevelUpScreen(int width, int height){
        wholeScreen = new Rectangle(0,0, width, height);
    }

    public void testCreateCards(){
        tries++;
        if(tries >= 15){
            if(!firstAbilities){
            tries = 0;
            int range = (int) (Math.random()*5)+1;
            if(createMoreCards){
                for(int x = 0; x < range; x++){
                    int randomNumber = (int) (Math.random()*3);
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
                            case 1:
                                new Card("All I-Frames -1    Seconds", 1);
                                break;
                            case 2:
                                new Card("Gain +2 Seconds    I-Frame, But lose  50HP", 2);
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
                            default:
                                new Card("Unlucky", -666);
                                break;
                            }
                        }
                    }
                }
            }
            else if(firstAbilities && createMoreCards){//Creates the starter abilites, because its the easiest way currenly 
                new Card("Get a starter Sword", 0);
                new Card("Gain +15 Contact   DMG", -2);




                firstAbilities = false;
            }
            tries = 0;
            createMoreCards = false;
        }
    }

    public static void testCreateAbilities(int abilityID, PlayerData player){
        tries = 0;
        switch(abilityID){
            case-666:
                break;
            case -2: //Increase player maxHP by 10
                player.contactDMG += 15;
                break;
            case -1: //Increase player maxHP by 10
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
            case 1: //Works?
                Entity.iFrameTimeStatAddition -= 1;
                for(int x = 0; x < People.peopleList.size(); x++){
                    People.peopleList.get(x).iFrameTime -= 1;
                    People.peopleList.get(x).iFrame = false;
                }
                player.changeIFrame(-1);
                dontCallAbilities.add(1);
                dontCallAbilities.add(2);
                break;
            case 2: //Works?
                player.changeIFrame(2);
                player.healthMax -=50;
                player.health -=50;
                dontCallAbilities.add(1);
                dontCallAbilities.add(2);
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
