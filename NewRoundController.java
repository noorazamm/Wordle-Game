import javax.swing.*;
import java.awt.event.*;

/**NewRoundController 
  * count the points of each round when the player moves on to the next round in the game
  *    @since Jan 14, 2023
  *    @author Farah Ismail*/
public class NewRoundController implements ActionListener{
  //variable declaration
  private WordleModel wordle;      //the model manipulated by the controller
  private WordleView view;        //the view manipulated by the controller 
  private JLabel roundNumber;    //the label of round numbers during the game 
  
  
  /**NewRoundCOntroller  uses the model, to begin the next round
    * @param model - the model the controller manipulates*/  
  public NewRoundController(WordleModel model, WordleView theView, JLabel roundNum){
    //set the instances passed to the instances in the controllers 
    this.wordle = model;        
    this.view = theView;        
    this.roundNumber = roundNum;
    
  } 
  
  /**the action performed by the controller using the data in the model
    * @param e - the event that took place*/
  public void actionPerformed(ActionEvent e)
  { 
    //increment the rounds in the model
    this.wordle.addRound();
    //change the round number and make next round button invisible
    this.roundNumber.setText("Round " + wordle.getCounter()+ "/" + wordle.getRounds());
    
    //reset values for next round
    this.wordle.resetRound(); 
    //reset the view for the new round 
    this.view.resetRoundView();
    
  }
}
