package org.diagramsascode.core;

/**
 * Interface to be implemented by all elements displayed on a diagram.
 * 
 * @author b_muth
 *
 */
public interface DiagramElement {
  /**
   * Returns the unique identifier of the diagram element.
   * 
   * @return the unique identifier
   */
  String getId();
  
  /**
   * Returns the text of the diagram element.
   * 
   * @return the text
   */
  String getText();
}
