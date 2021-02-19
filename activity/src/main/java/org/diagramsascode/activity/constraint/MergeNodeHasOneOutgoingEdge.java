package org.diagramsascode.activity.constraint;

import java.util.List;
import java.util.Optional;

import org.diagramsascode.activity.node.MergeNode;
import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.core.DiagramNodeConstraint;

public class MergeNodeHasOneOutgoingEdge implements DiagramNodeConstraint{
  private final Diagram diagram;

  public MergeNodeHasOneOutgoingEdge(Diagram diagram) {
    this.diagram = diagram;
  }

  @Override
  public Optional<ConstraintViolation<DiagramNode>> validate(DiagramNode node) {
    ConstraintViolation<DiagramNode> constraintViolation = null;

    if (node instanceof MergeNode) {
      List<DiagramEdge> outgoingEdges = diagram.getOutgoingEdgesOf(node);
      if (outgoingEdges.size() != 1) {
        constraintViolation = new ConstraintViolation<>(this, node, "");
      }
    }

    return Optional.ofNullable(constraintViolation);
  }
}
