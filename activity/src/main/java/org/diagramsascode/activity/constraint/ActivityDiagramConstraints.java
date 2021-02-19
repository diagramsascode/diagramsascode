package org.diagramsascode.activity.constraint;

import java.util.Arrays;
import java.util.Collection;

import org.diagramsascode.core.DiagramConstraints;
import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdgeConstraint;
import org.diagramsascode.core.DiagramNodeConstraint;

public class ActivityDiagramConstraints implements DiagramConstraints {

  @Override
  public Collection<DiagramNodeConstraint> nodeConstraintsFor(Diagram diagram) {
    return Arrays.asList(
      new OnlyActivityNodesOnDiagram(),
      new ActionHasName(),
      new InitialNodeHasNoIncomingEdges(diagram), 
      new FinalNodeHasNoOutgoingEdges(diagram),
      new DecisionNodeHasOneIncomingEdge(diagram),
      new DecisionNodeHasAtLeastOneOutgoingEdge(diagram),
      new MergeNodeHasAtLeastOneIncomingEdge(diagram),
      new MergeNodeHasOneOutgoingEdge(diagram));
  }

  @Override
  public Collection<DiagramEdgeConstraint> edgeConstraintsFor(Diagram diagram) {
    return Arrays.asList(new OnlyActivityEdgesOnDiagram());
  }
}
