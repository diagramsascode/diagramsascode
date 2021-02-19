package org.diagramsascode.core;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class DiagramEdge implements DiagramElement{
  private static final String ID_PREFIX = "EDGE";
  private static final AtomicInteger ID_INDEX = new AtomicInteger();
  
  private final String id;
  private final String text;
  private final DiagramNode from;
  private final DiagramNode to;

  public DiagramEdge(DiagramNode from, DiagramNode to, String text) {
    this.id = newId();
    this.from = Objects.requireNonNull(from, "from must be non-null");
    this.to = Objects.requireNonNull(to, "to must be non-null");
    this.text = Objects.requireNonNull(text, "text must be non-null");
  }
  
  public String getId() {
    return id;
  }
  
  public DiagramNode getFrom() {
    return from;
  }

  public DiagramNode getTo() {
    return to;
  }

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
    return getClass().getSimpleName() + " [from=" + getFrom() + ", to=" + getTo() + ", guard=" + getText()
        + "]";
  }
}
