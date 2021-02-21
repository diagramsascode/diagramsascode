package org.diagramsascode.core;

import java.util.Collection;

/** 
 * Used for collecting all constraints that are applied to a diagram.
 * 
 * @author b_muth
 *
 */
public interface DiagramConstraints {
  /**
   * Specifies constraints that affect nodes on a diagram.
   *  
   * @param diagram the diagram whose nodes are constrained.
   * 
   * @return the node constraints specified for the diagram
   */
  Collection<DiagramNodeConstraint> nodeConstraintsFor(Diagram diagram);
  
  /**
   * Specifies constraints that affect edges on a diagram.
   *  
   * @param diagram the diagram whose edges are constrained.
   * 
   * @return the edge constraints specified for the diagram
   */
  Collection<DiagramEdgeConstraint> edgeConstraintsFor(Diagram diagram);
}
