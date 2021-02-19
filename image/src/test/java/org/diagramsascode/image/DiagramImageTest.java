package org.diagramsascode.image;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.diagramsascode.activity.edge.ControlFlow;
import org.diagramsascode.activity.node.Action;
import org.diagramsascode.activity.node.DecisionNode;
import org.diagramsascode.activity.node.FinalNode;
import org.diagramsascode.activity.node.InitialNode;
import org.diagramsascode.activity.node.MergeNode;
import org.diagramsascode.core.Diagram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiagramImageTest {
  private File outputFile;

  @BeforeEach
  void setup() throws IOException {
    outputFile = File.createTempFile("activity", ".png");
  }

  @Test
  void writesDiagramImageToFile() {
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
        
    Diagram d = Diagram.builder()
      .withNodes(initialNode, action1, decision, merge, action2, finalNode)
      .withEdges(edge1, edge2, edge31, edge32, edge4, edge5)
      .build();
    
    ImageSource source = ImageSource.ofActivityDiagram(d);
    
    Image image = Image.fromSource(source);
    image.writeToFile(outputFile);
    assertTrue(outputFile.exists());
  }
}
