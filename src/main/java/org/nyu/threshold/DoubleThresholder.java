package main.java.org.nyu.threshold;

public class DoubleThresholder {

    public int[][] threshold(int[][] image, double[][] gradient, double[][] theta, int upper, int lower) {

        int[][] threshold = new int[image.length][image[0].length];

        // padding of one because we'll need to check 8-connected neighbors
        for(int i = 1; i < image.length - 1; i++) {
            for(int j = 1; j < image[i].length - 1; j++) {

                if(image[i][j] < lower) {
                    threshold[i][j] = 0;
                }
                else if(image[i][j] > upper) {
                    threshold[i][j] = 255;
                }

                // TODO - final case for double threshold!
                else {
                    // assume 0
                    threshold[i][j] = 0;

                    // check neighbors
                    for(int m = 0; m < 3; m++) {
                        for (int n = 0; n < 3; n++) {

                            // skip center cell
                            if(m == 1 && n == 1) continue;

                            int xshift = m - 1;
                            int yshift = n - 1;

                            if((gradient[i + xshift][j + yshift] > upper)
                                    && (Math.abs(theta[i + xshift][j + yshift] - theta[i][j]) <= 45)) {
                                threshold[i][j] = 255;
                            }
                        }
                    }
                }
            }
        }

        return threshold;
    }
}
