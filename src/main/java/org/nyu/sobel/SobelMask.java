package main.java.org.nyu.sobel;

public class SobelMask {

    private final int[][] Gx = {
            {-1, 0, 1},
            {-2, 0, 2},
            {-1, 0, 1}
    };

    private final int[][] Gy = {
            {1, 2, 1},
            {0, 0, 0,},
            {-1, -2, -1}
    };

    // getters only
    public int[][] getGx() {
        return this.Gx;
    }

    public int[][] getGy() {
        return this.Gy;
    }
}
