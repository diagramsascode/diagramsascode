package org.diagramsascode.core;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * An edge connecting two nodes.
 * 
 * @author b_muth
 *
 */
public abstract class DiagramEdge implements DiagramElement{
  private static final String ID_PREFIX = "EDGE";
  private static final AtomicInteger ID_INDEX = new AtomicInteger();
  
  private final String id;
  private final String text;
  private final DiagramNode from;
  private final DiagramNode to;

  /**
   * Creates a new edge.
   * 
   * @param from the starting node of the edge
   * @param to the ending node of the edge
   * @param text the text displayed on the edge
   */
  public DiagramEdge(DiagramNode from, DiagramNode to, String text) {
    this.id = newId();
    this.from = Objects.requireNonNull(from, "from must be non-null");
    this.to = Objects.requireNonNull(to, "to must be non-null");
    this.text = Objects.requireNonNull(text, "text must be non-null");
  }
  
  @Override
  public String getId() {
    return id;
  }
  
  /**
   * Returns the starting node of the edge.
   * 
   * @return the starting node
   */
  public DiagramNode getFrom() {
    return from;
  }

  /**
   * Returns the ending node of the edge.
   * 
   * @return the ending node
   */
  public DiagramNode getTo() {
    return to;
  }

  @Override
  public final String getText() {
    return text;
  }

  private static String newId() {
    return ID_PREFIX + ID_INDEX.getAndIncrement();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    DiagramEdge other = (DiagramEdge) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
  
  @Override
  public String toString() {
    String guard = getText().isEmpty()? "" : ", guard=" + getText();
    return getClass().getSimpleName() + " [from=" + getFrom() + ", to=" + getTo() + guard + "]";
  }
}
