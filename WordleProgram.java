import javax.swing.*;

/** Wordle Program
  * The main program to run the wordle
  * @since 01/120/22
  * @author Lily Zhang
  */
public class WordleProgram
{
  public static void main (String [] args)
  {
    WordleModel wordle = new WordleModel();         //wordle model
    WordleView mainPanel = new WordleView (wordle); //wordle view
    
    //Initialize the Frame
    JFrame frame = new JFrame("Wordle");
    frame.setContentPane(mainPanel);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    
  }
}
