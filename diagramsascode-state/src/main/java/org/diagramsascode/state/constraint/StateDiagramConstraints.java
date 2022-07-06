package org.diagramsascode.state.constraint;

import java.util.Arrays;
import java.util.Collection;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramConstraints;
import org.diagramsascode.core.DiagramEdgeConstraint;
import org.diagramsascode.core.DiagramNodeConstraint;

/**
 * Collects all constraints that are relevant for an sequence diagram.
 * 
 * @author b_muth
 *
 */
public class StateDiagramConstraints implements DiagramConstraints {
  public static final String DIAGRAM_TYPE = "state";

  @Override
  public Collection<DiagramNodeConstraint> nodeConstraintsFor(Diagram diagram) {
    return Arrays.asList(new OnlyStateDiagramNodesOnDiagram(), new StateHasName());
  }

  @Override
  public Collection<DiagramEdgeConstraint> edgeConstraintsFor(Diagram diagram) {
    return Arrays.asList(new OnlyStateDiagramEdgesOnDiagram());
  }
}
