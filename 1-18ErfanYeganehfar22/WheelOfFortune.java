/*
* Erfan Yeganehfar
* Ms. Krasteva
* December 17th, 2018
* Purpose:  This program executes a playable version of The Wheel Of Fortune.
	    The program starts off with a splashScreen method that animates a moving wheel of fortune spinning to the left with the title (Wheel of Fortune)
	    After 2 seconds the program continues to the mainMenu where the user may choose to play he game, see the highscores, the instructions, or to exit the program.
	    If the user chooses to play the game they are asked for each player's names. The game will start with player 1 going first, the players will either guess a letter
	    or the whole puzzle. If they are correct they gain prizes (points in $ format). The game ends when the puzzle is solved and a winner is choosen from the best scores.
	    The user will be asked if they want to redirect to the mainMenu. If they choose to reply the game, the game will re-iterate, if thwy choose to leave they are then
	    shown the mainMenu. If the user chooses to view the High Scores, they are shown the top 10 best winning High scores in ascending order. If there are no high scores
	    available then a message tells them that they're non at the moment. The user may also choose to clear the high scores if they'd like to. The user is also prompted
	    within the displayHighScores method if they would like to leave, if they would they are then redirected to the mainMenu once again. The program will end when the user
	    chooses to exit in the mainMenu. Inside the goodbye method they are shown a goodbye message and the console closes 2 seconds later

       Variable Dictionary
       Name             Type        Purpose
       c                Console     New console object where the program is displayed
       choice           String      Reprsesnts the value that the user inputs in mainMenu() to determine where they would like to continue with the program or exit
       puzzle           String      Stores the value of the current puzzle for the mainGame
       userGuess        String      Stores the user guess to then compare to the actual puzzle
       player1          String      Stores the first players username
       player2          String      Stores the second players username
       highScoreNames   String[]    An array of 10 highscore names used to display the highscores within the displayHighScores method
       wrdBank          String[]    Stores the various amounts of words, phrases, and nouns that are randomly chosen to represent the puzzle within the game
       highScores       int[]       An array of 10 high score values used to display the highscores within the displayHighScores method in ascending order
       puzzleNum        int         Stores the index of the current wrdBank string that is the puzzle for the current permutation, when it reaches the end of wrdBank.length
       player1Score     int         Stores the first player's score
       player2Score     int         Stores the second player's score


       Citation: Help from Harit + https://docs.oracle.com/javase/tutorial/2d/text/measuringtext.html for Font metrics (width of fonts)
       * String to char array from: https://www.javatpoint.com/java-string-tochararray
       * Labels can be found in the oracle documentation: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/branch.html
       * Example of ascending order buble sort at :https://www.javatpoint.com/bubble-sort-in-java
       * isCharAvail() is a hsa Conosle method found within the hsa library
       * Nick helped me with some file io (reading input till null) + leo fr the bubble sort highscore display

*/

import java.awt.*; // import java.awt library
import hsa.*; // import hsa library
import javax.swing.JOptionPane; // import JOptionPane
import java.io.*; // import io library

//main class WheelOfFortune
public class WheelOfFortune
{
    // Global class variables
    //Some variables are given literals to avoid a null pointer exception
    Console c;
    char choice;
    String userGuess = "", player1 = "", player2 = "", puzzle;
    String[] highScoreNames = new String [10];
    int[] highScores = new int [10];
    int puzzleNum = 0, player1Score, player2Score;
    String[] wrdBank = {
	"nADULT", "nAEROPLANE", "nBACKPACK", "nBATHROOM", "nBUTTERFLY", "nCHOCOLATES", "nDIAMOND", "nEXPLOSIVE", "nFEATHER", "nFESTIVAL", "nGEMSTONE", "nKALEIDOSCOPE", "nMICROSCOPE", "nMILKSHAKE", "nMOSQUITO",
	"nNECKLACE", "nPARACHUTE", "nPASSPORT", "nPENDULUM", "nPERFUME", "nPRISON", "nPYRAMID", "nRAINBOW", "nRESTAURANT", "nSANDPAPER", "nSANDWICH", "nSATELLITE", "nVULTURE", "pICS IS FUN", "pYAY COMPUTER SCIENCE", "pFRIDAYS ARE COOL", "eANDREY", "eMONTGOMERY",
	"eSINCLAIR", "eMICHAEL", "eALEXANDER HAMILTON", "pPINK FLOYD IS A GOOD BAND", "eFRANK SINATRA", "nCRAB RAVE", "nEUROPEAN UNION", "eJOHN LENNON", "eOZZY OSBOURNE", "pPLAY WHEEL OF FORTUNE", "pYOU ARE WHAT YOU EAT"};

    //Constructor for WheelOfFortune initilizes the Console c and gives the window a title
    public WheelOfFortune ()
    {
	c = new Console (16, "Wheel of Fortune");
    }


    /*
    prompts the user for any char to continue the program
    */
    public void pauseProgram ()  //Method  pauseProgram
    {
	c.println ("Press any key to continue...");
	//variable to receive user input to move on
	c.getChar ();
    }


    /*
    clears the screen and centers the program title
	Name        Type              Purpose
	FONT        final Font        initializes the title's font as Verdana Bold
    */
    private void title ()  // Method  title
    {
	c.clear (); // clears output screen
	final Font FONT = new Font ("MS Verdana", Font.BOLD, 30); // declares font as Verdana Bold
	c.setColor (new Color (204, 116, 10));
	c.setFont (FONT); //sets font
	printMidLn (FONT, "Wheel Of Fortune", 30, 2); // prints the centered title
    }


    /*
    [1] Prints text centered onto the console with a new line
    [2] Prints Fonts centered onto the console using FontMetrics

	Name    Type         Purpose
	fm      FontMetrics  Creates an instance of the FontMetrics Object to get the metrics (width in this case) of the Font
	canvas  Canvas       Creates an instance of a Canvas object to acces the getFontMetrics method
	width   int          Integer values of how width a String is based on a font

	CITATION: Help from Harit and https://docs.oracle.com/javase/tutorial/2d/text/measuringtext.html
    */
    private void printMidLn (Font font, String text, int y, int choice)
    {
	switch (choice)
	{
		//[1]
	    case 1:
		c.print ("", c.getMaxColumns () / 2 - text.length () / 2); // centers text using getMaxColumns (hsa uilt in method)
		c.print (text);
		c.println (""); //moves the cursor to the next line
		break; // ends case 1
		//[2]
	    case 2:
		Canvas canvas = new Canvas (); // a new Canvas is initiated to access Font metrics
		FontMetrics fm = canvas.getFontMetrics (font); // uses a built in getFontMetrics method to create a new instance of the specific Font's metrics
		int width = fm.stringWidth (text); //
		c.drawString (text, c.maxx () / 2 - width / 2, y); // draws the String centered onto the console
		break; // ends case 2
	}
    }


    /*
    Displays the instructions for the program to the user

    Name    Type        Purpose
    TNR     final Font  Creates a new instance of a Font (the specified Times New Roman Font)
    */
    public void instructions ()
    {
	title (); // Clears the screen and outputs the title
	final Font TNR = new Font ("Times New Roman", Font.BOLD, 17); // Sets font as Times New Roman
	c.setFont (TNR); // Sets font as Times New Roman

	c.setColor (Color.black);
	printMidLn (TNR, "In this game two players will compete in a \"puzzle\" based  guessing game.", 100, 2);
	printMidLn (TNR, "The players will be presented a hidden puzzle word, phrase, or noun.", 125, 2);
	printMidLn (TNR, "They are then able to guess the full puzzle or spin the wheel to guess a letter.", 150, 2);
	printMidLn (TNR, "If the letter is contained inside the word or phrase,", 175, 2);
	printMidLn (TNR, "they would get as much money as they spun multiplied by the occurence of the letter.", 200, 2);
	printMidLn (TNR, "If the player guesses the full puzzle correctly,", 225, 2);
	printMidLn (TNR, "then they would also obtain money based on the amount of letters already guessed and the puzzle length.", 250, 2);
	printMidLn (TNR, "The game ends when the puzzle is solved.", 275, 2);
	printMidLn (TNR, "A winner is then choosen with the highest score. The winners with the highest scores can be seen in High Scores", 300, 2);
	printMidLn (TNR, "When the game has ended, you may choose to play again or to return to the main menu.", 325, 2);
	printMidLn (TNR, "During any game the user is able to leave if they choose to.", 350, 2);
	c.setCursor (17, 30);
	pauseProgram ();
    }



    /*
    Used to display the introduce the user to the program with an animation
    After 2000 miliseconds the program continues to the mainMenu

    Name    Type            Purpose
    s       SplashScreen    Creates an instance of the SplashScreen Class so that a seperate Thread can be run

    [1] A while loop that makes sure that the user does not choose menu options while the splash screen is running
    CITATION: isCharAvail() is a hsa Conosle method
    */
    public void splashScreen ()
    {
	SplashScreen s = new SplashScreen (c);
	c.setTextBackgroundColor (s.BACKGROUND); //sets the background colour
	s.run (); // starts the thread
	//[1]
	while (c.isCharAvail ())
	{
	    c.getChar ();
	}
	c.setTextBackgroundColor (new Color (255, 180, 94)); //sets the background colour
    }


    // goodbye method used to display the goodbye message to the user, ends the program
    public void goodbye ()
    {
	title ();
	c.setCursor (10, 0);
	printMidLn (null, "Thanks for playing Wheel Of Fortune", 0, 1);
	printMidLn (null, "This program was made by Erfan Yeganehfar", 0, 1);
	try // waits for around 2 seconds before closing the console
	{
	    Thread.sleep (2000);
	}
	catch (Exception e)
	{
	}
	c.close (); // closes the cosole
    }


    /*
    The main menu displays the choices for the user

    Name        Type            Purpose
    FONT_1      final Font      Creates a new instance of a Font Object (the specified Times New Roman Font Larger)
    FONT_2      final Font      Creates a new instance of a Font Object (the specified Times New Roman Font Smaller)

    [1] A while loop that re-iterates if the user does not input an integer value from 1-4
    [2] If structure that checks if the inputted choice does not match one of the atual choices, then a error message is displayed and the while loop iterates
    */
    public void mainMenu ()
    {
	title (); // clears the screen and outputs the title
	openHighScores ();
	final Font FONT_1 = new Font ("Times New Roman", Font.BOLD, 18); // initiats font as Times New Roman
	final Font FONT_2 = new Font ("Times New Roman", Font.BOLD, 15); // initiats font as Times New Roman

	c.setFont (FONT_1);
	c.setColor (new Color (35, 20, 2));
	printMidLn (FONT_1, "Please choose one of the options below to continue:", 70, 2); // Prompts for choice
	printMidLn (FONT_2, "1. Play Wheel of Fortune", 130, 2); // Menu options 1
	printMidLn (FONT_2, "2. Instructions", 160, 2); // Menu options 2
	printMidLn (FONT_2, "3. High Scores", 190, 2); // Menu options 3
	printMidLn (FONT_2, "4. Exit Program", 220, 2); // Menu options 4

	c.setColor (new Color (0, 29, 73));
	c.fillRect (c.maxx () / 2 - 500 / 2, 300, 500, 200); // Bottom text box
	c.setColor (new Color (66, 141, 255));
	c.fillRect (c.maxx () / 2 - 480 / 2, 310, 480, 180); //Bottom text box
	c.setColor (Color.black);
	c.setCursor (16, 18);
	c.setCursorVisible (true); // displays the cursor

	//[1]
	while (true)
	{
	    //[2]
	    choice = c.getChar (); // Prompt for the users choice that is error checked
	    if (choice < '1' || choice > '4')
	    {
		JOptionPane.showMessageDialog (null, "Please enter any integer from 1-4", "Error", JOptionPane.ERROR_MESSAGE); // Displays message dialogue box with error message
	    }
	    else
	    {
		break;
	    }
	}
	c.setCursorVisible (false);

    }


    /*
    Opens the Highscore.txt files and reads the input into their respected arrays and sorts them into ascending order

       Name         Type            Purpose
       brInput      String          Buffered input read from the high scores file
       br           BuffredReader   Creates a new BufferedReader instance, used to read the highscores file
       output       PrintWriter     Used to reset the highscores file

       [1] Try catch  statment used to catch file io errors, or corrupted files (files have been tampered with)
       [2] A for loop that iterates through the file loading the data into their respected arrays
       [3] An if statment to catch null objects so that they are not read into the arrays (breaks the loop if oe is encountered)
       [4] If a NumberFormatException is caught then the scores are reset so the program does not crash

       CITATION: Nick helped me with some file io (reading input till null)
       */
    public void openHighScores ()  //
    {
	String brInput; // reader variable used to read the file
	//[1]
	try // opens Highscores.txt and reads the scores
	{
	    BufferedReader br = new BufferedReader (new FileReader ("Highscores.txt"));
	    //[2]
	    for (int i = 9 ; i >= 0 ; i--)
	    {
		brInput = br.readLine ();
		//[3]
		if (brInput == null)
		    break;
		highScoreNames [i] = brInput; // reads the high score names
		highScores [i] = Integer.parseInt (br.readLine ()); // parses the read string into the high score integer values
	    }
	    sortScores (); // sorts the high scores in ascending order
	    br.close (); // closes buffered stream
	}
	catch (IOException e)  //io exception
	{
	    JOptionPane.showMessageDialog (null, "Invalid file data", "File Error", JOptionPane.ERROR_MESSAGE);
	}
	//[4]
	catch (NumberFormatException nfe)  // If file is corrupted
	{
	    JOptionPane.showMessageDialog (null, "File may be corrupted or tampered with", "Corrupted File ", JOptionPane.ERROR_MESSAGE);
	    try // Resets data in the file
	    {
		PrintWriter output = new PrintWriter (new FileWriter ("Highscores.txt"));
		output.close (); // Closes the PrintWriter without (resetting the file
	    }
	    catch (IOException ioe)
	    {
		JOptionPane.showMessageDialog (null, "Invalid file data", "File Error", JOptionPane.ERROR_MESSAGE);
	    }
	    highScoreNames = new String [10]; // clears previous highScoreNames
	    highScores = new int [10]; // clears previous highScore values

	}
	catch (NullPointerException npe)
	{
	    JOptionPane.showMessageDialog (null, "Invalid file data", "File Error", JOptionPane.ERROR_MESSAGE);
	}
    }



    /*
	This method displays the winning high scores stored in a file. If there are none it displays a message. The highscores are sorted in ascending order
    They user may choose to clear the highscores by inputting "c"

    [1] An if statment that displays a messgae if there are no current high scores
    [2] A for loop that outputs the highscores onto the screen
    [3] A if condition tht makes sure the outputted high scores are not null
    [4] An if statment that gets the user input and checks if they want to clear the highscores
    [5] A try catch statment for file io when the user chooses to erase the file (an empty PrintWriter is opened than closed, erasing the file)
    */
    public void displayHighScores ()
    {
	title (); // execute title method
	openHighScores ();
	c.setCursor (3, 0);
	printMidLn (null, "Winner High Scores", 0, 1); // high scores title
	//[1]
	if (highScoreNames [9] == null)
	{
	    c.setCursor (6, 0);
	    printMidLn (null, "There are currently no scores", 0, 1);
	    printMidLn (null, "Please play a game to have a chance of being featured on the High Scores board", 0, 1);

	}
	c.setCursor (4, 21);
	c.print ("UserNames"); // names header
	c.setCursor (4, 53);
	c.print ("Score ($)"); // scores header
	//[2]
	for (int i = 0 ; i < 10 ; i++) // Outputs the high scores
	{
	    c.setCursor (6 + i, 23); //makes sure that they are outputted on different rows
	    //[3]
	    if (highScoreNames [9 - i] != null) // prevents nulls objects from being shown
	    {
		c.print (i + 1 + ".", 4);
		c.print (highScoreNames [9 - i], 27);
		c.println ("$ " + highScores [9 - i]);
	    }
	}

	c.setCursor (17, 0);
	printMidLn (null, "Press any key to continue to the menu or press 'c' to reset the High Scores.", 0, 1); //user prompt to reset or to go to mainMenu
	//[4]
	if (c.getChar () == 'c')  // prompt for reset
	{
	    title (); // Execute title method
	    //[5]
	    try // Resets data in the file
	    {
		PrintWriter writer = new PrintWriter (new FileWriter ("Highscores.txt"));
		writer.close (); // Closes the PrintWriter without (resetting the file
	    }
	    catch (IOException ioe)
	    {
		JOptionPane.showMessageDialog (null, "Invalid file data", "File Error", JOptionPane.ERROR_MESSAGE);
	    }
	    highScoreNames = new String [10]; // clears previous highScoreNames
	    highScores = new int [10]; // clears previous highScore values
	    c.setCursor (3, 0);
	    printMidLn (null, "The High Scores have been reset", 0, 1);
	    c.setCursor (5, 27);

	    pauseProgram ();
	}

    }


    /*
    Asks the users for their usernames, makes sure that they follow the user name rules

    Name        Type            Purpose
    input       char            individual character inputs for the usernames (the display is updated when the input is valid)

    [1] While the user has not entered a valid username
    [2] A for loop that prompts for a character input until the input is a newline
    [3] If the input is a backspace (8) then the last index of the username will be "deleted" (a new string without the last index)
    [4] Makes sure that if the last index is going to be taken out, that atleast 1 character is inside the username
    [5] If the length of the user name does not exceed 10 characters, if it doesnt it adds the input to the username and then updates the display of the username
    [6] An if statment that checks if the player chooses to go back to the main menu by pressing "q"
    [7] An If statment: Errortraping that gives the user an warning message if they enter less than 2 characters as their username (re-iterates if they do so unless its a q)
    [8] An if statment that only asks the second players name if the user hasnt decided to go back to the menu
    [9] An If statment making sure that the two usernames are not the same

    CITATION: Vansh helped/gave me ideas on how to make the user not enter too many characters
    */
    private void askUserName ()
    {
	title ();
	Font font1 = new Font ("Times New Roman", Font.BOLD, 18); //sets font as Times New Roman
	c.setFont (font1);

	c.setCursorVisible (true); //sets the cursor to visible
	c.setColor (Color.black);
	printMidLn (font1, "Welcome to Wheel of Fortune!", 120, 2);
	printMidLn (font1, "Where you will be playing head to head against another player for a cash prize", 150, 2);
	printMidLn (font1, "To continue with the game, please specify who is playing", 200, 2);
	printMidLn (font1, "Make sure that the usernames have a min of 2 characters to a max of 10", 225, 2);
	printMidLn (font1, "Enter \"q\" if you'd like to go back to the Main Menu", 250, 2);

	printMidLn (font1, "Please enter Player 1's name", 275, 2);
	//[1]
	while (true)
	{
	    c.setCursor (14, c.getMaxColumns () / 2); //centers the username input
	    c.println ("");
	    c.setCursor (14, c.getMaxColumns () / 2);
	    //[2]
	    for (char input = c.getChar () ; input != '\n' ;)
	    {
		c.setCursor (14, 1);
		c.println ("");
		c.setCursor (14, 1);
		//[3]
		if (input == 8)
		{
		    //[4]
		    if (player1.length () > 0)
			player1 = player1.substring (0, player1.length () - 1);
		}
		//[5]
		else if (player1.length () < 10)
		    player1 += input; //adds input to the usernae

		c.setCursor (14, c.getMaxColumns () / 2 - player1.length () / 2); //center erase
		c.print (player1);
		input = c.getChar ();
	    }
	    //[6]
	    if (player1.equalsIgnoreCase ("q"))
		break;
	    //[7]
	    if (player1.length () <= 1)
	    {
		JOptionPane.showMessageDialog (null, "Please enter a name that is at least 2 characters long ", "USERNAME", JOptionPane.WARNING_MESSAGE); // Displays message dialogue box with a warning message
		player1 = ""; //resets player 1's user name
	    }
	    else
		break;
	}
	//[8]
	if (!player1.equalsIgnoreCase ("q"))
	{
	    c.println ("");
	    printMidLn (font1, "Please enter Player 2's name", 325, 2);
	    //Same execution as [1] - [7] except with player 2
	    while (true)
	    {
		c.setCursor (16, 0);
		c.println ("");
		c.setCursor (16, c.getMaxColumns () / 2); //centers the input
		//Same execution as [1] - [7] except with player 2
		for (char input = c.getChar () ; input != '\n' ;)
		{
		    c.setCursor (16, 1);
		    c.println ("");
		    c.setCursor (16, 1);

		    //Same execution as [1] - [7] except with player 2
		    if (input == 8)
		    {
			if (player2.length () > 0)
			    player2 = player2.substring (0, player2.length () - 1);
		    }
		    else if (player2.length () < 9)
			player2 += input; //adds input into the player 2 username

		    c.setCursor (16, c.getMaxColumns () / 2 - player2.length () / 2); //centers the erase + display
		    c.print (player2);
		    input = c.getChar ();
		}
		//exits the loop when the user wants to leave
		if (player2.equalsIgnoreCase ("q"))
		    break;
		//Same execution as [1] - [7] except with player 2
		if (player2.length () <= 1)
		{
		    JOptionPane.showMessageDialog (null, "Please enter a name that is at least 2 characters long ", "USERNAME", JOptionPane.WARNING_MESSAGE); // Displays message dialogue box with a warning message
		    player2 = ""; //resets player 2's username
		}
		//[9]
		else if (player2.equals (player1))
		{
		    JOptionPane.showMessageDialog (null, "please make sure both usernames are different", "USERNAME", JOptionPane.ERROR_MESSAGE); // Displays message dialogue box with error message
		    player2 = ""; //resets player 2's username
		}
		//If all the errortraping conditions are by passed, then both usernames are valid and the program continues back to the display
		else
		    break;
	    }
	}
	c.setCursorVisible (false); //makes the cursor invisble

    }


    /*
    Randomly Spins the Wheel of Fortune
    Name                Type            Purpose
    randomIncrement     int             Determines the amount of times the wheel spins by increments 23 degrees
    randomSpin          int             Determins the amount of times the wheel spins


    [1] A for loop that determines the amount of time the wheel does a full rotation and the amount of sectors it passes on the last rotation (randomly)
    */
    private void spinWheel ()
    {
	c.setColor (Color.black);
	c.fillOval (660, 95, 110, 110); // draws the initial circle (drawfill as to make the program less flashy)
	int randomSpin = 180 * (int) (1 + Math.random () * 4);
	int randomIncrement = 23 * (int) (1 + Math.random () * 17);
	//[1]
	for (int x = 0 ; x < randomSpin + randomIncrement ; x++)
	{
	    c.setColor (Color.red);
	    c.fillArc (665, 100, 100, 100, 0 + x, 23);
	    c.setColor (new Color (249, 124, 7)); //dark orange
	    c.fillArc (665, 100, 100, 100, 23 + x, 23);
	    c.setColor (Color.yellow);
	    c.fillArc (665, 100, 100, 100, 45 + x, 23);
	    c.setColor (new Color (91, 218, 247)); //cyan
	    c.fillArc (665, 100, 100, 100, 68 + x, 23);
	    c.setColor (new Color (3, 175, 32)); //light green
	    c.fillArc (665, 100, 100, 100, 90 + x, 23);
	    c.setColor (new Color (226, 29, 177)); //magenta
	    c.fillArc (665, 100, 100, 100, 113 + x, 23);
	    c.setColor (new Color (22, 22, 224)); //blue
	    c.fillArc (665, 100, 100, 100, 135 + x, 23);
	    c.setColor (new Color (56, 0, 178)); //purple
	    c.fillArc (665, 100, 100, 100, 158 + x, 23);
	    c.setColor (new Color (112, 110, 108)); //gray
	    c.fillArc (665, 100, 100, 100, 180 + x, 23);
	    c.setColor (new Color (91, 218, 247)); //cyan
	    c.fillArc (665, 100, 100, 100, 203 + x, 23);
	    c.setColor (new Color (3, 175, 32)); //light green
	    c.fillArc (665, 100, 100, 100, 225 + x, 23);
	    c.setColor (Color.yellow); //yellow
	    c.fillArc (665, 100, 100, 100, 248 + x, 23);
	    c.setColor (new Color (22, 22, 224)); //blue
	    c.fillArc (665, 100, 100, 100, 270 + x, 23);
	    c.setColor (Color.red);
	    c.fillArc (665, 100, 100, 100, 293 + x, 23);
	    c.setColor (new Color (56, 0, 178)); //purple
	    c.fillArc (665, 100, 100, 100, 315 + x, 23);
	    c.setColor (new Color (226, 29, 177)); //magenta
	    c.fillArc (665, 100, 100, 100, 338 + x, 23);

	    try
	    {
		Thread.sleep (3);
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    /*
    A black box method

    Name        Type            Purpose
    actGuess    String          Determines the amount of actual letters the players have already guessed within the puzzle and uses it and the puzzle length to determine the score given
    [1] An if statment that checks if the input is the same as the puzzle (not case sensitive)
    [2] A for loop that is used to count the amount of letters that the players have already guessed within the puzzle
    [3] returns the score that is deterined from the puzzle length and the amount of letters already guessed
    */
    private int fullCheck (String input, String checkPuzzle)
    {
	String actGuess = "";
	//[1]
	if (input.toUpperCase ().equals (puzzle))
	{
	    //[2]
	    for (int i = 0 ; i < checkPuzzle.length () ; i++)
		if (checkPuzzle.charAt (i) >= 65 && checkPuzzle.charAt (i) <= 90)
		    actGuess += checkPuzzle.charAt (i);
	    //[3]
	    return (puzzle.length () - actGuess.length ()) * 500;
	}
	else
	    return 0;
    }


    /*
    Shuffles the word bank based on a modified Fisher Yates Shuffle and returns the type of puzzle the String choosen is
    Re shuffles when the current permuatation has completed

    Name        Type            Purpose
    swap        String          Temporary stores a String to be swapped
    pType       char            The type of puzzle the choosen String is (determined by the first character in the choosen String)
    j           int             Picks a random index from 0 to i to be swapped

    [1] This if condition checks if the word bank is ready to be shuffled again (if the user has already gone through the previous shuffled array)
    [2] A for loop that traverses the array (so a permutatio of the word bank can be made)
    [3] A switch statment that determines what type of puzzle the choosen String is ased on the first letter of the String

    CITATION: Fisher Yates Shuffle https://www.i-programmer.info/programming/theory/2744-how-not-to-shuffle-the-kunth-fisher-yates-algorithm.html
    */
    private String shufflePuzzle (String[] puzzleList, int n)
    {
	String swap;
	char pType;
	//[1]
	if (puzzleNum == 0) // if it is the first index of the permuation
	{
	    //[2]
	    for (int i = 0 ; i < n ; i++)
	    {
		int j = (int) (Math.random () * (i + 1));
		swap = puzzleList [j]; //swaps randomly shoosen indices
		puzzleList [j] = puzzleList [i];
		puzzleList [i] = swap;
	    }
	}
	puzzle = puzzleList [puzzleNum].substring (1); //The choosen puzzle
	pType = puzzleList [puzzleNum].charAt (0); //The first character represents the type of puzzle

	//[3]
	switch (pType)
	{
	    case 'n':
		return "Noun";
	    case 'p':
		return "Phrase";
	    default:
		return "Name";
	}
    }


    /*
    clears the screen and centers the program title
	Name            Type        Purpose
	correctCount    int         Counts the amount of times a letter apears inside the puzzle
	isFound         int         Indicates if the letter is found within the puzzle
	checkGuess      char[]      Used to update the user's guess if they correctly guessed a letter contained within the puzzle

    [1] An if statment that checks that the letter guessed has not been guessed before if it has the blackbox returns -1
    [2] If the indexOf returns -1 (meaning the guessed letter was not found inside the puzzle)  returns 0
    [3] + [4] If the letter is found then a traversal for loop is used to check how many times it apears inside the puzzle
	[4] checks if the current index has the same element as the guess and increments the count + replaces that index within the user guess with the letter
     CITATION: String to char array method from https://www.javatpoint.com/java-string-tochararray

    */
    private int checkChar (char input, String checkPuzzle, boolean ifLetterAvail[])
    {
	//[1]
	if (ifLetterAvail [input - 65] == false)
	{
	    int correctCount = 0, isFound = puzzle.indexOf (input);
	    char[] checkGuess = checkPuzzle.toCharArray ();
	    //[2]
	    if (isFound == -1)
		return correctCount;
	    else
	    {
		//[3]
		for (int i = 0 ; i < puzzle.length () ; i++)
		{
		    //[4]
		    if (input == puzzle.charAt (i))
		    {
			correctCount++;
			checkGuess [i] = input;
		    }
		}
		userGuess = new String (checkGuess);
	    }
	    ifLetterAvail [input - 65] = true;
	    return correctCount;
	}
	else
	    return -1;
    }


    //Returns a randomly choosen prize from the passed array (a random index of arrPrize from 0 to arrPrize.length)
    private int randomizedPrize (int[] arrPrize)
    {
	return arrPrize [(int) (Math.random () * arrPrize.length)];
    }


    /*
    The main game display screen where black boxes and other methods do the main proccess of the game

	Name            Type        Purpose
	FONT_1          Font        An instance of a Font is declared. That font being Tahoma Bold, used for displaying the board
	FONT_2          Font        An instance of a Font is declared. That font being the default Bold, used for displaying the available letters
	FONT_2          Font        An instance of a Font is declared. That font being the Times New Roman Bold, used for the winners screen
	wheelChoice     char        The players choice on what to do next; guess a letter, guess the puzzle, or exit
	reLoop          char        This variable dictates if they game should re-iterate if the player chooses to
	type            String      The type of puzzle the users are solving for
	winner          String      Stores the winners name to be sorted into the highscores
	wheelPrizes     int[]       An array of prizes, randomly a prize is choosen by a black box method
	availLetters    boolean[]   States whether or not a letter has been choosen before
	currentPrize    int         The current round value of the prize
	count           int         The amount of times a letter is found within the puzzle (dictates the players points)
	fullCheckPrize  int         Stores the prize the user receives if they guess the full puzzle correctly
	letterIndex     int         The index at which a letter is at in the alphabet (used to display the current available letters)
	winnerScore     int         Stores the winner's score that may later be sorted in the highscores
	player1Turn     boolean     Determines who's turn it is in the game
	fullGuess       String      Stores the user's guess for the puzzle
	tempGuess       int         Indicates where the erase for the guessed char should be

    [1] A while loop with an outter label, used to re-iterate the whole game if the user wants to play again
    [2] If statment that checks if the player chose to quit to main menu innside of askUserName, then the outter while loop is broken and they are redirected to the mainMenu
    [3] If the index of the current puzzle is the last puzzle within the permutation, the index is reset to 0 and the wrdBank is reshuffled, giving it a new permutation
    [4] A for loop that traverses the length of the choosen puzzles and replaces the user guess variable's characters with hyphens (encrypted puzzle)
    [5] An if statment that checks if the current character at the loop variable is a space, if it is, then a space is added added if it is any thing else a hypen is added to mimic the puzzle (encrypt it)
    [6] A for loop that is used to draw the full alphabet onto the screen in 2 rows
    [7] An if condition inside [6] that dictates where the letters are displayed. The letters in the first row are up until "N", after that they are put onto the second row
    [8] A while loop that keeps asking the user for a prompt basd on what they choose, it also errortraps the user choice. It directs the choices to certain condionals. This is done until the player chooses to leave or the puzzle is solved
    [9] An if conditional that checks how the player wants to proceed with the program. This particular if conditional is to error trap the input
    [10] An if condition if the player chooses the first option. They are prompted to solve the puzzle by guessing a letter, the letter is checked and the scores + turn is updated
    [11] An if conditional in [10] that check what the choosen prize is, in this statment it checks whether the player has been bankrupt, if they are the score board is updated and a message is given to them
    [12] An if statment inside [11] that checks who is the current player, their scores are reset (becasue they are bankrupt) and it switchees their turn to the next players
    [13] An if statment inside [10] that checks if the player prize lands anything other than bankrupt, they are prompted for an input if it's valid their score is updated and they get to play again if they guessed correctly
    [14] A while loop that is used to prompt the user to guess a letter, the while loop iterates if the charcter is already choosen or they do not input a letter
    [15] The user is prompted to guess a letter contain within the puzzle, An if condition checks if it really a letter, if not they are promppyed again
    [16] After a blackbox method is used to check the iputted letter. This if statment checks if the letter is not conatined inside the puzzle, the next if statment checks if that letter has already been guessed, if so they are given a message.
	 The last if statment within the conditional structure checks and updates if the user has guessed a letter within the puzzle
    [17] The if statment inside [16] switches the current player if the guess was not correct
    [18] If the user has guessed correctly then their scores are updated with this if condition (based on the current player)
    [19] This if statment erases the guessed letters within the available letters table
    [20] This if statment checks if the user guess' have collectively solved the puzzle, if they have the guess is erased and the puzzle index is increased. The players are shown the winners screen
    [21] An if condition if the player chose to gess the full puzzle, updates the turn and score accordingly. The user is prompted to input their guess
    [22] A for loop that prompts for a character input until the input is a newline
    [23] If the input is a backspace (8) then the last index of the guess will be "deleted" (a new string without the last index)
    [24] Makes sure that if the last index is going to be taken out, that atleast 1 character is inside the guess
    [25] If the length of the guess does not exceed the maximium of the puzzle's, if it doesnt it adds the input to the guess and then updates the display of the guess
    [26] A black box method checks the guess. An If statmen checks if the return was greater than zero, if it was, then the player score is updated and the current game is ended (winners screen is displayed)
	 If the user did not guess properly a message is then displayed saying they did not guess the puzzle correctly
    [27] An if statment iniside [26] that updates the score according to the current player
    [28] The final condition within [10] that corresponds with the users choice to exit the program. Player names are reset, the puzzle index is increased, and the user guess is reset. The big while loop is broken out of using a label break statment
    [29] When the game has ended this if structure is responsible for setting the winner based on score
    [30] An if statment that checks if the current winner can be put into the highscores array
    [31] An if statment that checks if the user would like to play again or exit to the mainMenu, resets player names if they decide to leave

     CITATION: Loop labels from the oracle documentation: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/branch.html
    */
    public void display ()
    {
	askUserName ();
	final Font FONT_1 = new Font ("Tahoma", Font.BOLD, 25);
	final Font FONT_2 = new Font ("", Font.BOLD, 12);
	final Font FONT_3 = new Font ("Times New Roman", Font.BOLD, 20);
	char reLoop = 'y';
	//[1]
	outter: // A while loop label, used later on to end the main game and return to the main menu
	while (reLoop == 'y')
	{
	    //[2]
	    if (player1.equalsIgnoreCase ("q") || player2.equalsIgnoreCase ("q"))
	    {
		player1 = "";
		player2 = "";
		break outter;
	    }

	    title (); //clears the screen and outputs the title
	    //[3]
	    if (puzzleNum == wrdBank.length - 1)
		puzzleNum = 0;

	    String type = shufflePuzzle (wrdBank, wrdBank.length), winner = "";
	    char wheelChoice = ' ';
	    int[] wheelPrizes = {2500, 400, 700, 600, 650, 500, 700, 600, 0, 400, 900, 500, 600, 650, 700, 800, 500, 650, 500, 0, 900, 1000};
	    boolean[] availLetters = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
	    int currentPrize, count = 0, letterIndex = 0, winnerScore = 0;
	    boolean player1Turn = true;

	    //[4]
	    for (int i = 0 ; i < puzzle.length () ; i++)
	    {
		//[5]
		if (puzzle.charAt (i) == ' ')
		    userGuess += " ";
		else
		    userGuess += "-";
	    }
	    //________________________________Default Wheel____________________________________

	    c.setColor (Color.black); //black
	    c.fillOval (660, 95, 110, 110); // outter wheel
	    // main wheel
	    c.setColor (Color.red);
	    c.fillArc (665, 100, 100, 100, 0, 23);
	    c.setColor (new Color (249, 124, 7)); //dark orange
	    c.fillArc (665, 100, 100, 100, 23, 23);
	    c.setColor (Color.yellow);
	    c.fillArc (665, 100, 100, 100, 45, 23);
	    c.setColor (new Color (91, 218, 247)); //cyan
	    c.fillArc (665, 100, 100, 100, 68, 23);
	    c.setColor (new Color (3, 175, 32)); //light green
	    c.fillArc (665, 100, 100, 100, 90, 23);
	    c.setColor (new Color (226, 29, 177)); //magenta
	    c.fillArc (665, 100, 100, 100, 113, 23);
	    c.setColor (new Color (22, 22, 224)); //blue
	    c.fillArc (665, 100, 100, 100, 135, 23);
	    c.setColor (new Color (56, 0, 178)); //purple
	    c.fillArc (665, 100, 100, 100, 158, 23);
	    c.setColor (new Color (112, 110, 108)); //gray
	    c.fillArc (665, 100, 100, 100, 180, 23);
	    c.setColor (new Color (91, 218, 247)); //cyan
	    c.fillArc (665, 100, 100, 100, 203, 23);
	    c.setColor (new Color (3, 175, 32)); //light green
	    c.fillArc (665, 100, 100, 100, 225, 23);
	    c.setColor (Color.yellow); //yellow
	    c.fillArc (665, 100, 100, 100, 248, 23);
	    c.setColor (new Color (22, 22, 224)); //blue
	    c.fillArc (665, 100, 100, 100, 270, 23);
	    c.setColor (Color.red);
	    c.fillArc (665, 100, 100, 100, 293, 23);
	    c.setColor (new Color (56, 0, 178)); //purple
	    c.fillArc (665, 100, 100, 100, 315, 23);
	    c.setColor (new Color (226, 29, 177)); //magenta
	    c.fillArc (665, 100, 100, 100, 338, 23);
	    //_________________________________________________________________________________________

	    c.setColor (new Color (35, 20, 2));
	    c.setFont (FONT_1);
	    c.drawString ("Scores", 40, 70); // header for the scores

	    c.setCursor (5, 4);
	    c.print (player1 + ":"); //player 1's score
	    c.setCursor (6, 4);
	    c.print ("$0");
	    c.setCursor (7, 4);
	    c.print (player2 + ":"); //player 2's score
	    c.setCursor (8, 4);
	    c.print ("$0");
	    c.setCursor (10, 4);
	    c.print ("Current Turn:"); //displays who is currently playing
	    c.setCursor (11, 4);
	    c.print (player1);

	    c.setCursor (13, 1);
	    c.println ("Please choose from one of the options below to guess a " + type); //Introduces the player to the program and tells the the type of puzzle they have to decipher
	    c.println ("[1] Spin the wheel and guess a letter");
	    c.println ("[2] Guess the puzzle");
	    c.print ("[3] Exit the game \n");

	    c.setColor (new Color (0, 29, 73));
	    c.fillRect (c.maxx () / 2 - 460 / 2, 60, 460, 170); //Border
	    c.setColor (new Color (66, 141, 255));
	    c.fillRect (c.maxx () / 2 - 440 / 2, 70, 440, 150); //Box where the puzzle is displayed

	    c.setColor (new Color (35, 20, 2));
	    printMidLn (FONT_1, "" + userGuess, 155, 2); //sets font as Tahoma

	    c.setFont (FONT_2);
	    c.drawString ("Available Letters", 640, 300); // Letters Available header
	    //[6]
	    for (char letter = 'A' ; letter <= 'Z' ; letter++, letterIndex++)
	    {
		//[7]
		if (letter > 77)
		    c.drawString ("" + letter, 395 + 15 * letterIndex, 340);
		else
		    c.drawString ("" + letter, 590 + 15 * letterIndex, 320);
	    }
	    //[8]
	    while (true)
	    {
		//c.setTextBackgroundColor (new Color (244, 153, 48)); //sets background colour to light orange
		c.setCursor (18, 1);
		c.print ("Your choice (1-3):");
		c.setCursor (18, 13);
		wheelChoice = c.getChar (); //prompt for the users choice that is error checked
		//[9]
		if (wheelChoice < '1' || wheelChoice > '3')
		{
		    JOptionPane.showMessageDialog (null, "Please enter a valid choice of integers from 1 to 3", "Error", JOptionPane.ERROR_MESSAGE); // Displays message dialogue box with error message
		}
		//[10]
		else if (wheelChoice == '1')
		{
		    c.setColor (new Color (255, 180, 94)); //Background colour
		    c.fillRect (649, 220, 300, 70); //prize erase

		    c.setFont (FONT_1);
		    spinWheel (); // spins the wheel of fortune
		    currentPrize = randomizedPrize (wheelPrizes); // randomizes the prize the player "spins"
		    //[11]
		    if (currentPrize == 0)
		    {
			c.drawString ("BANKRUPT!", 650, 250); // shows the user that they are bankrupt
			JOptionPane.showMessageDialog (null, "You have gone Bankrupt, it is now the next player's turn", "Not Found", JOptionPane.WARNING_MESSAGE);

			//[12]
			if (player1Turn) //updates player 1 score
			{
			    player1Turn = false;
			    player1Score = 0;
			    c.setCursor (11, 4);
			    c.print ("\t\t ");
			    c.setCursor (11, 4);
			    c.print (player2);
			}
			else if (player1Turn == false) //updates player 2 score
			{
			    player1Score = 0;
			    player1Turn = true;
			    c.setCursor (11, 4);
			    c.print ("\t\t ");
			    c.setCursor (11, 4);
			    c.print (player1);
			}
		    }
		    //[13]
		    else
		    {
			c.drawString ("$" + currentPrize, 685, 250); //displays the current prize the player has a cance to obtain
			//[14]
			while (true)
			{
			    c.setCursor (19, 1);
			    c.println ("");
			    c.setCursor (19, 1);
			    c.println ("please choose any letter available to solve the puzzle");
			    char guess = Character.toUpperCase (c.getChar ()); //prompt for the users choice that is error checked
			    //[15]
			    if (guess < 65 || guess > 90)
			    {
				JOptionPane.showMessageDialog (null, "Please enter a valid letter between A-Z", "Error", JOptionPane.ERROR_MESSAGE); // Displays message dialogue box with error message

			    }
			    else
			    {
				count = checkChar (guess, userGuess, availLetters);
				//[16]
				if (count == 0)
				{
				    //[17]
				    if (player1Turn) // updates player 1 score
				    {
					player1Turn = false;
					c.setCursor (11, 4);
					c.print ("\t\t ");
					c.setCursor (11, 4);
					c.print (player2);
				    }
				    else if (player1Turn == false) // updates player 2 score
				    {
					player1Turn = true;
					c.setCursor (11, 4);
					c.print ("\t\t ");
					c.setCursor (11, 4);
					c.print (player1);
				    }
				    JOptionPane.showMessageDialog (null, "This character is not contained inside the puzzle, it is now the next players turn", "Not Found", JOptionPane.WARNING_MESSAGE);

				}
				else if (count == -1)
				{
				    JOptionPane.showMessageDialog (null, "This character has been choosen before please try a different one", "Not Available", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
				    c.setColor (new Color (66, 141, 255));
				    c.fillRect (c.maxx () / 2 - 440 / 2, 70, 440, 150);
				    c.setColor (Color.black);
				    printMidLn (FONT_1, "" + userGuess, 155, 2); //PRINTS
				    //[18]
				    if (player1Turn) // updates player 1's score
				    {
					player1Score += currentPrize * count; // their score is added onto their previous one
					c.setCursor (6, 4);
					c.print ("\t\t");
					c.setCursor (6, 4);
					c.print ("$" + player1Score);

				    }
				    else if (player1Turn == false) // updates player 2's score
				    {
					player2Score += currentPrize * count; // their score is added onto their previous one
					c.setCursor (8, 4);
					c.print ("\t\t");
					c.setCursor (8, 4);
					c.print ("$" + player2Score);
				    }
				}
				c.setColor (new Color (255, 180, 94)); //Background colour
				c.setFont (FONT_2);
				int tempGuess = guess - 65; //indexing variable

				//[19]
				if (tempGuess > 13)
				    c.drawString ("" + guess, 395 + 15 * tempGuess, 340);
				else
				    c.drawString ("" + guess, 590 + 15 * tempGuess, 320);
				c.setColor (Color.black);

				c.setCursor (18, 1); //erases previous lines
				c.println ("");
				c.setCursor (18, 1);
				break;
			    }
			}
		    }
		    c.setCursor (19, 1); //erases previous lines
		    c.println ("");
		    c.setCursor (19, 1);
		    //[20]
		    if (userGuess.equals (puzzle))
		    {
			puzzleNum++;
			userGuess = "";
			break;
		    }

		}
		//[21]
		else if (wheelChoice == '2')
		{
		    String fullGuess = "";
		    c.setCursor (19, 1);
		    c.println ("Please guess the full puzzle (not case sensitive):");
		    //[22]
		    for (char input = c.getChar () ; input != '\n' ;)
		    {
			c.setCursor (20, 1);
			c.println ("");
			c.setCursor (20, 1);
			//[23]
			if (input == 8)
			{
			    //[24]
			    if (fullGuess.length () > 0)
				fullGuess = fullGuess.substring (0, fullGuess.length () - 1);
			}
			//[25]
			else if (fullGuess.length () < puzzle.length ())
			    fullGuess += input;

			c.setCursor (20, 1);
			c.print (fullGuess);
			input = c.getChar ();
		    }

		    c.setCursor (19, 1);
		    c.println ("\n");

		    int fullCheckPrize = fullCheck (fullGuess, userGuess);
		    //[26]
		    if (fullCheckPrize > 0)
		    {
			//[27]
			if (player1Turn)
			{
			    player1Score += fullCheckPrize;
			}
			else
			{
			    player2Score += fullCheckPrize;
			}
			puzzleNum++;
			userGuess = "";
			break;
		    }
		    else
			JOptionPane.showMessageDialog (null, "Sorry, your guess wasn't correct", "Not Available", JOptionPane.WARNING_MESSAGE);
		}
		//[28]
		else
		{
		    puzzleNum++;
		    userGuess = "";
		    player1 = "";
		    player2 = "";
		    break outter; //breaks the big main while loop (using the label)
		}
	    }
	    title (); //clears the screen and outputs the title
	    //[29]
	    //finds the game winner
	    if (player1Score > player2Score)
	    {
		winner = player1;
		winnerScore = player1Score;
	    }
	    else if (player1Score < player2Score)
	    {
		winner = player2;
		winnerScore = player2Score;
	    }
	    //[30]
	    if (winnerScore > highScores [0])  // check if winner is worthy of leaderboard status
	    {
		highScoreNames [0] = winner;
		highScores [0] = winnerScore;
	    }

	    saveHighScores (); // re-sort highscores and save high scores

	    c.setFont (FONT_3);
	    c.setColor (Color.black);
	    printMidLn (FONT_3, "Congratulations to " + winner + ", You are the winner", 100, 2);
	    printMidLn (FONT_3, "with a score of $" + winnerScore, 125, 2);
	    printMidLn (FONT_3, "Thank you both for playing", 150, 2);
	    printMidLn (FONT_3, "Check the High Scores to see if your win is on there", 240, 2);
	    printMidLn (FONT_3, "Please press y to play again or anything else to go back to the Main Menu", 300, 2);
	    //[31]
	    if (Character.toLowerCase (c.getChar ()) != 'y')
	    {
		player1 = "";
		player2 = "";
		break;
	    }

	    //c.setCursorVisible (false);

	}
    }


    /*
    The method that sorts the two highscore arrays into ascending order

    Name            Type            Purpose
    scoresTemp      int             A Integer value that temoparary swaps the current index with the next of the next is greater
    nameTemp        String          A Integer value that temoparary swaps the current high score name index with the next based on whether the scores are different

    [1] The main for loop that allows the entire array to be traversed more than once (up to 9 times)
    [2] The next for loop that actually swaps the indices of the arrays if the next element is larger, this happens based on what cycle the main loop is on (i)
    [3] An if statment that checks if the next element is greator than the current (switches them if it is)
    */
    private void sortScores ()  // bubble sorting of names and scores
    {
	int scoresTemp = 0;
	String nameTemp = "";
	//[1]
	for (int i = 0 ; i < 10 ; i++)
	{
	    //[2]
	    for (int j = 1 ; j < 10 - i ; j++)
	    {
		//[3]
		if (highScores [j - 1] > highScores [j])
		{
		    scoresTemp = highScores [j - 1];
		    nameTemp = highScoreNames [j - 1];

		    highScores [j - 1] = highScores [j];
		    highScoreNames [j - 1] = highScoreNames [j];
		    highScores [j] = scoresTemp; //switches elements (in ascending order)
		    highScoreNames [j] = nameTemp; //switches elements (in ascending order)
		}
	    }
	}
    }


    /*
    Opens the Highscore.txt files writes the high scores into the file in ascending order

       Name         Type            Purpose
       output       PrintWriter     Writes the highscores into the file

       [1] Try catch  statment used to catch file io errors, or corrupted files (files have been tampered with)
       [2] A for loop that iterates 10 times to output the (possible) 10 highscores to the file
       [3] An if statment to catch null objects so they are not written to the file

       CITATION: Nick helped me with some file io (reading input till null) + leo for the bubble sort
       */
    private void saveHighScores ()
    {
	sortScores ();
	//[1]
	try
	{
	    PrintWriter output = new PrintWriter (new FileWriter ("Highscores.txt"));
	    //[2]
	    for (int i = 0 ; i < 10 ; i++)
	    {
		//[3]
		if (highScoreNames [i] != null) // filter out empty lines
		{
		    output.println (highScoreNames [i]);
		    output.println (highScores [i]);
		}
	    }
	    output.close (); // close PrintWriter stream
	}
	catch (IOException e) //file io exception catching
	{
	    JOptionPane.showMessageDialog (null, "File Writer has encountered a problem", "Writer Error", JOptionPane.ERROR_MESSAGE);
	}

    }


    /*
    Main program controls the flow of the program, the if statment is used to check if the user would like to exit the program, wants to continue,
    or wants to view the instructions
    */
    public static void main (String[] args)  //main program
    {
	WheelOfFortune wf = new WheelOfFortune ();
	wf.splashScreen ();

	while (!(wf.choice == '4'))
	{
	    wf.mainMenu ();
	    if (wf.choice == '1')
		wf.display ();
	    else if (wf.choice == '2')
		wf.instructions ();
	    else if (wf.choice == '3')
		wf.displayHighScores ();
	}
	wf.goodbye ();
    }
}


