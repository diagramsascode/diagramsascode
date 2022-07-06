package org.diagramsascode.image.state;

import java.util.ArrayList;
import java.util.List;

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
public class StateDiagramToSource implements DiagramToSource {
  static final String TRANSITION = " --> ";
  static final String QUOTE = "\"";
  static final String DIAGRAM_HEADER = "@startuml\n!pragma layout smetana\n" + 
      "skinparam style strictuml\n" +
      "skinparam monochrome true\nhide empty description\n";
  static final String DIAGRAM_FOOTER = "@enduml\n";
  private List<DiagramNode> nodes;
    
  @Override
  public String header(Diagram diagram) {
    this.nodes = new ArrayList<>(diagram.getNodes());
    return DIAGRAM_HEADER;
  }

  @Override
  public String node(DiagramNode node) {
    String source = "state \"" + node.getText() + "\" as " + referenceOf(node) + "\n";
    return source;
  }

  @Override
  public String edge(DiagramEdge edge) {
    DiagramNode fromNode = edge.getFrom();
    DiagramNode toNode = edge.getTo();
    
    String source = referenceOf(fromNode) + TRANSITION +  referenceOf(toNode) + " : " + edge.getText() + "\n";
    return source;
  }

  @Override
  public String footer(Diagram diagram) {
    return DIAGRAM_FOOTER;
  }
  
  private String referenceOf(DiagramNode node) {
    return "S" + String.valueOf(nodes.indexOf(node));
  }
}
