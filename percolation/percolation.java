import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // create N-by-N grid, with all sites blocked
    private boolean[][] grid;
    private int size;
    private WeightedQuickUnionUF wQF;
    private int top = 0;
    private int bottom;
    
    public Percolation(int N) {
        size = N;
        grid = new boolean[N][N];
        bottom = size * size + 1;
        wQF = new WeightedQuickUnionUF(size * size + 2);
    }     
    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        if (i > size || j > size || i < 1 || j < 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        grid[i - 1][j - 1] = true;
        if (i == 1) {
            wQF.union(getWQFIndex(i, j), top);
        }
        if (i == size) {
            wQF.union(getWQFIndex(i, j), bottom);
        }

        if (j > 1 && isOpen(i, j - 1)) {
            wQF.union(getWQFIndex(i, j), getWQFIndex(i, j - 1));
        }
        if (j < size && isOpen(i, j + 1)) {
            wQF.union(getWQFIndex(i, j), getWQFIndex(i, j + 1));
        }
        if (i > 1 && isOpen(i - 1, j)) {
            wQF.union(getWQFIndex(i, j), getWQFIndex(i - 1, j));
        }
        if (i < size && isOpen(i + 1, j)) {
            wQF.union(getWQFIndex(i, j), getWQFIndex(i + 1, j));
        }
    }      
       
   // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        if (0 < i && i <= size && 0 < j && j <= size) {
            return grid[i - 1][j - 1];
        } else {
            throw new java.lang.IndexOutOfBoundsException();
        }
        
    }     
    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        if (0 < i && i <= size && 0 < j && j <= size) {
            return wQF.connected(top, getWQFIndex(i , j));
        } else {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }    
    // does the system percolate?
    public boolean percolates() {
        return wQF.connected(top, bottom);
    }            
    
    private int getWQFIndex(int i, int j) {
        return size * (i - 1) + j;
    }
}
