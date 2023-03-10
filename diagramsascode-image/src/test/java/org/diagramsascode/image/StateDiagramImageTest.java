package org.diagramsascode.image;

import java.io.File;
import java.io.IOException;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.state.constraint.StateDiagramConstraints;
import org.diagramsascode.state.edge.Transition;
import org.diagramsascode.state.node.State;
import org.junit.jupiter.api.Test;

class StateDiagramImageTest {
  @Test
  void writesStateDiagramImageToFile() throws IOException {
    // Create the nodes
    var node0  = new State("Node 0");
    var node1  = new State("Node 1");
    
    // Create the edges
    var edge0 = new Transition(node0, node1, "Forward");
    var edge1 = new Transition(node1, node0, "Backward");
    
    // Build the diagram
    var diagram = Diagram.builder()
      .withNodes(node0, node1)
      .withEdges(edge0, edge1)
      .withConstraints(new StateDiagramConstraints())
      .build();
    
    // Create the image of the diagram and write it to a PNG file.
    var outputFile = File.createTempFile("state", ".png");
    StateDiagramImage.of(diagram).writeToPngFile(outputFile);
    
    System.out.println("State diagram written to: " + outputFile);
  }
}
