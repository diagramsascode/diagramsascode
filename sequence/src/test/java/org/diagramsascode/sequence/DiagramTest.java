package org.diagramsascode.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramConstraints;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.sequence.constraint.SequenceDiagramConstraints;
import org.diagramsascode.sequence.edge.Message;
import org.diagramsascode.sequence.node.Participant;
import org.junit.jupiter.api.Test;

class DiagramTest {
  @Test
  void createsEmptyDiagram() {
    Diagram diagram = Diagram.builder()
      .withNodes()
      .withEdges()
      .withConstraints(new SequenceDiagramConstraints())
      .build();

    Collection<DiagramNode> nodes = diagram.getNodes();
    assertTrue(nodes.isEmpty());

    Collection<DiagramEdge> edges = diagram.getEdges();
    assertTrue(edges.isEmpty());
  }
  
  @Test
  void buildsDiagramWithSingleNodeAndEdge() {
    final Participant participant = new Participant("Participant");
    final Message controlFlow = new Message(participant, participant, "text");
    
    Diagram diagram = Diagram.builder()
      .withNodes(participant)
      .withEdges(controlFlow)
      .withConstraints(new SequenceDiagramConstraints())
      .build();

    Collection<DiagramNode> actualNodes = diagram.getNodes();
    assertEquals(1, actualNodes.size());
    DiagramNode actualNode = actualNodes.iterator().next();
    assertEquals(participant.getText(), actualNode.getText());
 
    Collection<DiagramEdge> actualEdges = diagram.getEdges();
    assertEquals(1, actualEdges.size());
    DiagramEdge actualEdge = actualEdges.iterator().next();
    assertEquals(controlFlow.getText(), actualEdge.getText());
    assertEquals(controlFlow.getFrom(), actualEdge.getFrom());
    assertEquals(controlFlow.getTo(), actualEdge.getTo());
  }
  
  @Test
  void buildsDiagramWithMultipleNodesAndEdges() {
    final Participant participant1 = new Participant("Participant1");
    final Participant participant2 = new Participant("Participant2");
    
    final Message edge1 = new Message(participant1, participant2, "text1");
    final Message edge2 = new Message(participant2, participant1, "text2");
    
    DiagramConstraints constraints = new SequenceDiagramConstraints();
    
    Diagram diagram = Diagram.builder()
      .withNodes(participant1, participant2)
      .withEdges(edge1, edge2)
      .withConstraints(constraints)
      .build();

    Collection<DiagramNode> actualNodes = diagram.getNodes();
    assertEquals(2, actualNodes.size());
    Collection<DiagramNode> expectedNodes = Arrays.asList(participant1, participant2);
    assertTrue(actualNodes.containsAll(expectedNodes));
 
    Collection<DiagramEdge> actualEdges = diagram.getEdges();
    assertEquals(2, actualEdges.size());
    Collection<DiagramEdge> expectedEdges = Arrays.asList(edge1, edge2);
    assertTrue(actualEdges.containsAll(expectedEdges));
    
    assertEquals(constraints, diagram.getConstraints());
  }
}
