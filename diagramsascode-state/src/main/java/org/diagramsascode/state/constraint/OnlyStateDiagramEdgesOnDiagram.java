package org.diagramsascode.state.constraint;

import static org.diagramsascode.state.constraint.StateDiagramConstraints.DIAGRAM_TYPE;

import java.util.Optional;

import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramEdgeConstraint;
import org.diagramsascode.state.edge.StateDiagramEdge;

/**
 * Constraint that ensures only edges of types that are valid on the diagram are
 * actually shown on it.
 * 
 * @author b_muth
 *
 */
public class OnlyStateDiagramEdgesOnDiagram implements DiagramEdgeConstraint {
  @Override
  public Optional<ConstraintViolation<DiagramEdge>> validate(DiagramEdge edge) {
    ConstraintViolation<DiagramEdge> constraintViolation = null;

    if (!(edge instanceof StateDiagramEdge)) {
      constraintViolation = new ConstraintViolation<>(this, edge,
          "Edge " + edge.getText() + " isn't valid on " + DIAGRAM_TYPE + " diagrams");
    }

    return Optional.ofNullable(constraintViolation);
  }

}
