package org.diagramsascode.state.node;

/**
 * An state node on a state diagram.
 * 
 * @author b_muth
 *
 */
public class State extends StateDiagramNode {
	/**
	 * Creates a new state.
	 * 
	 * @param name the name to display inside the rectangle
	 */
	public State(String name) {
		super(name);
	}

	/**
	 * Creates a new state with a custom identifier
	 * 
	 * @param id the identifier of the state
	 * @param name the name to display inside the rectangle
	 */
	public State(String id, String name) {
		super(id, name);
	}
}
