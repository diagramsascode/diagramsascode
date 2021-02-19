package org.diagramsascode.activity.constraint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.diagramsascode.activity.edge.ControlFlow;
import org.diagramsascode.activity.node.Action;
import org.diagramsascode.activity.node.DecisionNode;
import org.diagramsascode.activity.node.FinalNode;
import org.diagramsascode.activity.node.InitialNode;
import org.diagramsascode.activity.node.MergeNode;
import org.diagramsascode.core.ConstraintViolation;
import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;
import org.junit.jupiter.api.Test;

class ConstraintTest {
  @Test
  void diagramContainsOnlyActivityNodes() {
    TestNode foreignNode = new TestNode("ForeignNode");

    Diagram diagram = Diagram.builder()
      .withNodes(foreignNode)
      .withEdges()
      .withConstraints(new ActivityConstraints())
      .build();
    List<ConstraintViolation<?>> violations = diagram.validate();

    ConstraintViolation<?> violation = violations.get(0);
    
    assertEquals(OnlyActivityNodesOnDiagram.class, violation.getConstraint().getClass());
    assertEquals(foreignNode, violation.getDiagramElement());
    
    String message = violation.getMessage();
    assertTrue(message.contains(foreignNode.getText()));
  }
  
  @Test
  void diagramContainsOnlyActivityEdges() {
    Action fromNode = new Action("From");
    Action toNode = new Action("To");
    TestEdge foreignEdge = new TestEdge(fromNode, toNode, "ForeignEdge");

    Diagram diagram = Diagram.builder()
      .withNodes(fromNode, toNode)
      .withEdges(foreignEdge)
      .withConstraints(new ActivityConstraints())
      .build();
    List<ConstraintViolation<?>> violations = diagram.validate();

    ConstraintViolation<?> violation = violations.get(0);
    
    assertEquals(OnlyActivityEdgesOnDiagram.class, violation.getConstraint().getClass());
    assertEquals(foreignEdge, violation.getDiagramElement());
    
    String message = violation.getMessage();
    assertTrue(message.contains(foreignEdge.getText()));
  }
  
  @Test
  void actionHasName() {
    Action action = new Action("");

    Diagram diagram = Diagram.builder()
      .withNodes(action)
      .withEdges()
      .withConstraints(new ActivityConstraints())
      .build();
    List<ConstraintViolation<?>> violations = diagram.validate();

    ConstraintViolation<?> violation = violations.get(0);
    
    assertEquals(ActionHasName.class, violation.getConstraint().getClass());
    assertEquals(action, violation.getDiagramElement());
    
    String message = violation.getMessage();
    assertTrue(message.contains(action.getId()));
  }

  @Test
  void initialNodeHasNoIncomingEdges() {
    InitialNode initialNode = new InitialNode();
    Action action = new Action("Action");
    ControlFlow invalidEdge = new ControlFlow(action, initialNode);

    Diagram diagram = Diagram.builder()
      .withNodes(initialNode)
      .withEdges(invalidEdge)
      .withConstraints(new ActivityConstraints())
      .build();
    
    List<ConstraintViolation<?>> violations = diagram.validate();
    
    ConstraintViolation<?> violation = violations.get(0);
    
    assertEquals(InitialNodeHasNoIncomingEdges.class, violation.getConstraint().getClass());
    assertEquals(initialNode, violation.getDiagramElement());
  }
  
  @Test
  void finalNodeHasNoOutgoingEdges() {
    FinalNode finalNode = new FinalNode();
    Action action = new Action("Action");
    ControlFlow invalidEdge = new ControlFlow(finalNode, action);

    Diagram diagram = Diagram.builder()
      .withNodes(finalNode)
      .withEdges(invalidEdge)
      .withConstraints(new ActivityConstraints())
      .build();
    
    List<ConstraintViolation<?>> violations = diagram.validate();
    
    ConstraintViolation<?> violation = violations.get(0);
    
    assertEquals(FinalNodeHasNoOutgoingEdges.class, violation.getConstraint().getClass());
    assertEquals(finalNode, violation.getDiagramElement());
  }
  
  @Test
  void decisionNodeHasOneIncomingEdge() {
    InitialNode initialNode = new InitialNode();
    Action action = new Action("Action");
    DecisionNode decisionNodeWithNoIncomingEdge = new DecisionNode();
    FinalNode finalNode = new FinalNode();
    
    DecisionNode decisionNodeWithTwoIncomingEdges = new DecisionNode();
    ControlFlow edge1 = new ControlFlow(initialNode, action);
    ControlFlow edge2 = new ControlFlow(initialNode, decisionNodeWithTwoIncomingEdges);
    ControlFlow edge3 = new ControlFlow(action, decisionNodeWithTwoIncomingEdges);
    ControlFlow edge4 = new ControlFlow(decisionNodeWithTwoIncomingEdges, finalNode);
    ControlFlow edge5 = new ControlFlow(decisionNodeWithNoIncomingEdge, finalNode);

    Diagram diagram = Diagram.builder()
      .withNodes(initialNode, action, decisionNodeWithNoIncomingEdge, decisionNodeWithTwoIncomingEdges)
      .withEdges(edge1, edge2, edge3, edge4, edge5)
      .withConstraints(new ActivityConstraints())
      .build();
    
    List<ConstraintViolation<?>> violations = diagram.validate();

    ConstraintViolation<?> firstViolation = violations.get(0);
    
    assertEquals(DecisionNodeHasOneIncomingEdge.class, firstViolation.getConstraint().getClass());
    assertEquals(decisionNodeWithNoIncomingEdge, firstViolation.getDiagramElement());
    
    ConstraintViolation<?> secondViolation = violations.get(1);
    
    assertEquals(DecisionNodeHasOneIncomingEdge.class, secondViolation.getConstraint().getClass());
    assertEquals(decisionNodeWithTwoIncomingEdges, secondViolation.getDiagramElement());
  }
  
  @Test
  void decisionNodeHasAtLeastOneOutgoingEdge() {
    InitialNode initialNode = new InitialNode();
    Action action = new Action("Action");
    DecisionNode decisionNodeWithNoOutgoingEdge = new DecisionNode();
    
    ControlFlow edge1 = new ControlFlow(initialNode, action);
    ControlFlow edge2 = new ControlFlow(action, decisionNodeWithNoOutgoingEdge);

    Diagram diagram = Diagram.builder()
      .withNodes(initialNode, action, decisionNodeWithNoOutgoingEdge)
      .withEdges(edge1, edge2)
      .withConstraints(new ActivityConstraints())
      .build();
    
    List<ConstraintViolation<?>> violations = diagram.validate();

    ConstraintViolation<?> violation = violations.get(0);
    
    assertEquals(DecisionNodeHasAtLeastOneOutgoingEdge.class, violation.getConstraint().getClass());
    assertEquals(decisionNodeWithNoOutgoingEdge, violation.getDiagramElement());
  }
  
  @Test
  void mergeNodeHasAtLeastOneIncomingEdge() {
    Action action = new Action("Action");
    MergeNode mergeNodeWithNoIncomingEdge = new MergeNode();
    
    ControlFlow edge1 = new ControlFlow(mergeNodeWithNoIncomingEdge, action);

    Diagram diagram = Diagram.builder()
      .withNodes(action, mergeNodeWithNoIncomingEdge)
      .withEdges(edge1)
      .withConstraints(new ActivityConstraints())
      .build();
    
    List<ConstraintViolation<?>> violations = diagram.validate();

    ConstraintViolation<?> violation = violations.get(0);
    
    assertEquals(MergeNodeHasAtLeastOneIncomingEdge.class, violation.getConstraint().getClass());
    assertEquals(mergeNodeWithNoIncomingEdge, violation.getDiagramElement());
  }
  
  @Test
  void mergeNodeHasOneOutgoingEdge() {
    InitialNode initialNode = new InitialNode();
    Action action = new Action("Action");

    MergeNode mergeNodeWithNoOutgoingEdge = new MergeNode();
    ControlFlow edge1 = new ControlFlow(initialNode, mergeNodeWithNoOutgoingEdge);
    
    MergeNode mergeNodeWithTwoOutgoingEdges = new MergeNode();
    ControlFlow edge2 = new ControlFlow(initialNode, mergeNodeWithTwoOutgoingEdges);
    ControlFlow edge3 = new ControlFlow(mergeNodeWithTwoOutgoingEdges, action);
    ControlFlow edge4 = new ControlFlow(mergeNodeWithTwoOutgoingEdges, action);

    Diagram diagram = Diagram.builder()
      .withNodes(action, mergeNodeWithNoOutgoingEdge, mergeNodeWithTwoOutgoingEdges)
      .withEdges(edge1, edge2, edge3, edge4)
      .withConstraints(new ActivityConstraints())
      .build();
    
    List<ConstraintViolation<?>> violations = diagram.validate();

    ConstraintViolation<?> firstViolation = violations.get(0);
    
    assertEquals(MergeNodeHasOneOutgoingEdge.class, firstViolation.getConstraint().getClass());
    assertEquals(mergeNodeWithNoOutgoingEdge, firstViolation.getDiagramElement());
    
    ConstraintViolation<?> secondViolation = violations.get(1);
    
    assertEquals(MergeNodeHasOneOutgoingEdge.class, secondViolation.getConstraint().getClass());
    assertEquals(mergeNodeWithTwoOutgoingEdges, secondViolation.getDiagramElement());
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
