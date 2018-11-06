
import java.util.Arrays;
import edu.princeton.cs.algs4.Queue;

public class Board {
    private int N;
    private int[] BoardState;
    private int blankRow;
    private int blankCol;
    private int block;

    /** Board(tiles): Constructs a board from an N-by-N array of tiles where
     * tiles[i][j] = tile at row i, column j */

    public Board(int[][] tiles){
        N = tiles.length;
        BoardState = reshapeArray(tiles);
        for (int i = 0; i < N*N; i += 1){
            if (BoardState[i] == 0){
                block = i;
                break;
            }
        }
        blankRow = block / N;
        blankCol = block % N;
    }

    private Board(int[] tiles){
        N = (int) Math.sqrt(tiles.length);
        BoardState = copyBlocks(tiles);
        for (int i = 0; i < N*N; i += 1){
            if (BoardState[i] == 0){
                block = i;
                break;
            }
        }
        blankRow = block / N;
        blankCol = block % N;
    }


    /** Copy the array */
    private int[] copyBlocks(int[] source) {
        int[] copy = new int[N*N];
        System.arraycopy(source, 0, copy, 0, N*N);
        return copy;
    }

    /** Reshape 2D array to 1D */
    private int[] reshapeArray (int[][] source){
        int[] reshapedArray = new int[N*N];
        for (int i = 0; i < N; i += 1){
            System.arraycopy(source[i], 0, reshapedArray, i*N, N);
        }
        return reshapedArray;
    }

    /** tileAt(i, j): Returns value of tile at row i, column j (or 0 if blank) */
    private int tileAt(int i, int j){
        return BoardState[N*i+j];
    }

    /** board dimension N */
    public int dimension(){
        return N;
    }

    /** Swap the element between i,j and newi,newj. */
    private int[] swap(int[] array, int i, int j, int newi, int newj){
        int[] returnArray;
        returnArray = copyBlocks(array);
        returnArray [N*newi+newj] = array[i*N+j];
        returnArray [N*i+j] = array[newi*N+newj];
        return returnArray;
    }

    /** a board that is obtained by exchanging any pair of blocks */
    public Board twin(){
        int iswap = 0;
        int jswap = 1;
        int[] returnArray = copyBlocks(BoardState);
        if (iswap == block){
            iswap = 2;
        }
        if (jswap == block){
            jswap = 2;
        }
        returnArray[iswap] = BoardState[jswap];
        returnArray[jswap] = BoardState[iswap];
        return new Board(returnArray);
    }

    /** neighbors(): Returns the neighbors of the current board */
    public Iterable<Board> neighbors(){
        Queue<Board> neighbs = new Queue<>();

        if (blankRow > 0){
            neighbs.enqueue(new Board(swap(BoardState,blankRow,blankCol,blankRow-1,blankCol)));
        }
        if (blankRow < N-1){
            neighbs.enqueue(new Board(swap(BoardState,blankRow,blankCol,blankRow+1,blankCol)));
        }
        if (blankCol > 0){
            neighbs.enqueue(new Board(swap(BoardState,blankRow,blankCol,blankRow,blankCol-1)));
        }
        if (blankCol < N-1){
            neighbs.enqueue(new Board(swap(BoardState,blankRow,blankCol,blankRow,blankCol+1)));
        }
        return neighbs;
    }

    /** manhattan(): Manhattan estimate described below */
    public int manhattan(){
        int manhattan = 0;
        for (int i = 0; i < N; i += 1){
            for (int j = 0; j < N; j += 1){
                if(BoardState[i*N+j]!= 0){
                    manhattan += Math.abs(i - (BoardState[i*N+j]-1) / N) + Math.abs(j - (BoardState[i*N+j]-1) % N);
                }
            }
        }
        return manhattan;
    }

    public int hamming(){
        int count = 0;
        for (int i = 0; i < N; i += 1){
            for (int j = 0; j < N; j += 1){
                if(BoardState[i*N+j] != 0 && BoardState[i*N+j] != N*i + j + 1){
                    count += 1;
                }
            }
        }
        return count;
    }

    private int estimatedDistanceToGoal(){
        return manhattan();
    }

    public boolean isGoal() {
        return estimatedDistanceToGoal() == 0;
    }

    /** equals(y): Returns true if this board's tile values are the same position as y's */
    @Override
    public boolean equals(Object y){
        if (this == y) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (this.getClass() != y.getClass()) {
            return false;
        }
        Board that = (Board) y;
        return Arrays.equals(this.BoardState, that.BoardState);
    }

    /** toString(): Returns the string representation of the board.
     * Uncomment this method. */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = dimension();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
