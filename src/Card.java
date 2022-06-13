import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class Card{

    Rectangle abilityCard;
    Rectangle abilityCardBorder;
    Rectangle abilityPicture;
    static int totalAmountOfCardsCreated = 1;
    int cardNumberID;
    String abilityText;
    Boolean isSelected = false;
    int abilityID;
    static int tries = 0;
    public static ArrayList <Card> cardList = new ArrayList<>();

    //FOR ABILITES AND STUFF
    public static ArrayList <Integer> dontCallAbilities = new ArrayList <Integer>();
    public static boolean createMoreCards = true;
    static boolean firstAbilities = true;
    static int time = 0;

    //Creating New Swords Cap
    static int swordLimit = 5;
    static int currentAmountOfSwords = 0;

    //Creating CircleZone Diameter Cap;
    static int circleZoneLimit = 1;
    static int currentAmountOfcircleZones = 0;



    public Card(String text, int abilityID){

        int borderSizeBothSides = 10;
        cardNumberID = totalAmountOfCardsCreated;
        totalAmountOfCardsCreated++;
        int cardWidth = 170 - borderSizeBothSides;
        int cardHeight = (int)(cardWidth*1.7);
        abilityText = text;
        this.abilityID = abilityID;


        abilityCard = new Rectangle(0,0, cardWidth, cardHeight);
        abilityCardBorder = new Rectangle(0,0, cardWidth+(borderSizeBothSides/2), cardHeight+(borderSizeBothSides/2));

        cardList.add(this);
        for(Card cards: Card.cardList){
            cards.changePosition(borderSizeBothSides);
            cards.changeCardBorder(borderSizeBothSides);
            cards.createAbilityFrame(borderSizeBothSides);
        }


    }

    public void changePosition(int borderSize){
        int cardXPos = 0;
        int cardWidthTemp = (int) abilityCard.getWidth() + borderSize;
        int spaceBetweenCards = cardWidthTemp/2;
        int cardNum = cardNumberID -1;
        int halfOfTheScreen = (int) ((Game.screenWidth/2) - (totalAmountOfCardsCreated * cardWidthTemp) + (cardWidthTemp/2));
        // 1- 683 - 300  50 = 333;
        // 2- 683 - 300 - 50 = 333;
        // 3- 683 - 300 - 50 = 333;


        // System.out.println(("CARD NUMBER: " + cardNumberID));

        // System.out.println((Game.screenWidth/2));
        // System.out.println((cardNumberID * cardWidthTemp));
        // System.out.println((abilityCard.getWidth()/2));
        // System.out.println(halfOfTheScreen);
        // System.out.println();

        if(cardNum == 0){
            // int cardXPos = (int) (cardList.get(cardNum).abilityCard.getX()) + (cardNum*cardWidthTemp);
            cardXPos = halfOfTheScreen + (cardNum * totalAmountOfCardsCreated); //Yes it doesn't affect the cards position alot, but whatever WHY IS IT EVEN HERE
            if(totalAmountOfCardsCreated == 2){
                isSelected = true;
                cardXPos += spaceBetweenCards*(totalAmountOfCardsCreated);
            }else{
                cardXPos += (int) spaceBetweenCards*(totalAmountOfCardsCreated - (totalAmountOfCardsCreated/3.0));
                cardXPos += (borderSize * 4);

            }
        }
        else{

            cardXPos = (int) (cardList.get(cardNum-1).abilityCard.getX()) + (cardWidthTemp + spaceBetweenCards);
            // 2- 433 + 200 = 633;
            // 3- 633 + 200 = 833;

        }


        int cardYPos = (int) ((Game.screenHeight/2 - abilityCard.getHeight()/2));




        // abilityCard.setLocation(cardXPos, cardYPos);
        abilityCard.x = cardXPos;
        abilityCard.y = cardYPos;

        // System.out.println(cardXPos);
        // System.out.println();



    }

    public static void clearCards(){
        for(int y = Card.cardList.size()-1 ; y >= 0; y--){
            Card.cardList.remove(y);
        }
    }

    public void createAbilityFrame(int borderSize){
        int spaceBetweenWalls = 7;
        abilityPicture = new Rectangle
        (
            (int) abilityCard.getX() + spaceBetweenWalls,
            (int) abilityCard.getY()+spaceBetweenWalls,
            (int) abilityCard.getWidth() - spaceBetweenWalls*2,
            (int) (abilityCard.getHeight()/2.0) -spaceBetweenWalls*2
            );
        //abilityCard.y + (abilityCard.y/2)
    }

    public void changeCardBorder(int borderSize){

        abilityCardBorder.width = (int) ((borderSize) + abilityCard.getWidth());
        abilityCardBorder.height = (int) ((borderSize) + abilityCard.getHeight());

        abilityCardBorder.setLocation((int) (abilityCard.getX()-(borderSize/2)),(int) (abilityCard.getY()-(borderSize/2)));
    }

    //START OF GENERAL METHODS

    public static void changeIsSelected(KeyHandler key, Game game, PlayerData player){
        tries++;// determines the speed of the movement of whatever
        for(int x = Card.cardList.size()-1 ; x >= 0; x--){
            Card currentCard = Card.cardList.get(x);
            if(key.enterKey && currentCard.isSelected){
                // System.out.println("Chosen");
                // System.out.println(currentCard.abilityText);
                game.gameState = game.playState;
                createAbilities(currentCard.abilityID, player);
                //Resets everything.
                clearCards();
                totalAmountOfCardsCreated = 1;
                createMoreCards = true;
                break;
            }
            if(key.leftKey && currentCard.isSelected && tries >= 5){// Meaning that the currentCard is the selected one, and we are moving left
                currentCard.isSelected = false;
                tries = 0;
                if(x == 0){ //if it is already at the end of the "list" meaning being the first card;
                    Card.cardList.get(Card.cardList.size()-1).isSelected = true; //Meaning that the one all the way on the right is now selected
                    break;

                }else{
                    Card.cardList.get(x-1).isSelected = true;
                    break;

                }
            }
            if(key.rightKey && currentCard.isSelected & tries >= 5){//like the left one, but we are moving right
                currentCard.isSelected = false;
                tries = 0;
                if(currentCard.equals(Card.cardList.get(Card.cardList.size()-1))){//if we are at the end of the "list" on the right side.
                    Card.cardList.get(0).isSelected = true; //Beginning of the list card thing is equal to true
                    break;

                }else{
                    Card.cardList.get(x+1).isSelected = true;
                    break;

                }
            }
        }
    }

    public static void createCards(){
        time++;
        if(time >= 15){
            if(!firstAbilities){
            time = 0;
            int range = (int) (Math.random()*4)+2;
            if(createMoreCards){
                for(int x = 0; x < range; x++){
                    int randomNumber = (int) (Math.random()*4);
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
                                new Card("Double DMG, But    lose your I-Frames", 1);
                                break;
                            case 2:
                                new Card("Gain +2 Seconds    I-Frame, But lose  50HP", 2);
                                break;
                            case 3:
                                new Card("Get a damaging areaaround you", 3);
                            default:
                                new Card("You made a mistake with the randomNumber Variable", 777);
                                break;
    
    
                        }
                    }
                    else{//If you can't create the good cards because they are already used?
                        switch(randomNumber){
                            case 0://Because this is for when you have the max amount of swords I'll also add a thing for swords.
                                int tempRandom = (int) (Math.random()*2);
                                if(tempRandom == 0){
                                    new Card("Gain +15 Max HP", -1);
                                }else{
                                    new Card("Haha longer sword", -4);
                                }
                                break;
                            case 1:
                                new Card("Gain +4 DMG", -2);
                                break;
                            case 2:
                                new Card("Deal your contact  DMG to enemies", -3);
                                break;
                            case 3:
                                new Card("Increase your       damaging zone size", -5);
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
                new Card("Gain +20 Contact   DMG", -100);
                new Card("Start with a       damaging field", 3);

                firstAbilities = false;
            }
            time = 0;
            createMoreCards = false;
        }
    }

    public static void createAbilities(int abilityID, PlayerData player){
        time = 0;
        switch(abilityID){
            case-666:
                break;
            case -100: //Increase player contactDMG by 20
                player.contactDMG += 20;
                break;
            case -5: //Increase circleZone by 30?
                for(int x = 0; x < CircleZone.circleZoneList.size(); x++){
                    CircleZone legitTheOnlyCircleZone =  CircleZone.circleZoneList.get(x);
                    legitTheOnlyCircleZone.increaseSize(30);
                }
                break;
            case -4: //Increase ALL sword length by 50
                for(int x = 0; x < Sword.swordList.size(); x++){
                    Sword tempSword = Sword.swordList.get(x);
                    tempSword.makeSwordLonger(100);
                }
                break;
            case -3: //Does player.ContactDMG to all peoples
                boolean noIFrame = false;
                if(player.iFrameTime == 0){
                    noIFrame = true;
                }
                for(int x = 0; x < People.peopleList.size(); x++){
                    People tempPeople = People.peopleList.get(x);
                    if(!noIFrame){
                        tempPeople.takeDMG(player.contactDMG);
                    }
                    else{
                        tempPeople.takeDMG((int) (player.contactDMG*1.5));
                    }
                }
                break;
            case -2: //Increase player item DMG by 4
                for(int x = 0; x < Item.itemList.size(); x++){
                    Item tempItem = Item.itemList.get(x);
                    tempItem.dmg += 4;
                }
                player.contactDMG += 4;
                break;
            case -1: //Increase player maxHP by 10
                player.healthMax += 15;
                player.health += 15;
                break;
            case 0: //Gain a new Sword.
                new Sword(new int[] {player.posX, player.posX + player.sizeX+400, player.posX + player.sizeX+400, player.posX}, new int[] {player.posY+30, player.posY+30, player.posY + player.sizeY-30, player.posY + player.sizeY-30} );
                currentAmountOfSwords++;
                if(currentAmountOfSwords == swordLimit){
                    dontCallAbilities.add(abilityID);
                }
                break;
            case 1: //Works?

                // Entity.iFrameTimeStatAddition -= 1;
                // for(int x = 0; x < People.peopleList.size(); x++){
                //     People.peopleList.get(x).iFrameTime -= 1;
                //     People.peopleList.get(x).iFrame = false;
                // }

                player.changeIFrame(-1);
                player.contactDMG *= 2;
                for(int x = 0; x < Item.itemList.size(); x++){
                    Item tempItem = Item.itemList.get(x);
                    tempItem.dmg *= 2;
                }

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
            case 3:
                new CircleZone(player);
                dontCallAbilities.add(abilityID);
                //Why would you ever need to have more than 1?
                // currentAmountOfcircleZones++;
                // if(currentAmountOfcircleZones == circleZoneLimit){
                //     dontCallAbilities.add(abilityID);
                // }
                break;
            case 777:
                break;
            
        }

        
    }



    public void draw(Graphics2D g3){
        if(!isSelected){
            g3.setColor(Color.WHITE);
            g3.fill(abilityCardBorder);
            g3.draw(abilityCardBorder);
        }else{
            g3.setColor(Color.YELLOW);
            g3.fill(abilityCardBorder);
            g3.draw(abilityCardBorder);

        }
        

        g3.setColor(Color.BLACK);
        g3.fill(abilityCard);
        g3.draw(abilityCard);
        g3.setColor(Color.WHITE);
        g3.setFont(new Font("Arial", Font.BOLD, 15));

        //Can be created into its own set up method, where we make a Arraylist of Strings and the strings are all 19 or less long.
        int letterCount = 0;
        int totalAmountOfLettersleft = abilityText.length();
        int currentYPos = abilityCard.y + (abilityCard.y/2) + 30;
        while(totalAmountOfLettersleft >= 19){
            // System.out.println(abilityText.substring(letterCount, letterCount+19));
            //I have no idea how to change the abilityCard.x + 5 into something more dynamic when changing the card borderLength.
            g3.drawString(abilityText.substring(letterCount, letterCount+19), abilityCard.x + 10, currentYPos);
            currentYPos += 30;
            letterCount += 19;
            totalAmountOfLettersleft -= 19;
        }
        g3.drawString(abilityText.substring(letterCount, abilityText.length()), abilityCard.x + 10, currentYPos);
        g3.draw(abilityPicture);

    }

    
}
