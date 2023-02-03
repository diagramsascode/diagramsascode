package org.diagramsascode.image.state;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.image.DiagramToSource;
import org.diagramsascode.state.node.InitialState;

/**
 * Converts a state diagram's content to a source text that can be used by
 * PlantUML to generate an activity diagram image.
 * 
 * @author b_muth
 *
 */
public class StateDiagramToSource implements DiagramToSource {
	static final String TRANSITION = " --> ";
	static final String QUOTE = "\"";
	static final String DIAGRAM_HEADER = "@startuml\n!pragma layout smetana\n" + "skinparam style strictuml\n"
			+ "skinparam monochrome true\nhide empty description\n";
	static final String DIAGRAM_FOOTER = "@enduml\n";
	static final String STATE = "state ";

	@Override
	public String header(Diagram diagram) {
		return DIAGRAM_HEADER;
	}

	@Override
	public String node(DiagramNode node) {
		String source;
		if (node instanceof InitialState) {
			source = "";
		} else {
			source = STATE + "\"" + node.getText() + "\" as " + node.getId() + "\n";
		}
		return source;
	}

	@Override
	public String edge(DiagramEdge edge) {
		String fromNodeId = nodeId(edge.getFrom());
		String toNodeId = nodeId(edge.getTo());
		edgeText(edge);
		String source = fromNodeId + TRANSITION + toNodeId + edgeText(edge) + "\n";
		return source;
	}

	private String edgeText(DiagramEdge edge) {
		String text = edge.getText();
		return text.isEmpty()? "" : " : " + text;
	}

	@Override
	public String footer(Diagram diagram) {
		return DIAGRAM_FOOTER;
	}

	private String nodeId(DiagramNode node) {
		String nodeId = node instanceof InitialState ? "[*]" : node.getId();
		return nodeId;
	}
}
