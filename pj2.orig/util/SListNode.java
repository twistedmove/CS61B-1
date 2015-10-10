/** SListNode is the node for the SList.
 * */

package util;

public class SListNode {
    // item stores the item of the list; next refers to the next node.
    public Object item;
    public SListNode next;

    // Constructor with item and next.
    public SListNode(Object item, SListNode next) {
        this.item = item;
        this.next = next;
    }

    // Constructor with item.
    public SListNode(Object item) {
        this(item, null);
    }

    // Constructor to copy a SListNode
    public SListNode(SListNode orig) {
        if (orig == null) {
            return;
        } else if (orig.next != null) {
            this.item = orig.item;
            this.next = new SListNode(orig.next);
        } else {
            this.item = orig.item;
        }
    }

    public int sizeAfter() {
        if (this.next == null) {
            return 0;
        }
        return 1 + this.next.sizeAfter();
    }

    // this turns a SListNode into a String.
    public String toString() {
        if (this == null) {
            return "{this is null}";
        }
        if (this.item == null && this.next != null) {
            return "{this item is null}" + this.next.toString();
        }
        if (this.item == null) {
            return "{this item is null}";
        }
        if (this.next == null) {
            return item.toString();
        } else {
            return item + next.toString();
        }
    }
}

