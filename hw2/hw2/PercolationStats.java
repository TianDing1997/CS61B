package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;


public class PercolationStats {
    private Percolation p;
    private int Time;
    private double[] xt;

    public PercolationStats(int N, int T, PercolationFactory pf){
        if(N<=0 || T<=0)  throw new java.lang.IllegalArgumentException();
        else{
            this.Time = T;
            xt = new double[Time];
            for(int i = 0; i<T; i++){
                p = pf.make(N);
                while(!p.percolates()){
                    int row = StdRandom.uniform(N);
                    int col = StdRandom.uniform(N);
                    p.open(row,col);
                }
                xt[i]  = ((double)p.numberOfOpenSites())/((double)N*N);
            }
        }
    }   // perform T independent experiments on an N-by-N grid
    public double mean(){
        return StdStats.mean(xt);
    }                                           // sample mean of percolation threshold
    public double stddev(){
        return StdStats.stddev(xt);
    }                                      // sample standard deviation of percolation threshold
    public double confidenceLow(){
        return this.mean() - ((1.96*this.stddev())/Math.sqrt((double)this.Time));
    }                             // low endpoint of 95% confidence interval
    public double confidenceHigh(){
        return this.mean() + ((1.96*this.stddev())/Math.sqrt((double)this.Time));
    }

    public static void main(String[] args){
        PercolationFactory PF = new PercolationFactory();
        Stopwatch s = new Stopwatch();
        PercolationStats PS = new PercolationStats(100,30000, PF);
        System.out.println("run time: "+s.elapsedTime());
        System.out.println(PS.mean());
        System.out.println(PS.stddev());
        System.out.println(PS.confidenceHigh());
        System.out.println(PS.confidenceLow());
    }// high endpoint of 95% confidence interval
}
