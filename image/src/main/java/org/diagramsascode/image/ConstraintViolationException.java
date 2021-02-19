package org.diagramsascode.image;

import java.util.List;
import java.util.stream.Collectors;

import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.DiagramElement;

@SuppressWarnings("serial")
public class ConstraintViolationException extends RuntimeException{
  private final List<ConstraintViolation<? extends DiagramElement>> constraintViolations;

  public ConstraintViolationException(List<ConstraintViolation<? extends DiagramElement>> constraintViolations) {
    super("Constraints violated");
    this.constraintViolations = constraintViolations;
  }
  
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
