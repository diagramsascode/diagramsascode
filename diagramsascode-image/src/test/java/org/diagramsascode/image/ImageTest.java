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

class ImageTest { 
  @Test
  void writesActivityDiagramImageToFile() throws IOException {
    // Create the initial and final node (to define where the flow starts and ends)
    InitialNode initialNode = new InitialNode();
    FinalNode finalNode = new FinalNode();
    
    // Create the decision and merge node (to split the flow and merge it back together)
    DecisionNode decisionNode = new DecisionNode();
    MergeNode mergeNode = new MergeNode();
    
    // Create actions (for the flow steps)
    Action action1 = new Action("Action1");
    Action action2a = new Action("Action2a");
    Action action2b = new Action("Action2b");
    Action action3 = new Action("Action3");
    
    // Connect the nodes with control flow edges.
    // If they originate from a decision node, the third constructor parameter
    // specifies the decision's condition (e.g. "x < 100")
    ControlFlow edge1 = new ControlFlow(initialNode, action1);
    ControlFlow edge2 = new ControlFlow(action1, decisionNode);
    ControlFlow edge3_a = new ControlFlow(decisionNode, action2a, "x < 100");
    ControlFlow edge3_b = new ControlFlow(decisionNode, action2b, "x >= 100");
    ControlFlow edge4_a = new ControlFlow(action2a, mergeNode);
    ControlFlow edge4_b = new ControlFlow(action2b, mergeNode);
    ControlFlow edge5 = new ControlFlow(mergeNode, action3);
    ControlFlow edge6 = new ControlFlow(action3, finalNode);
    
    // Build the diagram
    Diagram diagram = Diagram.builder()
      .withNodes(initialNode, finalNode, decisionNode, mergeNode, action1, action2a, action2b, action3)
      .withEdges(edge1, edge2, edge3_a, edge3_b, edge4_a, edge4_b, edge5, edge6)
      .withConstraints(new ActivityDiagramConstraints())
      .build();
    
    // Create the source text for PlantUML. You can print it to read it, if you want to.
    ImageSource source = ImageSource.ofActivityDiagram(diagram);
    
    // Create the image of the diagram and write it to a PNG file.
    Image image = Image.fromSource(source);
    File outputFile = File.createTempFile("activity", ".png");
    image.writeToPngFile(outputFile);
    
    System.out.println("Activity diagram written to: " + outputFile);
    assertTrue(outputFile.exists());
  }
}
