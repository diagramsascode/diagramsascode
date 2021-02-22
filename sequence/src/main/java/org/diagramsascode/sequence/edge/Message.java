package org.diagramsascode.sequence.edge;

import org.diagramsascode.sequence.node.Participant;

/**
 * A message edge on a sequence diagram.
 * It connects participants.
 * 
 * @author b_muth
 *
 */
public class Message extends SequenceDiagramEdge {
  /**
   * Creates a message edge.
   * 
   * @param from the starting node of the edge
   * @param to the ending node of the edge
   * @param text the text of the message
   */
  public Message(Participant from, Participant to, String text) {
    super(from, to, text);
  }
}
