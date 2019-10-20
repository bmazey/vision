package main.java.org.nyu.gaussian;

public class GaussianSmoother {

    private GaussianMask mask = new GaussianMask();

    public int[][] smooth(int[][] image) {
        // here we'll apply the gaussian smoothing technique to reduce the noise in our image.
        // get the mask
        int[][] mask = this.mask.getMask();

        // let's start by creating a new array which will contain our filtered image
        // TODO - change resizing to computation
        int[][] filtered = new int[image.length][image[0].length];

        // iterate over the image and apply the convolution operation
        // TODO - starting at 3 because of size of mask, change to computation
        for(int i = 3; i < image.length - 3; i++) {
            for(int j = 3; j < image[i].length - 3; j++) {

                // this gives us image[i][j]
                int sum = 0;

                // inner loops iterate over mask
                for (int k = 0; k < mask.length; k++) {
                    for (int m = 0; m < mask[k].length; m++) {
                        int xshift = k - 3;
                        int yshift = m - 3;
                        sum += image[i + xshift][j + yshift] * mask[k][m];
                    }
                }
                // divide by sum of mask
                // TODO - should we round or cast?
                // TODO calculate sum of mask instead of using 140
                filtered[i][j] = Math.round(sum / 140);
            }
        }

        return filtered;
    }
}
