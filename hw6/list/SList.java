package list;
import dict.*;

public class SList {

  private SListNode head;
  private int size;

  public SList() {
    this.head = null;
    this.size = 0;
  }

  public int length() {
      return size;
  }

  public SListNode front() {
    return this.head;
  }

  public void insertFront(Entry item) {
    if (this.size == 0) {
      head = new SListNode(item);
    } else {
      head = new SListNode(item, head);
    }
  }

  public void remove(SListNode node) {
    if (this.head == node) {
      this.head = this.head.next;
      return;
    }
    SListNode i = this.head;
    while (i.next != null) {
      if (i.next == node) {
        i.next = i.next.next;
        return;
      }
    }
  }

  public String toString() {
      if (this.head == null) {
          return "null";
      }
      SListNode i = this.head;
      String a = "";
      while (i.next != null) {
          a += (((Entry) i.item).key() + ": " + ((Entry) i.item).value());
      }
      a += (((Entry) i.item).key() + ": " + ((Entry) i.item).value());
      return a;
  }
}
