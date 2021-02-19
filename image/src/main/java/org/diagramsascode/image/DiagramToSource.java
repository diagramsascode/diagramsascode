package org.diagramsascode.image;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;

public interface DiagramToSource {
  String header(Diagram diagram);
  String node(DiagramNode node);
  String edge(DiagramEdge node);
  String footer(Diagram diagram);
}
