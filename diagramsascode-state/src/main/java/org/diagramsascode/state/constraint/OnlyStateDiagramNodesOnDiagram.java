package org.diagramsascode.state.constraint;

import java.util.Optional;

import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.core.DiagramNodeConstraint;
import org.diagramsascode.state.node.StateDiagramNode;
import static org.diagramsascode.state.constraint.StateDiagramConstraints.DIAGRAM_TYPE;

/**
 * Constraint that ensures only nodes of types that are valid on the
 * diagram are actually shown on it.
 * 
 * @author b_muth
 *
 */
public class OnlyStateDiagramNodesOnDiagram implements DiagramNodeConstraint {

  @Override
  public Optional<ConstraintViolation<DiagramNode>> validate(DiagramNode node) {
    ConstraintViolation<DiagramNode> constraintViolation = null;

    if (!(node instanceof StateDiagramNode)) {
      constraintViolation = new ConstraintViolation<>(this, node,
          "Node " + node.getText() + " isn't valid on " + DIAGRAM_TYPE + " diagrams");
    }

    return Optional.ofNullable(constraintViolation);
  }
}
