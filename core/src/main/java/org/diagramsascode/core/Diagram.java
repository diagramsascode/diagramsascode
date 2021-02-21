package org.diagramsascode.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The model of a diagram, with its edges and nodes.
 * Create an instance of it by using a builder.
 * 
 * @author b_muth
 *
 */
public class Diagram{
  private final List<DiagramNode> nodes;
  private final List<DiagramEdge> edges;
  private final DiagramConstraints constraints;
  
  private Diagram(List<DiagramNode> nodes, List<DiagramEdge> edges, DiagramConstraints constraints) {
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
  public Collection<DiagramNode> getNodes() {
    return nodes;
  }

  /**
   * Returns the edges contained on this diagram.
   * 
   * @return the edges
   */
  public Collection<DiagramEdge> getEdges() {
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
    private List<DiagramNode> nodes;
    private List<DiagramEdge> edges;
    private DiagramConstraints constraints;
    
    private DiagramBuilder() {
    }
    
    /**
     * Specifies the nodes of this diagram.
     * 
     * @param nodes the nodes to be shown on the diagram
     * 
     * @return a builder to continue building the diagram
     */
    public NodeBuilder withNodes(DiagramNode... nodes) {
      List<DiagramNode> nodeList = Arrays.asList(nodes);
      return new NodeBuilder(nodeList);
    }
    
    public class NodeBuilder{
      private NodeBuilder(List<DiagramNode> nodeList) {
        DiagramBuilder.this.nodes = new ArrayList<>(nodeList);
      }
      
      /**
       * Specifies the edges of this diagram.
       * 
       * @param edges the edges to be shown on the diagram
       * 
       * @return a builder to continue building the diagram
       */
      public EdgeBuilder withEdges(DiagramEdge... edges) {
        List<DiagramEdge> edgeList = Arrays.asList(edges);
        return new EdgeBuilder(edgeList);
      }
      
      public class EdgeBuilder{
        private EdgeBuilder(List<DiagramEdge> edgeList) {
          DiagramBuilder.this.edges = new ArrayList<>(edgeList);
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
