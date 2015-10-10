/**
 * GameBoard.java 
 *
 * This is the board for the game.
 * @author Jiahui Huang, Meihua Luo, Hansong Zhang
 *
 **/

package player;

import util.*;

public class GameBoard {

    // Using a 2-D array for a game board.
    private int[][] board = new int[8][8];

    // The following constant numbers represent Chips.
    public static final int BLACK = 0;
    public static final int WHITE = 1;
    public static final int NOCHIP = -1;

    // The following numbers represent numbers of chips of black/white.
    private int blackNum;
    private int whiteNum;

    // The following constant number represent an INVALID grid (corners).
    public static final int INVALID = -100;

    // myColor is the Machine Player's color.
    public int myColor;

    /**
     * Constructor for GameBoard with 0 parameter: The four corners will
     * be INVALID. For other grids, the value will be NOCHIP.
     * There are no chips, so blackNum = 0 and whiteNum = 0.
     **/
    public GameBoard() {
        for (int i = 0; i < 8; i++) {
            board[i] = new int[8];
        }

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board[x][y] = NOCHIP;
            }
        }

        board[0][0] = INVALID;
        board[7][0] = INVALID;
        board[0][7] = INVALID;
        board[7][7] = INVALID;

        blackNum = 0;
        whiteNum = 0;
    }

    /**
     * copy() returns a GameBoard EXACTLY the same as this one.
     */
    public GameBoard copyBoard() {
        GameBoard cp = new GameBoard();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                cp.board[x][y] = this.board[x][y];
            }
        }
        return cp;
    }

    /**
     * blackChips() returns a collection of all black chips.
     * This collection is blackCollection, which is a 2-D array.
     * For each element, it is a array {x, y} which refers to the
     * location of a black chip.
     **/
    public int[][] blackChips() {
        if (blackNum == 0) {
            return new int[0][];
        }
        int[][] blackCollection = new int[blackNum][2];
        for (int i = 0; i < blackNum; i++) {
            blackCollection[i] = new int[2];
        }

        int n = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (this.board[x][y] == BLACK) {
                    blackCollection[n][0] = x;
                    blackCollection[n][1] = y;
                    n++;
                }
            }
        }
        return blackCollection;
    }

    /**
     * whiteChips() returns a collection of all white chips.
     * This collection is whiteCollection, which is a 2-D array.
     * For each element, it is a array {x, y} which refers to the
     * location of a white chip.
     **/
    public int[][] whiteChips() {
        if (whiteNum == 0) {
            return new int[0][];
        }
        int[][] whiteCollection = new int[whiteNum][2];
        for (int i = 0; i < whiteNum; i++) {
            whiteCollection[i] = new int[2];
        }

        int n = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (this.board[x][y] == WHITE) {
                    whiteCollection[n][0] = x;
                    whiteCollection[n][1] = y;
                    n++;
                }
            }
        }
        return whiteCollection;
    }

    /**
     * colorChips returns a collection of all chips of given color.
     */
    public int[][] colorChips(int color) {
        if (color == BLACK) {
            return this.blackChips();
        } else if (color == WHITE) {
            return this.whiteChips();
        }
        return null;
    }

    /**
     * chip(int x, int y) returns the color of chip (if any) at a point
     * (x, y). If no chips here, return NOCHIP.
     **/
    public int chip(int x, int y) {
        return board[x][y];
    }

    // getBlackNum() returns the number of black chip
    public int getBlackNum() {
        return blackNum;
    }

    // getWhiteNum() returns the number of white chip
    public int getWhiteNum() {
        return whiteNum;
    }

    /**
     * add(int x, int y, int color) will add a chip of color z on the board.
     * This does NOT check whether this move (add) is valid or not.
     **/
    public void add(int x, int y, int color) {
        this.board[x][y] = color;
        if (color == BLACK) {
            blackNum++;
        } else if (color == WHITE) {
            whiteNum++;
        }
    }

    /**
     * move(int xx1, int yy1, int xx2, int yy2) will move a chip
     * from (xx2, yy2) to (xx1, yy1). 
     * This does NOT check whether this move is valid or not.
     **/
    public void move(int xx1, int yy1, int xx2, int yy2) {
        this.board[xx1][yy1] = this.chip(xx2, yy2);
        this.board[xx2][yy2] = NOCHIP;
    }

    /** isValid(Move m, int color)
     * determind whether a move m is valid for a player of a given color.
     * @return true iff valid.
     */
    public boolean isValid(Move m, int color) {
        if (m.moveKind == Move.ADD) {
            if (this.chip(m.x1, m.y1) != NOCHIP) {
                return false;
            } else if (color == BLACK && (m.x1 == 0 || m.x1 == 7)) {
                return false;
            } else if (color == WHITE && (m.y1 == 0 || m.y1 == 7)) {
                return false;
            } else if (!this.checkColor(m.x1, m.y1, color)) {//moveHelper(m.x1, m.y1, color)) {
                return false;
            }
        } else if (m.moveKind == Move.STEP) {
            if (this.chip(m.x1, m.y1) != NOCHIP) {
                return false;
            } else if (this.chip(m.x2, m.y2) != color) {
                return false;
            } else if (m.x1 == m.x2 && m.y1 == m.y2) {
                return false;
            } else if (color == BLACK && (m.x1 == 0 || m.x1 == 7)) {
                return false;
            } else if (color == WHITE && (m.y1 == 0 || m.y1 == 7)) {
                return false;
            } else if (!this.checkColor(m.x1, m.y1, m.x2, m.y2, color)) {//moveHelper(m.x1, m.y1, color)) {
                return false;
            }
        }
        return true;
    }

    // A helper function for isValid: checks whether has more than two chips in a group. 
    //
    private boolean checkColor(int x, int y, int color) {
        GameBoard cpBoard = this.copyBoard();
        cpBoard.add(x, y, color);
        for (int xx = 0; xx < 8; xx++) {
            for (int yy = 0; yy < 8; yy++) {
                if (cpBoard.board[xx][yy] == color) {
                    if (!cpBoard.moveHelper(xx, yy, color)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean checkColor(int color) {
        GameBoard cpBoard = this.copyBoard();
        int[][] chips = cpBoard.colorChips(color);
        for (int xx = 0; xx < 8; xx++) {
            for (int yy = 0; yy < 8; yy++) {
                if (cpBoard.board[xx][yy] == color) {
                    if (!cpBoard.moveHelper(xx, yy, color)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean checkColor(int x1, int y1, int x2, int y2, int color) {
        GameBoard cpBoard = this.copyBoard();
        cpBoard.move(x1, y1, x2, y2);
        int[][] chips = cpBoard.colorChips(color);
        for (int xx = 0; xx < 8; xx++) {
            for (int yy = 0; yy < 8; yy++) {
                if (cpBoard.board[xx][yy] == color) {
                    if (!cpBoard.moveHelper(xx, yy, color)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean moveHelper(int x, int y, int color) {
        int count = 0;
        GameBoard cpBoard = this.copyBoard();
        cpBoard.add(x, y, color);
        if (y == 0 && x != 0 && x != 7) {
            if (cpBoard.board[x-1][y] == color) {
                count++;
            }
            if (cpBoard.board[x+1][y] == color) {
                count++;
            }
            if (cpBoard.board[x-1][y+1] == color) {
                count++;
            }
            if (cpBoard.board[x][y+1] == color) {
                count++;
            }
            if (cpBoard.board[x+1][y+1] == color) {
                count++;
            }
        } else if (y == 7 && x != 0 && x != 7) {
            if (cpBoard.board[x-1][y] == color) {
                count++;
            }
            if (cpBoard.board[x+1][y] == color) {
                count++;
            }
            if (cpBoard.board[x-1][y-1] == color) {
                count++;
            }
            if (cpBoard.board[x][y-1] == color) {
                count++;
            }
            if (cpBoard.board[x+1][y-1] == color) {
                count++;
            }
        } else if (x != 0 && x != 7) {
            if (cpBoard.board[x-1][y] == color) {
                count++;
            }
            if (cpBoard.board[x+1][y] == color) {
                count++;
            }
            if (cpBoard.board[x-1][y-1] == color) {
                count++;
            }
            if (cpBoard.board[x][y-1] == color) {
                count++;
            }
            if (cpBoard.board[x+1][y-1] == color) {
                count++;
            }
            if (cpBoard.board[x-1][y+1] == color) {
                count++;
            }
            if (cpBoard.board[x][y+1] == color) {
                count++;
            }
            if (cpBoard.board[x+1][y+1] == color) {
                count++;
            }
        } else if (x == 0 && y != 0 && y != 7) {
            if (cpBoard.board[x][y-1] == color) {
                count++;
            }
            if (cpBoard.board[x][y+1] == color) {
                count++;
            }
            if (cpBoard.board[x+1][y-1] == color) {
                count++;
            }
            if (cpBoard.board[x+1][y] == color) {
                count++;
            }
            if (cpBoard.board[x+1][y+1] == color) {
                count++;
            }
        } else if (x == 7 && y != 0 && y != 7) {
            if (cpBoard.board[x][y-1] == color) {
                count++;
            }
            if (cpBoard.board[x][y+1] == color) {
                count++;
            }
            if (cpBoard.board[x-1][y-1] == color) {
                count++;
            }
            if (cpBoard.board[x-1][y] == color) {
                count++;
            }
            if (cpBoard.board[x-1][y+1] == color) {
                count++;
            }
        }
        if (count > 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * totalMoves(int color)
     * It returns all possible mvoes.
     * @return a SList of Move[s].
     */
    public SList totalMoves(int color) {
        SList moveList = new SList();
        Move tester;
        if (color == BLACK) {
            if (blackNum < 10) {
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        tester = new Move(x, y);
                        if (isValid(tester, color)) {
                            moveList.insertFront(tester);
                        }
                    }
                }
            } else {
                int[][] chips = this.blackChips();
                for (int i = 0; i < 10; i++) {
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            tester = new Move(x, y, chips[i][0], chips[i][1]);
                            if (isValid(tester, color)) {
                                moveList.insertFront(tester);
                            }
                        }
                    }
                }
            }
        } else if (color == WHITE) {
            if (whiteNum < 10) {
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        tester = new Move(x, y);
                        if (isValid(tester, color)) {
                            moveList.insertFront(tester);
                        }
                    }
                }
            } else {
                int[][] chips = this.whiteChips();
                for (int i = 0; i < 10; i++) {
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            tester = new Move(x, y, chips[i][0], chips[i][1]);
                            if (isValid(tester, color)) {
                                moveList.insertFront(tester);
                            }
                        }
                    }
                }
            }
        }
        return moveList;
    }

    /**
     * notInTheWay is to check whether there is a chip between chips.
     * @return true iff not in the way.
     */
    public boolean notInTheWay(int x1, int y1, int x2, int y2) {
        if (x1 == x2) {
            for (int y = y1+1; y < y2; y++) {
                if (this.chip(x1, y) != NOCHIP) {
                    return false;
                }
            }
            for (int y = y2+1; y < y1; y++) {
                if (this.chip(x1, y) != NOCHIP) {
                    return false;
                }
            }
        } else if (y1 == y2) {
            for (int x = x1+1; x < x2; x++) {
                if (this.chip(x, y1) != NOCHIP) {
                    return false;
                }
            }
            for (int x = x2+1; x < x1; x++) {
                if (this.chip(x, y1) != NOCHIP) {
                    return false;
                }
            }
        } else if (x1 < x2 && y1 < y2) {
            for (int i = 1; i < x2-x1; i++) {
                if (this.chip(x1+i, y1+i) != NOCHIP) {
                    return false;
                }
            }
        } else if (x1 < x2 && y1 > y2) {
            for (int i = 1; i < x2-x1; i++) {
                if (this.chip(x1+i, y1-i) != NOCHIP) {
                    return false;
                }
            }
        } else if (x1 > x2 && y1 > y2) {
            for (int i = 1; i < x1-x2; i++) {
                if (this.chip(x1-i, y1-i) != NOCHIP) {
                    return false;
                }
            }
        } else if (x1 > x2 && y1 < y2) {
            for (int i = 1; i < x1-x2; i++) {
                if (this.chip(x1-i, y1+i) != NOCHIP) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * connectionChip
     * @return all connections given a chip at a point.
     */
    public SList connectionChip(int x, int y) {
        SList chipList = new SList();
        if (this.chip(x, y) == BLACK) {
            for (int i = 0; i < 8; i++) {
                if (y != 0 && y != 7 && this.chip(i, y) == BLACK && i != x && notInTheWay(x, y, i, y)) {
                    chipList.insertFront(new Chip(i, y, BLACK));
                }
            }
            for (int j = 0; j < 8; j++) {
                if (this.chip(x, j) == BLACK && j != y && notInTheWay(x, y, x, j)) {
                    chipList.insertFront(new Chip(x, j, BLACK));
                }
            }
            for (int p = 1; x - p >= 0 && y - p >= 0; p++) {
                if (this.chip(x-p, y-p) == BLACK && notInTheWay(x, y, x-p, y-p)) {
                    chipList.insertFront(new Chip(x-p, y-p, BLACK));
                }
            }
            for (int p = 1; x + p <= 7 && y + p <= 7; p++) {
                if (this.chip(x+p, y+p) == BLACK && notInTheWay(x, y, x+p, y+p)) {
                    chipList.insertFront(new Chip(x+p, y+p, BLACK));
                }
            }
            for (int p = 1; x + p <= 7 && y - p >= 0; p++) {
                if (this.chip(x+p, y-p) == BLACK && notInTheWay(x, y, x+p, y-p)) {
                    chipList.insertFront(new Chip(x+p, y-p, BLACK));
                }
            }
            for (int p = 1; x - p >= 0 && y + p <= 7; p++) {
                if (this.chip(x-p, y+p) == BLACK && notInTheWay(x, y, x-p, y+p)) {
                    chipList.insertFront(new Chip(x-p, y+p, BLACK));
                }
            }
        } else if (this.chip(x, y) == WHITE) {
            for (int i = 0; i < 8; i++) {
                if (this.chip(i, y) == WHITE && i != x && notInTheWay(x, y, i, y)) {
                    chipList.insertFront(new Chip(i, y, WHITE));
                }
            }
            for (int j = 0; j < 8; j++) {
                if (x != 0 && x != 7 && this.chip(x, j) == WHITE && j != y && notInTheWay(x, y, x, j)) {
                    chipList.insertFront(new Chip(x, j, WHITE));
                }
            }
            for (int p = 1; x - p >= 0 && y - p >= 0; p++) {
                if (this.chip(x-p, y-p) == WHITE && notInTheWay(x, y, x-p, y-p)) {
                    chipList.insertFront(new Chip(x-p, y-p, WHITE));
                }
            }
            for (int p = 1; x + p <= 7 && y + p <= 7; p++) {
                if (this.chip(x+p, y+p) == WHITE && notInTheWay(x, y, x+p, y+p)) {
                    chipList.insertFront(new Chip(x+p, y+p, WHITE));
                }
            }
            for (int p = 1; x + p <= 7 && y - p >= 0; p++) {
                if (this.chip(x+p, y-p) == WHITE && notInTheWay(x, y, x+p, y-p)) {
                    chipList.insertFront(new Chip(x+p, y-p, WHITE));
                }
            }
            for (int p = 1; x - p >= 0 && y + p <= 7; p++) {
                if (this.chip(x-p, y+p) == WHITE && notInTheWay(x, y, x-p, y+p)) {
                    chipList.insertFront(new Chip(x-p, y+p, WHITE));
                }
            }
        }
        if (chipList.head != null) {
            return chipList;
        } else {
            return null;
        }
    }

    // a helper function that returns true iff a chip at location (x, y) has
    // connection to other chips.
    public boolean hasConnection(int x, int y) {
        return connectionChip(x, y) != null;
    }

    /**
     * connectionNum will give the total # of connections for a color.
     * a helper for evaluation.
     * @return number of connections.
     */
    public int connectionNum(int color) {
        int num = 0;
        int[][] chips = this.colorChips(color);
        for (int i = 0; i < chips.length; i++) {
            if (hasConnection(chips[i][0], chips[i][1])) {
                num += connectionChip(chips[i][0], chips[i][1]).length();
            }
        }
        return num / 2;
    }


    /** pcsGoal will give the total # of chips in goal area for a color.
     *
     */
    public int pcsGoal(int color) {
        int num = 0;
        for (int i = 1; i < 7; i++) {
            if (this.chip(i, 0) == color || this.chip(i, 7) == color ||
                    this.chip(0, i) == color || this.chip(7, i) == color) {
                num++;
                    }
        }
        return num;
    }

    public int pcsStart(int color) {
        int num = 0;
        for (int i = 1; i < 7; i++) {
            if (this.chip(i, 0) == color || this.chip(0, i) == color) {
                num++;
            }
        }
        return num;
    }

    public int pcsEnd(int color) {
        int num = 0;
        for (int i = 1; i < 7; i++) {
            if (this.chip(i, 7) == color || this.chip(7, i) == color) {
                num++;
            }
        }
        return num;
    }

    /**
     * longestPath will give the # of nodes of the longest path of a chip at (x, y).
     * It will not go thru chips in the list visited.
     */
    /*
    public int longestPath(int num, Chip a, SList visited) {
        int d = 0;
        int dmax = 0;
        int visitedNum = 0;
        SList visitedCopy = new SList(visited);
        if (!hasConnection(a.x, a.y)) {
            return 0;
        } else {
            SList candidate = this.connectionChip(a.x, a.y);
            for (int i = 1; i <= candidate.length(); i++) {
                if (!visitedCopy.contains(candidate.getItem(i))) {
                    visitedCopy.insertFront(candidate.getItem(i));
                    d = longestPath(1 + num, (Chip) candidate.getItem(i), visitedCopy);
                    if (d > dmax) {
                        dmax = d;
                    }
                } else {
                    visitedNum++;
                }
            }
            if (visitedNum == candidate.length()) {
                return num;
            }
        return dmax;
        }
    }
    */
    // Not done!!!
    public SListNode longestPath(Chip a, Chip b, SList visited) {
        if (a.equals(b)) {
            return new SListNode(b, null);
        }
        SList cList = this.connectionChip(a.x, a.y);
        SListNode[] candidates = new SListNode[cList.length()];
        SList visitedCp = new SList(visited);
        visitedCp.insertFront(a);
        for (int i = 1; i <= cList.length(); i++) {
            if (visited.hasChip((Chip) cList.getItem(i))) {
                candidates[i-1] = null;
                continue;
            } else {
                candidates[i-1] = longestPath((Chip) cList.getItem(i), b, visitedCp);
            }
        }
        int maxSize = 0;
        SListNode currCandidate = new SListNode(null);
        for (int j = 0; j < candidates.length; j++) {
            if (candidates[j] != null && candidates[j].sizeAfter() >= maxSize) {
                currCandidate = candidates[j];
            }
        }
        return currCandidate;
    }

    // Helper

    public static boolean containsDuplicate(SList list) {
        if (list == null) {
            return false;
        }
        for (int i = 1; i <= list.length(); i++) {
            for(int j = i+1; j <= list.length(); j++) {
                if (list.getItem(i) != null && list.getItem(i).equals(list.getItem(j))) {
                    return true;
                }
            }
        }
        return false;
    }
            
    // Not done!!!
    
    public int maxLengthPathold(Chip from, Chip curr, Chip dest, SList visited) {
        int c = 0;
        if (this.connectionChip(dest.x, dest.y) == null) {
            return 0;
        }
        SList visitedCopy = new SList();
        if (visited != null) {
            visitedCopy = new SList(visited);
        }
        Chip candidate;
        if (containsDuplicate(visited)) {
            return 0;
        }
        if (visitedCopy.hasChip(curr)) {
            return 0;
        }
        if (curr != null) {
            visitedCopy.insertFront(curr);
        }
        if (curr.x == dest.x && curr.y == dest.y) {
            return 100;
        }
        SList cList = this.connectionChip(curr.x, curr.y);
        if (cList == null) {
            return 0;
        }
        int[] scores = new int[cList.length()];
        for (int i = 1; i <= cList.length(); i++) {
            candidate = (Chip) cList.getItem(i);
            if (!(sameSlope(from, curr, candidate))) {
                c = maxLengthPath(curr, candidate, dest, visitedCopy) + 1;
                scores[i-1] = c;
            }
        }
        if (maxOf(scores) == 0 && curr.x != dest.x && curr.y != dest.y) {
            return -1000;
        }
        int len = maxOf(scores) % 100;
        if (len < 0) {
            len += 100;
        }
        return len;
    }

    public int maxLengthPath(Chip from, Chip curr, Chip dest, SList visited) {
        int c = 0;
        Chip candidate;
        if (curr == null) {
            return 0;
        }
        if (curr.equalsChip(dest)) {
            return 0;
        }
        SList visitedCp = new SList(visited);
        visitedCp.insertFront(curr);
        SList cList = this.connectionChip(curr.x, curr.y);
        if (cList == null) {
            return 0;
        }
        int[] scores = new int[cList.length()];
        for (int i = 1; i <= cList.length(); i++) {
            candidate = (Chip) cList.getItem(i);
            if (visited != null && visited.hasChip(candidate)) {
                continue;
            }
            if (!(sameSlope(from, curr, candidate))) {
                c = maxLengthPath(curr, candidate, dest, visitedCp) + 1;
                scores[i-1] = c;
            }
        }
        if (maxOf(scores) == 0 && curr.x != dest.x && curr.y != dest.y) {
            return -1000;
        }

        return maxOf(scores);
    }

    /*
    public int maxLengthPath(Chip a, Chip b, SList visited, Chip from) {
        int c = 0;
        SList visitedCopy = new SList(visited);
        System.out.println(visitedCopy);
        if (containsDuplicate(visited)) {
            return 0;
        }
        if (visitedCopy.hasChip(a)) {
            return 0;
        } else if (a != null) {
            visitedCopy.insertFront(a);
        }
        if (a.x == b.x && a.y == b.y) {
            return 0;
        }
        SList cList = this.connectionChip(a.x, a.y);
        if (cList == null) {
            return 0;
        }
        int[] scores = new int[cList.length()];
        for (int i = 1; i <= cList.length(); i++) {
            if (from == null) {
                c = maxLengthPath((Chip) cList.getItem(i), b, visitedCopy, a) + 1;
                scores[i-1] = c;
            } else {
                if (sameSlope(a, (Chip) cList.getItem(i), from)) {
                    System.out.println("same slope!");
                    return 0;
                } else {
                    c = maxLengthPath((Chip) cList.getItem(i), b, visitedCopy, a) + 1;
                    scores[i-1] = c;
                }
            }

        }
        if (maxOf(scores) == 0 && a.x != b.x && a.y != b.y) {
            return -1;
        }
        System.out.println(visitedCopy);
        return maxOf(scores);
    }
    */

    public static int maxOf(int[] arr) {
        int curr = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > curr) {
                curr = arr[i];
            }
        }
        return curr;
    }
    
    // a helper function that returns true iff three chips are in the same line.
    public static boolean sameSlope(Chip a, Chip b, Chip c) {
        if (a == null || b == null || c == null) {
            return false;
        }
        if (a.x == b.x && a.x == c.x) {
            return true;
        } else if ((c.y-b.y) * (c.x - a.x) == (c.y - a.y) * (c.x - b.x)) {
            return true;
        } else {
            return false;
        }
    }

    // a helper function that returns true iff a chip is in starting area.
    // Starting area: for black, y = 0; for white, x = 0.
    public static boolean startingArea(Chip a) {
        return a != null && (a.x == 0 || a.y == 0);
    }

    // a helper function that returns true iff a chip is in ending area.
    // Ending area: for black, y = 7; for white, x = 7.
    public static boolean endingArea(Chip a) {
        return a != null && (a.x == 7 || a.y == 7);
    }
    
    // searchNext is the helper function for determining whether a network exists.
    // It will call itself recursively.
    // to avoid infinite loop, rDepth records the recursive depth it reaches.
    /*
    public int searchNext(int length, Chip curr, Chip from, int rDepth) {
        int done = 0;
        // Base case
        if (curr == null) {
            return 0;
        }
        if (endingArea(curr) && length >= 5) {
            return 1;
        }
        if (endingArea(curr) && length < 5) {
            return 0;
        }
        if (rDepth > 9) {
            return 0;
        }
        SList cList = new SList();
        cList = this.connectionChip(curr.x, curr.y);
        for (int i = 1; i <= cList.length(); i++) {
            Object candidate = cList.getItem(i);
            if (from == null) {
                if (searchNext(length + 1, (Chip) candidate, curr, rDepth + 1) == 1) {
                    return 1;
                }
            }
            if (from != null && startingArea(curr)) {
                return 0;
            }
            if (!((Chip) candidate).equals(from) && !sameSlope(curr, from, (Chip) candidate)) {
                if (searchNext(length + 1, (Chip) candidate, curr, rDepth + 1) == 1) {
                    return 1;
                }
            }
        }
        return 0;
    }
    */

    public int longestConn(int color) {
        int[][] chips = this.colorChips(color);
        int max = 0;
        int curr = 0;
        for (int i = 0; i < chips.length; i++) {
            for (int j = i+1; j < chips.length; j++) {
                curr = this.maxLengthPath(null, new Chip(chips[i][0], chips[i][1], color), new Chip(chips[j][0], chips[j][1], color), new SList());
                if (curr >= max) {
                    max = curr;
                }
            }
        }
        return max;
    }
                    
    /** this is the helper function for anyNetwork. 
     * @return true iff black has network (already wins).
     */
    public boolean blackNetwork() {
        for (int x = 1; x < 7; x++) {
            if (hasConnection(x, 0)) {
                for (int i = 1; i < 7; i++) {
                    if (this.chip(i, 7) == BLACK) {
                        if (maxLengthPath(null, new Chip(x, 0, BLACK), 
                                    new Chip(i, 7, BLACK), null) >= 5) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /** this is the helper function for anyNetwork.
     * @return true iff white has network (already wins).
     */
    /*
    public boolean whiteNetwork() {
        for (int y = 1; y < 7; y++) {
            if (hasConnection(0, y)) {
                if (searchNext(0, new Chip(0, y, WHITE), null, 0) > 0) {
                    return true;
                }
            }
        }
        return false;
    }
    */
    public boolean whiteNetwork() {
        for (int y = 1; y < 7; y++) {
            if (hasConnection(0, y)) {
                for (int i = 1; i < 7; i++) {
                    if (this.chip(7, i) == WHITE) {
                        if (maxLengthPath(null, new Chip(0, y, WHITE), 
                                    new Chip(7, i, WHITE), null) >= 5) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /** 
     * anyNetwork returns whether a network already exists.
     * @param color the color of given player.
     * @return true iff the player has network.
     */
    public boolean anyNetwork(int color) {
        if (color == BLACK) {
            return this.blackNetwork();
        } else if (color == WHITE) {
            return this.whiteNetwork();
        } else {
            return false;
        }
    }

    private void undoMove(Move m) {
        if (m.moveKind == Move.ADD) {
            int color = this.chip(m.x1, m.y1);
            if (color == BLACK) {
                blackNum--;
            } else if (color == WHITE) {
                whiteNum--;
            }
            board[m.x1][m.y1] = NOCHIP;
        } else if (m.moveKind == Move.STEP) {
            board[m.x2][m.y2] = this.chip(m.x1, m.y1);
            board[m.x1][m.y1] = NOCHIP;
        }
    }

    private void tryMove(Move m, int color) {
        if (m.moveKind == m.ADD) {
            this.add(m.x1, m.y1, color);
        } else if (m.moveKind == m.STEP) {
            this.move(m.x1, m.y1, m.x2, m.y2);
        }
    }


    // toString turns a GameBoard into a string.
    public String toString() {
        String str = "-----------------\n";
        for (int y = 0; y < 8; y++) {
            str += "|";
            for (int x = 0; x < 8; x++) {
                if (chip(x, y) == BLACK) {
                    str += "B|";
                } else if (chip(x, y) == WHITE) {
                    str += "W|";
                } else if (chip(x, y) == INVALID) {
                    str += "X|";
                } else {
                    str += " |";
                }
            }
            str += "\n";
            str += "-----------------\n";
        }
        return str;
    }

    /**
     * score will evaluate the score of the player given current board.
     * @return a score for the board.
     * The higher the value, the black is more likely to win.
     * The lower the value, the white is more likely to win.
     */
    public int evaluation() {
        if (this.blackNetwork()) {
            return Integer.MAX_VALUE;
        } else if (this.whiteNetwork()) {
            return Integer.MIN_VALUE;
        } else {
            int score = (this.connectionNum(BLACK) - this.connectionNum(WHITE)) * 100;
            int blackConn = this.longestConn(BLACK);
            int whiteConn = this.longestConn(WHITE);
            score += (blackConn * blackConn - whiteConn * whiteConn) * 50;
            score += this.pcsGoal(WHITE) * this.pcsGoal(WHITE) * 5;
            score -= this.pcsGoal(BLACK) * this.pcsGoal(BLACK) * 5;
            if (this.pcsStart(BLACK) == 1) {
                score += 50;
            } else if (this.pcsEnd(BLACK) == 1) {
                score += 50;
            } else if (this.pcsStart(WHITE) == 1) {
                score -= 50;
            } else if (this.pcsEnd(WHITE) == 1) {
                score -= 50;
            }

            return score;
        }
    }

    public Best minimax(int color, int alpha, int beta, int depth) {
        Best myBest = new Best();
        Best reply;
        Move m;

        SList moves = this.totalMoves(color);

        if (this.anyNetwork(BLACK) || this.anyNetwork(WHITE)) {
            myBest.score = this.evaluation();
            myBest.move = (Move) moves.getItem(1);
            return myBest;
        }

        if (color == BLACK) {
            myBest.score = alpha;
        } else {
            myBest.score = beta;
        }


        if (moves.length() == 0) {
            return new Best();
        }


        myBest.move = (Move) moves.getItem(1);

        if (depth == 0) {
            myBest.score = this.evaluation();
            return myBest;
        }

        //opponentBest = minimax(1-color , alpha, beta, depth-1);

        for (int i = 1; i <= moves.length(); i++) {
            m = (Move) moves.getItem(i);
            this.tryMove(m, color);
            reply = minimax(1-color, alpha, beta, depth-1);
            undoMove(m);
            if (color == BLACK && reply.score > myBest.score) {
                myBest.move = m;
                myBest.score = reply.score;
                alpha = reply.score;
            } else if (color == WHITE && reply.score < myBest.score) {
                myBest.move = m;
                myBest.score = reply.score;
                beta = reply.score;
            }
            if (alpha >= beta) {
                return myBest;
            }
        }
        return myBest;

    }


    /*
    public Move minimax(int color, int alpha, int beta, int depth) {
        SList total = this.totalMoves(color);
        Move best = new Move();
        if (this.blackConnection() || this.whiteConnection) {
            return null;
        }
    }
    */

    // Test
    //
    public static void test1() {
        GameBoard board = new GameBoard();
        board.add(3, 1, BLACK);
        board.add(4, 1, BLACK);
        board.add(5, 1, WHITE);
        System.out.println(board.isValid(new Move(0, 0), BLACK));
        System.out.println(board.isValid(new Move(3, 1), BLACK));
        System.out.println(board.isValid(new Move(4, 1), BLACK));
        System.out.println(board.isValid(new Move(6, 1), BLACK));
    }

    public static void test2() {
        GameBoard board = new GameBoard();
        board.add(3, 1, BLACK);
        board.add(1, 4, BLACK);
        System.out.println(board.blackChips()[1][1]);
        board.add(6, 1, BLACK);
        board.add(1, 1, BLACK);
        board.add(3, 3, BLACK);
        board.add(3, 4, BLACK);
        board.add(3, 6, BLACK);
        board.add(5, 5, BLACK);
        board.add(5, 4, BLACK);
        board.add(6, 0, BLACK);
        System.out.println(board);
        System.out.println(board.blackChips()[1][1]);

        board.add(3, 2, WHITE);
        board.add(6, 6, WHITE);
        board.add(6, 2, WHITE);
        board.add(1, 2, WHITE);
        board.add(3, 5, WHITE);
        board.add(3, 1, WHITE);
        board.add(0, 3, WHITE);
        board.add(1, 6, WHITE);
        board.add(6, 4, WHITE);
        board.add(7, 6, WHITE);
        System.out.println(board.checkColor(BLACK));
        System.out.println(board.checkColor(WHITE));

        System.out.println(board.isValid(new Move(1, 1, 7, 6), WHITE));
        System.out.println(board.isValid(new Move(0, 5, 7, 6), WHITE));
        System.out.println(board);
        System.out.println(board.totalMoves(BLACK));
        System.out.println(board.totalMoves(WHITE));
    }

    public static void test5() {
        GameBoard board = new GameBoard();
        board.add(1, 0, BLACK);
        board.add(3, 4, BLACK);
        board.add(1, 6, BLACK);
        board.add(6, 4, BLACK);
        board.add(4, 6, BLACK);
        board.add(4, 7, BLACK);
        board.add(0, 1, WHITE);
        board.add(6, 1, WHITE);
        board.add(4, 5, WHITE);
        board.add(7, 4, WHITE);
        board.add(2, 2, WHITE);
        board.add(2, 3, WHITE);
        System.out.println(board.anyNetwork(BLACK));
        System.out.println(board.minimax(BLACK, -10000, 10000, 1).move);
        System.out.println(board);
    }

    public static void main(String[] args) {
        test5();
        //test2();
        GameBoard board = new GameBoard();
        /*
        System.out.println(board.minimax(BLACK, -100000, 100000, 2));
        */
        /*
        System.out.println(board.isValid(new Move(0, 0), BLACK));
        board.add(3, 4, BLACK);
        board.add(5, 4, BLACK);
        board.add(2, 5, BLACK);
        board.add(1, 7, BLACK);
        board.add(2, 0, BLACK);
        board.add(5, 0, BLACK);
        board.add(3, 2, BLACK);
        board.add(1, 2, BLACK);
        board.add(6, 6, BLACK);
        board.add(1, 1, WHITE);
        board.add(1, 4, WHITE);
        *
        System.out.println(board.connectionChip(1, 2));
        System.out.println(board.isValid(new Move(6, 0), BLACK));
        System.out.println(board);
        System.out.println(board.totalMoves(BLACK));
        */
        /*
        board.add(2, 0, BLACK);
        board.add(4, 2, BLACK);
        board.add(3, 3, BLACK);
        board.add(3, 6, BLACK);
        board.add(4, 7, BLACK);
        */
        /*
        board.add(2, 0, BLACK);
        board.add(3, 4, BLACK);
        board.add(1, 6, BLACK);
        //board.add(3, 1, BLACK);
        board.add(2, 6, BLACK);
        board.add(4, 6, BLACK);

        System.out.println(board);
        System.out.println(board.connectionNum(BLACK));
        System.out.println("board.blackNetwork()");
        System.out.println(board.blackNetwork());
        System.out.println(board.evaluation());
        System.out.println(board);
        System.out.println(board.totalMoves(0));
        System.out.println("minimax");
        System.out.println(board.minimax(BLACK, -1000, 1000, 1));
        System.out.println("++++++++++++++++");
        System.out.println(board.longestConn(BLACK));
        System.out.println(board.connectionChip(2, 0));
        System.out.println(board.maxLengthPath(null, new Chip(2, 0, 0), new Chip(4, 7, 0), new SList()));
        /*
        board.add(1, 5, WHITE);
        board.add(1, 2, BLACK);
        board.add(4, 5, WHITE);
        board.add(3, 0, BLACK);
        board.add(0, 6, WHITE);
        board.add(1, 3, BLACK);
        board.add(6, 6, WHITE);
        board.add(1, 1, WHITE);
        board.add(3, 1, WHITE);
        board.add(5, 1, WHITE);
        board.add(3, 3, WHITE);
        board.add(5, 3, WHITE);
        board.add(6, 5, WHITE);
        */
        /*
        board.add(2, 2, WHITE);
        board.add(1, 7, BLACK);
        board.add(5, 1, WHITE);
        board.add(1, 1, BLACK);
        board.add(2, 4, WHITE);
        board.add(2, 1, BLACK);
        board.add(4, 4, WHITE);
        */
        /*
        board.add(1,1,BLACK);
        board.add(2,1,BLACK);
        board.add(4,1,BLACK);
        board.add(5,1,BLACK);
        board.add(1,6,BLACK);
        board.add(0,2,WHITE);
        board.add(1,2,WHITE);
        board.add(1,5,WHITE);
        board.add(4,2,WHITE);
        board.add(4,5,WHITE);
        board.add(7,1,WHITE);
        board.add(7,2,WHITE);

        //System.out.println(board.isValid(new Move(4, 4), BLACK));
        System.out.println(board);
        System.out.println(board.totalMoves(1));
        System.out.println(board.maxLengthPath(null, new Chip(0, 2, 0), new Chip(7, 2, 0), new SList()));
        System.out.println(board.whiteNetwork());
        System.out.println(board.connectionChip(7, 2));
        System.out.println(board.minimax(1, -10000, 10000, 3));
        */
    }

}
