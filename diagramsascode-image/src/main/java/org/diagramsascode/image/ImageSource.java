package org.diagramsascode.image;

import java.util.List;
import java.util.Objects;

import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramElement;

/**
 * Represents the textual source code that contains information how to generate the image.
 * 
 * Right now, this is PlantUML text based (https://plantuml.com/).
 * That might change in the future.
 * 
 * @author b_muth
 *
 */
public class ImageSource {
  private final Diagram diagram;
  private final DiagramToSource diagramToSource;

  private ImageSource(Diagram diagram, DiagramToSource diagramToSource) {
    this.diagram = Objects.requireNonNull(diagram, "diagram must be non-null");
    this.diagramToSource = Objects.requireNonNull(diagramToSource, "diagramToSource must be non-null");
    throwExceptionIfDiagramIsInvalid(diagram);
  }

  private void throwExceptionIfDiagramIsInvalid(Diagram diagram) {
    List<ConstraintViolation<? extends DiagramElement>> constraintViolations = diagram.validate();
    if(!constraintViolations.isEmpty()) {
      throw new ConstraintViolationException(constraintViolations);
    }
  }
  
  /**
   * Generic constructor, enabling users to customize how the contents of the diagram
   * are converted to a textual image source.
   * 
   * @param diagram the diagram to be converted to text 
   * @param diagramToSource the converter from the diagram contents to the text
   * @return the image source text
   */
  public static ImageSource of(Diagram diagram, DiagramToSource diagramToSource) {
    return new ImageSource(diagram, diagramToSource);
  }

  @Override
  public String toString() {    
    StringBuilder builder = new StringBuilder();
    
    appendHeader(builder);
    appendNodes(builder);
    appendEdges(builder);
    appendFooter(builder);
    
    return builder.toString();
  }
  
  private void appendHeader(StringBuilder builder) {
    String header = diagramToSource.header(diagram);
    builder.append(header);
  }

  private void appendNodes(StringBuilder builder) {
    diagram.getNodes().stream()
      .map(diagramToSource::node)
      .forEach(builder::append);
  }
  
  private void appendEdges(StringBuilder builder) {
    diagram.getEdges().stream()
      .map(diagramToSource::edge)
      .forEach(builder::append);
  }
  
  private void appendFooter(StringBuilder builder) {
    String footer = diagramToSource.footer(diagram);
    builder.append(footer);
  }
}
