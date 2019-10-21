package main.java.org.nyu.threshold;

public class DoubleThresholder {

    public int[][] threshold(int[][] image, double[][] gradient, double[][] theta, int upper, int lower) {

        int[][] threshold = new int[image.length][image[0].length];

        for(int i = 0; i < image.length; i++) {
            for(int j = 0; j < image[i].length; j++) {

                if(image[i][j] < lower) {
                    threshold[i][j] = 0;
                }
                if(image[i][j] > upper) {
                    threshold[i][j] = 255;
                }


            }
        }

        return threshold;
    }
}
