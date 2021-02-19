package org.diagramsascode.activity.constraint;

import java.util.List;
import java.util.Optional;

import org.diagramsascode.activity.node.InitialNode;
import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.core.DiagramNodeConstraint;

public class InitialNodeHasNoIncomingEdges implements DiagramNodeConstraint {
  private final Diagram diagram;

  public InitialNodeHasNoIncomingEdges(Diagram diagram) {
    this.diagram = diagram;
  }

  @Override
  public Optional<ConstraintViolation<DiagramNode>> validate(DiagramNode node) {
    ConstraintViolation<DiagramNode> constraintViolation = null;

    if (node instanceof InitialNode) {
      List<DiagramEdge> incomingEdges = diagram.getIncomingEdgesOf(node);
      if (!incomingEdges.isEmpty()) {
        constraintViolation = new ConstraintViolation<>(this, node, "Initial node must not have incoming edges");
      }
    }

    return Optional.ofNullable(constraintViolation);
  }
}
