/** SList is a data structure in this project.
 */

package util;
import player.Chip;

public class SList {
    // head refers to the first node in the list (if exists). size is the # of nodes.
    public SListNode head;
    public int size;

    // Constructor with zero parameter.
    public SList() {
        head = null;
        size = 0;
    }

    // Constructor with the head item.
    public SList(Object item) {
        head = new SListNode(item, null);
        size = 1;
    }
    
    // Constructor to copy a SList.
    public SList(SList orig) {
        if (orig == null || orig.size == 0) {
            return;
        }
        this.head = new SListNode(orig.head);
        this.size = orig.size;
    }

    // length() returns the size of the SList.
    public int length() {
        return size;
    }

    // this inserts an item to the front of the SList.
    public void insertFront(Object item) {
        if (head == null) {
            head = new SListNode(item, null);
            size++;
        } else {
            head.next = new SListNode(item, head.next);
            size++;
        }
    }

    // this inserts an item to the end of the SList.
    public void insertEnd(Object item) {
        if (head == null) {
            head = new SListNode(item, null);
            size++;
        } else {
            SListNode curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = new SListNode(item);
            size++;
        }
    }

    // this returns the item in the given position.
    public Object getItem(int position) {
        if (position < 1 || position > size || head == null) {
            return null;
        } else {
            SListNode curr = head;
            while (position > 1) {
                position--;
                curr = curr.next;
                if (curr == null) {
                    return null;
                }
            }
            return curr.item;
        }
    }

    // this removes a node at the given position.
    public void remove(int position) {
        if (position < 1 || position > size || head == null) {
            return;
        } else {
            SListNode curr = head;
            SListNode back = head;
            while (position > 1) {
                position--;
                if (curr == null) {
                    return;
                }
                back = curr;
                curr = curr.next;
            }
            back.next = back.next.next;
        }
    }

    public boolean contains(Object c) {
        SListNode curr = head;
        if (curr == null) {
            return false;
        } else if (curr.item == null) {
            return false;
        } else if (curr.item.equals(c)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasChip(Chip c) {
        if (c == null) {
            return false;
        } else {
            for (int i = 1; i <= size; i++) {
                if (c.equalsChip((Chip) this.getItem(i))) {
                    return true;
                }
            }
        return false;
        }
    }

    // this turns a SList into a String.
    public String toString() {
        if (head == null) {
            return "[empty SList]";
        } else {
            return head.toString();
        }
    }

}
