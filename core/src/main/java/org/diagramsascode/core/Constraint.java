package org.diagramsascode.core;

import java.util.Optional;

/**
 * Constrains diagram elements of the specified diagram element type.
 * 
 * @author b_muth
 *
 * @param <T> the type to constrain
 */
public interface Constraint<T extends DiagramElement> {
  
  /**
   * Validate if the specified diagram element's information is correct,
   * and return a constraint violation if not. Implementations first
   * have to check whether the constraint applies to the specified
   * diagram element (typically using instanceof), because other types
   * of diagram elements may be passed in as argument.
   * 
   * @param diagramElement the element to validate
   * @return the (optional) constraint violation, or an empty optional
   */
  Optional<ConstraintViolation<T>> validate(T diagramElement);
}
