// mylistNode.java
// Hansong Zhang

public class mylistNode {

  public int red;
  public int green;
  public int blue;
  public int times;

  public mylistNode prev;
  public mylistNode next;

  public mylistNode() {
    this.red = 0;
    this.green = 0;
    this.blue = 0;
    this.times = 0;

    this.prev = null;
    this.next = null;
  }

  public mylistNode(int red, int green, int blue, int times) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.times = times;

    this.prev = null;
    this.next = null;
  }

  public void remove() {
    this.prev.next = this.next;
    this.next.prev = this.prev;
  }
}
