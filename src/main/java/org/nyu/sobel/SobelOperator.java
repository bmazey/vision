package main.java.org.nyu.sobel;

import main.java.org.nyu.gaussian.GaussianMask;

public class SobelOperator {

    private SobelMask mask = new SobelMask();
    private GaussianMask gmask = new GaussianMask();
    private int padding = mask.getMaskPadding() + gmask.getMaskPadding();

    public double[][] computeGradientMagnitude(int[][] image) {

        int[][] Gx = mask.getGx();
        int[][] Gy = mask.getGy();

        double[][] gradient = new double[image.length][image[0].length];

        // TODO - compute padding, similar to GaussianSmoother. 1 comes from the sobel masks, 3 from gaussian
        // TODO - split into two different functions for Gx image and Gy image!


        for(int i = padding; i < image.length - padding; i++) {
            for(int j = padding; j < image[i].length - padding; j++) {

                int xsum = 0;

                for(int k = 0; k < Gx.length; k++) {
                    for (int l = 0; l < Gx[k].length; l++) {
                        int xshift = k - 1;
                        int yshift = l - 1;
                        xsum += image[i + xshift][j + yshift] * Gx[k][l];
                    }
                }

                int ysum = 0;

                for(int m = 0; m < Gy.length; m++) {
                    for(int n = 0; n < Gy[m].length; n++) {
                        int xshift = m - 1;
                        int yshift = n - 1;
                        ysum += image[i + xshift][j + yshift] * Gy[m][n];
                    }
                }

                gradient[i][j] = Math.sqrt((xsum * xsum) + (ysum * ysum));
            }
        }

        return gradient;
    }

    public double[][] computeGxGradientMagnitude(int[][] image) {

        int[][] Gx = mask.getGx();

        double[][] gradient = new double[image.length][image[0].length];

        // TODO - compute padding, similar to GaussianSmoother. 1 comes from the sobel masks, 3 from gaussian
        // TODO - split into two different functions for Gx image and Gy image!

        for(int i = padding; i < image.length - padding; i++) {
            for(int j = padding; j < image[i].length - padding; j++) {

                int sum = 0;

                for (int k = 0; k < Gx.length; k++) {
                    for (int l = 0; l < Gx[k].length; l++) {
                        int xshift = k - 1;
                        int yshift = l - 1;
                        sum += image[i + xshift][j + yshift] * Gx[k][l];
                    }
                }

                gradient[i][j] = sum;
            }
        }

        return gradient;
    }

    public double[][] computeGyGradientMagnitude(int[][] image) {

        int[][] Gy = mask.getGy();

        double[][] gradient = new double[image.length][image[0].length];

        // TODO - compute padding, similar to GaussianSmoother. 1 comes from the sobel masks, 3 from gaussian
        // TODO - split into two different functions for Gx image and Gy image!

        for(int i = padding; i < image.length - padding; i++) {
            for(int j = padding; j < image[i].length - padding; j++) {

                int sum = 0;

                for (int k = 0; k < Gy.length; k++) {
                    for (int l = 0; l < Gy[k].length; l++) {
                        int xshift = k - 1;
                        int yshift = l - 1;
                        sum += image[i + xshift][j + yshift] * Gy[k][l];
                    }
                }

                gradient[i][j] = sum;
            }
        }

        return gradient;
    }

    public int[][] normalizeGradientImage(double[][] image) {

        int[][] normalized = new int[image.length][image[0].length];

        for(int i = 0; i < image.length; i++) {
            for(int j = 0; j < image[i].length; j++) {

                int normal = (int)Math.round(Math.abs(image[i][j]));
                // assignment says to use the below, but this just appears to make the image darker ...
                // int normal = (int)Math.round(Math.abs(image[i][j]) / 4);

                normalized[i][j] = normal;
            }
        }

        return normalized;
    }
}
