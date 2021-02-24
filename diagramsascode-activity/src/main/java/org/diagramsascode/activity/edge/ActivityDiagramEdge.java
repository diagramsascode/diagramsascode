package org.diagramsascode.activity.edge;

import org.diagramsascode.activity.node.ActivityDiagramNode;
import org.diagramsascode.core.DiagramEdge;

/**
 * Abstract base class for all edges shown on an activity diagram.
 *  
 * @author b_muth
 *
 */
public abstract class ActivityDiagramEdge extends DiagramEdge {
  /**
   * Creates an activity diagram edge with empty text.
   * 
   * @param from the starting node of the edge
   * @param to the ending node of the edge
   */
  public ActivityDiagramEdge(ActivityDiagramNode from, ActivityDiagramNode to) {
    this(from, to, "");
  }
  
  /**
   * Creates an activity diagram edge with the specified text.
   * 
   * @param from the starting node of the edge
   * @param to the ending node of the edge
   * @param text the text of the edge
   */
  public ActivityDiagramEdge(ActivityDiagramNode from, ActivityDiagramNode to, String text) {
    super(from, to, text);
  }
}
