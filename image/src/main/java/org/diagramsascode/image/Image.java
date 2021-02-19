package org.diagramsascode.image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.core.DiagramDescription;

public class Image {
  private final ImageSource imageSource;

  private Image(ImageSource imageSource) {
    this.imageSource = Objects.requireNonNull(imageSource, "imageSource must be non-null");
  }
  
  public static Image fromSource(ImageSource imageSource) {
    return new Image(imageSource);
  }

  public DiagramDescription writeToFile(File outputFile) {
    FileOutputStream outputStream = outputStreamFor(outputFile);
    return writeToStream(outputStream);
  }

  public DiagramDescription writeToStream(OutputStream outputStream) {
    SourceStringReader reader = readerOf(imageSource);
    return writeImageToStream(reader, outputStream);
  }

  private SourceStringReader readerOf(ImageSource imageSource) {
    String sourceString = imageSource.toString();
    return new SourceStringReader(sourceString);
  }

  private DiagramDescription writeImageToStream(SourceStringReader reader, OutputStream outputStream) {
    try {
      DiagramDescription desc = reader.outputImage(outputStream);
      return desc;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private FileOutputStream outputStreamFor(File outputFile) {
    FileOutputStream fileOutputStream;
    try {
      fileOutputStream = new FileOutputStream(outputFile);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    return fileOutputStream;
  }
}
