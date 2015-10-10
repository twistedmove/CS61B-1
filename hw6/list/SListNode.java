package list;

import dict.*;

public class SListNode {

  Entry item;
  SListNode next;

  public SListNode(Entry item, SListNode next) {
    this.item = item;
    this.next = next;
  }

  public SListNode(Entry item) {
    this(item, null);
  }

  public Entry item() {
    return this.item;
  }

  public SListNode next() {
    return this.next;
  }

}
