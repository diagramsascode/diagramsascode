package org.diagramsascode.image.sequence;

import static org.diagramsascode.image.sequence.SequenceDiagramToSource.*;
import static org.diagramsascode.image.sequence.SequenceDiagramToSource.DIAGRAM_HEADER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.image.ImageSource;
import org.diagramsascode.sequence.edge.Message;
import org.diagramsascode.sequence.node.Participant;
import org.junit.jupiter.api.Test;

class SequenceDiagramToSourceTest {
  @Test
  void convertsDiagramWithoutNodesAndEdgesToCode() {
    Diagram diagram = Diagram.builder()
      .withNodes()
      .withEdges()
      .build();

    String expectedSource = DIAGRAM_HEADER + DIAGRAM_FOOTER;
    String actualSource = 
      ImageSource.of(diagram, new SequenceDiagramToSource()).toString();
    
    assertEquals(expectedSource, actualSource);
  }
  
  @Test
  void convertsDiagramWithOneEdgeToText() {
    final Participant from  = new Participant("From");
    final Participant to  = new Participant("To");
    final Message edge = new Message(from, to, "Message");
    
    Diagram diagram = Diagram.builder()
      .withNodes(from, to)
      .withEdges(edge)
      .build();
    
    String expectedSource = 
      DIAGRAM_HEADER + 
      "\"" + from.getText() + "\" ->> \"" + to.getText() + "\" : Message\n" +
      DIAGRAM_FOOTER;
    
    String actualSource = 
        ImageSource.of(diagram, new SequenceDiagramToSource()).toString();
    
    assertEquals(expectedSource, actualSource);
  }
  
  @Test
  void convertsDiagramWithTwoEdgesToText() {
    final Participant from  = new Participant("First Participant");
    final Participant to  = new Participant("Second Participant");
    final Message edge1 = new Message(from, to, "Request Message");
    final Message edge2 = new Message(to, from, "Response Message");
    
    Diagram diagram = Diagram.builder()
      .withNodes(from, to)
      .withEdges(edge1, edge2)
      .build();
    
    String expectedSource = 
      DIAGRAM_HEADER + 
      "\"" + from.getText() + "\" ->> \"" + to.getText() + "\" : Request Message\n" +
      "\"" + to.getText() + "\" ->> \"" + from.getText() +  "\" : Response Message\n" +
      DIAGRAM_FOOTER;
    
    String actualSource = 
        ImageSource.of(diagram, new SequenceDiagramToSource()).toString();
    
    assertEquals(expectedSource, actualSource);    
  }
}
