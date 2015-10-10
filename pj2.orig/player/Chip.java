/** Chip is an ADT which refers to a chip. */

package player;

public class Chip {

    //Constants
    public static final int BLACK = 0;
    public static final int WHITE = 1;

    public int x;
    public int y;
    public int color;

    public boolean visited;
    
    public Chip(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.visited = false;
    }

    public boolean equals(Object c) {
        if (!(c instanceof Chip)) {
            return false;
        }
        if (this == null || c == null) {
            return false;
        }
        if (this.x == ((Chip) c).x && this.y == ((Chip) c).y && 
                this.color == ((Chip) c).color) {
            return true;
        } else {
            return false;
        }
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
