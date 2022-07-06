package org.diagramsascode.state.edge;

import org.diagramsascode.state.node.State;

/**
 * A message edge on a sequence diagram.
 * It connects participants.
 * 
 * @author b_muth
 *
 */
public class Transition extends StateDiagramEdge {
  /**
   * Creates a message edge.
   * 
   * @param from the starting node of the edge
   * @param to the ending node of the edge
   * @param text the text of the message
   */
  public Transition(State from, State to, String text) {
    super(from, to, text);
  }
}
