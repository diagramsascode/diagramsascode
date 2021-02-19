package org.diagramsascode.activity.node;

public class Action extends ActivityNode{
  public Action(String text) {
    super(text);
  }

  @Override
  public String toString() {
    return "Action [text=" + getText() + "]";
  }
}
