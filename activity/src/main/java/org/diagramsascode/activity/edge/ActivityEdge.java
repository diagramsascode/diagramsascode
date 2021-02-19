package org.diagramsascode.activity.edge;

import org.diagramsascode.activity.node.ActivityNode;
import org.diagramsascode.core.DiagramEdge;

public abstract class ActivityEdge extends DiagramEdge {
  public ActivityEdge(ActivityNode from, ActivityNode to) {
    this(from, to, "");
  }
  
  public ActivityEdge(ActivityNode from, ActivityNode to, String text) {
    super(from, to, text);
  }
}
