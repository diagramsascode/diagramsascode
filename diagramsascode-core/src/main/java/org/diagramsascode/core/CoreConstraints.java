package org.diagramsascode.core;

import java.util.Arrays;
import java.util.Collection;

/**
 * Implicit additional constraints that are always validated, no matter
 * what you pass in as constraints to a diagram builder.
 * 
 * @author b_muth
 *
 */
class CoreConstraints implements DiagramConstraints {

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
