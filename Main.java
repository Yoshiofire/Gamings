import javax.swing.JFrame;

public class Main extends JFrame {
    public static void main(String args[]){
      JFrame window = new JFrame();
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setResizable(false); // can be changed maybe?
      window.setTitle(""); // sdadsadsasd

      //things we p
      //1. Eniity collision, gotta know when something hits (practically done)

      //2.



      
      Game gamePanel = new Game();
      window.add(gamePanel);
      window.pack();
      
      window.setLocationRelativeTo(null);
      window.setVisible(true);

      gamePanel.startGameThread();
    }

}