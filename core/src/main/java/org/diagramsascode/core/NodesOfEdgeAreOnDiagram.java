package org.diagramsascode.core;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

class NodesOfEdgeAreOnDiagram implements DiagramEdgeConstraint {
  private final Diagram diagram;

  public NodesOfEdgeAreOnDiagram(Diagram diagram) {
    this.diagram = Objects.requireNonNull(diagram, "diagram must be non-null");
  }

  @Override
  public Optional<ConstraintViolation<DiagramEdge>> validate(DiagramEdge edge) {
    ConstraintViolation<DiagramEdge> constraintViolation = null;

    Collection<DiagramNode> nodesOnDiagram = diagram.getNodes();
    if (!nodesOnDiagram.contains(edge.getFrom()) || !nodesOnDiagram.contains(edge.getTo())) {
      String message = "The nodes of " + edge + " from " + edge.getFrom() + " to " + edge.getTo()
          + " must both be on diagram";
      constraintViolation = new ConstraintViolation<>(this, edge, message);
    }

    return Optional.ofNullable(constraintViolation);
  }
}
