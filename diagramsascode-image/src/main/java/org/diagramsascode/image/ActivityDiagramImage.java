package org.diagramsascode.image;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.image.activity.ActivityDiagramToSource;

public class ActivityDiagramImage {
  public static Image of(Diagram diagram) {
    ImageSource source = ImageSource.of(diagram, new ActivityDiagramToSource());
    Image image = Image.fromSource(source);
    return image;
  }
}
