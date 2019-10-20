package main.java.org.nyu;

import main.java.org.nyu.gaussian.GaussianSmoother;
import main.java.org.nyu.image.ImageLoader;
import main.java.org.nyu.image.ImageWriter;
import main.java.org.nyu.sobel.SobelOperator;

import java.io.File;

public class VisionApplication {
    public static void main(String[] args) throws Exception {
        // TODO - load image as gray-level array
        File f = new File("C:\\workspace\\vision\\resources\\grayscale_cat.jpg");
        ImageLoader loader = new ImageLoader();

        // load image and print
        int[][] image = loader.load(f);
        // loader.printImageArray(image);

        System.out.println("image length: " + image.length + " image width: " + image[0].length);

        // gaussian smoothing
        GaussianSmoother smoother = new GaussianSmoother();
        int[][] smooth = smoother.smooth(image);
        loader.printImageArray(smooth);

        ImageWriter writer = new ImageWriter();
        writer.write(smooth);

        // sobel's operator
        SobelOperator sobel = new SobelOperator();
        double[][] gradient = sobel.computeGradientMagnitude(smooth);
        int[][] normalized = sobel.normalizeGradientImage(gradient);

        writer.write(normalized);
    }
}
