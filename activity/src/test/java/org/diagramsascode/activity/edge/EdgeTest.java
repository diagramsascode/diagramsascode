package org.diagramsascode.activity.edge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.diagramsascode.activity.constraint.ActivityConstraints;
import org.diagramsascode.activity.node.Action;
import org.diagramsascode.activity.node.DecisionNode;
import org.diagramsascode.activity.node.FinalNode;
import org.diagramsascode.activity.node.InitialNode;
import org.diagramsascode.activity.node.MergeNode;
import org.diagramsascode.core.Constraints;
import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdge;
import org.junit.jupiter.api.Test;

class EdgeTest {
  @Test
  void intialNodeHasNoIncomingAndNoOutgoingEdge() {
    InitialNode initialNode = new InitialNode();
    
    Diagram diagram = Diagram.builder()
      .withNodes(initialNode)
      .withEdges()
      .withConstraints(new ActivityConstraints())
      .build();

    assertEquals(0, diagram.getIncomingEdgesOf(initialNode).size());
    assertEquals(0, diagram.getOutgoingEdgesOf(initialNode).size());
  }

  @Test
  void actionWithSingleIncomingAndSingleOutgoingEdge() {
    final Action action = new Action("Action");
    final ControlFlow controlFlow = new ControlFlow(action, action);
    
    Diagram diagram = Diagram.builder()
        .withNodes(action)
        .withEdges(controlFlow)
        .withConstraints(new ActivityConstraints())
        .build();
    
    List<DiagramEdge> actionEdges = Arrays.asList(controlFlow);
    assertEquals(actionEdges, diagram.getIncomingEdgesOf(action));
    assertEquals(actionEdges, diagram.getOutgoingEdgesOf(action));
  }

  @Test
  void decisionNodeHasOneIncomingAndSeveralOutgoingEdges() {
    final InitialNode initialNode = new InitialNode();
    final Action action1 = new Action("Action1");
    final DecisionNode decision = new DecisionNode();
    final MergeNode merge = new MergeNode();
    final Action action2 = new Action("Action2");
    final FinalNode finalNode = new FinalNode();
    
    final ControlFlow edge1 = new ControlFlow(initialNode, action1);
    final ControlFlow edge2 = new ControlFlow(action1, decision);
    final ControlFlow edge31 = new ControlFlow(decision, merge, "Condition1");
    final ControlFlow edge32 = new ControlFlow(decision, action2, "Condition2");
    final ControlFlow edge4 = new ControlFlow(action2, merge);
    final ControlFlow edge5 = new ControlFlow(merge, finalNode);
    
    Constraints constraints = new ActivityConstraints();
    
    Diagram diagram = Diagram.builder()
      .withNodes(initialNode, action1, decision, merge, action2, finalNode)
      .withEdges(edge1, edge2, edge31, edge32, edge4, edge5)
      .withConstraints(constraints)
      .build();

    List<DiagramEdge> expectedIncomingEdges = Arrays.asList(edge2);
    assertEquals(expectedIncomingEdges, diagram.getIncomingEdgesOf(decision));
    
    List<DiagramEdge> expectedOutgoingEdges = Arrays.asList(edge31, edge32);
    assertEquals(expectedOutgoingEdges, diagram.getOutgoingEdgesOf(decision));
  }
}
