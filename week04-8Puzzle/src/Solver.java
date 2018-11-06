import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private Stack<Board> solution;
    private boolean isSolvable;

    private class searchNode implements Comparable<searchNode> {
        private Board board;
        private int moves;
        private searchNode preNode;
        private int dis;

        private searchNode(Board board, int moves, searchNode preNode) {
            this.board = board;
            this.moves = moves;
            this.preNode = preNode;
            this.dis = moves + board.manhattan();
        }


        public int moves(){
            return moves;
        }

        public Board board(){
            return board;
        }

        public searchNode preNode(){
            return preNode;
        }

        @Override
        public int compareTo(searchNode that){
            if (this.dis > that.dis){
                return 1;
            }
            if (this.dis < that.dis){
                return -1;
            }
            return 0;
        }
    }

    /**Solver(initial): Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.*/

    public Solver(Board initial){
        if (initial == null){
            throw new java.lang.IllegalArgumentException("Initial array can not be null!");
        }

        solution = new Stack<>();
        MinPQ<searchNode> minPQ = new MinPQ<>();
        MinPQ<searchNode> twinMinPQ = new MinPQ<>();

        searchNode node = new searchNode(initial,0,null);
        minPQ.insert(node);

        searchNode twinNode = new searchNode(initial.twin(),0,null);
        twinMinPQ.insert(twinNode);

        while (true){
            node = minPQ.delMin();
            twinNode = twinMinPQ.delMin();

            if (node.board().isGoal() || twinNode.board().isGoal()){
                break;
            }

            for (Board ws : node.board().neighbors()){
                searchNode next = new searchNode(ws,node.moves()+1,node);
                if (node.preNode()==null || !ws.equals(node.preNode().board())){
                    minPQ.insert(next);
                }
            }
            for (Board ws : twinNode.board().neighbors()){
                searchNode next = new searchNode(ws,twinNode.moves()+1,twinNode);
                if (twinNode.preNode()==null || !ws.equals(twinNode.preNode().board())){
                    twinMinPQ.insert(next);
                }
            }
        }

        isSolvable = node.board().isGoal();

        if (isSolvable()) {
            while (node != null) {
                solution.push(node.board());
                node = node.preNode();
            }
        }
    }


    /**moves(): Returns the minimum number of moves to solve the puzzle starting
     * at the initial WorldState.*/

    public int moves(){
        return solution.size()-1;
    }

    /**solution(): Returns a sequence of WorldStates from the initial WorldState
     * to the solution. */

    public Iterable<Board> solution(){
        return solution;
    }

    /** is the initial board solvable? */
    public boolean isSolvable(){
        return isSolvable;
    }

}