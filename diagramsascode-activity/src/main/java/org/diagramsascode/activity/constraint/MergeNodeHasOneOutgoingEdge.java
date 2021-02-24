package org.diagramsascode.activity.constraint;

import java.util.List;
import java.util.Optional;

import org.diagramsascode.activity.node.MergeNode;
import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.core.DiagramNodeConstraint;

/**
 * Constraint that ensures that each merge node has exactly one outgoing edge.
 * 
 * @author b_muth
 *
 */
public class MergeNodeHasOneOutgoingEdge implements DiagramNodeConstraint{
  private final Diagram diagram;

  /**
   * Creates the constraint for the specified diagram
   * 
   * @param diagram the diagram whose elements are constrained by this constraint
   */
  public MergeNodeHasOneOutgoingEdge(Diagram diagram) {
    this.diagram = diagram;
  }

  @Override
  public Optional<ConstraintViolation<DiagramNode>> validate(DiagramNode node) {
    ConstraintViolation<DiagramNode> constraintViolation = null;

    if (node instanceof MergeNode) {
      List<DiagramEdge> outgoingEdges = diagram.getOutgoingEdgesOf(node);
      if (outgoingEdges.size() != 1) {
        constraintViolation = new ConstraintViolation<>(this, node, "Merge node with id " + node.getId() + " should have 1 outgoing edge, but has " + outgoingEdges.size());
      }
    }

    return Optional.ofNullable(constraintViolation);
  }
}
