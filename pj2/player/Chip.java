/** Chip is an ADT which refers to a chip. */

package player;

public class Chip {

    //Constants
    public static final int BLACK = 0;
    public static final int WHITE = 1;

    int x;
    int y;
    int color;
    
    Chip(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public boolean equalsChip(Chip c) {
        if (c == null) {
            return false;
        } else {
            return this.x == c.x && this.y == c.y && this.color == c.color;
        }
    }

    public String toString() {
        return "Chip(" + x + " " + y + " " + color + ") ";
    }
}
