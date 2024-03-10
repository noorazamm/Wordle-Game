import javax.swing.*;
import java.awt.event.*;

/**RoundsController 
  * sets the number of rounds given by the user in the model and updates the display
  *    @since Jan 19, 2023
  *    @author Farah Ismail*/

public class RoundsController implements ActionListener{
  //variable declaration
  private WordleModel wordle;         //the model manipulated by the controller
  private JTextField numRounds;      //the amount of rounds the user chooses to play
  private JLabel thePrompt;         //the label asking the user to enter the number of rounds
  private JButton round;           //the enter button 
  private JButton guessButton;    //the guess button
  private JLabel roundDisplay;   //the label of rounds in the view
  private JLabel word;
  
  /**RoundController  uses the model, and textfield to set the number of rounds for the game 
    * @param model - the model the controller manipulates
    * @param rounds - the number of rounds entered by the user
    * @param prompt - the label to change once the rounds are given
    * @param enter  - the button to enter the desired number of rounds
    * @param guess  - the button to enabe once the number of rounds is set*/
  
  public RoundsController(WordleModel model, JTextField rounds, JLabel prompt, JButton enter, JButton guess, JLabel roundNum, JLabel theWord){
    //set the instances passed to the instances in  in the controller  
    this.wordle = model;             
    this.roundDisplay = roundNum;
    this.numRounds = rounds;       
    this.thePrompt = prompt;      
    this.round = enter;           
    this.guessButton = guess;
    this.word = theWord;
  } 
  
  /**the action performed by the controller using the data in the model
    * @param e - the event that took place*/
  public void actionPerformed(ActionEvent e){  
    
    //create a variable for the rounds using the int in the textfield given by the user
    int rounds;
    //set the rounds in the model to the number the user gave if input is integer
    try{
      //turn the rounds in the textfield from string to int 
      rounds = (Integer.parseInt(this.numRounds.getText()));
      this.wordle.setRounds(rounds);
      
      //display the rounds in the view
      this.roundDisplay.setVisible(true);
      //make the round textfield invisible 
      this.numRounds.setVisible(false);
      //reset the textfield for the new game
      this.numRounds.setText(null);
      //make the enter button invisible
      this.round.setVisible(false);        
      //change the prompt jlabel
      this.thePrompt.setText("Enter your guess: ");
      //make the guess button visible
      this.guessButton.setVisible(true);
      //make the user's guess visible
      this.word.setVisible(true);
      //start to display rouns after each round
      this.roundDisplay.setText("Round " + wordle.getCounter() + "/" + wordle.getRounds());        
    }
    //set to invalid if input is not integer
    catch(NumberFormatException ae){
      this.numRounds.setText("Invalid");
    }
  }
}

