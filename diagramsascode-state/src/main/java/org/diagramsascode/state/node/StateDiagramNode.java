package org.diagramsascode.state.node;

import org.diagramsascode.core.DiagramNode;

/**
 * Abstract base class for all nodes shown on an a state diagram.
 * 
 * @author b_muth
 *
 */
public abstract class StateDiagramNode extends DiagramNode {
	/**
	 * Creates a state diagram node with empty text.
	 * 
	 */
	public StateDiagramNode() {
		this("");
	}

	/**
	 * Creates a node with the specified text.
	 * 
	 * @param text the text of the node
	 */
	public StateDiagramNode(String text) {
		super(text);
	}
}
