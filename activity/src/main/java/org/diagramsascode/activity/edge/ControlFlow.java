package org.diagramsascode.activity.edge;

import java.util.Objects;

import org.diagramsascode.activity.node.ActivityNode;

/**
 * A control flow edge on an activity diagram.
 * It connects to activity nodes.
 * 
 * @author b_muth
 *
 */
public class ControlFlow extends ActivityEdge {
  /**
   * Creates a control flow edge without a condition.
   * 
   * @param from the starting node of the edge
   * @param to the ending node of the edge
   */
  public ControlFlow(ActivityNode from, ActivityNode to) {
    super(from, to);
  }
  
  /**
   * Creates a control flow edge with a condition.
   * 
   * @param from the starting node of the edge
   * @param to the ending node of the edge
   * @param condition the condition of the edge. Don't use brackets [] in it.
   */
  public ControlFlow(ActivityNode from, ActivityNode to, String condition) {
    super(from, to, createGuard(condition));
  }

  private static String createGuard(String condition) {
    return "[" + Objects.requireNonNull(condition, "condition must be non-null") + "]";
  }
}
