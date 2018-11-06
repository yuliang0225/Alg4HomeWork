import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class EightPuzzleSolver {
    /***********************************************************************
     * Test routine for your Solver class. Uncomment and run to test
     * your basic functionality.
     **********************************************************************/
    public static void main(String[] args) {
//        In in = new In(args[0]);
        In in = new In("input/puzzle10.txt");
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        System.out.println("Data loading complete!");
        Board initial = new Board(tiles);
        System.out.println("Initial complete!");
        Solver solver = new Solver(initial);
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board ws : solver.solution()) {
            StdOut.println(ws);
        }
    }
}
