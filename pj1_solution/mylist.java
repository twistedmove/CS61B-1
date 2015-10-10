// mylist.java
// Hansong Zhang

public class mylist {
  public mylistNode head;
  public mylistNode tail;
  public int size;

  public mylist() {
    head = new mylistNode(-1, -1, -1, -200);
    head.next = head;
    head.prev = head;
    size = 0;
  }

  public void insertEnd(int red, int green, int blue, int times) {
    if(size == 0) {
      this.head.next = new mylistNode(red, green, blue, times);
      this.head.prev = this.head.next;
      this.head.next.prev = this.head;
      this.head.next.next = this.head;
      size = 1;
    } else {
      mylistNode temp = new mylistNode(red, green, blue, times);
      head.prev.next = temp;
      temp.prev = head.prev;
      temp.next = head;
      head.prev = temp;
      size++;
    }
  }
}
