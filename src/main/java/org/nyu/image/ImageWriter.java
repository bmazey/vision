package main.java.org.nyu.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;

public class ImageWriter {

    public void write(int[][] image) throws Exception {
        BufferedImage buffered = new BufferedImage(image.length, image[0].length, BufferedImage.TYPE_BYTE_GRAY);

        WritableRaster raster = buffered.getRaster();
        for(int i = 0; i < image.length; i++) {
            for(int j = 0; j < image[i].length; j++) {
                raster.setSample(i, j, 0, image[i][j]);
            }
        }

        File f = new File("C:\\workspace\\vision\\resources\\test.png");
        ImageIO.write(buffered, "png", f);
    }
}
