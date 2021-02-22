package org.diagramsascode.image.sequence;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.image.DiagramToSource;

/**
 * Converts a sequence diagrams content to a source text
 * that can be used by PlantUML to generate an activity diagram image.
 * 
 * @author b_muth
 *
 */
public class SequenceDiagramToSource implements DiagramToSource {
  static final String ASYNC_MESSAGE = " ->> ";
  static final String QUOTE = "\"";
  static final String DIAGRAM_HEADER = "@startuml\n!pragma graphviz_dot smetana\n" + 
      "skinparam style strictuml\n" +
      "skinparam monochrome true\nhide empty description\n";
  static final String DIAGRAM_FOOTER = "@enduml\n";
    
  @Override
  public String header(Diagram diagram) {
    return DIAGRAM_HEADER;
  }

  @Override
  public String node(DiagramNode node) {
    String source = "";
    return source;
  }

  @Override
  public String edge(DiagramEdge edge) {
    String fromNodeText = edge.getFrom().getText();
    String toNodeText = edge.getTo().getText();
    
    String source = QUOTE + fromNodeText + QUOTE + ASYNC_MESSAGE + QUOTE + toNodeText + QUOTE + " : " + edge.getText() + "\n";
    return source;
  }

  @Override
  public String footer(Diagram diagram) {
    return DIAGRAM_FOOTER;
  }
}
