/* MachinePlayer.java */

package player;

/**
 *  An implementation of an automatic Network player.  Keeps track of moves
 *  made by both players.  Can select a move for itself.
 */
public class MachinePlayer extends Player {
    final static int BLACK = 0;
    final static int WHITE = 1;
    final static int NOCHIP = -1;
    final static int INVALD = -100;

    boolean side;

    GameBoard board;

    int depth;
    int score;


    int color;
    int opponentColor;


  // Creates a machine player with the given color.  Color is either 0 (black)
  // or 1 (white).  (White has the first move.)
  public MachinePlayer(int color) {
      this.color = color;
      this.depth = 1;
      board = new GameBoard();
      board.myColor = color;
      opponentColor = 1 - color;
  }

  // Creates a machine player with the given color and search depth.  Color is
  // either 0 (black) or 1 (white).  (White has the first move.)
  public MachinePlayer(int color, int searchDepth) {
      this(color);
      this.depth = searchDepth;

  }

  // Returns a new move by "this" player.  Internally records the move (updates
  // the internal game board) as a move by "this" player.
  public Move chooseMove() {
      Move m = board.minimax(color, -100000, 100000, depth).move;
      forceMove(m);
      return m;
  } 

  // If the Move m is legal, records the move as a move by the opponent
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method allows your opponents to inform you of their moves.
  public boolean opponentMove(Move m) {
      if (!board.isValid(m, opponentColor)) {
          return false;
      } else {
          if (m.moveKind == Move.ADD) {
              board.add(m.x1, m.y1, opponentColor);
          } else if (m.moveKind == Move.STEP) {
              board.move(m.x1, m.y1, m.x2, m.y2);
          }
      }
      return true;
  }

  // If the Move m is legal, records the move as a move by "this" player
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method is used to help set up "Network problems" for your
  // player to solve.
  public boolean forceMove(Move m) {
      if (!board.isValid(m, this.color)) {
          return false;
      } else {
          if (m.moveKind == Move.ADD) {
              board.add(m.x1, m.y1, this.color);
          } else if (m.moveKind == Move.STEP) {
              board.move(m.x1, m.y1, m.x2, m.y2);
          }
      }
      return true;
  }

}
