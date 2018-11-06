import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private boolean[] BN;
    private int size;
    private WeightedQuickUnionUF grid;
    private WeightedQuickUnionUF grid2;
    private int upNode;
    private int downNode;
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        this.N = N;
        BN = new boolean[N*N];
        size = 0;

        if (N <= 0){
            throw new IllegalArgumentException("N should be larger than 0!");
        }

        this.upNode = N*N;
        this.downNode = N*N+1;

        /** Create a grid with 2 virtual node */
        grid = new WeightedQuickUnionUF(N*N+2);
        grid2 = new WeightedQuickUnionUF(N*N+2);

        /** union the upNode with the row 1 and union the downNode with the row N */
        for (int i = 0; i < N; i +=1){
            grid.union(upNode,i);
            grid.union(downNode,N*N-1-i);
            grid2.union(upNode,i);

        }

    }

    // calculated the index from row and col

    private int rowcolToIndex (int row, int col){
        return (N*(row-1)+col-1);
    }

    private boolean isIllegal (int row, int col){
        if ( row < 1 || row > N || col < 1 || col > N) {
            return true;
        }
        return false;
    }



    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        if (isIllegal(row,col)){
            throw new IllegalArgumentException("Row or col is illegal");
        }
        int index = rowcolToIndex(row,col);
        if (BN[index]){
            return;
        }
        BN[index] = true;
        size += 1;

        /** union up down left and right for the index */
        if (row-1>0){
            int indexUp = rowcolToIndex(row-1,col);
            if (BN[indexUp]){
                grid.union(index,indexUp);
                grid2.union(index,indexUp);
            }
        }
        if (row+1<=N){
            int indexDown = rowcolToIndex(row+1,col);
            if (BN[indexDown]){
                grid.union(index,indexDown);
                grid2.union(index,indexDown);
            }
        }
        if (col-1>0){
            int indexLeft = rowcolToIndex(row,col-1);
            if (BN[indexLeft]){
                grid.union(index,indexLeft);
                grid2.union(index,indexLeft);
            }
        }
        if (col+1<=N){
            int indexRight = rowcolToIndex(row,col+1);
            if (BN[indexRight]){
                grid.union(index,indexRight);
                grid2.union(index,indexRight);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (isIllegal(row,col)){
            throw new IllegalArgumentException("Row or col is illegal");
        }
        int index = rowcolToIndex(row,col);
        if (BN[index]) {
            return true;
        }
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if (isIllegal(row,col)){
            throw new IllegalArgumentException("Row or col is illegal");
        }
        int index = rowcolToIndex(row,col);
        if (BN[index]){
            return grid2.connected(index,upNode);
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites(){
        return size;
    }

    // does the system percolate?
    public boolean percolates(){
        if (size > 0) {
            return grid.connected(upNode, downNode);
        }
        return false;
    }
    // use for unit testing (not required)
    public static void main(String[] args){
        Percolation testModel = new Percolation(200);
        testModel.open(1,1);
        testModel.open(1,2);
        testModel.open(2,2);
        testModel.open(3,2);
        System.out.println(testModel.percolates());
        System.out.println(testModel.numberOfOpenSites());
        testModel.open(4,2);
        System.out.println(testModel.percolates());
        testModel.open(5,2);
        testModel.open(5,2);
        System.out.println(testModel.percolates());
        testModel.open(4,2);
        System.out.println(testModel.numberOfOpenSites());
        System.out.println(testModel.percolates());
    }

}
