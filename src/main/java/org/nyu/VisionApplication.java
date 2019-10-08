package main.java.org.nyu;

import main.java.org.nyu.image.ImageLoader;

import java.io.File;

public class VisionApplication {
    public static void main(String[] args) throws Exception {
        // TODO - load image as gray-level array
        File f = new File("C:\\workspace\\vision\\resources\\grayscale_cat.jpg");
        ImageLoader loader = new ImageLoader();

        // load image and print
        int[][] image = loader.load(f);
        loader.printImageArray(image);
    }
}
