package main.java.org.nyu.threshold;

public class DoubleThresholder {

    public int[][] threshold(int[][] image, double[][] gradient, double[][] theta, int upper, int lower) {

        int[][] threshold = new int[image.length][image[0].length];

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

                }

            }
        }

        return threshold;
    }
}
