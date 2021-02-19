package org.diagramsascode.activity.constraint;

import java.util.Optional;

import org.diagramsascode.activity.edge.ActivityEdge;
import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramEdgeConstraint;

public class OnlyActivityEdgesOnDiagram implements DiagramEdgeConstraint {

  @Override
  public Optional<ConstraintViolation<DiagramEdge>> validate(DiagramEdge edge) {
    ConstraintViolation<DiagramEdge> constraintViolation = null;

    if (!(edge instanceof ActivityEdge)) {
      constraintViolation = new ConstraintViolation<>(this, edge, "Edge " + edge.getText() + " isn't valid on activity diagrams");
    }

    return Optional.ofNullable(constraintViolation);
  }

}
