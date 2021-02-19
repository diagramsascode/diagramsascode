package org.diagramsascode.core;

import java.util.Collection;

public interface DiagramConstraints {
  Collection<DiagramNodeConstraint> nodeConstraintsFor(Diagram diagram);
  Collection<DiagramEdgeConstraint> edgeConstraintsFor(Diagram diagram);
}
