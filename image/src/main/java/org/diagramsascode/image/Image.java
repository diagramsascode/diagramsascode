package org.diagramsascode.image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.core.DiagramDescription;

/**
 * Represents an diagram image to be written to a file or a stream.
 * 
 * Note that the binary contents of the image are created lazily, not
 * until the diagram is really written.
 * 
 * @author b_muth
 *
 */
public class Image {
  private final ImageSource imageSource;

  private Image(ImageSource imageSource) {
    this.imageSource = Objects.requireNonNull(imageSource, "imageSource must be non-null");
  }
  
  /**
   * Creates an image from an image source (i.e. a textual representation of a diagram).
   * 
   * @param imageSource the image source.
   */
  public static Image fromSource(ImageSource imageSource) {
    return new Image(imageSource);
  }
  
  /**
   * Writes a PNG image file to the specified location.
   * 
   * @param outputFile the file to write to
   */
  public void writeToPngFile(File outputFile) {
    FileOutputStream outputStream = outputStreamFor(outputFile);
    writeToPngStream(outputStream);
  }

  /**
   * Writes a PNG image file to the specified stream.
   * 
   * @param outputStream the stream to write to
   */
  public void writeToPngStream(OutputStream outputStream) {
    SourceStringReader reader = readerOf(imageSource);
    writeImageToStream(reader, outputStream);
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
