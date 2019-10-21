package main.java.org.nyu.nonmaxima;

public class NonMaximaSuppressor {

    // for reference only
    private final int[][] window = {
            {3, 2, 1},
            {0, 0, 0},
            {1, 2, 3}
    };

    public int[][] suppress(double[][] image, double[][] gximage, double[][] gyimage) {

        assert gximage.length == gyimage.length && gximage[0].length == gyimage[0].length;

        int[][] suppressed = new int[image.length][image[0].length];

        // TODO compute padding: 3 from gaussian, 1 from sobel
        for (int i = 3 + 1 + 1; i < image.length - (3 + 1 + 1); i++) {
            for(int j = 3 + 1 + 1; j < image[i].length - (3 + 1 + 1); j++) {

                // compute arc tangent - don't forget to convert to degrees!
                double angle;

                if(gyimage[i][j] != 0 && gximage[i][j] == 0) angle = 90.00;
                else if(gyimage[i][j] == 0 && gximage[i][j] == 0) angle = 0.00;
                else angle = Math.toDegrees(Math.atan(gyimage[i][j] / gximage[i][j]));

                // detect sector
                int sector = detectSector(angle);

                // thin magnitude image using window
                if(sector == 1) {
                    if(image[i][j] > image[i - 1][j + 1] && image[i][j] > image[i + 1][j - 1]) {
                        suppressed[i][j] = (int)Math.round(image[i][j]);
                    }
                    else suppressed[i][j] = 0;
                }

                if(sector == 2) {
                    if(image[i][j] > image[i - 1][j] && image[i][j] > image[i + 1][j]) {
                        suppressed[i][j] = (int)Math.round(image[i][j]);
                    }
                    else suppressed[i][j] = 0;
                }

                if(sector == 3) {
                    if(image[i][j] > image[i - 1][j - 1] && image[i][j] > image[i + 1][j + 1]) {
                        suppressed[i][j] = (int)Math.round(image[i][j]);
                    }
                    else suppressed[i][j] = 0;
                }

                // assume sector 0
                else {
                    if(image[i][j] > image[i][j - 1] && image[i][j] > image[i][j + 1]) {
                        suppressed[i][j] = (int)Math.round(image[i][j]);
                    }
                    else suppressed[i][j] = 0;
                }
            }
        }

        return suppressed;
    }

    public int detectSector(double angle) {
        // convert negative angle to 360 scale
        if (angle < 0) angle = angle + 360;

        // sector cases
        int sector = -1;

        // don't need to check for sector 0 - assume it's in sector 0 unless otherwise
        if((angle >= 0 && angle < 22.5) ||
                (angle >= 157.5 && angle < 202.5) || (angle >= 337.5 && angle <= 360)) sector = 0;
        if((angle >= 22.5 && angle < 67.5) || (angle >= 202.5 && angle < 247.5)) sector = 1;
        if((angle >= 67.5 && angle < 112.5) || (angle >= 247.5 && angle < 292.5)) sector = 2;
        if((angle >= 112.5 && angle < 157.5) || (angle >= 292.5 && angle < 337.5)) sector = 3;

        if (sector == -1) System.out.println("ERROR!");

        return sector;
    }
}
