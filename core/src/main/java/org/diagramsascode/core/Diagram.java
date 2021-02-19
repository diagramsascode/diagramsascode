package org.diagramsascode.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Diagram{
  private final List<DiagramNode> nodes;
  private final List<DiagramEdge> edges;
  private final DiagramConstraints constraints;
  
  private Diagram(List<DiagramNode> nodes, List<DiagramEdge> edges, DiagramConstraints constraints) {
    this.nodes = Objects.requireNonNull(nodes, "nodes must be non-null");
    this.edges = Objects.requireNonNull(edges, "edges must be non-null");
    this.constraints = Objects.requireNonNull(constraints, "constraints must be non-null");    
  }

  public List<ConstraintViolation<? extends DiagramElement>> validate() {
    ConstraintValidator validator = ConstraintValidator.of(this);
    List<ConstraintViolation<? extends DiagramElement>> violations = validator.validate();
    return violations;
  }

  public static DiagramBuilder builder() {
    return new DiagramBuilder();    
  }

  public Collection<DiagramNode> getNodes() {
    return nodes;
  }

  public Collection<DiagramEdge> getEdges() {
    return edges;
  }
  
  public DiagramConstraints getConstraints() {
    return constraints;
  }

  public static class DiagramBuilder {
    private List<DiagramNode> nodes;
    private List<DiagramEdge> edges;
    private DiagramConstraints constraints;
    
    private DiagramBuilder() {
    }
    
    public NodeBuilder withNodes(DiagramNode... nodes) {
      List<DiagramNode> nodeList = Arrays.asList(nodes);
      return new NodeBuilder(nodeList);
    }
    
    public class NodeBuilder{
      private NodeBuilder(List<DiagramNode> nodeList) {
        DiagramBuilder.this.nodes = new ArrayList<>(nodeList);
      }
      
      public EdgeBuilder withEdges(DiagramEdge... edges) {
        List<DiagramEdge> edgeList = Arrays.asList(edges);
        return new EdgeBuilder(edgeList);
      }
      
      public class EdgeBuilder{
        private EdgeBuilder(List<DiagramEdge> edgeList) {
          DiagramBuilder.this.edges = new ArrayList<>(edgeList);
        }

        public ConstraintsBuilder withConstraints(DiagramConstraints constraints) {
          return new ConstraintsBuilder(constraints);
        }
        
        public Diagram build() {
          return withConstraints(new EmptyConstraints()).build();
        }
        
        public class ConstraintsBuilder{
          private ConstraintsBuilder(DiagramConstraints constraints) {
            DiagramBuilder.this.constraints = constraints;
          }
          
          public Diagram build() {
            return new Diagram(nodes, edges, constraints);    
          }
        }
      }
    }
  }

  public List<DiagramEdge> getIncomingEdgesOf(DiagramNode node) {
    Objects.requireNonNull(node, "node must be non-null");
    List<DiagramEdge> incomingEdges = getEdges().stream()
      .filter(edge -> edge.getTo().equals(node))
      .collect(Collectors.toList());
    return incomingEdges;
  }

  public List<DiagramEdge> getOutgoingEdgesOf(DiagramNode node) {
    Objects.requireNonNull(node, "node must be non-null");
    List<DiagramEdge> outgoingEdges = getEdges().stream()
        .filter(edge -> edge.getFrom().equals(node))
        .collect(Collectors.toList());
      return outgoingEdges;
  }
}
