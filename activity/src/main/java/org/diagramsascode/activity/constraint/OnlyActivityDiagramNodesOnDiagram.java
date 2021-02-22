package org.diagramsascode.activity.constraint;

import java.util.Optional;

import org.diagramsascode.activity.node.ActivityDiagramNode;
import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.core.DiagramNodeConstraint;

/**
 * Constraint that ensures only nodes of types that are valid on activity diagrams
 * are actually shown on it.
 * 
 * @author b_muth
 *
 */
public class OnlyActivityDiagramNodesOnDiagram implements DiagramNodeConstraint {

  @Override
  public Optional<ConstraintViolation<DiagramNode>> validate(DiagramNode node) {
    ConstraintViolation<DiagramNode> constraintViolation = null;

    if (!(node instanceof ActivityDiagramNode)) {
      constraintViolation = new ConstraintViolation<>(this, node, "Node " + node.getText() + " isn't valid on activity diagrams");
    }

    return Optional.ofNullable(constraintViolation);
  }
}
