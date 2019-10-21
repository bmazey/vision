package main.java.org.nyu;

import main.java.org.nyu.gaussian.GaussianSmoother;
import main.java.org.nyu.image.ImageLoader;
import main.java.org.nyu.image.ImageWriter;
import main.java.org.nyu.nonmaxima.NonMaximaSuppressor;
import main.java.org.nyu.sobel.SobelOperator;
import main.java.org.nyu.threshold.DoubleThresholder;

import java.io.File;

public class VisionApplication {
    public static void main(String[] args) throws Exception {
        // TODO - load image as gray-level array
        File f = new File("C:\\workspace\\vision\\resources\\Zebra-crossing-1.bmp");
        ImageLoader loader = new ImageLoader();

        // load image and print
        int[][] image = loader.load(f);
        // loader.printImageArray(image);

        // System.out.println("image length: " + image.length + " image width: " + image[0].length);

        // gaussian smoothing
        GaussianSmoother smoother = new GaussianSmoother();
        int[][] smooth = smoother.smooth(image);
        // loader.printImageArray(smooth);

        ImageWriter writer = new ImageWriter();
        writer.write(smooth, "gaussian-zebra");

        // sobel's operator
        SobelOperator sobel = new SobelOperator();
        double[][] xgradient = sobel.computeGxGradientMagnitude(smooth);
        double[][] ygradient = sobel.computeGyGradientMagnitude(smooth);
        double[][] gradient = sobel.computeGradientMagnitude(smooth);

        int[][] xnormalized = sobel.normalizeGradientImage(xgradient);
        int[][] ynormalized = sobel.normalizeGradientImage(ygradient);
        int[][] normalized = sobel.normalizeGradientImage(gradient);

        writer.write(xnormalized, "sobel-Gx-zebra");
        writer.write(ynormalized, "sobel-Gy-zebra");
        writer.write(normalized, "sobel-zebra");

        // non-maxima suppression
        NonMaximaSuppressor suppressor = new NonMaximaSuppressor();
        double[][] theta = suppressor.computeGradientAngle(xgradient, ygradient);
        int[][] suppressed = suppressor.suppress(gradient, theta);
        writer.write(suppressed, "maxima-zebra");

        // double threshold
        DoubleThresholder thresholder = new DoubleThresholder();
        int[][] canny = thresholder.threshold(suppressed, gradient, theta, 100, 50);
        writer.write(canny, "canny-zebra");

    }
}
