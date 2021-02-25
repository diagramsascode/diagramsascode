package org.diagramsascode.image;

import org.diagramsascode.core.Diagram;

public class SequenceDiagramImage {

  public static Image of(Diagram diagram) {
    ImageSource source = ImageSource.ofSequenceDiagram(diagram);
    Image image = Image.fromSource(source);
    return image;
  }

}
