package model;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Tester class for image class.
 */
public class ImageTest {

  /**
   * Tester method for getPixels method and readPPM method.
   */
  @Test
  public void testGetPixelsAndReadPPM() {
    File file = new File("/Users/codychan/Desktop/OOD_Projects/Collage/res/grogubi.ppm");
    Image i = new Image(file);

    try {
      i.readImage();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    Pixel[][] pixels = i.getPixels();

    assertNotNull(pixels);
  }

}