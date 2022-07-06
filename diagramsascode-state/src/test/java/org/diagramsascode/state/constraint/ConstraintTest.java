package org.diagramsascode.state.constraint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.state.node.State;
import org.junit.jupiter.api.Test;

class ConstraintTest {
  @Test
  void diagramContainsOnlyDiagramSpecificNodes() {
    TestNode foreignNode = new TestNode("ForeignNode");

    Diagram diagram = Diagram.builder()
      .withNodes(foreignNode)
      .withEdges()
      .withConstraints(new StateDiagramConstraints())
      .build();
    List<ConstraintViolation<?>> violations = diagram.validate();

    ConstraintViolation<?> violation = violations.get(0);
    
    assertEquals(OnlyStateDiagramNodesOnDiagram.class, violation.getConstraint().getClass());
    assertEquals(foreignNode, violation.getDiagramElement());
    
    String message = violation.getMessage();
    assertTrue(message.contains(foreignNode.getText()));
  }
  
  @Test
  void diagramContainsOnlyDiagramSpecificEdges() {
    State fromNode = new State("From");
    State toNode = new State("To");
    TestEdge foreignEdge = new TestEdge(fromNode, toNode, "ForeignEdge");

    Diagram diagram = Diagram.builder()
      .withNodes(fromNode, toNode)
      .withEdges(foreignEdge)
      .withConstraints(new StateDiagramConstraints())
      .build();
    List<ConstraintViolation<?>> violations = diagram.validate();

    ConstraintViolation<?> violation = violations.get(0);
    
    assertEquals(OnlyStateDiagramEdgesOnDiagram.class, violation.getConstraint().getClass());
    assertEquals(foreignEdge, violation.getDiagramElement());
    
    String message = violation.getMessage();
    assertTrue(message.contains(foreignEdge.getText()));
  }
  
  @Test
  void nodeHasName() {
    State node = new State("");

    Diagram diagram = Diagram.builder()
      .withNodes(node)
      .withEdges()
      .withConstraints(new StateDiagramConstraints())
      .build();
    List<ConstraintViolation<?>> violations = diagram.validate();

    ConstraintViolation<?> violation = violations.get(0);
    
    assertEquals(StateHasName.class, violation.getConstraint().getClass());
    assertEquals(node, violation.getDiagramElement());
    
    String message = violation.getMessage();
    assertTrue(message.contains(node.getId()));
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
