package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private int top;
    private int bottom;
    private boolean[][] a;
    private WeightedQuickUnionUF UF;
    private int totalnumber;
    public Percolation(int N){
        if(N<=0) {throw new java.lang.IllegalArgumentException();}
        else{
            this.UF = new WeightedQuickUnionUF(N*N+2);
            this.N = N;
            top = N*N;
            bottom = N*N+1;
            this.totalnumber = 0;
            a = new boolean[N][N];

            for(int i = 0; i< N-1;i ++){
                for(int j = 0; j< N-1; j++){
                    a[i][j] = false;
                }
            }
        }
    }               // create N-by-N grid, with all sites initially blocked
    public void open(int row, int col){
        if(row<0||row>N-1||col<0||col>N-1){throw new java.lang.IndexOutOfBoundsException();}
        else{
            if(!this.isOpen(row,col)){
                a[row][col] = true;
                this.totalnumber++;
                if(row == 0){
                    UF.union(top,xyTo1D(row,col));
                }
                else if(row == N-1&&!percolates()){
                    UF.union(bottom, xyTo1D(row,col));
                }
                if(row-1 >= 0 && isOpen(row-1, col)){UF.union(xyTo1D(row,col),xyTo1D(row-1,col));}
                if(row+1 <= N-1 && isOpen(row+1,col)){UF.union(xyTo1D(row,col),xyTo1D(row+1,col));}
                if(col-1 >= 0 && isOpen(row, col-1)){UF.union(xyTo1D(row,col),xyTo1D(row,col-1));}
                if(col+1 <= N-1 && isOpen(row,col+1)){UF.union(xyTo1D(row,col),xyTo1D(row,col+1));}

            }
        }
    }       // open the site (row, col) if it is not open already
    public boolean isOpen(int row, int col){
        if(row<0||row>N-1||col<0||col>N-1){throw new java.lang.IndexOutOfBoundsException();}
        else{
            return a[row][col] == true;
        }
    }  // is the site (row, col) open?
    public boolean isFull(int row, int col){
        if(row<0||row>N-1||col<0||col>N-1){throw new java.lang.IndexOutOfBoundsException();}
        else{
            return (UF.find(top) == UF.find(xyTo1D(row, col)));
        }
    }  // is the site (row, col) full?
    public int numberOfOpenSites(){
        return this.totalnumber;
    }          // number of open sites
    public boolean percolates(){
        return (UF.find(top) == UF.find(bottom));
    }

    public int xyTo1D(int r, int c){
        return r*N+c;
    }// does the system percolate?
    public static void main(String[] args){

    }   // use for unit testing (not required, but keep this here for the autograder)
}
