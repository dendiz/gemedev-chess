/***************************************************************************
 * jcFrame.java - GUI for JavaChess
 * by F.D. Laramée
 *
 * Purpose: Sometime in the very distant future, I may graft a true GUI onto
 * this game (i.e., drag-and-drop pieces to move, etc.)  In the meantime, this
 * class will only contain the absolute bare minimum functionality required
 * by Java: an empty window with a "close box" allowing quick exit.
 ***************************************************************************/

package javachess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/****************************************************************************
 * public class jcFrame
 ***************************************************************************/

public class jcFrame extends JFrame
{
  // GUI data members
  JPanel contentPane;
  BorderLayout borderLayout1 = new BorderLayout();

  // Constructor
  public jcFrame()
  {
    enableEvents( AWTEvent.WINDOW_EVENT_MASK );
    try
    {
      jbInit();
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }

  // GUI Component initialization
  private void jbInit() throws Exception
  {
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout( borderLayout1 );
    this.setSize( new Dimension( 400, 300 ) );
    this.setTitle( "Java Chess 1.0" );
  }

/**************************************************************************
 * Event handlers
 *************************************************************************/

  // processWindowEvent: Overridden so we can exit when window is closed
  protected void processWindowEvent( WindowEvent e )
  {
    super.processWindowEvent( e );
    if ( e.getID() == WindowEvent.WINDOW_CLOSING )
    {
      System.exit(0);
    }
  }
}