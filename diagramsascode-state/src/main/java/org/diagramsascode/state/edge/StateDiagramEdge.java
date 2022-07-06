package org.diagramsascode.state.edge;

import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.state.node.StateDiagramNode;

/**
 * Abstract base class for all edges shown on a state diagram.
 *  
 * @author b_muth
 *
 */
public abstract class StateDiagramEdge extends DiagramEdge {
  /**
   * Creates an edge with the specified text.
   * 
   * @param from the starting node of the edge
   * @param to the ending node of the edge
   * @param text the text of the edge
   */
  public StateDiagramEdge(StateDiagramNode from, StateDiagramNode to, String text) {
    super(from, to, text);
  }
}
