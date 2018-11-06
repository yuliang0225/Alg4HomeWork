import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int T;
    private double[] x;
    private double meanValue;
    private double stdValu;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T){
        /** deal with exceptions. */
        if (N <= 0){
            throw new IllegalArgumentException("N should larger than 1!");
        }
        if ( T<= 0){
            throw new IllegalArgumentException("T should larger than 1!");
        }
        /** pass parameters. */
        this.T = T;
        x = new double[T];

        /** create a new percolation system (N*N) and do the test (T). */

        for (int i = 0; i< T; i +=1){
            Percolation grid = new Percolation(N);
            int j = 0;
            while (!grid.percolates()){
                int row = StdRandom.uniform(N)+1;
                int col = StdRandom.uniform(N)+1;
                if (!grid.isOpen(row,col)){
                    grid.open(row,col);
                    j += 1;
                }
            }
            x[i] = (double)j/(N*N);
        }

        meanValue = StdStats.mean(x);
        stdValu = StdStats.stddev(x);


    }

    // sample mean of percolation threshold
    public double mean(){
        return (meanValue);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return (stdValu);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo(){
        return (mean()-1.96*stddev()/Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return (mean()+1.96*stddev()/Math.sqrt(T));
    }

    public static void main(String[] args){
         int inputN;
         int inputT;
         inputN = Integer.parseInt(args[0]);
         inputT = Integer.parseInt(args[1]);
         PercolationStats temp = new PercolationStats (inputN,inputT);
//         PercolationStats temp = new PercolationStats (42,-1);
         System.out.printf("mean \t = %f \n",temp.mean());
         System.out.printf("stddev \t = %f \n",temp.stddev());
         System.out.printf("95%% confidence interval = [%f, %f] \n",temp.confidenceLo(),temp.confidenceHi());
       }

}
