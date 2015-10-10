package list;

public class LockDListNode extends DListNode {
    protected boolean locked;

    public LockDListNode(Object item, DListNode prev, DListNode next) {
        super(item, prev, next);
        this.locked = false;
    }
}
