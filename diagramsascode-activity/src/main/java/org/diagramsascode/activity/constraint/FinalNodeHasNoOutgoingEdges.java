package org.diagramsascode.activity.constraint;

import java.util.List;
import java.util.Optional;

import org.diagramsascode.activity.node.FinalNode;
import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.core.DiagramNodeConstraint;

/**
 * Constraint that ensures that a final node has no outgoing edges
 * 
 * @author b_muth
 *
 */
public class FinalNodeHasNoOutgoingEdges implements DiagramNodeConstraint {
  private final Diagram diagram;

  /**
   * Creates the constraint for the specified diagram
   * 
   * @param diagram the diagram whose elements are constrained by this constraint
   */
  public FinalNodeHasNoOutgoingEdges(Diagram diagram) {
    this.diagram = diagram;
  }

  @Override
  public Optional<ConstraintViolation<DiagramNode>> validate(DiagramNode node) {
    ConstraintViolation<DiagramNode> constraintViolation = null;

    if (node instanceof FinalNode) {
      List<DiagramEdge> outgoingEdges = diagram.getOutgoingEdgesOf(node);
      if (!outgoingEdges.isEmpty()) {
        constraintViolation = new ConstraintViolation<>(this, node, "Final node must not have outgoing edges");
      }
    }

    return Optional.ofNullable(constraintViolation);
  }

}
