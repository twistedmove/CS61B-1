/** 
 * Best class stores a Move and its score.
 * This is a helper for minimax.
 */

package player;

public class Best {

    // m is a Move with a given score.
    int score;
    Move move;

    // Constructor without parameter.
    public Best() {
        this.score = 0;
        this.move = new Move();
    }

    // Constructor with score and Move.
    public Best(int score, Move m) {
        this.score = score;
        this.move = m;
    }

    public String toString() {
        return "Best(" + this.score + ", " + this.move + ") ";
    }
}
