package org.diagramsascode.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.diagramsascode.activity.constraint.ActivityConstraints;
import org.diagramsascode.activity.edge.ControlFlow;
import org.diagramsascode.activity.node.Action;
import org.diagramsascode.activity.node.DecisionNode;
import org.diagramsascode.activity.node.FinalNode;
import org.diagramsascode.activity.node.InitialNode;
import org.diagramsascode.activity.node.MergeNode;
import org.diagramsascode.core.Constraints;
import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;
import org.junit.jupiter.api.Test;

class DiagramTest {
  @Test
  void createsEmptyDiagram() {
    Diagram diagram = Diagram.builder()
      .withNodes()
      .withEdges()
      .withConstraints(new ActivityConstraints())
      .build();

    Collection<DiagramNode> nodes = diagram.getNodes();
    assertTrue(nodes.isEmpty());

    Collection<DiagramEdge> edges = diagram.getEdges();
    assertTrue(edges.isEmpty());
  }
  
  @Test
  void buildsDiagramWithSingleNodeAndEdge() {
    final Action action = new Action("Action");
    final ControlFlow controlFlow = new ControlFlow(action, action);
    
    Diagram diagram = Diagram.builder()
      .withNodes(action)
      .withEdges(controlFlow)
      .withConstraints(new ActivityConstraints())
      .build();

    Collection<DiagramNode> actualNodes = diagram.getNodes();
    DiagramNode actualNode = actualNodes.iterator().next();
    assertEquals(action.getText(), actualNode.getText());
 
    Collection<DiagramEdge> actualEdges = diagram.getEdges();
    DiagramEdge actualEdge = actualEdges.iterator().next();
    assertEquals(controlFlow.getText(), actualEdge.getText());
    assertEquals(controlFlow.getFrom(), actualEdge.getFrom());
    assertEquals(controlFlow.getTo(), actualEdge.getTo());
  }
  
  @Test
  void buildsDiagramWithMultipleNodesAndEdges() {
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

    Collection<DiagramNode> actualNodes = diagram.getNodes();
    Collection<DiagramNode> expectedNodes = Arrays.asList(initialNode, action1, decision, merge, action2, finalNode);
    assertTrue(actualNodes.containsAll(expectedNodes));
 
    Collection<DiagramEdge> actualEdges = diagram.getEdges();
    Collection<DiagramEdge> expectedEdges = Arrays.asList(edge1, edge1, edge2, edge31, edge32, edge4, edge5);
    assertTrue(actualEdges.containsAll(expectedEdges));
    
    assertEquals(constraints, diagram.getConstraints());
  }
}
