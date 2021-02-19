package org.diagramsascode.activity.edge;

import java.util.Objects;

import org.diagramsascode.activity.node.ActivityNode;

public class ControlFlow extends ActivityEdge {
  public ControlFlow(ActivityNode from, ActivityNode to) {
    super(from, to);
  }
  
  public ControlFlow(ActivityNode from, ActivityNode to, String condition) {
    super(from, to, createGuard(condition));
  }

  private static String createGuard(String condition) {
    return "[" + Objects.requireNonNull(condition, "condition must be non-null") + "]";
  }

  @Override
  public String toString() {
    return "ControlFlow [from=" + getFrom() + ", to=" + getTo() + ", guard=" + getText()
        + "]";
  }
}
