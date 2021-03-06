package main.java.org.nyu.image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ImageLoader {

    public int[][] load(File f) throws IOException {

        // start by loading file - throws exception if file not found
        BufferedImage image = ImageIO.read(f);

        // image is n x m
        int n = image.getWidth();
        int m = image.getHeight();

        int[][] imageArray = new int[n][m];

        Raster raster = image.getData();

        // iterate over new array and populate grayscale values
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {

                // FIXME - a way to assert grayscale?
                // 0 is selected as third argument because image is assumed to be grayscale
                // imageArray[i][j] = raster.getSample(i, j, 0);

                imageArray[i][j] = image.getRGB(i, j) & 0xFF;
            }
        }

        return imageArray;
    }

    public void printImageArray(int[][] image) {
        System.out.println(Arrays.deepToString(image).replace("], ", "]\n"));
    }
}
