package game2048;

import java.util.Formatter;
import java.util.Observable;


/**
 * The state of a game of 2048.
 *
 * @author TODO: YOUR NAME HERE
 */
public class Model extends Observable {
    /**
     * Current contents of the board.
     */
    private Board board;
    /**
     * Current score.
     */
    private int score;
    /**
     * Maximum score so far.  Updated when game ends.
     */
    private int maxScore;
    /**
     * True iff game is ended.
     */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /**
     * Largest piece value.
     */
    public static final int MAX_PIECE = 2048;

    /**
     * A new 2048 game on a board of size SIZE with no pieces
     * and score 0.
     */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /**
     * A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes.
     */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /**
     * Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     * 0 <= COL < size(). Returns null if there is no tile there.
     * Used for testing. Should be deprecated and removed.
     */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /**
     * Return the number of squares on one side of the board.
     * Used for testing. Should be deprecated and removed.
     */
    public int size() {
        return board.size();
    }

    /**
     * Return true iff the game is over (there are no moves, or
     * there is a tile with value 2048 on the board).
     */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /**
     * Return the current score.
     */
    public int score() {
        return score;
    }

    /**
     * Return the current maximum game score (updated at end of game).
     */
    public int maxScore() {
        return maxScore;
    }

    /**
     * Clear the board to empty and reset the score.
     */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /**
     * Add TILE to the board. There must be no Tile currently at the
     * same position.
     */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /**
     * self build helper function 1.
     * return true if two tile has the same value
     */
    public boolean compareTileValue(Tile a, Tile b) {
        if ((a == null) | (b == null)) {
            return false;
        } else if (a.value() == b.value()) {
            return true;
        }
        return false;
    }

    /**
     * self build helper function 2.
     * count how many null in a column
     */
    public int countNullinCol(int col) {
        int count = 0;
        for (int i = 0; i < board.size(); i++) {
            if (board.tile(col, i) == null) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * self build helper function 3.
     * tilt only one column col
     * two returns in an array
     * return a score of this tilt on the column col
     * return 1 if the column moves, otherwise return 0
     */
    public int[] singleColTilt(int col) {
        int addScore = 0;
        int moveBoolean = 0;
        Tile t0 = board.tile(col, 0);
        Tile t1 = board.tile(col, 1);
        Tile t2 = board.tile(col, 2);
        Tile t3 = board.tile(col, 3);

        if (countNullinCol(col) == 4) {
            // do nothing
        } else if (countNullinCol(col) == 3) {
            // only 1 tile has value, 3 are null
            for (int i = 0; i < board.size() - 1; i++) {
                Tile t = board.tile(col, i);
                if (t != null) {
                    board.move(col, 3, t);
                    moveBoolean = 1;
                }
            }

        } else if (countNullinCol(col) == 2) {
            // 2 tiles has value, 2 are null
            if ((t0 != null) & (t1 != null)) {
                // t0 and t1 has value;
                if (compareTileValue(t0, t1)) {
                    boolean b1 = board.move(col, 3, t1);
                    boolean b2 = board.move(col, 3, t0);
                    moveBoolean = 1;
                    if (b1 | b2) {
                        addScore += t0.value() * 2;
                    }
                } else {
                    board.move(col, 3, t1);
                    board.move(col, 2, t0);
                    moveBoolean = 1;
                }
            } else if ((t0 != null) & (t2 != null)) {
                if (compareTileValue(t0, t2)) {
                    boolean b1 = board.move(col, 3, t2);
                    boolean b2 = board.move(col, 3, t0);
                    moveBoolean = 1;
                    if (b1 | b2) {
                        addScore += t0.value() * 2;
                    }
                } else {
                    board.move(col, 3, t2);
                    board.move(col, 2, t0);
                    moveBoolean = 1;
                }
            } else if ((t0 != null) & (t3 != null)) {
                if (compareTileValue(t0, t3)) {
                    boolean b1 = board.move(col, 3, t0);
                    moveBoolean = 1;
                    if (b1) {
                        addScore += t0.value() * 2;
                    }
                } else {
                    board.move(col, 2, t0);
                    moveBoolean = 1;
                }
            } else if ((t1 != null) & (t2 != null)) {
                if (compareTileValue(t1, t2)) {
                    boolean b1 = board.move(col, 3, t2);
                    boolean b2 = board.move(col, 3, t1);
                    moveBoolean = 1;
                    if (b1 | b2) {
                        addScore += t1.value() * 2;
                    }
                } else {
                    board.move(col, 3, t2);
                    board.move(col, 2, t1);
                    moveBoolean = 1;
                }
            } else if ((t1 != null) & (t3 != null)) {
                if (compareTileValue(t1, t3)) {
                    boolean b1 = board.move(col, 3, t1);
                    moveBoolean = 1;
                    if (b1) {
                        addScore += t1.value() * 2;
                    }
                } else {
                    board.move(col, 2, t1);
                    moveBoolean = 1;
                }
            } else if ((t2 != null) & (t3 != null)) {
                if (compareTileValue(t2, t3)) {
                    boolean b1 = board.move(col, 3, t2);
                    moveBoolean = 1;
                    if (b1) {
                        addScore += t2.value() * 2;
                    }
                }
            } else {
                System.out.println("2 tiles not null, have some issue");
            }

        } else if (countNullinCol(col) == 1) {
            // 3 tiles has value and 1 tile is null
            if ((t0 != null) & (t1 != null) & (t2 != null)) {
                if (compareTileValue(t2, t1)) {
                    boolean b1 = board.move(col, 3, t2);
                    boolean b2 = board.move(col, 3, t1);
                    board.move(col, 2, t0);
                    moveBoolean = 1;
                    if (b1 | b2) {
                        addScore += t2.value() * 2;
                    }
                } else if (compareTileValue(t1, t0)) {
                    board.move(col, 3, t2);
                    boolean b2 = board.move(col, 2, t1);
                    boolean b3 = board.move(col, 2, t0);
                    moveBoolean = 1;
                    if (b2 | b3) {
                        addScore += t1.value() * 2;
                    }
                } else {
                    board.move(col, 3, t2);
                    board.move(col, 2, t1);
                    board.move(col, 1, t0);
                    moveBoolean = 1;
                }
            } else if ((t0 != null) & (t1 != null) & (t3 != null)) {
                if (compareTileValue(t3, t1)) {
                    boolean b1 = board.move(col, 3, t1);
                    board.move(col, 2, t0);
                    moveBoolean = 1;
                    if (b1) {
                        addScore += t1.value() * 2;
                    }
                } else if (compareTileValue(t1, t0)) {
                    boolean b1 = board.move(col, 2, t1);
                    boolean b2 = board.move(col, 2, t0);
                    moveBoolean = 1;
                    if (b1 | b2) {
                        addScore += t1.value() * 2;
                    }
                } else {
                    board.move(col, 2, t1);
                    board.move(col, 1, t0);
                }
            } else if ((t0 != null) & (t2 != null) & (t3 != null)) {
                if (compareTileValue(t3, t2)) {
                    boolean b1 = board.move(col, 3, t2);
                    board.move(col, 2, t0);
                    moveBoolean = 1;
                    if (b1) {
                        addScore += t2.value() * 2;
                    }
                } else if (compareTileValue(t2, t0)) {
                    boolean b1 = board.move(col, 2, t0);
                    moveBoolean = 1;
                    if (b1) {
                        addScore += t0.value() * 2;
                    }
                } else {
                    board.move(col, 1, t0);
                    moveBoolean = 1;
                }
            } else if ((t1 != null) & (t2 != null) & (t3 != null)) {
                if (compareTileValue(t3, t2)) {
                    boolean b1 = board.move(col, 3, t2);
                    board.move(col, 2, t1);
                    moveBoolean = 1;
                    if (b1) {
                        addScore += t2.value() * 2;
                    }
                } else if (compareTileValue(t2, t1)) {
                    boolean b1 = board.move(col, 2, t1);
                    moveBoolean = 1;
                    if (b1) {
                        addScore += t1.value() * 2;
                    }
                }
            } else {
                System.out.println("3 tiles not null, have some issue");
            }

        } else if (countNullinCol(col) == 0) {
            if (compareTileValue(t3, t2)) {
                if (compareTileValue(t1, t0)) {
                    boolean b1 = board.move(col, 3, t2);
                    boolean b2 = board.move(col, 2, t1);
                    boolean b3 = board.move(col, 2, t0);
                    moveBoolean = 1;
                    if (b1) {
                        addScore += t2.value() * 2;
                    }
                    if (b2 | b3) {
                        addScore += t0.value() * 2;
                    }
                } else {
                    boolean b1 = board.move(col, 3, t2);
                    board.move(col, 2, t1);
                    board.move(col, 1, t0);
                    moveBoolean = 1;
                    if (b1) {
                        addScore += t2.value() * 2;
                    }
                }
            } else if (compareTileValue(t2, t1)) {
                boolean b1 = board.move(col, 2, t1);
                board.move(col, 1, t0);
                moveBoolean = 1;
                if (b1) {
                    addScore += t1.value() * 2;
                }
            } else if (compareTileValue(t1, t0)) {
                boolean b1 = board.move(col, 1, t0);
                moveBoolean = 1;
                if (b1) {
                    addScore += t0.value() * 2;
                }
            }

        } else {
            System.out.println("Board is not 4 * 4");
        }

        return new int[]{addScore, moveBoolean};
    }

    /**
     * Tilt the board toward SIDE. Return true iff this changes the board.
     * <p>
     * 1. If two Tile objects are adjacent in the direction of motion and have
     * the same value, they are merged into one Tile of twice the original
     * value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     * tilt. So each move, every tile will only ever be part of at most one
     * merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     * value, then the leading two tiles in the direction of motion merge,
     * and the trailing tile does not.
     */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        board.setViewingPerspective(side);
        for (int i = 0; i < board.size(); i++) {
            int[] colMove = singleColTilt(i);
            if (colMove[1] == 1) {
                changed = true;
            }
            score += colMove[0];
        }

        board.setViewingPerspective(Side.NORTH);

        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    /**
     * Checks if the game is over and sets the gameOver variable
     * appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /**
     * Determine whether game is over.
     */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /**
     * Returns true if at least one space on the Board is empty.
     * Empty spaces are stored as null.
     */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (b.tile(i, j) == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // TODO: Fill in this function.
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (b.tile(i, j) == null) {
                    continue;
                } else if (b.tile(i, j).value() == MAX_PIECE) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // TODO: Fill in this function.
        if (emptySpaceExists(b)) {
            return true;
        } else {
            for (int i = 0; i < b.size(); i++) {
                for (int j = 0; j < b.size(); j++) {
                    if ((j + 1 < b.size()) & (i + 1 < b.size())) {
                        if ((b.tile(i, j).value() == b.tile(i, j + 1).value()) | (b.tile(i, j).value() == b.tile(i + 1, j).value())) {
                            return true;
                        }
                    } else if ((j == b.size() - 1) & (i < b.size() - 1)) {
                        if (b.tile(i, j).value() == b.tile(i + 1, j).value()) {
                            return true;
                        }
                    } else if ((i == b.size() - 1) & (j < b.size() - 1)) {
                        if (b.tile(i, j).value() == b.tile(i, j + 1).value()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    @Override
    /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
