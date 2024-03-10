import javax.swing.*;
import java.awt.event.*;

/**KeyboardController  uses the model, and buttons to type the users guess into the textArea then color code the keyboard according to the accuracy of the word
  * @param model - the model the controller manipulates
  * @param aLetter - the letter clicked by the user
  * @param aWord - the word entered by the user*/

public class KeyboardController implements ActionListener{
  //variable declaration
  private WordleModel wordle;      //the model manipulated by the controller
  private JLabel theWord;     //the word typed 
  
  public KeyboardController(WordleModel model, JLabel aWord){
    this.wordle = model;        //set the model in the controller to the model passed 
    this.theWord = aWord;     //set the textArea in the controller to the textArea passed
  }
  
  /**the action performed by the controller using the data in the model
    * @param e - the event that took place*/
  public void actionPerformed(ActionEvent e){  
    //the string representation of the button clicked 
    String key = e.getActionCommand();
    //the text in the label
    String currentText = this.theWord.getText();
    
    int rounds = this.wordle.getRounds();
    if(rounds !=0){
      //start if: subtract one letter from the current text if action command is BACK
      if (key.equals("BACK")) {
        //if there is text in the jlabel
        if (currentText.length() > 0) {
          //set the new text to the same text minus the last substring 
          this.theWord.setText(currentText.substring(0, currentText.length() - 1));
        }            
      } 
      //otherwise if the action command is not BACK
      else {
        //add the action command to the current text for 5 letters 
        if(currentText.length() < 5){
          this.theWord.setText(this.theWord.getText() + key);
        }
      }
    }
  }
}
