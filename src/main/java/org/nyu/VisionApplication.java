package main.java.org.nyu;


import main.java.org.nyu.canny.CannyEdgeDetector;

public class VisionApplication {
    public static void main(String[] args) throws Exception {

        // specify base directory
        String path = "C:\\workspace\\vision\\resources\\";

        CannyEdgeDetector zebraDetector = new CannyEdgeDetector("Zebra-crossing-1.bmp", 60, 30);
        zebraDetector.detect(path);

        CannyEdgeDetector houseDetector = new CannyEdgeDetector("Houses-225.bmp", 60, 30);
        houseDetector.detect(path);

        CannyEdgeDetector catDetector = new CannyEdgeDetector("grayscale_cat.jpg", 60, 30);
        catDetector.detect(path);

    }
}
