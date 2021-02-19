package org.diagramsascode.activity.node;

import org.diagramsascode.core.DiagramNode;

public abstract class ActivityNode extends DiagramNode {
  public ActivityNode() {
    this("");
  }
  
  public ActivityNode(String text) {
    super(text);
  }
}
