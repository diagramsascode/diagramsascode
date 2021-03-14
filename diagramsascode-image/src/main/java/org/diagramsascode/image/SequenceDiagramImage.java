package org.diagramsascode.image;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.image.sequence.SequenceDiagramToSource;

public class SequenceDiagramImage {

  public static Image of(Diagram diagram) {
    ImageSource source = ImageSource.of(diagram, new SequenceDiagramToSource());
    Image image = Image.fromSource(source);
    return image;
  }

}
