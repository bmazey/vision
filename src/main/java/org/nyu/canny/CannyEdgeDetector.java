package main.java.org.nyu.canny;

import main.java.org.nyu.gaussian.GaussianSmoother;
import main.java.org.nyu.image.ImageLoader;
import main.java.org.nyu.image.ImageWriter;
import main.java.org.nyu.nonmaxima.NonMaximaSuppressor;
import main.java.org.nyu.sobel.SobelOperator;
import main.java.org.nyu.threshold.DoubleThresholder;

import java.io.File;

public class CannyEdgeDetector {

    private String filename;
    private String edited;
    private int upper;
    private int lower;

    public CannyEdgeDetector(String filename, int upper, int lower) {
        // assume filename contains extension
        this.filename = filename;
        this.edited = filename.substring(0, filename.lastIndexOf('.'));
        this.upper = upper;
        this.lower = lower;
    }

    public void detect() throws Exception {
        // TODO - load image as gray-level array
        File f = new File("C:\\workspace\\vision\\resources\\" + this.filename);
        ImageLoader loader = new ImageLoader();

        // load image and print
        int[][] image = loader.load(f);

        // gaussian smoothing
        GaussianSmoother smoother = new GaussianSmoother();
        int[][] smooth = smoother.smooth(image);

        ImageWriter writer = new ImageWriter();
        writer.write(smooth, this.edited + "-gaussian");

        // use to assert how image loader is reading image!
        // writer.write(image, "zebra-test");

        // sobel's operator
        SobelOperator sobel = new SobelOperator();
        double[][] xgradient = sobel.computeGxGradientMagnitude(smooth);
        double[][] ygradient = sobel.computeGyGradientMagnitude(smooth);
        double[][] gradient = sobel.computeGradientMagnitude(smooth);

        int[][] xnormalized = sobel.normalizeGradientImage(xgradient);
        int[][] ynormalized = sobel.normalizeGradientImage(ygradient);
        int[][] normalized = sobel.normalizeGradientImage(gradient);

        writer.write(xnormalized, this.edited + "-sobel-Gx");
        writer.write(ynormalized, this.edited + "-sobel-Gy");
        writer.write(normalized, this.edited + "-sobel");

        // non-maxima suppression
        NonMaximaSuppressor suppressor = new NonMaximaSuppressor();
        double[][] theta = suppressor.computeGradientAngle(xgradient, ygradient);
        int[][] suppressed = suppressor.suppress(gradient, theta);
        writer.write(suppressed, this.edited + "-maxima");

        // double thresholding
        DoubleThresholder thresholder = new DoubleThresholder();
        int[][] canny = thresholder.threshold(suppressed, gradient, theta, this.upper, this.lower);
        writer.write(canny, this.edited + "-canny");
    }

}
