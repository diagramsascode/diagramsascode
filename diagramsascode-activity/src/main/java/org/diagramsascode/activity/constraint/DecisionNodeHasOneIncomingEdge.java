package org.diagramsascode.activity.constraint;

import java.util.List;
import java.util.Optional;

import org.diagramsascode.activity.node.DecisionNode;
import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.core.DiagramNodeConstraint;

/**
 * Constraint that ensures that each decision node has exactly one incoming edge
 * 
 * @author b_muth
 *
 */
public class DecisionNodeHasOneIncomingEdge implements DiagramNodeConstraint{
  private final Diagram diagram;

  /**
   * Creates the constraint for the specified diagram
   * 
   * @param diagram the diagram whose elements are constrained by this constraint
   */
  public DecisionNodeHasOneIncomingEdge(Diagram diagram) {
    this.diagram = diagram;
  }

  @Override
  public Optional<ConstraintViolation<DiagramNode>> validate(DiagramNode node) {
    ConstraintViolation<DiagramNode> constraintViolation = null;

    if (node instanceof DecisionNode) {
      List<DiagramEdge> incomingEdges = diagram.getIncomingEdgesOf(node);
      if (incomingEdges.size() != 1) {
        constraintViolation = new ConstraintViolation<>(this, node, "Decision node with id " + node.getId() + " should have 1 incoming edge, but has " + incomingEdges.size());
      }
    }

    return Optional.ofNullable(constraintViolation);
  }
}
