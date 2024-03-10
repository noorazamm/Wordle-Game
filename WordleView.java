import javax.swing.*;
import java.awt.*;

/** Wordle View Class
  * The GUI View for the wordle game
  * @since 01/20/23
  * @author Lily Zhang
  */
public class WordleView extends JPanel
{
  //Instance Variables
  private WordleModel wordle;                                   //the wordle model
  private ConfettiAnimation confetti = new ConfettiAnimation(); //the confetti animation when user wins
  private JPanel guessDisplayPanel = new JPanel();              //the panel displaying the previous guesses
  private JPanel inputPanel = new JPanel();                     //the panel displaying the user's input
  private JPanel topInputPanel = new JPanel();                  //the top of the input panel
  private JPanel buttonPanel = new JPanel();                    //the bottom of the input panel, containing the buttons
  private JPanel pointsPanel = new JPanel();                    //the panel displaying the points
  private JPanel keyboardPanel = new JPanel();                  //the panel displaying the keyboard
  
  private JLabel prompt = new JLabel("Please enter the number of rounds you would like to play:");  //the label displaying the prompt for user
  private JTextField roundInput = new JTextField();                        //the textfield to enter number of rounds
  private JButton enter = new JButton("Enter");                            //enter button to submit guess
  private JButton exit = new JButton("Exit");                              //exit button
  private JButton nextRound = new JButton("Next Round");                   //button to move onto next round
  private JButton playAgain = new JButton("Play Again");                   //button to play again
  private JLabel computerPointsLabel = new JLabel("Computer points: ");    //label to display computer points
  private JLabel playerPointsLabel = new JLabel("Player points: ");        //label to display player points
  private JLabel playerPoints = new JLabel("0");                           //numerical value of player points
  private JLabel computerPoints = new JLabel("0");                         //numerical value of computer points
  private JLabel guessLabel = new JLabel("");                                //label displaying player's current guess
  private JButton guessButton = new JButton("Guess");                      //button to enter guess
  private JLabel roundNum = new JLabel();                                  //the current round display
  
  private JLabel winner = new JLabel("");                                    //winner of the whole game
  private JLabel roundWinner = new JLabel("");                               //winner of the round
  
  private JButton a = new JButton("A");                                   //all letters of the alphabet as buttons on the keyboard
  private JButton b = new JButton("B");
  private JButton c = new JButton("C");
  private JButton d = new JButton("D");
  private JButton e = new JButton("E");
  private JButton f = new JButton("F");
  private JButton g = new JButton("G");
  private JButton h = new JButton("H");
  private JButton i = new JButton("I");
  private JButton j = new JButton("J");
  private JButton k = new JButton("K");
  private JButton l = new JButton("L");
  private JButton m = new JButton("M");
  private JButton n = new JButton("N");
  private JButton o = new JButton("O");
  private JButton p = new JButton("P");
  private JButton q = new JButton("Q");
  private JButton r = new JButton("R");
  private JButton s = new JButton("S");
  private JButton t = new JButton("T");
  private JButton u = new JButton("U");
  private JButton v = new JButton("V");
  private JButton w = new JButton("W");
  private JButton x = new JButton("X");
  private JButton y = new JButton("Y");
  private JButton z = new JButton("Z");
  private JButton backspace = new JButton("BACK");   //back (delete) button on keyboard
  
  private JLabel [] round1Letters = new JLabel [5];  //the 5 letters of the first round's guess
  private JLabel [] round2Letters = new JLabel [5];  //the 5 letters of the second round's guess
  private JLabel [] round3Letters = new JLabel [5];  //the 5 letters of the third round's guess
  private JLabel [] round4Letters = new JLabel [5];  //the 5 letters of the fourth round's guess
  private JLabel [] round5Letters = new JLabel [5];  //the 5 letters of the fifth round's guess
  private JLabel [] round6Letters = new JLabel [5];  //the 5 letters of the sixth round's guess
  
  
  /** Constructor for the GUI view
    * @param newWordle the model for the wordle game
    */ 
  public WordleView (WordleModel newWordle)
  {
    super();
    this.wordle = newWordle;
    this.wordle.setGUI(this);
    
    //set components to invisible until needed to display
    this.winner.setVisible(false);
    this.roundWinner.setVisible(false);
    this.playerPoints.setVisible(false);
    this.playerPointsLabel.setVisible(false);
    this.computerPoints.setVisible(false);
    this.computerPointsLabel.setVisible(false);
    this.guessLabel.setVisible(false);
    this.nextRound.setVisible(false);
    this.playAgain.setVisible(false);
    this.guessButton.setVisible(false);
    this.roundNum.setVisible(false);
    this.confetti.setVisible(false);
    
    this.registerControllers();
    this.layoutView();
    this.update();
  }
  
  /** Draws the wordle layout*/ 
  private void layoutView()
  {
    //set fonts for jlabels
    guessLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
    winner.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 25));
    roundWinner.setFont(new Font("Serif", Font.PLAIN, 20));
    prompt.setFont(new Font("Serif", Font.PLAIN, 15));
    playerPoints.setFont(new Font("Helvetica", Font.PLAIN, 15));
    playerPointsLabel.setFont(new Font("Helvetica", Font.PLAIN, 15));
    computerPoints.setFont(new Font("Helvetica", Font.PLAIN, 15));
    computerPointsLabel.setFont(new Font("Helvetica", Font.PLAIN, 15));
    
    //add topInputLayout components
    BoxLayout topInputLayout = new BoxLayout(topInputPanel, BoxLayout.Y_AXIS);
    topInputPanel.setLayout(topInputLayout);
    topInputPanel.add(roundNum);
    topInputPanel.add(prompt);
    roundInput.setMaximumSize(new Dimension(300,100));
    topInputPanel.add(roundInput);
    topInputPanel.add(guessLabel);
    topInputPanel.add(enter);
    topInputPanel.add(guessButton);
    
    //add points panel components
    GridLayout pointsLayout = new GridLayout(1,2);
    pointsPanel.setLayout(pointsLayout);
    JPanel emptyPanel = new JPanel(); 
    pointsPanel.add(emptyPanel);
    
    //second panel for points
    JPanel pointsPanel2 = new JPanel();
    GridLayout pointsLayout2 = new GridLayout(2,2);
    pointsPanel2.setPreferredSize(new Dimension (700,300));
    pointsPanel2.setLayout(pointsLayout2);
    pointsPanel2.add(playerPointsLabel);
    pointsPanel2.add(playerPoints);
    pointsPanel2.add(computerPointsLabel);
    pointsPanel2.add(computerPoints);
    pointsPanel.add(pointsPanel2);
    
    //panel displaying the winner of round/game
    JPanel winnerPanel = new JPanel ();
    BoxLayout winnerLayout = new BoxLayout (winnerPanel, BoxLayout.Y_AXIS);
    winnerPanel.setLayout(winnerLayout);
    winnerPanel.add(roundWinner);
    winnerPanel.add(winner);
    
    //buttons panel
    BoxLayout buttonLayout = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
    buttonPanel.setLayout(buttonLayout);
    buttonPanel.add(nextRound);
    buttonPanel.add(exit);
    buttonPanel.add(playAgain); 
    buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT );
    
    //the total input panel
    BoxLayout inputLayout = new BoxLayout(inputPanel, BoxLayout.Y_AXIS);
    inputPanel.setLayout(inputLayout);
    inputPanel.setPreferredSize(new Dimension(700,700));
    inputPanel.add(topInputPanel);
    inputPanel.add(pointsPanel);
    inputPanel.add(winnerPanel);
    inputPanel.add(confetti);
    inputPanel.add(buttonPanel);
    
    //the guess display panel
    GridLayout guessLayout = new GridLayout(6,5);
    guessDisplayPanel.setLayout(guessLayout);
    guessDisplayPanel.setBorder(BorderFactory.createTitledBorder("GUESSES"));
    guessDisplayPanel.setPreferredSize(new Dimension (700,700));
    
    //initialize all the labels and add to the guess display panel
    for (int x = 0; x<5; x++) //first row
    {
      round1Letters[x] = new JLabel("");
      guessDisplayPanel.add(round1Letters[x]);
      round1Letters[x].setFont(new Font("Bahnshrift Light", Font.PLAIN, 30));
      round1Letters[x].setBorder(BorderFactory.createLineBorder(Color.BLACK));
      round1Letters[x].setHorizontalAlignment(JLabel.CENTER);
      round1Letters[x].setBackground(Color.GRAY);
      round1Letters[x].setOpaque(true);
    }
    
    //second row of letters
    for (int x = 0; x<5; x++)
    {
      round2Letters[x] = new JLabel("");
      guessDisplayPanel.add(round2Letters[x]);
      round2Letters[x].setFont(new Font("Bahnshrift Light", Font.PLAIN, 30));
      round2Letters[x].setBorder(BorderFactory.createLineBorder(Color.BLACK));
      round2Letters[x].setHorizontalAlignment(JLabel.CENTER);
      round2Letters[x].setBackground(Color.GRAY);
      round2Letters[x].setOpaque(true);
    }
    
    //third row of letters
    for (int x = 0; x<5; x++)
    {
      round3Letters[x] = new JLabel("");
      guessDisplayPanel.add(round3Letters[x]);
      round3Letters[x].setFont(new Font("Bahnshrift Light", Font.PLAIN, 30));
      round3Letters[x].setBorder(BorderFactory.createLineBorder(Color.BLACK));
      round3Letters[x].setHorizontalAlignment(JLabel.CENTER);
      round3Letters[x].setBackground(Color.GRAY);
      round3Letters[x].setOpaque(true);
    }
    
    //fourth row of letters
    for (int x = 0; x<5; x++)
    {
      round4Letters[x] = new JLabel("");
      guessDisplayPanel.add(round4Letters[x]);
      round4Letters[x].setFont(new Font("Bahnshrift Light", Font.PLAIN, 30));
      round4Letters[x].setBorder(BorderFactory.createLineBorder(Color.BLACK));
      round4Letters[x].setHorizontalAlignment(JLabel.CENTER);
      round4Letters[x].setBackground(Color.GRAY);
      round4Letters[x].setOpaque(true);
    }
    
    //fifth row of letters
    for (int x = 0; x<5; x++)
    {
      round5Letters[x] = new JLabel("");
      guessDisplayPanel.add(round5Letters[x]);
      round5Letters[x].setFont(new Font("Bahnshrift Light", Font.PLAIN, 30));
      round5Letters[x].setBorder(BorderFactory.createLineBorder(Color.BLACK));
      round5Letters[x].setHorizontalAlignment(JLabel.CENTER);
      round5Letters[x].setBackground(Color.GRAY);
      round5Letters[x].setOpaque(true);
    }
    
    //sixth row of letters
    for (int x = 0; x<5; x++)
    {
      round6Letters[x] = new JLabel("");
      guessDisplayPanel.add(round6Letters[x]);
      round6Letters[x].setFont(new Font("Bahnshrift Light", Font.PLAIN, 30));
      round6Letters[x].setBorder(BorderFactory.createLineBorder(Color.BLACK));
      round6Letters[x].setHorizontalAlignment(JLabel.CENTER);
      round6Letters[x].setBackground(Color.GRAY);
      round6Letters[x].setOpaque(true);
    }
    
    //the keyboard display at the bottom
    BoxLayout keyboardLayout = new BoxLayout(keyboardPanel, BoxLayout.Y_AXIS);
    keyboardPanel.setLayout(keyboardLayout);
    JPanel top = new JPanel();
    JPanel middle = new JPanel();
    JPanel bottom = new JPanel();
    
    //create 3 horizontal layouts for keyboard
    BoxLayout topLayout = new BoxLayout(top, BoxLayout.X_AXIS);
    BoxLayout middleLayout = new BoxLayout(middle, BoxLayout.X_AXIS);
    BoxLayout bottomLayout = new BoxLayout(bottom, BoxLayout.X_AXIS);
    top.setLayout(topLayout);
    middle.setLayout(middleLayout);
    bottom.setLayout(bottomLayout);
    
    //add letter buttons to each panel for keyboard
    top.add(q);
    top.add(w);
    top.add(e);
    top.add(r);
    top.add(t);
    top.add(y);
    top.add(u);
    top.add(i);
    top.add(o);
    top.add(p);
    top.add(backspace);
    
    middle.add(a);
    middle.add(s);
    middle.add(d);
    middle.add(f);
    middle.add(g);
    middle.add(h);
    middle.add(j);
    middle.add(k);
    middle.add(l);
    
    bottom.add(z);
    bottom.add(x);
    bottom.add(c);
    bottom.add(v);
    bottom.add(b);
    bottom.add(n);
    bottom.add(m);
    
    keyboardPanel.add(top);
    keyboardPanel.add(middle);
    keyboardPanel.add(bottom);
    
    //set font and colour for each button on keyboard
    a.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    a.setBackground(Color.BLACK);
    a.setOpaque(true);
    b.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    b.setBackground(Color.BLACK);
    b.setOpaque(true);
    c.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    c.setBackground(Color.BLACK);
    c.setOpaque(true);
    d.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    d.setBackground(Color.BLACK);
    d.setOpaque(true);
    e.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    e.setBackground(Color.BLACK);
    e.setOpaque(true);
    f.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    f.setBackground(Color.BLACK);
    f.setOpaque(true);
    g.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    g.setBackground(Color.BLACK);
    g.setOpaque(true);
    h.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    h.setBackground(Color.BLACK);
    h.setOpaque(true);
    i.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    i.setBackground(Color.BLACK);
    i.setOpaque(true);
    j.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    j.setBackground(Color.BLACK);
    j.setOpaque(true);
    k.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    k.setBackground(Color.BLACK);
    k.setOpaque(true);
    l.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    l.setBackground(Color.BLACK);
    l.setOpaque(true);
    m.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    m.setBackground(Color.BLACK);
    m.setOpaque(true);
    n.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    n.setBackground(Color.BLACK);
    n.setOpaque(true);
    o.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    o.setBackground(Color.BLACK);
    o.setOpaque(true);
    p.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    p.setBackground(Color.BLACK);
    p.setOpaque(true);
    q.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    q.setBackground(Color.BLACK);
    q.setOpaque(true);
    r.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    r.setBackground(Color.BLACK);
    r.setOpaque(true);
    s.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    s.setBackground(Color.BLACK);
    s.setOpaque(true);
    t.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    t.setBackground(Color.BLACK);
    t.setOpaque(true);
    u.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    u.setBackground(Color.BLACK);
    u.setOpaque(true);
    v.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    v.setBackground(Color.BLACK);
    v.setOpaque(true);
    w.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    w.setBackground(Color.BLACK);
    w.setOpaque(true);
    x.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    x.setBackground(Color.BLACK);
    x.setOpaque(true);
    y.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    y.setBackground(Color.BLACK);
    y.setOpaque(true);
    z.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    z.setBackground(Color.BLACK);
    z.setOpaque(true);
    backspace.setFont(new Font("Bahnshrift Light", Font.PLAIN, 50));
    backspace.setBackground(Color.BLACK);
    backspace.setOpaque(true);
    
    //the complete layout
    BorderLayout completeLayout = new BorderLayout();
    this.setLayout(completeLayout);
    this.add(guessDisplayPanel, BorderLayout.CENTER);
    this.add(inputPanel, BorderLayout.EAST);
    this.add(keyboardPanel, BorderLayout.SOUTH);
  }
  
  
  /**Updates view of grid and keyboard according to current game situation
    */ 
  public void updateGrid()
  {
    String [] playerLetters = wordle.getPlayerLetters(); //the player's guess, stored letter by letter
    String [] playerColours = wordle.getPlayerColours(); //the colours of each letter in relation to the player's guess
    String [] computerLetters = wordle.getCompLetters(); //the computer's guess, stored letter by letter
    String [] computerColours = wordle.getCompColours(); //the colours of each letter in relation to the computer's guess
    Color letterColour = Color.BLACK;                    //the colour of each letter converted to Color
    
    //repeat for each letter in the word
    for (int x = 0; x <5; x++)
    {
      //determine whose turn it is
      if (wordle.getNumGuesses() % 2 != 0) //odd guess number - player's turn  
      {
        letterColour = checkColour(playerColours[x]); //set letterColour to the colour of the letter guessed
        
        //display the letters guessed and their colours based on which guess player is on
        if (wordle.getNumGuesses() == 1) //guess 1
        {
          round1Letters[x].setText(playerLetters[x]);
          round1Letters[x].setBackground(letterColour);
        }
        else if (wordle.getNumGuesses() == 3) //guess 3
        {
          round3Letters[x].setText(playerLetters[x]);
          round3Letters[x].setBackground(letterColour);
        }
        else if (wordle.getNumGuesses() == 5) //guess 5
        {
          round5Letters[x].setText(playerLetters[x]);
          round5Letters[x].setBackground(letterColour);
        }
        
        //change the letter's colour on the keyboard
        updateKeyboard(playerLetters[x], playerColours[x], letterColour);
      }
      else //computer's turn
      {
        letterColour = checkColour(computerColours[x]); //set letterColour to the colour of the letter guessed
        
        //display the letters guessed and their colours based on which guess computer is on
        if (wordle.getNumGuesses() == 2) //guess 2
        {
          round2Letters[x].setText(computerLetters[x]);
          round2Letters[x].setBackground(letterColour);
        }
        else if (wordle.getNumGuesses() == 4) //guess 4
        {
          round4Letters[x].setText(computerLetters[x]);
          round4Letters[x].setBackground(letterColour);
        }
        else if (wordle.getNumGuesses() == 6) //guess 6
        {
          round6Letters[x].setText(computerLetters[x]);
          round6Letters[x].setBackground(letterColour);
        }  
        
        //change the letter's colour on the keyboard
        updateKeyboard(computerLetters[x], computerColours[x], letterColour);
      } 
    }
  }
  
  
  /** Updates the keyboard's colours
    * @param letter - The letter that was guessed
    * @param colour - The colour associated to the guess as a string
    * @param theColour - The colour associated to the guess as a Color
    */ 
  private void updateKeyboard(String letter, String colour, Color theColour)
  {
    //convert guess to a char
    char letterAsChar = letter.charAt(0);
    
    //determine which letter and change colour of button to theColour
    switch (letterAsChar)
    {
      case 'A':
        this.a.setBackground(theColour);
        break;
      case 'B':
        this.b.setBackground(theColour);
        break;
      case 'C':
        this.c.setBackground(theColour);
        break;
      case 'D':
        this.d.setBackground(theColour);
        break;
      case 'E':
        this.e.setBackground(theColour);
        break;
      case 'F':
        this.f.setBackground(theColour);
        break;
      case 'G':
        this.g.setBackground(theColour);
        break;
      case 'H':
        this.h.setBackground(theColour);
        break;
      case 'I':
        this.i.setBackground(theColour);
        break;
      case 'J':
        this.j.setBackground(theColour);
        break;
      case 'K':
        this.k.setBackground(theColour);
        break;
      case 'L':
        this.l.setBackground(theColour);
        break;
      case 'M':
        this.m.setBackground(theColour);
        break;
      case 'N':
        this.n.setBackground(theColour);
        break;
      case 'O':
        this.o.setBackground(theColour);
        break;
      case 'P':
        this.p.setBackground(theColour);
        break;
      case 'Q':
        this.q.setBackground(theColour);
        break;
      case 'R':
        this.r.setBackground(theColour);
        break;
      case 'S':
        this.s.setBackground(theColour);
        break;
      case 'T':
        this.t.setBackground(theColour);
        break;
      case 'U':
        this.u.setBackground(theColour);
        break;
      case 'V':
        this.v.setBackground(theColour);
        break;
      case 'W':
        this.w.setBackground(theColour);
        break;
      case 'X':
        this.x.setBackground(theColour);
        break;
      case 'Y':
        this.y.setBackground(theColour);
        break;
      case 'Z':
        this.z.setBackground(theColour);
        break;
    }   
  }
  
  
  /** Determines the colour of the letter guessed
    * @param colour - the String of the colour's name
    * @returns aColour - the String as a colour
    */
  private Color checkColour(String colour)
  {
    Color aColour; //colour string as a Color
    
    //convert String to Color
    if (colour.equals("Green"))       //green letter
      aColour = Color.GREEN;
    else if (colour.equals("Yellow"))  //yellow letter
      aColour= Color.YELLOW;
    else                               //grey letter
      aColour = Color.GRAY;
    
    return aColour;
  }
  
  
  /** Resets all values back to starting screen
    */
  public void resetGame()
  {
    //reset all values 
    this.winner.setVisible(false);
    this.roundWinner.setVisible(false);
    this.playerPoints.setVisible(false);
    this.playerPointsLabel.setVisible(false);
    this.computerPoints.setVisible(false);
    this.computerPointsLabel.setVisible(false);
    this.guessLabel.setVisible(false);
    this.nextRound.setVisible(false);
    this.playAgain.setVisible(false);
    this.guessButton.setVisible(false);
    this.guessButton.setEnabled(true); 
    this.roundNum.setVisible(false);
    this.confetti.setVisible(false);
    
    //display components to enter number of rounds
    this.roundInput.setVisible(true);
    this.enter.setVisible(true);
    this.prompt.setText("Please enter the number of rounds you would like to play:");
    
    //reset letters and colours
    resetGuessDisplay();
    resetKeyboard();  
    this.revalidate();
    this.repaint();
  }
  
  
  /** Resets the display of the GUI for the next round
    */
  public void resetRoundView()
  {
    resetKeyboard();
    resetGuessDisplay();
    this.guessButton.setEnabled(true);
    this.roundWinner.setVisible(false);
    this.nextRound.setVisible(false);
  }
  
  
  /** Resets the keyboard colours to black
    */
  private void resetKeyboard()
  {
    //reset keyboard colours
    this.a.setBackground(Color.BLACK);
    this.b.setBackground(Color.BLACK);
    this.c.setBackground(Color.BLACK);
    this.d.setBackground(Color.BLACK);
    this.e.setBackground(Color.BLACK);
    this.f.setBackground(Color.BLACK);
    this.g.setBackground(Color.BLACK);
    this.h.setBackground(Color.BLACK);
    this.i.setBackground(Color.BLACK);
    this.j.setBackground(Color.BLACK);
    this.k.setBackground(Color.BLACK);
    this.l.setBackground(Color.BLACK);
    this.m.setBackground(Color.BLACK);
    this.n.setBackground(Color.BLACK);
    this.o.setBackground(Color.BLACK);
    this.p.setBackground(Color.BLACK);
    this.q.setBackground(Color.BLACK);
    this.r.setBackground(Color.BLACK);
    this.s.setBackground(Color.BLACK);
    this.t.setBackground(Color.BLACK);
    this.u.setBackground(Color.BLACK);
    this.v.setBackground(Color.BLACK);
    this.w.setBackground(Color.BLACK);
    this.x.setBackground(Color.BLACK);
    this.y.setBackground(Color.BLACK);
    this.z.setBackground(Color.BLACK);
  }
  
  
  /** Resets the guess display panel
    */
  private void resetGuessDisplay()
  {
    //reset guess display labels to display no letter and back to grey background
    for (int x = 0; x<5; x++)
    {
      round1Letters[x].setText("");
      round1Letters[x].setBackground(Color.GRAY);
      round2Letters[x].setText("");
      round2Letters[x].setBackground(Color.GRAY);
      round3Letters[x].setText("");
      round3Letters[x].setBackground(Color.GRAY);
      round4Letters[x].setText("");
      round4Letters[x].setBackground(Color.GRAY);
      round5Letters[x].setText("");
      round5Letters[x].setBackground(Color.GRAY);
      round6Letters[x].setText("");
      round6Letters[x].setBackground(Color.GRAY);
    }
  }
  
  
  /**Assigns the controllers to the buttons, labels, and textfield
    */ 
  private void registerControllers()
  {
    //controller for keyboard buttons
    KeyboardController keyCntrl = new KeyboardController(this.wordle, guessLabel);
    this.a.addActionListener(keyCntrl);
    this.b.addActionListener(keyCntrl);
    this.c.addActionListener(keyCntrl);
    this.d.addActionListener(keyCntrl);
    this.e.addActionListener(keyCntrl);
    this.f.addActionListener(keyCntrl);
    this.g.addActionListener(keyCntrl);
    this.h.addActionListener(keyCntrl);
    this.i.addActionListener(keyCntrl);
    this.j.addActionListener(keyCntrl);
    this.k.addActionListener(keyCntrl);
    this.l.addActionListener(keyCntrl);
    this.m.addActionListener(keyCntrl);
    this.n.addActionListener(keyCntrl);
    this.o.addActionListener(keyCntrl);
    this.p.addActionListener(keyCntrl);
    this.q.addActionListener(keyCntrl);
    this.r.addActionListener(keyCntrl);
    this.s.addActionListener(keyCntrl);
    this.t.addActionListener(keyCntrl);
    this.u.addActionListener(keyCntrl);
    this.v.addActionListener(keyCntrl);
    this.w.addActionListener(keyCntrl);
    this.x.addActionListener(keyCntrl);
    this.y.addActionListener(keyCntrl);
    this.z.addActionListener(keyCntrl);
    this.backspace.addActionListener(keyCntrl);
    
    //controller for entering the number of rounds at the beginning of game
    RoundsController roundCntrl = new RoundsController(this.wordle, this.roundInput, this.prompt, this.enter, this.guessButton, this.roundNum, this.guessLabel);
    this.enter.addActionListener(roundCntrl);
    
    //controller for the button to enter guess
    GuessController guessCntrl = new GuessController(this.wordle, this, this.guessLabel, this.prompt);
    this.guessButton.addActionListener(guessCntrl);
    
    //controller for play again button
    ResetController resetCntrl = new ResetController(this.wordle, this);
    this.playAgain.addActionListener(resetCntrl);
    
    //controller for next round button
    NewRoundController newRoundCntrl = new NewRoundController(this.wordle, this, this.roundNum);
    this.nextRound.addActionListener(newRoundCntrl);
    
    //controller for exit button
    ExitController exitCntrl = new ExitController (this.wordle);
    this.exit.addActionListener(exitCntrl);
  }
  
  
  /** Updates view of the wordle to the current situation
    */ 
  public void update()
  { 
    //when word is guessed or out of guesses, display view to move onto next round/end game
    if (wordle.getWordGuessed() == true || wordle.getNumGuesses() == 6)
    {
      //disable the guess button so player cannot enter more guesses
      this.guessButton.setEnabled(false);
      
      //if not done the game, display the nextRound button
      if(wordle.getCounter() != wordle.getRounds())
        this.nextRound.setVisible(true);
      
      //display message based on who the winner of the round is
      if (wordle.getWinner().equals("Player")) //player wins the round
        this.roundWinner.setText("You win this round!");
      else if (wordle.getWinner().equals("Computer")) //computer wins the round
        this.roundWinner.setText("Computer wins this round!");
      else
        this.roundWinner.setText("The word was " + wordle.getWord());
      
      this.roundWinner.setVisible(true);
    }
    
    //if the game is done, display the winner
    if (wordle.getCounter() == wordle.getRounds())
    {
      if (wordle.getWordGuessed() == true || wordle.getNumGuesses()==6)
      {
        //disable guess button to prevent futher guesses
        this.guessButton.setEnabled(false);
        
        //determine winner and display
        if (wordle.getPlayerTotal() > wordle.getCompTotal()) //player has more points than computer
        {
          this.winner.setText("Congrats! You win the game!");
          this.confetti.setVisible(true);  //display confetti to congratulate user
        }
        else if (wordle.getPlayerTotal() < wordle.getCompTotal()) //computer has more points than player
        {
          this.winner.setText("Computer wins! Better luck next time.");
        }
        else //tie game
        {
          this.winner.setText("Tie Game!");
        }
        
        //display winner of game
        this.winner.setVisible(true);
        this.playAgain.setVisible(true);
      }  
    }
    
    //after user enters number of rounds, display the guessing screen
    if (this.enter.isVisible() == false)
    {
      this.playerPoints.setVisible(true);
      this.playerPointsLabel.setVisible(true);
      this.computerPoints.setVisible(true);
      this.computerPointsLabel.setVisible(true);
      this.guessLabel.setVisible(true);
    }
    
    //update current points
    this.playerPoints.setText(Integer.toString(wordle.getPlayerTotal()));
    this.computerPoints.setText(Integer.toString(wordle.getCompTotal()));
  }
}

