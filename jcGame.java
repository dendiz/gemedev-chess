/***************************************************************************
 * jcGame.java - The JavaChess game controller
 * by François Dominic Laramée
 *
 * Purpose: Coordinate the efforts of all other game-related objects.  This
 * work has been separated from the jcApp (application-level object) because
 * the latter may come to take on more message-passing and related duties
 * if I ever add a GUI to the game, and I didn't want interface and game
 * mechanics to get mixed up in a single class.
 *
 * History
 * 08.06.00 Created
 **************************************************************************/
package javachess;

import javachess.jcPlayer;
import javachess.jcBoard;
import javachess.jcMoveListGenerator;
import javachess.jcOpeningBook;
import java.io.*;

/**************************************************************************
 * public class jcGame
 *************************************************************************/

public class jcGame
{
  // The two players involved in the current game
  jcPlayer Players[];

  // The state of the game
  jcBoard GameBoard;

  // The opening book
  jcOpeningBook Openings;

  // A wrapper for the keyboard
  InputStreamReader kbd;

  // Constructor
  public jcGame()
  {
  }

  // boolean InitializeGame()
  // Select the players, create subsidiary objects and prepare to play
  public boolean InitializeGame( String openingBook, String startingPos ) throws Exception
  {
    // Read the opening book
    Openings = new jcOpeningBook();
    Openings.Load( openingBook );

    // Load the initial position, if any
    GameBoard = new jcBoard();
    if ( startingPos.equalsIgnoreCase( "NONE" ) )
      GameBoard.StartingBoard();
    else
      GameBoard.Load( startingPos );

    // Initialize the keyboard
    kbd = new InputStreamReader( System.in );
    int key;

    // Identify the two players
    Players = new jcPlayer[ 2 ];
    key = 'C';
    System.out.println( "Welcome to Java Chess.  Who plays white: [H]uman or [C]omputer? " );
    try {
      do
      {
        key = kbd.read();
      }
      while( ( key != 'H' ) && ( key != 'h' ) && ( key != 'c' ) && ( key != 'C' ) );
    } catch( IOException e ) {}

    if ( ( key == 'H' ) || ( key == 'h' ) )
    {
      Players[ jcPlayer.SIDE_WHITE ] = new jcPlayerHuman( jcPlayer.SIDE_WHITE, kbd );
    }
    else
    {
      Players[ jcPlayer.SIDE_WHITE ] =
        new jcPlayerAI( jcPlayer.SIDE_WHITE, jcAISearchAgent.AISEARCH_MTDF, Openings );
    }

    System.out.println( "And who plays black: [H]uman or [C]omputer? " );
    try {
      do
      {
        key = kbd.read();
      }
      while( ( key != 'H' ) && ( key != 'h' ) && ( key != 'c' ) && ( key != 'C' ) );
    } catch( IOException e ) {}

    if ( ( key == 'H' ) || ( key == 'h' ) )
    {
      Players[ jcPlayer.SIDE_BLACK ] = new jcPlayerHuman( jcPlayer.SIDE_BLACK, kbd );
    }
    else
    {
      Players[ jcPlayer.SIDE_BLACK ] =
        new jcPlayerAI( jcPlayer.SIDE_BLACK, jcAISearchAgent.AISEARCH_MTDF, Openings );
    }

    return true;
  }

  // boolean RunGame()
  // A simple loop getting moves from the current player until the game is over
  public boolean RunGame() throws Exception
  {
    jcPlayer CurrentPlayer;
    jcMove Mov;

    do
    {
      // Show the current game board
      GameBoard.Print();

      // Ask the next player for a move
      CurrentPlayer = Players[ GameBoard.GetCurrentPlayer() ];
      Mov = CurrentPlayer.GetMove( GameBoard );
      System.out.print( jcPlayer.PlayerStrings[ GameBoard.GetCurrentPlayer() ] );
      System.out.print( " selects move: " );
      Mov.Print();

      // Change the state of the game accordingly
      GameBoard.ApplyMove( Mov );

      // Pause
      Thread.currentThread().sleep( 2000 );

    } while( ( Mov.MoveType != jcMove.MOVE_RESIGN ) &&
             ( Mov.MoveType != jcMove.MOVE_STALEMATE ) );

    System.out.println( "Game Over.  Thanks for playing!" ) ;

    return true;
  }
}