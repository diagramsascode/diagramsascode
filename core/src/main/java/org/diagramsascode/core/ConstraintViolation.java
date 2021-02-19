package org.diagramsascode.core;

import java.util.Objects;

public class ConstraintViolation<T extends DiagramElement> {
  private final Constraint<T> constraint;
  private final T diagramElement;
  private final String message;

  public ConstraintViolation(Constraint<T> constraint, T diagramElement, String message) {
    this.constraint = Objects.requireNonNull(constraint, "constraint must be non-null");
    this.diagramElement = Objects.requireNonNull(diagramElement, "diagramElement must be non-null");
    this.message = Objects.requireNonNull(message, "message must be non-null");
  }

  public Constraint<T> getConstraint() {
    return constraint;
  }

  public T getDiagramElement() {
    return diagramElement;
  }

  public String getMessage() {
    return message;
  }
  
  @Override
  public String toString() {
    return constraint.getClass().getSimpleName() + " [element=" + diagramElement + ", message=" + message + "]";
  }
}
