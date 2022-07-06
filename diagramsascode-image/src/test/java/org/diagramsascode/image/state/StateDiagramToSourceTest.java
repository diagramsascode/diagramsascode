package org.diagramsascode.image.state;

import static org.diagramsascode.image.state.StateDiagramToSource.DIAGRAM_FOOTER;
import static org.diagramsascode.image.state.StateDiagramToSource.DIAGRAM_HEADER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.image.ImageSource;
import org.diagramsascode.state.edge.Transition;
import org.diagramsascode.state.node.State;
import org.junit.jupiter.api.Test;

class StateDiagramToSourceTest {
  @Test
  void convertsDiagramWithoutNodesAndEdgesToCode() {
    Diagram diagram = Diagram.builder()
      .withNodes()
      .withEdges()
      .build();

    String expectedSource = DIAGRAM_HEADER + DIAGRAM_FOOTER;
    String actualSource = 
      ImageSource.of(diagram, new StateDiagramToSource()).toString();
    
    assertEquals(expectedSource, actualSource);
  }
  
  @Test
  void convertsDiagramWithOneEdgeToText() {
    final State node0  = new State("Node0");
    final State node1  = new State("Node1");
    final Transition edge = new Transition(node0, node1, "edge");
    
    Diagram diagram = Diagram.builder()
      .withNodes(node0, node1)
      .withEdges(edge)
      .build();
    
    String expectedSource = 
      DIAGRAM_HEADER + 
      "state \"" + node0.getText() + "\" as S0\n" +
      "state \"" + node1.getText() + "\" as S1\n" +
      "S0 --> S1 : " + edge.getText() + "\n" +
      DIAGRAM_FOOTER;
    
    String actualSource = 
        ImageSource.of(diagram, new StateDiagramToSource()).toString();
    
    assertEquals(expectedSource, actualSource);
  }
  
  @Test
  void convertsDiagramWithTwoEdgesToText() {
    final State node0  = new State("State0");
    final State node1  = new State("State1");
    final Transition edge0 = new Transition(node0, node1, "edge0");
    final Transition edge1 = new Transition(node1, node0, "edge1");
    
    Diagram diagram = Diagram.builder()
      .withNodes(node0, node1)
      .withEdges(edge0, edge1)
      .build();
    
    String expectedSource = 
      DIAGRAM_HEADER + 
      "state \"" + node0.getText() + "\" as S0\n" +
      "state \"" + node1.getText() + "\" as S1\n" +
      "S0 --> S1 : " + edge0.getText() + "\n" +
      "S1 --> S0 : " + edge1.getText() + "\n" +
      DIAGRAM_FOOTER;
    
    String actualSource = 
        ImageSource.of(diagram, new StateDiagramToSource()).toString();
    
    assertEquals(expectedSource, actualSource);
    
    System.out.println(actualSource);
  }
}
