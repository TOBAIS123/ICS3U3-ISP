/*
* Erfan Yeganhefar
* Ms.Krasteva
* Jan 1st 2019
* This program ainmates the Wheel of Fortune spinning from the right side of the screen to the left, 
* it stops, and the title animates onto the screen (from the left)
*/

import java.awt.*; //gives access to the awt library
import hsa.Console; //access to the Console class
import java.lang.*; // to access Thread class

public class SplashScreen extends Thread //extends thread class
{
    private Console c;
    // instance variable of the background colour, it's constat because it doesn't change
    final static Color BACKGROUND = new Color (255, 180, 94);

    public SplashScreen (Console con)  // SplashScreen Console (makes sure the output is on the main program console)
    {
	c = con;
    }


    public void animateDice ()
    {

	c.setColor (BACKGROUND);
	c.fillRect (0, 0, c.maxx (), c.maxy ()); // light orange background

	for (int x = 0 ; x < 800 ; x++)
	{
	    c.setColor (BACKGROUND); // erase colour
	    c.fillOval (848 - x, 116, 307, 307); //erase oval
	    //Start of the spinning wheel
	    c.setColor (Color.black);
	    c.fillOval (847 - x, 117, 306, 306);

	    c.setColor (Color.red); //red
	    c.fillArc (850 - x, 120, 300, 300, 0 + x, 23);

	    c.setColor (new Color (249, 124, 7)); //dark orange
	    c.fillArc (850 - x, 120, 300, 300, 23 + x, 23);

	    c.setColor (Color.yellow);
	    c.fillArc (850 - x, 120, 300, 300, 45 + x, 23);

	    c.setColor (new Color (91, 218, 247)); //cyan
	    c.fillArc (850 - x, 120, 300, 300, 68 + x, 23);

	    c.setColor (new Color (3, 175, 32)); //light green
	    c.fillArc (850 - x, 120, 300, 300, 90 + x, 23);

	    c.setColor (new Color (226, 29, 177)); //magenta
	    c.fillArc (850 - x, 120, 300, 300, 113 + x, 23);

	    c.setColor (new Color (22, 22, 224)); //blue
	    c.fillArc (850 - x, 120, 300, 300, 135 + x, 23);

	    c.setColor (new Color (56, 0, 178)); //purple
	    c.fillArc (850 - x, 120, 300, 300, 158 + x, 23);

	    c.setColor (new Color (112, 110, 108)); //gray
	    c.fillArc (850 - x, 120, 300, 300, 180 + x, 23);

	    c.setColor (new Color (91, 218, 247)); //cyan
	    c.fillArc (850 - x, 120, 300, 300, 203 + x, 23);

	    c.setColor (new Color (3, 175, 32)); //light green
	    c.fillArc (850 - x, 120, 300, 300, 225 + x, 23);

	    c.setColor (new Color (249, 124, 7)); //dark orange
	    c.fillArc (850 - x, 120, 300, 300, 248 + x, 23);

	    c.setColor (new Color (22, 22, 224)); //blue
	    c.fillArc (850 - x, 120, 300, 300, 270 + x, 23);

	    c.setColor (Color.red); //red
	    c.fillArc (850 - x, 120, 300, 300, 293 + x, 23);

	    c.setColor (new Color (56, 0, 178)); //purple
	    c.fillArc (850 - x, 120, 300, 300, 315 + x, 23);

	    c.setColor (new Color (226, 29, 177)); //magenta
	    c.fillArc (850 - x, 120, 300, 300, 338 + x, 23);

	    //delay so that animation is visibly animated
	    try
	    {

		Thread.sleep (9);
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    public void title ()  // draw title
    {
	c.setFont (new Font ("", Font.BOLD, 80)); // sets the font to the defualt bold (size 80)
	for (int x = 0 ; x < 370 ; x++)// ainmates the first word from the right side of the screen, onto about a quarter of the screen
	{
	    c.setColor (BACKGROUND); // erase
	    c.fillRect (800 - x, 220, 300, 120);

	    //first word
	    c.setColor (Color.yellow);
	    c.drawString ("Wheel", 800 - x, 280);

	    try // delay so that animation is visible
	    {
		Thread.sleep (2);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int x = 0 ; x < 300 ; x++)// ainmates the first word from the right side of the screen, onto about a quarter of the screen
	{
	    c.setColor (BACKGROUND);// erase
	    c.fillRect (800 - x, 290, 300, 120);

	    c.setColor (Color.yellow);
	    c.drawString ("Of", 800 - x, 350);

	    try// delay so that animation is visible
	    {
		Thread.sleep (2);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int x = 0 ; x < 400 ; x++)// ainmates the first word from the right side of the screen, onto about a quarter of the screen
	{
	    c.setColor (BACKGROUND);// erase
	    c.fillRect (800 - x, 360, 340, 120);

	    c.setColor (Color.yellow);
	    c.drawString ("Fortune", 800 - x, 420);

	    try// delay so that animation is visible
	    {
		Thread.sleep (2);
	    }
	    catch (Exception e)
	    {
	    }
	}
	// 2 second delay before going to mainMenu
	try
	{
	    Thread.sleep (2000);
	}
	catch (Exception e)
	{
	}
    }


    public void run ()// overwrites the run method within the Thread class allowing the methods to be run in the thread
    {
	animateDice ();
	title ();
    }
}


