package org.diagramsascode.sequence.constraint;

import java.util.Optional;

import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.core.DiagramNodeConstraint;
import org.diagramsascode.sequence.node.SequenceDiagramNode;

/**
 * Constraint that ensures only nodes of types that are valid on sequence diagrams
 * are actually shown on it.
 * 
 * @author b_muth
 *
 */
public class OnlySequenceDiagramNodesOnDiagram implements DiagramNodeConstraint {

  @Override
  public Optional<ConstraintViolation<DiagramNode>> validate(DiagramNode node) {
    ConstraintViolation<DiagramNode> constraintViolation = null;

    if (!(node instanceof SequenceDiagramNode)) {
      constraintViolation = new ConstraintViolation<>(this, node, "Node " + node.getText() + " isn't valid on sequence diagrams");
    }

    return Optional.ofNullable(constraintViolation);
  }
}
