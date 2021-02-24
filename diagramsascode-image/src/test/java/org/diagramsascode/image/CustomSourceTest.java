package org.diagramsascode.image;

import static org.diagramsascode.image.testclasses.TestDiagramToSource.DIAGRAM_FOOTER;
import static org.diagramsascode.image.testclasses.TestDiagramToSource.DIAGRAM_HEADER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.image.testclasses.TestDiagramToSource;
import org.diagramsascode.image.testclasses.TestEdge;
import org.diagramsascode.image.testclasses.TestNode;
import org.junit.jupiter.api.Test;

class CustomSourceTest {
  @Test
  void convertsDiagramWithoutNodesAndEdgesToString() {
    Diagram diagram = Diagram.builder()
      .withNodes()
      .withEdges()
      .build();

    String expectedSource = DIAGRAM_HEADER + DIAGRAM_FOOTER;
    String actualSource = 
      ImageSource.of(diagram, new TestDiagramToSource()).toString();
    
    assertEquals(expectedSource, actualSource);
  }
  
  @Test
  void convertsDiagramWithOneNodeToString() {
    final TestNode node = new TestNode("NODE");
    
    Diagram diagram = Diagram.builder()
      .withNodes(node)
      .withEdges()
      .build();
    
    String expectedSource = 
      DIAGRAM_HEADER + 
      node.getText() + "\n" + 
      DIAGRAM_FOOTER;
    
    String actualSource = 
        ImageSource.of(diagram, new TestDiagramToSource()).toString();
    
    assertEquals(expectedSource, actualSource);
  }
  
  @Test
  void convertsDiagramWithOneEdgeToString() {
    final TestNode from  = new TestNode("From");
    final TestNode to  = new TestNode("To");
    final TestEdge testEdge = new TestEdge(from, to);
    
    Diagram diagram = Diagram.builder()
      .withNodes(from, to)
      .withEdges(testEdge)
      .build();
    
    String expectedSource = 
      DIAGRAM_HEADER + 
      from.getText() + "\n" + 
      to.getText() + "\n" +
      from.getText() + " -> " + to.getText() + "\n" +
      DIAGRAM_FOOTER;
    
    String actualSource = 
        ImageSource.of(diagram, new TestDiagramToSource()).toString();
    
    assertEquals(expectedSource, actualSource);
  }
  
  @Test
  void convertsDiagramWithTwoEdgesToString() {
    final TestNode from1  = new TestNode("From1");
    final TestNode to1  = new TestNode("To1");
    final TestNode from2  = new TestNode("From2");
    final TestNode to2  = new TestNode("To2");
    final TestEdge testEdge1 = new TestEdge(from1, to1);
    final TestEdge testEdge2 = new TestEdge(from2, to2);
    
    Diagram diagram = Diagram.builder()
      .withNodes(from1, to1, from2, to2)
      .withEdges(testEdge1, testEdge2)
      .build();
    
    String expectedSource = 
      DIAGRAM_HEADER + 
      from1.getText() + "\n" + 
      to1.getText() + "\n" +
      from2.getText() + "\n" + 
      to2.getText() + "\n" +
      from1.getText() + " -> " + to1.getText() + "\n" +
      from2.getText() + " -> " + to2.getText() + "\n" +
      DIAGRAM_FOOTER;
    
    String actualSource = 
        ImageSource.of(diagram, new TestDiagramToSource()).toString();
    
    assertEquals(expectedSource, actualSource);
  }
}
