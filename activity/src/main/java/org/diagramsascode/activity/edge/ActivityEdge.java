package org.diagramsascode.activity.edge;

import org.diagramsascode.activity.node.ActivityNode;
import org.diagramsascode.core.DiagramEdge;

/**
 * Abstract base class for all edges shown on an activity diagram.
 *  
 * @author b_muth
 *
 */
public abstract class ActivityEdge extends DiagramEdge {
  /**
   * Creates an activity edge with empty text.
   * 
   * @param from the starting node of the edge
   * @param to the ending node of the edge
   */
  public ActivityEdge(ActivityNode from, ActivityNode to) {
    this(from, to, "");
  }
  
  /**
   * Creates an activity edge with the specified text.
   * 
   * @param from the starting node of the edge
   * @param to the ending node of the edge
   * @param text the text of the edge
   */
  public ActivityEdge(ActivityNode from, ActivityNode to, String text) {
    super(from, to, text);
  }
}
