package renderer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;
import renderer.Camera;
/**
 * Testing ImageWriter Class by creating a 800x500 pixels picture
 * of a net of 10x16 squares with two colors
 * @author Elad & Ishay
 *
 */
class ImageWriterTest {

    /**
     * Test method for
     * {@link renderer.ImageWriter#writeToImage()}.
     * In this test we will create a grid of 10x16 red squares on a blue background of 800x500 pixels
     */
    @Test
    void writeToImageTest() {
        // ============ Equivalence Partitions Tests ==============

        // Create a file for the image
        ImageWriter imageWriter = new ImageWriter("test", 800, 500);
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 800; j++) {
                /* since we know the grid is 10x16 and the picture is 500x800 pixels, we can calculate that
                 * every square is 250 pixels (40000 / 160) which is 50x50 pixels. so every border of each
                 * square is a multiple of 50 and that is what we check in the condition.
                 * */
                // if it's the line of the view-plain print it red
                if (i % 50 == 0 || j % 50 == 0)
                    imageWriter.writePixel(j, i, Color.BLACK);

                // if it's a square print it blue
                else
                    imageWriter.writePixel(j, i, new Color(0, 0, 255));

            }
        }
        imageWriter.writeToImage();
    }

}
