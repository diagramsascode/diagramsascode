package org.diagramsascode.activity.constraint;

import java.util.List;
import java.util.Optional;

import org.diagramsascode.activity.node.MergeNode;
import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.core.DiagramNodeConstraint;

public class MergeNodeHasAtLeastOneIncomingEdge implements DiagramNodeConstraint{
  private final Diagram diagram;

  public MergeNodeHasAtLeastOneIncomingEdge(Diagram diagram) {
    this.diagram = diagram;
  }

  @Override
  public Optional<ConstraintViolation<DiagramNode>> validate(DiagramNode node) {
    ConstraintViolation<DiagramNode> constraintViolation = null;

    if (node instanceof MergeNode) {
      List<DiagramEdge> incomingEdges = diagram.getIncomingEdgesOf(node);
      if (incomingEdges.isEmpty()) {
        constraintViolation = new ConstraintViolation<>(this, node, "Merge node with id " + node.getId() + " should have at least one incoming edge, but has none");
      }
    }

    return Optional.ofNullable(constraintViolation);
  }
}
