package org.diagramsascode.image.activity;

import org.diagramsascode.activity.node.DecisionNode;
import org.diagramsascode.activity.node.FinalNode;
import org.diagramsascode.activity.node.InitialNode;
import org.diagramsascode.activity.node.MergeNode;
import org.diagramsascode.core.Diagram;
import org.diagramsascode.core.DiagramEdge;
import org.diagramsascode.core.DiagramNode;
import org.diagramsascode.image.DiagramToSource;

/**
 * Converts an acitvity diagram's content to a source text that can be used by
 * PlantUML to generate an activity diagram image.
 * 
 * @author b_muth
 *
 */
public class ActivityDiagramToSource implements DiagramToSource {
	static final String DIAGRAM_HEADER = "@startuml\n!pragma layout smetana\n"
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
		if (node instanceof InitialNode || node instanceof FinalNode) {
			source = "";
		} else if (node instanceof DecisionNode || node instanceof MergeNode) {
			source = STATE + node.getId() + " <<choice>>\n";
		} else {
			source = STATE + "\"" + node.getText() + "\" as " + node.getId() + "\n";
		}

		return source;
	}

	@Override
	public String edge(DiagramEdge edge) {
		String fromNodeId = nodeId(edge.getFrom());
		String toNodeId = nodeId(edge.getTo());

		String source = fromNodeId + " --> " + toNodeId + guardOf(edge) + "\n";
		return source;
	}

	@Override
	public String footer(Diagram diagram) {
		return DIAGRAM_FOOTER;
	}

	private String nodeId(DiagramNode node) {
		String nodeId = node instanceof InitialNode || node instanceof FinalNode ? "[*]" : node.getId();
		return nodeId;
	}

	private String guardOf(DiagramEdge edge) {
		String condition = edge.getText().isEmpty() ? "" : " : " + edge.getText();
		return condition;
	}
}
