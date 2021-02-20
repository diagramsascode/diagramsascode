package org.diagramsascode.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

class ConstraintValidator {
  private final CoreConstraints coreConstraints;
  private final Diagram diagram;

  private ConstraintValidator(Diagram diagram) {
    this.coreConstraints = new CoreConstraints();
    this.diagram = Objects.requireNonNull(diagram, "diagram must be non-null");
  }

  public static ConstraintValidator of(Diagram diagram) {
    return new ConstraintValidator(diagram);
  }
  
  public List<ConstraintViolation<? extends DiagramElement>> validate() {
    DiagramConstraints diagramConstraints = diagram.getConstraints();

    Collection<DiagramNodeConstraint> coreAndDiagramSpecificNodeConstraints = coreAndDiagramSpecificNodeConstraints(
        diagramConstraints);

    Collection<DiagramEdgeConstraint> coreAndDiagramSpecificEdgeConstraints = coreAndDiagramSpecificEdgeConstraints(
        diagramConstraints);
    
    List<ConstraintViolation<? extends DiagramElement>> violations = new ArrayList<>();
    addNodeConstraintViolations(coreAndDiagramSpecificNodeConstraints, violations);
    addEdgeConstraintViolations(coreAndDiagramSpecificEdgeConstraints, violations);
    return violations;
  }

  private Collection<DiagramNodeConstraint> coreAndDiagramSpecificNodeConstraints(DiagramConstraints diagramConstraints) {
    Collection<DiagramNodeConstraint> allConstraints = new ArrayList<>();
    Collection<DiagramNodeConstraint> coreNodeConstraints = coreConstraints.nodeConstraintsFor(diagram);
    Collection<DiagramNodeConstraint> diagramSpecificNodeConstraints = Objects.requireNonNull(diagramConstraints.nodeConstraintsFor(diagram),
        "diagram node constraints must be non-null");
    allConstraints.addAll(coreNodeConstraints);
    allConstraints.addAll(diagramSpecificNodeConstraints);
    return allConstraints;
  }
  
  private Collection<DiagramEdgeConstraint> coreAndDiagramSpecificEdgeConstraints(DiagramConstraints diagramConstraints) {
    Collection<DiagramEdgeConstraint> allConstraints = new ArrayList<>();
    Collection<DiagramEdgeConstraint> coreEdgeConstraints = coreConstraints.edgeConstraintsFor(diagram);
    Collection<DiagramEdgeConstraint> diagramSpecificEdgeConstraints = Objects.requireNonNull(diagramConstraints.edgeConstraintsFor(diagram),
        "diagram edge constraints must be non-null");
    allConstraints.addAll(coreEdgeConstraints);
    allConstraints.addAll(diagramSpecificEdgeConstraints);
    return allConstraints;
  }

  private void addNodeConstraintViolations(Collection<DiagramNodeConstraint> nodeConstraints, List<ConstraintViolation<? extends DiagramElement>> violations) {
    diagram.getNodes().forEach(node -> {
      nodeConstraints.stream()
        .map(constraint -> constraint.validate(node))
        .filter(optinalViolation -> optinalViolation.isPresent())
        .map(optinalViolation -> optinalViolation.get())
        .forEach(violation -> violations.add(violation));
    });
  }
  
  private void addEdgeConstraintViolations(Collection<DiagramEdgeConstraint> edgeConstraints, List<ConstraintViolation<? extends DiagramElement>> violations) {
    diagram.getEdges().forEach(edge -> {
      edgeConstraints.stream()
        .map(constraint -> constraint.validate(edge))
        .filter(optinalViolation -> optinalViolation.isPresent())
        .map(optinalViolation -> optinalViolation.get())
        .forEach(violation -> violations.add(violation));
    });
  }
}
