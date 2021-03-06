package org.diagramsascode.activity.constraint;

import java.util.Optional;

import org.diagramsascode.activity.edge.ActivityDiagramEdge;
import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramEdgeConstraint;

/**
 * Constraint that ensures only edges of types that are valid on activity diagrams
 * are actually shown on it.
 * 
 * @author b_muth
 *
 */
public class OnlyActivityDiagramEdgesOnDiagram implements DiagramEdgeConstraint {

  @Override
  public Optional<ConstraintViolation<DiagramEdge>> validate(DiagramEdge edge) {
    ConstraintViolation<DiagramEdge> constraintViolation = null;

    if (!(edge instanceof ActivityDiagramEdge)) {
      constraintViolation = new ConstraintViolation<>(this, edge, "Edge " + edge.getText() + " isn't valid on activity diagrams");
    }

    return Optional.ofNullable(constraintViolation);
  }

}
