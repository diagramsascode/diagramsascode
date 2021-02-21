package org.diagramsascode.activity.node;

import org.diagramsascode.core.DiagramNode;

/**
  * Abstract base class for all nodes shown on an activity diagram.
  * 
 * @author b_muth
 *
 */
public abstract class ActivityNode extends DiagramNode {
  /**
   * Creates an activity node with empty text.
   * 
   */
  public ActivityNode() {
    this("");
  }
  
  /**
   * Creates an activity node with the specified text.
   * 
   * @param text the text of the node
   */
  public ActivityNode(String text) {
    super(text);
  }
}
