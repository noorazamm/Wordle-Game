import javax.swing.*;
import java.awt.event.*;

/**ExitController 
  * exits the game 
  *    @since Jan 16, 2023
  *    @author Farah Ismail*/
public class ExitController implements ActionListener{
  private WordleModel wordle; 
  
  /**ExitController  exits the game (system)when a button is clicked 
    */
  public ExitController(WordleModel wordle){
    this.wordle = wordle; 
  } 
  
  /**the action performed by the controller using the data in the model
    * @param e - the event that took place*/
  public void actionPerformed(ActionEvent e){  
    //count the points in case user exits before the game is over
    this.wordle.countPoints();
    //save data to a txt file
    this.wordle.saveData();
    //exit the system
    System.exit(1);
    
  }
}
