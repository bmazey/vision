package main.java.org.nyu.gaussian;

public class GaussianMask {

    // this 7 x 7 static array will act as our gaussian mask
    private final int[][] mask = {
            {1, 1, 2, 2, 2, 1, 1},
            {1, 2, 2, 4, 2, 2, 1},
            {2, 2, 4, 8, 4, 2, 2},
            {2, 4, 8, 16, 8, 4, 2},
            {2, 2, 4, 8, 4, 2, 2},
            {1, 2, 2, 4, 2, 2, 1},
            {1, 1, 2, 2, 2, 1, 1}
    };

    // getter only as array is final
    public int[][] getMask() {
        return this.mask;
    }
}
