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
        writer.write(smooth, "zebra-gaussian");
        writer.write(image, "zebra-test");

        // sobel's operator
        SobelOperator sobel = new SobelOperator();
        double[][] xgradient = sobel.computeGxGradientMagnitude(smooth);
        double[][] ygradient = sobel.computeGyGradientMagnitude(smooth);
        double[][] gradient = sobel.computeGradientMagnitude(smooth);

        int[][] xnormalized = sobel.normalizeGradientImage(xgradient);
        int[][] ynormalized = sobel.normalizeGradientImage(ygradient);
        int[][] normalized = sobel.normalizeGradientImage(gradient);

        writer.write(xnormalized, "zebra-sobel-Gx");
        writer.write(ynormalized, "zebra-sobel-Gy");
        writer.write(normalized, "zebra-sobel");

        // non-maxima suppression
        NonMaximaSuppressor suppressor = new NonMaximaSuppressor();
        double[][] theta = suppressor.computeGradientAngle(xgradient, ygradient);
        int[][] suppressed = suppressor.suppress(gradient, theta);
        writer.write(suppressed, "zebra-maxima");

        // double threshold
        DoubleThresholder thresholder = new DoubleThresholder();
        int[][] canny = thresholder.threshold(suppressed, gradient, theta, 60, 30);
        writer.write(canny, "zebra-canny");

    }
}
