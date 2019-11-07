package main.java.org.nyu.gaussian;

public class GaussianSmoother {

    private GaussianMask mask = new GaussianMask();
    private int padding = mask.getMaskPadding();

    public int[][] smooth(int[][] image) {
        // here we'll apply the gaussian smoothing technique to reduce the noise in our image.
        // get the mask
        int[][] mask = this.mask.getMask();

        // let's start by creating a new array which will contain our filtered image
        int[][] filtered = new int[image.length][image[0].length];

        // iterate over the image and apply the convolution operation
        // TODO - starting at 3 because of size of mask, change to computation
        for(int i = padding; i < image.length - padding; i++) {
            for(int j = padding; j < image[i].length - padding; j++) {

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
                // sum of mask should be 140
                filtered[i][j] = Math.round(sum / this.mask.getSum());
            }
        }

        return filtered;
    }
}
