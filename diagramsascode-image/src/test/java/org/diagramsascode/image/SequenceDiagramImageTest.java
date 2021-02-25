package org.diagramsascode.image;

import java.io.File;
import java.io.IOException;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.sequence.constraint.SequenceDiagramConstraints;
import org.diagramsascode.sequence.edge.Message;
import org.diagramsascode.sequence.node.Participant;
import org.junit.jupiter.api.Test;

class SequenceDiagramImageTest {
  @Test
  void writesSequenceDiagramImageToFile() throws IOException {
    // Create the participants (that exchange messages)
    Participant participant1  = new Participant("Client");
    Participant participant2  = new Participant("Server");
    
    // Create the request and response message
    Message message1 = new Message(participant1, participant2, "Request Message");
    Message message2 = new Message(participant2, participant1, "Response Message");
    
    // Build the diagram
    Diagram diagram = Diagram.builder()
      .withNodes(participant1, participant2)
      .withEdges(message1, message2)
      .withConstraints(new SequenceDiagramConstraints())
      .build();
        
    // Create the image of the diagram and write it to a PNG file.
    File outputFile = File.createTempFile("sequence", ".png");
    SequenceDiagramImage.of(diagram).writeToPngFile(outputFile);;
    
    System.out.println("Sequence diagram written to: " + outputFile);
  }
}