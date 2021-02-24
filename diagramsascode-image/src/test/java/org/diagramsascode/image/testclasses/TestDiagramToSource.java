package org.diagramsascode.image.testclasses;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.image.DiagramToSource;

public class TestDiagramToSource implements DiagramToSource {
  public static final String DIAGRAM_HEADER = "DiagramHeader\n";
  public static final String DIAGRAM_FOOTER = "DiagramFooter\n";

  @Override
  public String header(Diagram diagram) {
    return DIAGRAM_HEADER;
  }

  @Override
  public String node(DiagramNode node) {
    String source = node.getText() + "\n";
    return source;
  }

  @Override
  public String edge(DiagramEdge edge) {
    String source = edge.getFrom().getText() + " -> " + edge.getTo().getText() + "\n";
    return source;
  }

  @Override
  public String footer(Diagram diagram) {
    return DIAGRAM_FOOTER;
  }
}
