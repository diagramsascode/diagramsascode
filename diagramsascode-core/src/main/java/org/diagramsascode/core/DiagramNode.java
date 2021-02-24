package org.diagramsascode.core;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A node on a diagram. To be subclassed by concrete node types.
 * 
 * @author b_muth
 *
 */
public abstract class DiagramNode implements DiagramElement{
  private static final String ID_PREFIX = "NODE";
  private static final AtomicInteger ID_INDEX = new AtomicInteger();
  
  private final String id;
  private final String text;
  
  /**
   * Creates a new node.
   * 
   * @param text the text of the node
   */
  public DiagramNode(String text) {
    this.id = newId();
    this.text = Objects.requireNonNull(text, "text must be non-null");
  }
  
  @Override
  public String getId() {
    return id;
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
    DiagramNode other = (DiagramNode) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
  
  @Override
  public String toString() {
    String text = getText().isEmpty()? "" : " [text=" + getText() + "]";
    return getClass().getSimpleName() + text;
  }
}
