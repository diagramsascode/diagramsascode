package org.diagramsascode.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramConstraints;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.state.constraint.StateDiagramConstraints;
import org.diagramsascode.state.edge.Transition;
import org.diagramsascode.state.node.State;
import org.junit.jupiter.api.Test;

class DiagramTest {
  @Test
  void createsEmptyDiagram() {
    Diagram diagram = Diagram.builder()
      .withNodes()
      .withEdges()
      .withConstraints(new StateDiagramConstraints())
      .build();

    Collection<DiagramNode> nodes = diagram.getNodes();
    assertTrue(nodes.isEmpty());

    Collection<DiagramEdge> edges = diagram.getEdges();
    assertTrue(edges.isEmpty());
  }
  
  @Test
  void buildsDiagramWithSingleNodeAndEdge() {
    final State node = new State("Node");
    final Transition edge = new Transition(node, node, "text");
    
    Diagram diagram = Diagram.builder()
      .withNodes(node)
      .withEdges(edge)
      .withConstraints(new StateDiagramConstraints())
      .build();

    Collection<DiagramNode> actualNodes = diagram.getNodes();
    assertEquals(1, actualNodes.size());
    DiagramNode actualNode = actualNodes.iterator().next();
    assertEquals(node.getText(), actualNode.getText());
 
    Collection<DiagramEdge> actualEdges = diagram.getEdges();
    assertEquals(1, actualEdges.size());
    DiagramEdge actualEdge = actualEdges.iterator().next();
    assertEquals(edge.getText(), actualEdge.getText());
    assertEquals(edge.getFrom(), actualEdge.getFrom());
    assertEquals(edge.getTo(), actualEdge.getTo());
  }
  
  @Test
  void buildsDiagramWithMultipleNodesAndEdges() {
    final State node1 = new State("Node1");
    final State node2 = new State("Node2");
    
    final Transition edge1 = new Transition(node1, node2, "text1");
    final Transition edge2 = new Transition(node2, node1, "text2");
    
    DiagramConstraints constraints = new StateDiagramConstraints();
    
    Diagram diagram = Diagram.builder()
      .withNodes(node1, node2)
      .withEdges(edge1, edge2)
      .withConstraints(constraints)
      .build();

    Collection<DiagramNode> actualNodes = diagram.getNodes();
    assertEquals(2, actualNodes.size());
    Collection<DiagramNode> expectedNodes = Arrays.asList(node1, node2);
    assertTrue(actualNodes.containsAll(expectedNodes));
 
    Collection<DiagramEdge> actualEdges = diagram.getEdges();
    assertEquals(2, actualEdges.size());
    Collection<DiagramEdge> expectedEdges = Arrays.asList(edge1, edge2);
    assertTrue(actualEdges.containsAll(expectedEdges));
    
    assertEquals(constraints, diagram.getConstraints());
  }
}
