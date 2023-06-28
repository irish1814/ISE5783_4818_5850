package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

/**
 * Testing ImageWriter Class by creating a XxY pixels picture
 *
 * @author Elad &amp; Ishay
 */
class ImageWriterTest {

    /**
     * Test method for
     * {@link renderer.ImageWriter#writeToImage()}.
     * In this test we will create a grid of 10x16 blue squares on a black background of 800x500 pixels
     */
    @Test
    void writeToImageTest() {
        final int WIDTH = 800;
        final int HEIGHT = 500;
        final int STEP = 50;
        final Color color1 = Color.BLACK;
        final Color color2 = new Color(0, 0, 255);
        /* since we know the grid is 10x16 and the picture is 500x800 pixels, we can calculate that
         * every square is 2500 pixels (400000 / 160) which is 50x50 pixels. so every border of each
         * square is a multiple of 50 and that is what we check in the condition.
         */

        // Create a file for the image
        ImageWriter imageWriter = new ImageWriter("test", WIDTH, HEIGHT);
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++)
                imageWriter.writePixel(j, i, i % STEP == 0 || j % STEP == 0 ? color1 : color2);
        imageWriter.writeToImage();
    }

}
