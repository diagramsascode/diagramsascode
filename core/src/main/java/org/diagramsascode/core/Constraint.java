package org.diagramsascode.core;

import java.util.Optional;

/**
 * Constrains diagram elements of the specified type
 * 
 * @author b_muth
 *
 * @param <T> the type to constrain
 */
public interface Constraint<T extends DiagramElement> {
  Optional<ConstraintViolation<T>> validate(T diagramElement);
}
