package org.diagramsascode.core;

import java.util.Arrays;
import java.util.Collection;

class CoreConstraints implements Constraints {

  @Override
  public Collection<DiagramNodeConstraint> nodeConstraintsFor(Diagram diagram) {
    return Arrays.asList();
  }

  @Override
  public Collection<DiagramEdgeConstraint> edgeConstraintsFor(Diagram diagram) {
    return Arrays.asList(
      new NodesOfEdgeAreOnDiagram(diagram));
  }
}
