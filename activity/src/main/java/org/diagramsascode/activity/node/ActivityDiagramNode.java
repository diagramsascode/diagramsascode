package org.diagramsascode.activity.node;

import org.diagramsascode.core.DiagramNode;

/**
  * Abstract base class for all nodes shown on an activity diagram.
  * 
 * @author b_muth
 *
 */
public abstract class ActivityDiagramNode extends DiagramNode {
  /**
   * Creates an activity diagram node with empty text.
   * 
   */
  public ActivityDiagramNode() {
    this("");
  }
  
  /**
   * Creates an activity diagram node with the specified text.
   * 
   * @param text the text of the node
   */
  public ActivityDiagramNode(String text) {
    super(text);
  }
}
