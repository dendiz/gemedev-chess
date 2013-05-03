/***************************************************************************
 * jcAISearchAgentMDTF - A sophisticated search agent
 **************************************************************************/
package javachess;
import javachess.jcAISearchAgent;
import javachess.jcBoard;

public class jcAISearchAgentMDTF extends jcAISearchAgent
{

  // Construction
  public jcAISearchAgentMDTF()
  {
    Evaluator = new jcBoardEvaluator();
  }

  // Move selection
  public jcMove PickBestMove( jcBoard theBoard )
  {
    // FDL Do the real work later
    return( new jcMove() );
  }
}