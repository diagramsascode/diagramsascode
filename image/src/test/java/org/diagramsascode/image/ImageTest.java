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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ImageTest {
  private File outputFile;

  @BeforeEach
  void setup() throws IOException {
    outputFile = File.createTempFile("activity", ".png");
  }

  @Test
  void writesDiagramImageToFile() {
    // Create the initial and final node (to define where the flow starts and stop)
    final InitialNode initialNode = new InitialNode();
    final FinalNode finalNode = new FinalNode();
    
    // Create the decision and merge node (to split the flow and merge it back together)
    final DecisionNode decisionNode = new DecisionNode();
    final MergeNode mergeNode = new MergeNode();
    
    // Create actions (for the flow steps)
    final Action action1 = new Action("Action1");
    final Action action2a = new Action("Action2a");
    final Action action2b = new Action("Action2b");
    final Action action3 = new Action("Action3");
    
    // Connect the nodes with control flow edges.
    // If they originate from a decision node, the third constructor parameter
    // specifies the decision's condition (e.g. "x < 100")
    final ControlFlow edge1 = new ControlFlow(initialNode, action1);
    final ControlFlow edge2 = new ControlFlow(action1, decisionNode);
    final ControlFlow edge3_a = new ControlFlow(decisionNode, action2a, "x < 100");
    final ControlFlow edge3_b = new ControlFlow(decisionNode, action2b, "x >= 100");
    final ControlFlow edge4_a = new ControlFlow(action2a, mergeNode);
    final ControlFlow edge4_b = new ControlFlow(action2b, mergeNode);
    final ControlFlow edge5 = new ControlFlow(mergeNode, action3);
    final ControlFlow edge6 = new ControlFlow(action3, finalNode);
    
    // Build the diagram
    Diagram d = Diagram.builder()
      .withNodes(initialNode, finalNode, decisionNode, mergeNode, action1, action2a, action2b, action3)
      .withEdges(edge1, edge2, edge3_a, edge3_b, edge4_a, edge4_b, edge5, edge6)
      .withConstraints(new ActivityDiagramConstraints())
      .build();
    
    // Create the source text for PlantUML. You can print it to read it, if you want to.
    ImageSource source = ImageSource.ofActivityDiagram(d);
    
    // Create the image of the diagram and write it to a PNG file.
    Image image = Image.fromSource(source);
    image.writeToPngFile(outputFile);
    
    assertTrue(outputFile.exists());
    System.out.println("Diagram written to: " + outputFile);
  }
}
