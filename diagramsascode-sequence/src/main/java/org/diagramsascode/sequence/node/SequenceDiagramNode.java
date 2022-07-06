package org.diagramsascode.sequence.node;

import org.diagramsascode.core.DiagramNode;

/**
  * Abstract base class for all nodes shown on an sequence diagram.
  * 
 * @author b_muth
 *
 */
public abstract class SequenceDiagramNode extends DiagramNode {
  /**
   * Creates a node with empty text.
   * 
   */
  public SequenceDiagramNode() {
    this("");
  }
  
  /**
   * Creates a node with the specified text.
   * 
   * @param text the text of the node
   */
  public SequenceDiagramNode(String text) {
    super(text);
  }
}
