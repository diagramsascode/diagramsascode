package org.diagramsascode.image.state;

import java.io.File;
import java.io.IOException;

import org.diagramsascode.core.Diagram;
import org.diagramsascode.image.Image;
import org.diagramsascode.image.ImageSource;
import org.diagramsascode.state.constraint.StateDiagramConstraints;
import org.diagramsascode.state.edge.Transition;
import org.diagramsascode.state.node.InitialState;
import org.diagramsascode.state.node.State;
import org.junit.jupiter.api.Test;

class StateDiagramImageWithSourceTest {
	@Test
	void writesSequenceDiagramImageToFile() throws IOException {
		// Create the initial state
	    final InitialState initial = new InitialState();

		// Create the nodes
		final State node0 = new State("Node 0");
		final State node1 = new State("Node 1");

		// Create the edges
		final Transition edge0 = new Transition(initial, node0, "");
		final Transition edge1 = new Transition(node0, node1, "Forward");
		final Transition edge2 = new Transition(node1, node0, "Backward");

		// Build the diagram
		Diagram diagram = Diagram.builder()
			.withNodes(initial, node0, node1)
			.withEdges(edge0, edge1, edge2)
			.withConstraints(new StateDiagramConstraints()).build();

		// Create the source text for PlantUML. You can print it to read it, if you want
		// to.
		ImageSource source = ImageSource.of(diagram, new StateDiagramToSource());

		// Create the image of the diagram and write it to a PNG file.
		Image image = Image.fromSource(source);
		File outputFile = File.createTempFile("state", ".png");
		image.writeToPngFile(outputFile);

		System.out.println("State diagram written to: " + outputFile);
	}
}
