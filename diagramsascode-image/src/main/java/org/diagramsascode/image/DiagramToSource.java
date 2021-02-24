package org.diagramsascode.image;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;

/**
 * Interface to be implemented by converter classes that convert diagram models to text.
 * 
 * @author b_muth
 *
 */
public interface DiagramToSource {
  /**
   * Produce the header text of the diagram image source.
   * 
   * @param diagram the input diagram
   * @return the source text of the header
   */
  String header(Diagram diagram);
  
  /**
   * When a diagram node is passed in by the library,
   * this method produces the diagram image source text
   * of that node.
   * 
   * @param node the input node
   * @return the source text for the node
   */
  String node(DiagramNode node);
  
  /**
   * When a diagram edge is passed in by the library,
   * this method produces the diagram image source text
   * of that edge.
   * 
   * @param edge the input edge
   * @return the source text for the edge
   */
  String edge(DiagramEdge edge);
  
  /**
   * Produce the footer text of the diagram image source.
   * 
   * @param diagram the input diagram
   * @return the source text of the footer
   */
  String footer(Diagram diagram);
}
