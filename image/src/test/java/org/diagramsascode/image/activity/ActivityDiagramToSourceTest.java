package org.diagramsascode.image.activity;

import static org.diagramsascode.image.activity.ActivityDiagramToSource.DIAGRAM_FOOTER;
import static org.diagramsascode.image.activity.ActivityDiagramToSource.DIAGRAM_HEADER;
import static org.diagramsascode.image.activity.ActivityDiagramToSource.STATE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.diagramsascode.activity.edge.ControlFlow;
import org.diagramsascode.activity.node.Action;
import org.diagramsascode.activity.node.DecisionNode;
import org.diagramsascode.activity.node.FinalNode;
import org.diagramsascode.activity.node.InitialNode;
import org.diagramsascode.activity.node.MergeNode;
import org.diagramsascode.core.Diagram;
import org.diagramsascode.image.ImageSource;
import org.junit.jupiter.api.Test;

class ActivityDiagramToSourceTest {
  @Test
  void convertsDiagramWithoutNodesAndEdgesToCode() {
    Diagram diagram = Diagram.builder()
      .withNodes()
      .withEdges()
      .build();

    String expectedSource = DIAGRAM_HEADER + DIAGRAM_FOOTER;
    String actualSource = 
      ImageSource.ofActivityDiagram(diagram).toString();
    
    assertEquals(expectedSource, actualSource);
  }
  
  @Test
  void convertsDiagramWithOneNodeToText() {
    final Action action = new Action("Action");
    
    Diagram diagram = Diagram.builder()
      .withNodes(action)
      .withEdges()
      .build();
    
    String expectedSource = 
      DIAGRAM_HEADER + 
      STATE + "\"Action\" as " + action.getId() + "\n" + 
      DIAGRAM_FOOTER;
    
    String actualSource = 
        ImageSource.ofActivityDiagram(diagram).toString();
    
    assertEquals(expectedSource, actualSource);
  }
  
  @Test
  void convertsDiagramWithOneEdgeToText() {
    final Action from  = new Action("From");
    final Action to  = new Action("To");
    final ControlFlow edge = new ControlFlow(from, to);
    
    Diagram diagram = Diagram.builder()
      .withNodes(from, to)
      .withEdges(edge)
      .build();
    
    String expectedSource = 
      DIAGRAM_HEADER + 
      STATE + "\"From\" as " + from.getId() + "\n" + 
      STATE + "\"To\" as " + to.getId() + "\n" + 
      from.getId() + " --> " + to.getId() + "\n" +
      DIAGRAM_FOOTER;
    
    String actualSource = 
        ImageSource.ofActivityDiagram(diagram).toString();
    
    assertEquals(expectedSource, actualSource);
  }
  
  @Test
  void convertsDiagramWithTwoEdgesToText() {
    final Action firstFrom  = new Action("First From");
    final Action firstTo  = new Action("First To");
    final Action secondFrom  = new Action("Second From");
    final Action secondTo  = new Action("Second To");
    final ControlFlow edge1 = new ControlFlow(firstFrom, firstTo);
    final ControlFlow edge2 = new ControlFlow(secondFrom, secondTo);
    
    Diagram diagram = Diagram.builder()
      .withNodes(firstFrom, firstTo, secondFrom, secondTo)
      .withEdges(edge1, edge2)
      .build();
    
    String expectedSource = 
      DIAGRAM_HEADER + 
      STATE + "\"First From\" as " + firstFrom.getId() + "\n" +
      STATE + "\"First To\" as " + firstTo.getId() + "\n" +
      STATE + "\"Second From\" as " + secondFrom.getId() + "\n" +
      STATE + "\"Second To\" as " +secondTo.getId() + "\n" +
      firstFrom.getId() + " --> " + firstTo.getId() + "\n" +
      secondFrom.getId() + " --> " + secondTo.getId() + "\n" +
      DIAGRAM_FOOTER;
    
    String actualSource = 
        ImageSource.ofActivityDiagram(diagram).toString();
    
    assertEquals(expectedSource, actualSource);
  }
  
  @Test
  void convertsDiagramWithInitialAndFinalNodeToText() {
    final InitialNode initialNode = new InitialNode();
    final FinalNode finalNode = new FinalNode();
    final ControlFlow edge = new ControlFlow(initialNode, finalNode);

    Diagram diagram = Diagram.builder()
      .withNodes(initialNode, finalNode)
      .withEdges(edge)
      .build();
      
    String expectedSource = 
      DIAGRAM_HEADER + 
      "[*] --> [*]\n" + 
      DIAGRAM_FOOTER;
    
    String actualSource = 
        ImageSource.ofActivityDiagram(diagram).toString();
    
    assertEquals(expectedSource, actualSource);
  }
  
  @Test
  void convertsDiagramWithDecisionAndMergeNodeToText() {
    final DecisionNode decision = new DecisionNode();
    final MergeNode merge = new MergeNode();
    final ControlFlow edge = new ControlFlow(decision, merge, "Condition");

    Diagram diagram = Diagram.builder()
      .withNodes(decision, merge)
      .withEdges(edge)
      .build();
      
    String expectedSource = 
      DIAGRAM_HEADER + 
      STATE + decision.getId() + " <<choice>>\n" +
      STATE + merge.getId() + " <<choice>>\n" +
      decision.getId() + " --> " + merge.getId() + " : [Condition]\n" +
      DIAGRAM_FOOTER;
    
    String actualSource = 
        ImageSource.ofActivityDiagram(diagram).toString();
    
    assertEquals(expectedSource, actualSource);
  }
  
  @Test
  void convertsFullDiagramToText() {
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
        
    Diagram diagram = Diagram.builder()
      .withNodes(initialNode, action1, decision, merge, action2, finalNode)
      .withEdges(edge1, edge2, edge31, edge32, edge4, edge5)
      .build();

    String expectedSource = 
        DIAGRAM_HEADER +
        STATE + "\"Action1\" as " + action1.getId() + "\n" +
        STATE + decision.getId() + " <<choice>>\n" +
        STATE + merge.getId() + " <<choice>>\n" +
        STATE + "\"Action2\" as " + action2.getId() + "\n" +
        "[*] --> " + action1.getId() + "\n" +
        action1.getId() + " --> " + decision.getId() + "\n" +
        decision.getId() + " --> " + merge.getId() + " : [Condition1]\n" +
        decision.getId() + " --> " + action2.getId() + " : [Condition2]\n" +
        action2.getId() + " --> " + merge.getId() + "\n" +
        merge.getId() + " --> [*]\n" +
        DIAGRAM_FOOTER;
      
      String actualSource = 
          ImageSource.ofActivityDiagram(diagram).toString();
      System.out.println(actualSource);
      
      assertEquals(expectedSource, actualSource);
  }
}
