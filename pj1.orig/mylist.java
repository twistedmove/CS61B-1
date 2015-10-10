public class mylist {
    public mylistNode head;
    public mylistNode tail;
    private int size;

    public mylist() {
        head = new mylistNode();
        head.next = head;
        head.prev = head;
        size = 0;
    }

    public void insertEnd(int red, int green, int blue, int times) {
        if(size == 0) {
            mylistNode item2 = new mylistNode(red, green, blue, times);
            head.next = item2;
            head.prev = item2;
            item2.next = head;
            item2.prev = head;
            size++;
        } else {
            mylistNode item2 = new mylistNode(red, green, blue, times);
            item2.next = head;
            item2.prev = head.prev;
            head.prev.next = item2;
            head.prev = item2;
            size++;
        }
    }
}


            
