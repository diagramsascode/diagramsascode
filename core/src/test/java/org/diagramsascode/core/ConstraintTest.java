package org.diagramsascode.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class ConstraintTest {
  @Test
  void implicitCoreConstraint_nodesOfEdgeAreOnDiagram_fromNodeViolation() {
    TestNode offDiagramNode = new TestNode("OffDiagram");
    TestNode onDiagramNode = new TestNode("OnDiagram");
    TestEdge edge = new TestEdge(offDiagramNode, onDiagramNode, "InvalidEdge");

    Diagram diagram = Diagram.builder()
      .withNodes(onDiagramNode)
      .withEdges(edge)
      .build();
    List<ConstraintViolation<?>> violations = diagram.validate();

    ConstraintViolation<?> violation = violations.get(0);
    
    assertEquals(NodesOfEdgeAreOnDiagram.class, violation.getConstraint().getClass());
    assertEquals(edge, violation.getDiagramElement());
    
    String message = violation.getMessage();
    assertTrue(message.contains(edge.getText()));
    assertTrue(message.contains(offDiagramNode.getText()));
  }
  
  @Test
  void implicitCoreConstraint_nodesOfEdgeAreOnDiagram_toNodeViolation() {
    TestNode onDiagramNode = new TestNode("OnDiagram");
    TestNode offDiagramNode = new TestNode("OffDiagram");
    TestEdge edge = new TestEdge(onDiagramNode, offDiagramNode);

    Diagram diagram = Diagram.builder()
      .withNodes(onDiagramNode)
      .withEdges(edge)
      .build();
    List<ConstraintViolation<?>> violations = diagram.validate();

    ConstraintViolation<?> violation = violations.get(0);
    
    assertEquals(NodesOfEdgeAreOnDiagram.class, violation.getConstraint().getClass());
    assertEquals(edge, violation.getDiagramElement());
    
    String message = violation.getMessage();
    assertTrue(message.contains(edge.getText()));
    assertTrue(message.contains(offDiagramNode.getText()));
  }
  
  private static class TestNode extends DiagramNode{
    public TestNode(String text) {
      super(text);
    }
  }
  
  private static class TestEdge extends DiagramEdge {
    public TestEdge(DiagramNode from, DiagramNode to) {
      this(from, to, "");
    }

    public TestEdge(DiagramNode from, DiagramNode to, String text) {
      super(from, to, text);
    }
  }
}
