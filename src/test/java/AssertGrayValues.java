package test.java;

import main.java.org.nyu.image.ImageLoader;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class AssertGrayValues {

    @Test
    public void assertGrayLevelVales() throws Exception {

        /**
         * 0 is the darkest and 255 is the brightest, therefore we shouldn't have a value
         * in the array which is less than 0 or greater than 255!
         */

        File f = new File("C:\\workspace\\vision\\resources\\grayscale_cat.jpg");
        ImageLoader loader = new ImageLoader();

        // load image
        int[][] image = loader.load(f);

        // iterate over and assert each value is between 0 and 255
        for(int i = 0; i < image.length; i++) {
            for(int j = 0; j < image[i].length; j++) {
                assertThat(image[i][j], is(both(greaterThanOrEqualTo(0))
                        .and(lessThanOrEqualTo(255))));
            }
        }
    }
}
