/******************************************************************************
 * jcApp.java - The JavaChess main program
 * by F.D. Laram�e
 *
 * Purpose: Entry point, and not much else!
 *
 * History:
 * 07.06.00 Initial build.
 *****************************************************************************/

package javachess;

import javax.swing.UIManager;
import java.awt.*;
import javachess.jcGame;

/*****************************************************************************
 * public class jcApp
 * The application-level class, surrounding everything else.
 *
 * Most of this code has been auto-generated by JBuilder; my role was limited
 * to re-formatting it to make it legible, and to add the jcGame calls at the
 * end of the main program.
 ****************************************************************************/

public class jcApp
{
  // Constructor
  public jcApp()
  {
    // Make the window, since Java needs one
    // We won't be making much use of it, though; all of the i/o
    // will pass through the console
    jcFrame frame = new jcFrame();
    frame.validate();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if ( frameSize.height > screenSize.height )
    {
      frameSize.height = screenSize.height;
    }
    if ( frameSize.width > screenSize.width )
    {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible( true );
  }

  // Main method
  // Initialize and launch the jcGame object
  public static void main( String[] args )
  {
    // Extract the parameters
    String openingBook = args[ 0 ];
    String startingPos = "NONE";
    if ( args.length > 1 )
      startingPos = args[ 1 ];

    // Make the application
    try
    {
      UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
    new jcApp();

    // Initialize the game controller
    jcGame theGame = new jcGame();
    try
    {
      theGame.InitializeGame( openingBook, startingPos );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }

    // Run the game
    try
    {
      theGame.RunGame();
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }

    System.exit( 0 );
  }
}