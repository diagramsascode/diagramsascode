package org.diagramsascode.core;

import java.util.Objects;

/**
 * Class representing the violation of a constraint,
 * when a diagram is validated.
 * 
 * @author b_muth
 *
 * @param <T> the type of diagram element for which a constraint has been violated.
 */
public class ConstraintViolation<T extends DiagramElement> {
  private final Constraint<T> constraint;
  private final T diagramElement;
  private final String message;

  /**
   * Creates a constraint violation.
   * 
   * @param constraint the constraint that has been violated.
   * @param diagramElement the diagram element for which a constraint has been violated.
   * @param message a message describing the violation.
   */
  public ConstraintViolation(Constraint<T> constraint, T diagramElement, String message) {
    this.constraint = Objects.requireNonNull(constraint, "constraint must be non-null");
    this.diagramElement = Objects.requireNonNull(diagramElement, "diagramElement must be non-null");
    this.message = Objects.requireNonNull(message, "message must be non-null");
  }

  /**
   * Returns the constraint that has been violated.
   * 
   * @return the violated constraint
   */
  public Constraint<T> getConstraint() {
    return constraint;
  }

  /**
   * Returns the diagram element for which a constraint has been violated.
   * @return the diagram element
   */
  public T getDiagramElement() {
    return diagramElement;
  }

  /**
   * Returns the message describing the violation.
   * @return the message
   */
  public String getMessage() {
    return message;
  }
  
  @Override
  public String toString() {
    return constraint.getClass().getSimpleName() + " [element=" + diagramElement + ", message=" + message + "]";
  }
}
