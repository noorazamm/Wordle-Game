import javax.swing.*;
import java.awt.event.*;

/**GuessController 
  * sets the users guess in the model to the word the user entered using the keyboard in the game
  *    @since Jan 20, 2023
  *    @author Farah Ismail*/
public class GuessController implements ActionListener{
  //variable declaration
  private WordleModel wordle;      //the model manipulated by the controller
  private JLabel theWord;     //the user's guess
  private WordleView view;  //the view to update 
  private JLabel thePrompt;  //the prompt to update every round
  
  /**GuessControler  uses the model, and textArea to verify the user's guess based on correct word in model
    * @param model - the model the controller manipulates
    * @param word - the word entered by the user*/
  
  public GuessController(WordleModel model, WordleView theView, JLabel word, JLabel prompt){
    this.wordle = model;        //set the model in the controller to the model passed 
    this.theWord = word;        //set the textArea in the controller to the textArea passed 
    this.view = theView;       //set the view in the controller to the view passed 
    this.thePrompt = prompt;   //set the label in the controller to the label passed
  } 
  
  /**the action performed by the controller using the data in the model
    * @param e - the event that took place*/
  public void actionPerformed(ActionEvent e){  
    
    //get the word from the jlabel in the game    
    String guess = this.theWord.getText();
    
    //send the word to be checked by the model if the word is 5 letters and is valid 
    if(guess.length() == 5 && this.wordle.validateWord(guess) == true){
      this.wordle.checkWord(guess);
      //reset the jlabel of the word 
      this.theWord.setText("");
      //change the prompt if the user is still guessing 
      this.thePrompt.setText("Keep going!");
      //send the word to the grid in the view
      this.view.updateGrid();
    }
    
    //if the word is 5 lettes but is invalid, change the prompt 
    else if (guess.length() ==5 && this.wordle.validateWord(guess) == false)
    {
      this.thePrompt.setText("Not a word. Try again.");
    }
    
    //if the word is less than 5 letters, change the prompt 
    else if(guess.length()!=5){
      this.thePrompt.setText("Word is too short!");
    }
    
    //if the word is guessed, or the guesses are over, or the rounds are reset,change the prompt back to the starting prompt
    if(this.wordle.getWordGuessed()==true||this.wordle.getNumGuesses()==6||this.wordle.getRounds()==0){
      this.thePrompt.setText("Enter your guess: ");
    }
    
  }
}
