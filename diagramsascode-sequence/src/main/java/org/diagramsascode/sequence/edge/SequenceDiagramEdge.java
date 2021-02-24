package org.diagramsascode.sequence.edge;

import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.sequence.node.SequenceDiagramNode;

/**
 * Abstract base class for all edges shown on an sequence diagram.
 *  
 * @author b_muth
 *
 */
public abstract class SequenceDiagramEdge extends DiagramEdge {
  /**
   * Creates an sequence diagram edge with empty text.
   * 
   * @param from the starting node of the edge
   * @param to the ending node of the edge
   */
  public SequenceDiagramEdge(SequenceDiagramNode from, SequenceDiagramNode to) {
    this(from, to, "");
  }
  
  /**
   * Creates an sequence diagram edge with the specified text.
   * 
   * @param from the starting node of the edge
   * @param to the ending node of the edge
   * @param text the text of the edge
   */
  public SequenceDiagramEdge(SequenceDiagramNode from, SequenceDiagramNode to, String text) {
    super(from, to, text);
  }
}
