package org.diagramsascode.image;

import org.diagramsascode.core.Diagram;

public class ActivityDiagramImage {
  public static Image of(Diagram diagram) {
    ImageSource source = ImageSource.ofActivityDiagram(diagram);
    Image image = Image.fromSource(source);
    return image;
  }
}
