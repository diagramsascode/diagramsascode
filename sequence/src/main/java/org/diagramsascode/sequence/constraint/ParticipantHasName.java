package org.diagramsascode.sequence.constraint;

import java.util.Optional;

import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.core.DiagramNodeConstraint;
import org.diagramsascode.sequence.node.Participant;

/**
 * Constraint that ensures that each participant has a name (instead of an empty string).
 * 
 * @author b_muth
 *
 */
public class ParticipantHasName implements DiagramNodeConstraint {
  @Override
  public Optional<ConstraintViolation<DiagramNode>> validate(DiagramNode node) {
    ConstraintViolation<DiagramNode> constraintViolation = null;

    if (node instanceof Participant && node.getText().trim().isEmpty()) {
      constraintViolation = new ConstraintViolation<DiagramNode>(this, node,
          "Participant with id " + node.getId() + " should hava a name");
    }

    return Optional.ofNullable(constraintViolation);
  }

}
