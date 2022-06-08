import java.util.ArrayList;
public class People2 extends Entity {
        int direction = 0;
        public static ArrayList <People2> peopleList = new ArrayList<>();

        public People2(String filePath){ //DOESNT NEED TO BE A STRING, I AM JUST LAZY SO INSTEAD OF SENDING A BUFFEREDIMAGE I FORCE PEOPLE TO SEND IN STRING
            super(
                500 + (int) (Math.random() * 100),
                500 + (int) (Math.random() * 100),
                30,
                50,
                50);
            defaultFilePath = filePath;
            this.setSprite(filePath);
            this.moveHPBars();
            this.expWorth = 20;
            this.health = 40;
            peopleList.add(this);
}
}
