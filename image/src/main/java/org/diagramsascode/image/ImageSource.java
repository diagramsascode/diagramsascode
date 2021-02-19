package org.diagramsascode.image;

import java.util.Objects;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.image.activity.ActivityDiagramToSource;

public class ImageSource {
  private final Diagram diagram;
  private final DiagramToSource diagramToSource;

  private ImageSource(Diagram diagram, DiagramToSource diagramToSource) {
    this.diagram = Objects.requireNonNull(diagram, "diagram must be non-null");
    this.diagramToSource = Objects.requireNonNull(diagramToSource, "diagramToSource must be non-null");
  }
  
  public static ImageSource ofActivityDiagram(Diagram diagram) {
    return of(diagram, new ActivityDiagramToSource());
  }
  
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
