package org.diagramsascode.image.state;

import static org.diagramsascode.image.state.StateDiagramToSource.DIAGRAM_FOOTER;
import static org.diagramsascode.image.state.StateDiagramToSource.DIAGRAM_HEADER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.image.ImageSource;
import org.diagramsascode.state.edge.Transition;
import org.diagramsascode.state.node.InitialState;
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
    
    String n0 = node0.getId();
	String n1 = node1.getId();
	
	String expectedSource = 
      DIAGRAM_HEADER + 
      "state \"" + node0.getText() + "\" as " + n0 + "\n" +
      "state \"" + node1.getText() + "\" as " + n1 + "\n" +
      n0 + " --> " + n1 + " : " + edge.getText() + "\n" +
      DIAGRAM_FOOTER;
    
    String actualSource = 
        ImageSource.of(diagram, new StateDiagramToSource()).toString();
    
    assertEquals(expectedSource, actualSource);
  }
  
  @Test
  void convertsDiagramWithMutipleEdgesToText() {
	final InitialState initial = new InitialState();
    final State node0  = new State("State0");
    final State node1  = new State("State1");
    final Transition edge0 = new Transition(initial, node0, "");
    final Transition edge1 = new Transition(node0, node1, "edge1");
    final Transition edge2 = new Transition(node1, node0, "edge2");
    
    Diagram diagram = Diagram.builder()
      .withNodes(initial, node0, node1)
      .withEdges(edge0, edge1, edge2)
      .build();
    
    String n0 = node0.getId();
	String n1 = node1.getId();
    
    String expectedSource = 
      DIAGRAM_HEADER + 
      "state \"" + node0.getText() + "\" as " + n0 + "\n" +
      "state \"" + node1.getText() + "\" as " + n1 + "\n" +
      "[*] --> " + n0 + "\n" +
      n0 + " --> " + n1 +" : " + edge1.getText() + "\n" +
      n1 + " --> " + n0 + " : " + edge2.getText() + "\n" +
      DIAGRAM_FOOTER;
    
    String actualSource = 
        ImageSource.of(diagram, new StateDiagramToSource()).toString();
    
    assertEquals(expectedSource, actualSource);    
  }
  
  @Test
  void convertsDiagramWithMutipleEdgesToText_withCustomIdentifiers() {
	final InitialState initial = new InitialState();
    final State node0  = new State("n0", "State0");
    final State node1  = new State("n1", "State1");
    final Transition edge0 = new Transition("e0", initial, node0, "");
    final Transition edge1 = new Transition("e1", node0, node1, "edge1");
    final Transition edge2 = new Transition("e2", node1, node0, "edge2");
    
    Diagram diagram = Diagram.builder()
      .withNodes(initial, node0, node1)
      .withEdges(edge0, edge1, edge2)
      .build();
    
    String expectedSource = 
      DIAGRAM_HEADER + 
      "state \"" + node0.getText() + "\" as n0\n" +
      "state \"" + node1.getText() + "\" as n1\n" +
      "[*] --> n0\n" +
      "n0 --> n1 : edge1\n" +
      "n1 --> n0 : edge2\n" +
      DIAGRAM_FOOTER;
    
    String actualSource = 
        ImageSource.of(diagram, new StateDiagramToSource()).toString();
    
    assertEquals(expectedSource, actualSource);    
  }
}
