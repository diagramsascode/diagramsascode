package org.diagramsascode.state.edge;

import org.diagramsascode.state.node.State;

/**
 * A transition on a state diagram.
 * It connects states.
 * 
 * @author b_muth
 *
 */
public class Transition extends StateDiagramEdge {
  /**
   * Creates an edge.
   * 
   * @param from the starting node of the edge
   * @param to the ending node of the edge
   * @param text the text of the edge
   */
  public Transition(State from, State to, String text) {
    super(from, to, text);
  }
}
