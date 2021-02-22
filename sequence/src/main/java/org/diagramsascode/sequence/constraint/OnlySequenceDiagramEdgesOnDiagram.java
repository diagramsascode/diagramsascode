package org.diagramsascode.sequence.constraint;

import java.util.Optional;

import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramEdgeConstraint;
import org.diagramsascode.sequence.edge.SequenceDiagramEdge;

/**
 * Constraint that ensures only edges of types that are valid on sequence diagrams
 * are actually shown on it.
 * 
 * @author b_muth
 *
 */
public class OnlySequenceDiagramEdgesOnDiagram implements DiagramEdgeConstraint {

  @Override
  public Optional<ConstraintViolation<DiagramEdge>> validate(DiagramEdge edge) {
    ConstraintViolation<DiagramEdge> constraintViolation = null;

    if (!(edge instanceof SequenceDiagramEdge)) {
      constraintViolation = new ConstraintViolation<>(this, edge, "Edge " + edge.getText() + " isn't valid on sequence diagrams");
    }

    return Optional.ofNullable(constraintViolation);
  }

}
