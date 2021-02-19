package org.diagramsascode.activity.constraint;

import java.util.Optional;

import org.diagramsascode.activity.node.ActivityNode;
import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.core.DiagramNodeConstraint;

public class OnlyActivityNodesOnDiagram implements DiagramNodeConstraint {

  @Override
  public Optional<ConstraintViolation<DiagramNode>> validate(DiagramNode node) {
    ConstraintViolation<DiagramNode> constraintViolation = null;

    if (!(node instanceof ActivityNode)) {
      constraintViolation = new ConstraintViolation<>(this, node, "");
    }

    return Optional.ofNullable(constraintViolation);
  }
}
