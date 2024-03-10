import javax.swing.*;
import java.awt.event.*;

/**ResetController 
  * resets the values of the game in the model and creates the file of game results when the player ends the game
  *    @since Jan 13, 2023
  *    @author Farah Ismail*/
public class ResetController implements ActionListener{
  //variable declaration
  private WordleModel wordle;      //the model manipulated by the controller
  private WordleView view;
  
  /**ResetController  uses the model, to reset the values and end the game
    * @param model - the model the controller manipulates*/
  
  public ResetController(WordleModel model, WordleView theView){
    this.wordle = model;        //set the model in the controller to the model passed 
    this.view = theView;
  }
  
  /**the action performed by the controller using the data in the model
    * @param e - the event that took place*/
  public void actionPerformed(ActionEvent e){  
    
    this.wordle.saveData();   //output the results of the game 
    this.view.resetGame();   //reset the view to the starting view
    this.wordle.reset();      //reset the values in the model 
    
  }
}
