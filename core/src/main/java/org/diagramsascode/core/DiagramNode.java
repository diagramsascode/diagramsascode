package org.diagramsascode.core;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class DiagramNode implements DiagramElement{
  private static final String ID_PREFIX = "NODE";
  private static final AtomicInteger ID_INDEX = new AtomicInteger();
  
  private final String id;
  private final String text;
  
  public DiagramNode(String text) {
    this.id = newId();
    this.text = Objects.requireNonNull(text, "text must be non-null");
  }
  
  public String getId() {
    return id;
  }
  
  public final String getText() {
    return text;
  }

  private static String newId() {
    return ID_PREFIX + ID_INDEX.getAndIncrement();
  }
  
  @Override
  public String toString() {
    return getClass().getSimpleName() + " [text=" + getText() + "]";
  }
}
