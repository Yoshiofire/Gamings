import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class Card{

    Rectangle abilityCard;
    Rectangle abilityCardBorder;
    static int totalAmountOfCardsCreated = 1;
    int cardNumberID;
    String abilityText;
    Boolean isSelected = false;
    int abilityID;
    static int tries = 0;
    public static ArrayList <Card> cardList = new ArrayList<>();


    public Card(String text, int abilityID){

        int borderSizeBothSides = 20;
        cardNumberID = totalAmountOfCardsCreated;
        totalAmountOfCardsCreated++;
        int cardWidth = 150 - borderSizeBothSides;
        int cardHeight = (int)(cardWidth*1.8);
        abilityText = text;
        this.abilityID = abilityID;


        abilityCard = new Rectangle(0,0, cardWidth, cardHeight);
        abilityCardBorder = new Rectangle(0,0, cardWidth+(borderSizeBothSides/2), cardHeight+(borderSizeBothSides/2));

        cardList.add(this);
        for(Card cards: Card.cardList){
            cards.changePosition(borderSizeBothSides);
            cards.changeCardBorder(borderSizeBothSides);
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
            cardXPos = (int) halfOfTheScreen + (cardNum*cardWidthTemp);
            if(totalAmountOfCardsCreated == 2){
                isSelected = true;

                cardXPos += spaceBetweenCards*(totalAmountOfCardsCreated);
            }else{
                cardXPos += (int) spaceBetweenCards*(totalAmountOfCardsCreated - (totalAmountOfCardsCreated/3.0));
            }
            // 1- 333 + 100 = 433;
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


    public static void changeIsSelected(KeyHandler key, Game game, PlayerData player){
        tries++;// determines the speed of the movement of whatever
        for(int x = Card.cardList.size()-1 ; x >= 0; x--){
            Card currentCard = Card.cardList.get(x);
            if(key.enterKey && currentCard.isSelected){
                System.out.println("Chosen");
                System.out.println(currentCard.abilityText);
                game.gameState = game.playState;
                LevelUpScreen.testCreateAbilities(currentCard.abilityID, player);
                //Resets everything.
                clearCards();
                totalAmountOfCardsCreated = 1;
                LevelUpScreen.createMoreCards = true;
                break;
            }
            if(key.leftKey && currentCard.isSelected && tries >= 3){// Meaning that the currentCard is the selected one, and we are moving left
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
            if(key.rightKey && currentCard.isSelected & tries >= 3){//like the left one, but we are moving right
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


    public void changeCardBorder(int borderSize){

        abilityCardBorder.width = (int) ((borderSize) + abilityCard.getWidth());
        abilityCardBorder.height = (int) ((borderSize) + abilityCard.getHeight());

        abilityCardBorder.setLocation((int) (abilityCard.getX()-(borderSize/2)),(int) (abilityCard.getY()-(borderSize/2)));
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
        g3.setFont(new Font("Arial", Font.BOLD, (int) ((abilityCard.width/abilityText.length())*1.6)));
        g3.drawString(abilityText, abilityCard.x + (int) abilityText.length() , abilityCard.y + (abilityCard.y/2));

    }

    
}
