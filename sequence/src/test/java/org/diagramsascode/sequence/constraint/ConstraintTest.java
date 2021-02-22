package org.diagramsascode.sequence.constraint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.sequence.node.Participant;
import org.junit.jupiter.api.Test;

class ConstraintTest {
  @Test
  void diagramContainsOnlySequenceDiagramNodes() {
    TestNode foreignNode = new TestNode("ForeignNode");

    Diagram diagram = Diagram.builder()
      .withNodes(foreignNode)
      .withEdges()
      .withConstraints(new SequenceDiagramConstraints())
      .build();
    List<ConstraintViolation<?>> violations = diagram.validate();

    ConstraintViolation<?> violation = violations.get(0);
    
    assertEquals(OnlySequenceDiagramNodesOnDiagram.class, violation.getConstraint().getClass());
    assertEquals(foreignNode, violation.getDiagramElement());
    
    String message = violation.getMessage();
    assertTrue(message.contains(foreignNode.getText()));
  }
  
  @Test
  void diagramContainsOnlySequenceEdges() {
    Participant fromNode = new Participant("From");
    Participant toNode = new Participant("To");
    TestEdge foreignEdge = new TestEdge(fromNode, toNode, "ForeignEdge");

    Diagram diagram = Diagram.builder()
      .withNodes(fromNode, toNode)
      .withEdges(foreignEdge)
      .withConstraints(new SequenceDiagramConstraints())
      .build();
    List<ConstraintViolation<?>> violations = diagram.validate();

    ConstraintViolation<?> violation = violations.get(0);
    
    assertEquals(OnlySequenceDiagramEdgesOnDiagram.class, violation.getConstraint().getClass());
    assertEquals(foreignEdge, violation.getDiagramElement());
    
    String message = violation.getMessage();
    assertTrue(message.contains(foreignEdge.getText()));
  }
  
  @Test
  void participantHasName() {
    Participant action = new Participant("");

    Diagram diagram = Diagram.builder()
      .withNodes(action)
      .withEdges()
      .withConstraints(new SequenceDiagramConstraints())
      .build();
    List<ConstraintViolation<?>> violations = diagram.validate();

    ConstraintViolation<?> violation = violations.get(0);
    
    assertEquals(ParticipantHasName.class, violation.getConstraint().getClass());
    assertEquals(action, violation.getDiagramElement());
    
    String message = violation.getMessage();
    assertTrue(message.contains(action.getId()));
  }
  
  private static class TestNode extends DiagramNode{
    public TestNode(String text) {
      super(text);
    }
  }
  
  private static class TestEdge extends DiagramEdge{
    public TestEdge(DiagramNode from, DiagramNode to, String text) {
      super(from, to, text);
    }
  }
}
