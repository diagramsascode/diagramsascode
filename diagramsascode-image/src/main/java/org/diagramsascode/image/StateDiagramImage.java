package org.diagramsascode.image;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.image.state.StateDiagramToSource;

public class StateDiagramImage {

  public static Image of(Diagram diagram) {
    ImageSource source = ImageSource.of(diagram, new StateDiagramToSource());
    Image image = Image.fromSource(source);
    return image;
  }

}
