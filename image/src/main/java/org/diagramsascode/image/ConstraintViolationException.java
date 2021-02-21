package org.diagramsascode.image;

import java.util.List;
import java.util.stream.Collectors;

import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.DiagramElement;

/**
 * Thrown to indicate constraints have been violated when trying
 * to generate an image from a diagram model.
 * 
 * @author b_muth
 *
 */
@SuppressWarnings("serial")
public class ConstraintViolationException extends RuntimeException{
  private final List<ConstraintViolation<? extends DiagramElement>> constraintViolations;

  /**
   * Creates an exception based on the constraint violations that have occured
   * when validating the diagram.
   * 
   * @param constraintViolations the violations that have occured
   */
  public ConstraintViolationException(List<ConstraintViolation<? extends DiagramElement>> constraintViolations) {
    super("Constraints violated");
    this.constraintViolations = constraintViolations;
  }
  
  /**
   * Returns the constraint violations contained by the exception.
   * 
   * @return the violations
   */
  public List<ConstraintViolation<? extends DiagramElement>> getConstraintViolations() {
    return constraintViolations;
  }
  
  @Override
  public String toString() {
    String constraintViolationsString = constraintViolations.stream()
      .map(ConstraintViolation::toString)
      .collect(Collectors.joining("\n"));
        
    return constraintViolationsString;
  }
}
