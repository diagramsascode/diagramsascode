package org.diagramsascode.state.edge;

import org.diagramsascode.state.node.StateDiagramNode;

/**
 * A transition on a state diagram. It connects states.
 * 
 * @author b_muth
 *
 */
public class Transition extends StateDiagramEdge {
	/**
	 * Creates an edge.
	 * 
	 * @param from the starting node of the edge
	 * @param to   the ending node of the edge
	 * @param text the text of the edge
	 */
	public Transition(StateDiagramNode from, StateDiagramNode to, String text) {
		super(from, to, text);
	}

	/**
	 * Creates an edge with a custom identifier.
	 * 
	 * @param id the identifier of the edge
	 * @param from the starting node of the edge
	 * @param to   the ending node of the edge
	 * @param text the text of the edge
	 */
	public Transition(String id, StateDiagramNode from, StateDiagramNode to, String text) {
		super(id, from, to, text);
	}
}
