package org.diagramsascode.state.constraint;

import java.util.Optional;

import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.core.DiagramNodeConstraint;
import org.diagramsascode.state.node.State;

/**
 * Constraint that ensures that each participant has a name (instead of an empty string).
 * 
 * @author b_muth
 *
 */
public class StateHasName implements DiagramNodeConstraint {
  private static final String MODEL_ELEMENT_TYPE = "State";
  
  @Override
  public Optional<ConstraintViolation<DiagramNode>> validate(DiagramNode node) {
    ConstraintViolation<DiagramNode> constraintViolation = null;

    if (node instanceof State && node.getText().trim().isEmpty()) {
      constraintViolation = new ConstraintViolation<DiagramNode>(this, node,
          MODEL_ELEMENT_TYPE + " with id " + node.getId() + " should hava a name");
    }

    return Optional.ofNullable(constraintViolation);
  }

}
