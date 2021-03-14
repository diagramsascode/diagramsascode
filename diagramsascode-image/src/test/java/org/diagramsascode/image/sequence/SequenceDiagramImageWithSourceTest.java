package org.diagramsascode.image.sequence;

import java.io.File;
import java.io.IOException;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.image.Image;
import org.diagramsascode.image.ImageSource;
import org.diagramsascode.sequence.constraint.SequenceDiagramConstraints;
import org.diagramsascode.sequence.edge.Message;
import org.diagramsascode.sequence.node.Participant;
import org.junit.jupiter.api.Test;

class SequenceDiagramImageWithSourceTest {
  @Test
  void writesSequenceDiagramImageToFile() throws IOException {
    // Create the participants (that exchange messages)
    final Participant participant1  = new Participant("Client");
    final Participant participant2  = new Participant("Server");
    
    // Create the request and response message
    final Message message1 = new Message(participant1, participant2, "Request Message");
    final Message message2 = new Message(participant2, participant1, "Response Message");
    
    // Build the diagram
    Diagram diagram = Diagram.builder()
      .withNodes(participant1, participant2)
      .withEdges(message1, message2)
      .withConstraints(new SequenceDiagramConstraints())
      .build();
    
    // Create the source text for PlantUML. You can print it to read it, if you want to.
    ImageSource source = ImageSource.of(diagram, new SequenceDiagramToSource());
        
    // Create the image of the diagram and write it to a PNG file.
    Image image = Image.fromSource(source);
    File outputFile = File.createTempFile("sequence", ".png");
    image.writeToPngFile(outputFile);
    
    System.out.println("Sequence diagram written to: " + outputFile);
  }
}
