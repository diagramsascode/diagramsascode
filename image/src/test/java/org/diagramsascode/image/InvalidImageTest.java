package org.diagramsascode.image;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.diagramsascode.activity.constraint.ActivityDiagramConstraints;
import org.diagramsascode.activity.edge.ControlFlow;
import org.diagramsascode.activity.node.Action;
import org.diagramsascode.activity.node.FinalNode;
import org.diagramsascode.activity.node.InitialNode;
import org.diagramsascode.core.Diagram;
import org.junit.jupiter.api.Test;

class InvalidImageTest {
  @Test
  void tryingToWriteInvalidDiagramImageFailsWithException() {
    // Create the initial and final node (valid)
    final InitialNode initialNode = new InitialNode();
    final FinalNode finalNode = new FinalNode();
    
    // Create an action (valid)
    final Action action = new Action("Action");
    
    // Connect the initial node to the action, 
    // and the action to the final node (valid)
    final ControlFlow edge1 = new ControlFlow(initialNode, action);
    final ControlFlow edge2 = new ControlFlow(action, finalNode);
    
    // Connect the final node to the action (NOT VALID,
    // since the final node mustn't have outgoing edges!)
    final ControlFlow invalidEdge = new ControlFlow(finalNode, action);
    
    // Build the diagram. This will still work. 
    // If we wanted to validate it, we'd call the validate() method on the diagram instance.
    // But we'll leave validation to the next step, see below.
    Diagram invalidDiagram = Diagram.builder()
      .withNodes(initialNode, finalNode, action)
      .withEdges(edge1, edge2, invalidEdge)
      .withConstraints(new ActivityDiagramConstraints())
      .build();
    
    // Create the source text for PlantUML. This fails with an exception,
    // because the diagram is invalid
    assertThrows(ConstraintViolationException.class, () ->ImageSource.ofActivityDiagram(invalidDiagram));
  }
}
