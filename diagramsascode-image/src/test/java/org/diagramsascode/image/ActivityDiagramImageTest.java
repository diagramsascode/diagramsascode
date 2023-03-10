package org.diagramsascode.image;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.diagramsascode.activity.constraint.ActivityDiagramConstraints;
import org.diagramsascode.activity.edge.ControlFlow;
import org.diagramsascode.activity.node.Action;
import org.diagramsascode.activity.node.DecisionNode;
import org.diagramsascode.activity.node.FinalNode;
import org.diagramsascode.activity.node.InitialNode;
import org.diagramsascode.activity.node.MergeNode;
import org.diagramsascode.core.Diagram;
import org.junit.jupiter.api.Test;

class ActivityDiagramImageTest { 
  @Test
  void writesActivityDiagramImageToFile() throws IOException {
    // Create the initial and final node (to define where the flow starts and ends)
    var initialNode = new InitialNode();
    var finalNode = new FinalNode();
    
    // Create the decision and merge node (to split the flow and merge it back together)
    var decisionNode = new DecisionNode();
    var mergeNode = new MergeNode();
    
    // Create actions (for the flow steps)
    var action1 = new Action("Action1");
    var action2a = new Action("Action2a");
    var action2b = new Action("Action2b");
    var action3 = new Action("Action3");
    
    // Connect the nodes with control flow edges.
    // If they originate from a decision node, the third constructor parameter
    // specifies the decision's condition (e.g. "x < 100")
    var edge1 = new ControlFlow(initialNode, action1);
    var edge2 = new ControlFlow(action1, decisionNode);
    var edge3_a = new ControlFlow(decisionNode, action2a, "x < 100");
    var edge3_b = new ControlFlow(decisionNode, action2b, "x >= 100");
    var edge4_a = new ControlFlow(action2a, mergeNode);
    var edge4_b = new ControlFlow(action2b, mergeNode);
    var edge5 = new ControlFlow(mergeNode, action3);
    var edge6 = new ControlFlow(action3, finalNode);
    
    // Build the diagram
    var diagram = Diagram.builder()
      .withNodes(initialNode, finalNode, decisionNode, mergeNode, action1, action2a, action2b, action3)
      .withEdges(edge1, edge2, edge3_a, edge3_b, edge4_a, edge4_b, edge5, edge6)
      .withConstraints(new ActivityDiagramConstraints())
      .build();
    
    // Create the image of the diagram and write it to a PNG file.
    var outputFile = File.createTempFile("activity", ".png");
    ActivityDiagramImage.of(diagram).writeToPngFile(outputFile);
    
    System.out.println("Activity diagram written to: " + outputFile);
    assertTrue(outputFile.exists());
  }
}
