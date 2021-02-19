package org.diagramsascode.activity.constraint;

import java.util.Optional;

import org.diagramsascode.activity.node.Action;
import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.core.DiagramNodeConstraint;

public class ActionHasName implements DiagramNodeConstraint {
  @Override
  public Optional<ConstraintViolation<DiagramNode>> validate(DiagramNode node) {
    ConstraintViolation<DiagramNode> constraintViolation = null;

    if (node instanceof Action && node.getText().isBlank()) {
      constraintViolation = new ConstraintViolation<DiagramNode>(this, node,
          "Action with id " + node.getId() + " should hava a name");
    }

    return Optional.ofNullable(constraintViolation);
  }

}
