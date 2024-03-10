import java.util.*;
import java.util.*;
import java.io.*;

/** WordleModel
  *  The model for Wordle game 
  *    @since Jan 20, 2023
  *    @author Noor Azam and Farah Ismail
  */
public class WordleModel extends Object
{
  //variable declaration
  private WordleView view;                            //the view for the game
  private File wordBank = new File("word bank.txt");  //word bank file
  private File userBank = new File("user bank.txt");  //duplicate of word bank file used to check if user entered an existing word
  private Scanner in;                                 //scanner used to get input from file
  private Scanner inUser;                             //scanner used to check users guesses
  private String word;                                //the word to guess
  private int[][] points;                             //array of user and computers points
  private String[] playerLetters = new String[5];     //the letters of the users current guess
  private String[] playerColours = new String[5];     //stores the colours for the users guesses letters
  private String[] compGuesses;                       //stores the computers guess letters
  private String[] compColours;                       //stores the computers colours
  private String[] highestGreenNum = new String[]{"Grey", "Grey", "Grey", "Grey", "Grey"};    //the color sequence that the computer will compare to, stores the highest number of green letters guessed
  private String[] guessedWords = new String[6];      //array of all previous guesses 
  private String userGreenLetters;                    //letters user guessed that are part of the word to guess
  private String compGreenLetters;                    //letters computer guessed that are part of the word to guess 
  private String yellowLetters = "";                  //stores all yellow letters in a string
  private String[][] yellowsArr = new String[6][5];   //stores the yellow letters in their positions
  private String greyLetters = "";                    //stores all grey letters guessed
  private int guessNum = 0;                           //the number of guesses the user/computer is at 
  private int numWords = 9005;                        //the number of words in the word bank file
  private boolean wordGuessed = false;                //true if word has been guessed by a player
  private int wordBankPosition = 1;                   //the position of the words checked by computer in the word bank
  private int playerWins;                             //the players wins
  private int compWins;                               //the computers wins
  private String winner = "filler";                   //stores who won round
  private int rounds;                                 //stores total number of rounds
  private int currentRound = 1;                       //keeps track of the current round
  private boolean hasBeenOpened = false;              //true if the computer has opened the file before
  private int playerTotal = 0;                        //the total points the player has
  private int compTotal = 0;                          //the total points the computer has
  
  
  /** Creates a default game*/
  public WordleModel()
  {
    super();
    this.word = generateWord();
  }
  
  /** Sets the view for the wordle game
    * @param currentGUI        The View used to display the wordle game
    */ 
  public void setGUI(WordleView currentGUI)
  {
    this.view = currentGUI;
  }
  
  /** Get the number of guesses 
    * @return guessNum*/
  public int getNumGuesses()
  {
    return(this.guessNum);
  }
  
  /** Get the word to guess
    * @return word*/
  public String getWord()
  {
    return(this.word);
  }
  
  /** Get the users guesses 
    * @return guesses*/
  public int[][] getPoints()
  {
    return(this.points);
  }
  
  /** Get the green letters that the user guessed
    * @return userGreenLetters*/
  public String getGreenLetters()
  {
    return (userGreenLetters);
  }
  
  /**get the string of the users colours
    * @return playerColours**/
  public String[] getPlayerColours()
  {
    return (this.playerColours);
  }
  
  /**get the array of letters from users guess
    * @return playerLetters**/
  public String[] getPlayerLetters()
  {
    return(this.playerLetters);
  }
  
  /**get the array of letters from computers guess
    * @return compGuesses**/
  public String[] getCompLetters()
  {
    return(this.compGuesses);
  }
  
  /**get the computers colours
    * @return compColors**/
  public String[] getCompColours()
  {
    return(this.compColours);
  }
  
  /**get the number of rounds
    * @return rounds**/
  public int getRounds()
  {
    return(this.rounds);
  }
  
  /**get the current round
    * @return currentRound**/
  public int getCounter()
  {
    return(this.currentRound);
  }
  
  /**get the winner
    * @return winner**/
  public String getWinner()
  {
    return(this.winner);
  }
  
  /**get the players wins
    * @return playerWins**/
  public int getPlayerWins()
  {
    return(this.playerWins);
  }
  
  /**get the computers wins
    * @return compWins**/
  public int getCompWins()
  {
    return(this.compWins);
  }
  
  /**get whether the word has been guessed or not
    * @return wordGuessed**/
  public boolean getWordGuessed()
  {
    return(this.wordGuessed);
  }
  
  /**set the number of rounds
    * @param rounds the number of rounds from user**/
  public void setRounds(int roundsNum)
  {
    this.rounds = roundsNum;
    this.points = new int[this.rounds][2];
  }
  
  /**get the players total points
    * @return playerTotal**/
  public int getPlayerTotal()
  {
    return(this.playerTotal);
  }
  
  /**get the computers total points
    * @return compTotal**/
  public int getCompTotal()
  {
    return(this.compTotal);
  }
  
  /**validates file**/
  private void openFile(File file)
  {
    //validate the file used for the computers guess 
    if (file == this.wordBank)
    {
      try {
        this.in = new Scanner(file);
      }catch (FileNotFoundException ex) {}
      
      this.wordBankPosition = 1;
    }
    
    //validate the file used for validating users word
    else if (file == this.userBank)
    {
      try {
        this.inUser = new Scanner(file);
      }catch (FileNotFoundException ex) {}
    }
  }
  
  /**choose a random word from the word bank file
    * @retuns word**/
  private String generateWord()
  {
    String word = "filler";  //the generated word 
    int random = (int)(Math.random()*(this.numWords-1)+1);   //generate a random number
    
    openFile(this.wordBank);
    
    //loop through a random number of lines to choose a random word
    for (int i = 0; i < random; i++)
    {
      in.nextLine();
    }
    
    //save word 
    word = in.nextLine();
    
    return(word);
  }
  
  /**check if what user entered is a word or not
    * @param guess the users guess
    * @returns isValid*/
  public boolean validateWord(String guess)
  {
    boolean isValid = false;   //true is the word is valid
    
    //open user bank file 
    openFile(this.userBank);
    
    //loop through every word in the file to check if it matches the guess
    for (int i = 0; i <= this.numWords; i++)
    {
      if (guess.equals(inUser.nextLine()))
        isValid = true;
    }
    
    //close file
    inUser.close();
    return(isValid);
  }
  
  /**checks the users word for accuracy
    * @param word the word the user guesses**/
  public void checkWord(String guess)
  {
    //save guess
    this.guessedWords[this.guessNum] = guess;
    
    //reset values to be used in color checking
    this.userGreenLetters = "";
    this.playerColours = new String[]{"Grey", "Grey", "Grey", "Grey", "Grey"};
    
    //save guess as an array
    this.playerLetters = saveAsArray(guess);
    
    //check for green, yellow, and grey letters
    checkForGreen(this.playerLetters, this.playerColours, "User");
    checkForYellow(this.playerLetters, this.playerColours, this.userGreenLetters, "User");
    checkForGrey(this.playerLetters);
    
    //if any new green letters are guessed, add it to the index
    updateHighestGreenNum(this.playerColours);
    
    this.guessNum++;
    this.updateView();
    
    //if there are 5 green letters update winner info
    if (this.userGreenLetters.length() == 5)
    {
      this.wordGuessed = true;
      this.playerWins++;
      this.winner = "Player";
      countPoints();
      this.updateView();
    }
    
    //if user has not guessed, computer takes turn
    else
      computerGuess();
  }
  
  /**computers turn, makes a guess**/
  public void computerGuess()
  {
    boolean isValid = false;      //true if computers guess matches green letters of the users
    boolean hasGreens = false;    //true if any green letters have been guesses before
    String word = "word";         //string of computer guess 
    
    //open file if it hasn't been opened before
    if (this.hasBeenOpened == false)
    {
      openFile(this.wordBank);
      this.hasBeenOpened =true;
    }
    
    //reset previous computer guess values
    this.compGreenLetters = "";
    
    //check if a green letter has been guessed previously
    for (int i = 0; i <=4; i++)
    {
      if (this.highestGreenNum[i].equals("Green"))
      {
        hasGreens = true;
        break;
      }       
    }
    
    //loop through words in word bank till one matches the criteria
    while (isValid == false)
    {   
      //reset value of computer colors
      this.compColours = new String[]{"Grey", "Grey", "Grey", "Grey", "Grey"};

      //if no green letter has been guessed before, generate a random word
      if (hasGreens == false)
      {
        word = generateWord();
        
        //re-open file so that cursor can start from the beginning next turn
        in.close();
        openFile(this.wordBank);
        this.wordBankPosition = 1;
      }
      
      //if green has been guessed before, continue to next word in the word bank
      else
        word = in.nextLine();
      
      //save as array
      this.compGuesses = saveAsArray(word);
      
      //modify computers color array and green letters
      checkForGreen(this.compGuesses, this.compColours, "Computer"); 
      
      //check if word meets all requirements (has yellows in appropriate spots, same green letters, and no grey letters)
      isValid = compareColors(this.compColours, word, hasGreens);
      this.wordBankPosition++;
    }
    
    //check for yellow and grey
    checkForYellow(this.compGuesses, this.compColours, this.compGreenLetters, "Computer");
    checkForGrey(this.compGuesses);
    
    //if word includes any new green letters, add it to index
    updateHighestGreenNum(this.compColours);
    
    //save guess in array of guesses
    this.guessedWords[this.guessNum] = word;
    
    this.guessNum++;
    
    //if there are 5 green letters, computer has guessed word, update winner info
    if (this.compGreenLetters.length() == 5)
    {
      this.wordGuessed = true;
      this.compWins++;
      this.winner = "Computer";
      countPoints();
      this.updateView();
    }
    
    //update view 
    this.updateView();
  }
  
  /**saves a string as an array by letter
    * @param word the word to save as an array
    * @return array**/
  private String[] saveAsArray(String word)
  {
    String[] array = new String[5];  //create array
    
    //copy letters in string into array positions one by one
    for (int i = 0; i <= 4; i++)
    {
      array[i] = Character.toString(word.charAt(i));
    }
    
    //return the array
    return(array);
  }
  
  /**updates any new green characters from user in the highestGreenNum array
    * @param colours the colours of either user or computer**/
  private void updateHighestGreenNum(String[] colours)
  {
    //loop through each position and change the colour in the highestGreeNum array if green
    for(int i = 0; i <=4; i++)
    {
      if (colours[i].equals("Green"))
      {
        this.highestGreenNum[i] = "Green";
      }
    }
  }
  
  /**checks if the computers guess has the same green letters that were guessed before in the same position
    * @param comp the colours of the computers guess
    * @param word the computers word
    * @param hasGreens if any greens have been guessed before computers guess
    * @return isValid**/
  private boolean compareColors(String[] comp, String word, boolean hasGreens)
  {
    boolean isValid = true;   //true if word fits criteria to be used
    
    //loops for every grey letter guessed so far
    for (int i = 0; i < this.greyLetters.length(); i++)
    {
      //makes word invalid and breaks if it includes one of them 
      if (word.indexOf(this.greyLetters.charAt(i)) != -1)
      {
        isValid = false;
        break;
      }
    }
    
    //if the word is still valid, continue searching for conditions
    if (isValid == true)
    {
      //loop for every yellow letter found
      for (int i = 0; i < this.yellowLetters.length(); i++)
      {
        //makes word invalid and breaks if it doesn't include one of them
        if (word.indexOf(this.yellowLetters.charAt(i)) == -1)
        {
          isValid = false;
          break;
        }
      }
    }
    
     //if the word is still valid, continue searching for conditions
    if (isValid == true)
    {
      loop:
      //loop for every column of the wordle grid 
      for (int col = 0; col <=4; col++)
      {
        //loop for the amount of guesses the computer is at
        for(int row = 0; row <this.guessNum; row++)
        {
          //if the current letter is equal to any letter in the same column of the yellow letter array, make it invalid and break
          if (Character.toString(word.charAt(col)).equals(this.yellowsArr[row][col]))
          {
            isValid = false;
            break loop;
          }
        }
      }
    }
    
    //if there are green letters and word is still valid, check if the word matches them
    if (hasGreens == true && isValid == true)
    {
      //run for number of letters in word
      for (int i = 0; i <=4; i++)
      {
        //if the current position of the user letter is green but the computers isnt, word is not valid
        if (this.highestGreenNum[i].equals("Green"))
        {
          if (!comp[i].equals("Green"))
          {
            isValid = false;
            break;
          }
        }
      }
    }
    
    //if the word is still valid, continue searching for conditions
    if (isValid == true)
    {
      //run for number of guesses
      for (int i = 0; i <this.guessNum; i++)
      {
        //if word is equal to a word guessed before, word is not valid
        if (word.equals(this.guessedWords[i]))
        {
          isValid = false;
          break;
        }
      }
    }
    
    //return isValid
    return(isValid);
  }
  
  /**checks word for matching letters in the same position and adjusts colours
    * @param word the word guessed
    * @param colours the array of colours to be adjusted based on green letters found
    * @param user who the method is being used for*/
  private void checkForGreen(String[] word, String[] colours, String player)
  {
    String greens = "";  //the string of green letters found
    
    //loop for each letter in word
    for (int i = 0; i <= 4; i++)
    {
      //add letter to variable and modify colors if the letters are the same in the same position
      if (word[i].equals(Character.toString(this.word.charAt(i))))
      {
        greens = greens.concat(word[i]);
        colours[i] = "Green";
      }
    }
    
    //save info to respective variables depending on who it's for
    if (player.equals("Computer"))
    {
      this.compColours = colours;
      this.compGreenLetters = greens;
    }
    else if (player.equals("User"))
    {
      this.playerColours = colours;
      this.userGreenLetters = greens;
    }
  }
  

  /**checks word for matching letters in different positions and adjusts colours
    * @param word the word guessed
    * @param colours the array of colours to be adjusted based on yellow letters found
    * @param tempGreens green letters in the users guess
    * @param user user or computer who the method is being used for*/
  private void checkForYellow(String[] word, String[] colours, String greens, String user)
  {
    String userLetter;              //the current letter in the guess array
    String wordLetter;              //current letter in the word to guess
    boolean isGreen;                //true if currentLetter is green
    String tempYellowLetters = "";  //stores the yellow characters in the guess found so far in the current word so that they dont get repeated
    
    //loop for every letter in guess
    for (int n = 0; n <=4; n++)
    {
      userLetter = word[n];

      //run if current letter is not green 
      if (greens.indexOf(userLetter) == -1)
      {
        //loop for each letter in the word to guess         
        for (int j = 0; j <=4; j++)
        {
          wordLetter = Character.toString(this.word.charAt(j));
          
          //compare the letter in the guessed word to every letter in the word to guess and check if it is already part of string of yellow letters
          if (userLetter.equals(wordLetter) && tempYellowLetters.indexOf(userLetter) == -1)
          {
            //add yellow letter to all variables that deal with yellow letters
            colours[n] = "Yellow";
            this.yellowsArr[this.guessNum][n] = userLetter;
            this.yellowLetters = this.yellowLetters.concat(userLetter);
            tempYellowLetters = tempYellowLetters.concat(userLetter);
          }
        }
      }
    }
    
    //save info to variable depending on who it's for
    if (user.equals("Computer"))
    {
      this.compColours = colours;
    }
    else if (user.equals("User"))
    {
      this.playerColours = colours;
    }
  }
  
  
  /**checks if there are grey letters and adds them to a string
    * @param guess the word guessed**/
  private void checkForGrey(String[] guess)
  {
    //loop for every letter in the word
    for (int i = 0; i <=4; i++)
    {
      //if word is not part of the word to be guessed and is not already part of the grey letter variable, add to the grey letter variable
      if (this.word.indexOf(guess[i]) == -1 && this.greyLetters.indexOf(guess[i]) == -1)
      {
        this.greyLetters = this.greyLetters.concat(guess[i]);
      }
    }
  }
  
  /**udpdate points at the end of round**/
  public void countPoints()
  {
    //add points to user or computer based on what turn the guessed the word on
    if (this.winner.equals("Computer"))
      this.points[this.currentRound-1][1] = 7 - this.guessNum;
    else if (this.winner.equals("Player"))
      this.points[this.currentRound-1][0] = 7 - this.guessNum;
    
    //calculate winner
    calcWinner();
    
    //generate a new word for the next round
    this.word = generateWord(); 
  }
  
  /**resets round values**/
  public void resetRound()
  {
    this.guessNum = 0;
    this.hasBeenOpened = false;
    this.yellowLetters = "";
    this.greyLetters = "";
    this.highestGreenNum = new String[]{"Grey", "Grey", "Grey", "Grey", "Grey"};
    this.yellowsArr = new String[6][5];
    this.winner = "filler";
    this.word = generateWord();
    this.wordGuessed = false;
  }
  
  /**Reset the values in the model every time the game ends*/
  public void reset(){
    //reset the word to guess
    this.word = generateWord();
    this.winner = "filler";
    
    //reset the number of guesses the player/computer have
    this.guessNum = 0; 
    
    //reset wins and rounds
    this.playerWins = 0; 
    this.compWins = 0; 
    this.rounds = 0; 
    this.currentRound = 1;
    this.wordGuessed = false;
    
    //reset the rows and columns of the points array
    this.points = new int [0][0];
    
    //reset variables used for computer to guess a word 
    this.hasBeenOpened = false;
    this.yellowLetters = "";
    this.greyLetters = "";
    this.highestGreenNum = new String[]{"Grey","Grey","Grey","Grey","Grey"};
    this.yellowsArr = new String[6][5];
    
    //reset total points
    this.playerTotal = 0;
    this.compTotal = 0;
  }
  
  /**Calculates the total points of player and computer by adding all points in the array*/
  private void calcWinner(){
    
    this.playerTotal += points[this.currentRound-1][0];
    this.compTotal += points[this.currentRound-1][1];
  }
  
  
  /**increment the rounds by one everytime next round is clicked*/
  public void addRound(){
    this.currentRound++;
  }
  
  /**Creates a new file with the results of the game*/
  public void saveData(){
    
    //create a new file
    File outputFile = new File("Wordle Game Results.txt");
    
    //try to create a printwriter that will print data into file once its created 
    try{
      //create a printwriter that will print data into a file given
      PrintWriter output = new PrintWriter(outputFile);
      //label the rows and columns of the 2d array
      output.println("Results:");
      output.println("Round:\tPlayer:\tComputer:");
      //loop through the 2d array to print the points of each round
      for (int i = 0; i < points.length; i++) {
        //print to the file each round number and its points separated by spaces
        output.println((i + 1) + "\t" + points[i][0] + "\t" + points[i][1]);
      }
      output.println("Total:\t" + this.playerTotal + "\t" + this.compTotal);
      //close the file
      output.close();
      //let the user know the file has been saved
      System.out.println("file saved");
    }
    
    //if the output file is not found
    catch(FileNotFoundException ex){
      //get filenotfoundexception message 
      System.out.println(ex.getMessage());
      System.out.println("in" + System.getProperty("user.dir"));
    }     
  }
  
  /**connect to the view*/
  public void updateView(){
    this.view.updateGrid();
    this.view.update();
  }
}






