package org.diagramsascode.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The model of a diagram, with its edges and nodes.
 * Create an instance of it by using a builder.
 * 
 * @author b_muth
 *
 */
public class Diagram{
  private final Set<DiagramNode> nodes;
  private final Set<DiagramEdge> edges;
  private final DiagramConstraints constraints;
  
  private Diagram(Set<DiagramNode> nodes, Set<DiagramEdge> edges, DiagramConstraints constraints) {
    this.nodes = Objects.requireNonNull(nodes, "nodes must be non-null");
    this.edges = Objects.requireNonNull(edges, "edges must be non-null");
    this.constraints = Objects.requireNonNull(constraints, "constraints must be non-null");    
  }

  /**
   * Call this method to validate the diagram nodes and edges,
   * to check if they contain correct information according to 
   * the constraints.
   * 
   * @return the constraint violations, or an empty list if none occured.
   */
  public List<ConstraintViolation<? extends DiagramElement>> validate() {
    ConstraintValidator validator = ConstraintValidator.of(this);
    List<ConstraintViolation<? extends DiagramElement>> violations = validator.validate();
    return violations;
  }

  /**
   * Use this builder to create a diagram instance.
   * 
   * @return the diagram builder
   */
  public static DiagramBuilder builder() {
    return new DiagramBuilder();    
  }

  /**
   * Returns the nodes contained on this diagram.
   * 
   * @return the nodes
   */
  public Set<DiagramNode> getNodes() {
    return nodes;
  }

  /**
   * Returns the edges contained on this diagram.
   * 
   * @return the edges
   */
  public Set<DiagramEdge> getEdges() {
    return edges;
  }
  
  /**
   * Returns the constraints of the diagram.
   * They define which diagram elements are valid,
   * and which aren't.
   * 
   * @return the constraints
   */
  public DiagramConstraints getConstraints() {
    return constraints;
  }
  
  /**
   * Returns the incoming edges of the specified node.
   * 
   * @param node the node queried for incoming edges
   * 
   * @return list of incoming edges
   */
  public List<DiagramEdge> getIncomingEdgesOf(DiagramNode node) {
    Objects.requireNonNull(node, "node must be non-null");
    List<DiagramEdge> incomingEdges = getEdges().stream()
      .filter(edge -> edge.getTo().equals(node))
      .collect(Collectors.toList());
    return incomingEdges;
  }

  /**
   * Returns the outgoing edges of the specified node.
   * 
   * @param node the node queried for outgoing edges
   * 
   * @return list of outgoing edges
   */
  public List<DiagramEdge> getOutgoingEdgesOf(DiagramNode node) {
    Objects.requireNonNull(node, "node must be non-null");
    List<DiagramEdge> outgoingEdges = getEdges().stream()
        .filter(edge -> edge.getFrom().equals(node))
        .collect(Collectors.toList());
      return outgoingEdges;
  }

  public static class DiagramBuilder {
    private Set<DiagramNode> nodes;
    private Set<DiagramEdge> edges;
    private DiagramConstraints constraints;
    
    private DiagramBuilder() {
    }
    
    /**
     * Specifies the nodes of this diagram, comma separated (varargs).
     * Duplicates nodes will be ignored.
     * 
     * @param nodes the nodes to be shown on the diagram
     * 
     * @return a builder to continue building the diagram
     */
    public NodeBuilder withNodes(DiagramNode... nodes) {
      return withNodes(Arrays.asList(nodes));
    }
    
    /**
     * Specifies the nodes of this diagram, as a list.
     * Duplicates nodes will be ignored.
     * 
     * @param nodes the nodes to be shown on the diagram
     * 
     * @return a builder to continue building the diagram
     */
    public NodeBuilder withNodes(List<DiagramNode> nodes) {
      HashSet<DiagramNode> nodeSet = new LinkedHashSet<>(nodes);
      return new NodeBuilder(nodeSet);
    }
    
    public class NodeBuilder{
      private NodeBuilder(Set<DiagramNode> nodes) {
        DiagramBuilder.this.nodes = nodes;
      }
      
      /**
       * Specifies the edges of this diagram, comma separated (varargs).
       * Duplicates will be ignored.
       * 
       * @param edges the edges to be shown on the diagram
       * 
       * @return a builder to continue building the diagram
       */
      public EdgeBuilder withEdges(DiagramEdge... edges) {
        return withEdges(Arrays.asList(edges));
      }
      
      /**
       * Specifies the edges of this diagram, as as list.
       * Duplicate edges will be ignored.
       * 
       * @param edges the edges to be shown on the diagram
       * 
       * @return a builder to continue building the diagram
       */
      public EdgeBuilder withEdges(List<DiagramEdge> edges) {
        Set<DiagramEdge> edgeSet = new LinkedHashSet<>(edges);
        return new EdgeBuilder(edgeSet);
      }
      
      public class EdgeBuilder{
        private EdgeBuilder(Set<DiagramEdge> edges) {
          DiagramBuilder.this.edges = edges;
        }

        /**
         * Specifies the constraints of this diagram.
         * 
         * They define which diagram elements are valid, and which aren't.
         * 
         * @param constraints the constraints
         * @return builder to continue building the diagram
         */
        public ConstraintsBuilder withConstraints(DiagramConstraints constraints) {
          return new ConstraintsBuilder(constraints);
        }
        
        /**
         * Finish building the diagram without constraints.
         * 
         * @return the diagram you've built
         */
        public Diagram build() {
          return withConstraints(new EmptyConstraints()).build();
        }
        
        public class ConstraintsBuilder{
          private ConstraintsBuilder(DiagramConstraints constraints) {
            DiagramBuilder.this.constraints = constraints;
          }
          
          /**
           * Finish building the diagram,
           * 
           * @return the diagram you've built
           */
          public Diagram build() {
            return new Diagram(nodes, edges, constraints);    
          }
        }
      }
    }
  }
}
