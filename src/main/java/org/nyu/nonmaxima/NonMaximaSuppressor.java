package main.java.org.nyu.nonmaxima;

public class NonMaximaSuppressor {

    private final int[][] window = {
            {3, 2, 1},
            {0, 0, 0},
            {1, 2, 3}
    };

    public int[][] suppress(double[][] gximage, double[][] gyimage) {

        // ensure both arrays are of same dimensions
        assert gximage.length == gyimage.length && gximage[0].length == gyimage[0].length;

        int[][] suppressed = new int[gximage.length][gximage[0].length];

        // TODO compute padding: 3 from gaussian, 1 from sobel, and 1 from non-maxima
        for (int i = 3 + 1 + 1; i < gximage.length - (3 + 1 + 1); i++) {
            for(int j = 3 + 1 + 1; j < gximage.length - (3 + 1 + 1); j++) {

                // compute arc tangent
                double angle = Math.atan(Math
                        .sqrt((gximage[i][j] * gximage[i][j]) + (gyimage[i][j] * gyimage[i][j])));

                // detect sector
                int sector = detectSector(angle);

                // thin magnitude image using window

            }
        }

        return suppressed;
    }

    public int detectSector(double angle) {
        // convert negative angle to 360 scale
        if (angle < 0) angle += 360;

        // sector cases
        int sector = 0;

        // don't need to check for sector 0 - assume it's in sector 0 unless otherwise
        // if((angle >= 0 && angle < 22.5) ||
                // (angle >= 157.5 && angle < 202.5) || (angle >= 337.5 && angle <= 360)) sector = 0;

        // magic numbers
        if((angle >= 22.5 && angle < 67.5) || (angle >= 202.5 && angle < 247.5)) sector = 1;
        if((angle >= 67.5 && angle < 112.5) || (angle >= 247.5 && angle < 292.5)) sector = 2;
        if((angle >= 112.5 && angle < 157.5) || (angle >= 292.5 && angle < 337.5)) sector = 3;

        return sector;
    }
}
