package model;

import org.junit.Test;

import java.io.InputStreamReader;
import java.io.StringReader;

import model.CollageModel;
import model.CollageModelImpl;
import view.CollageView;
import view.CollageViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tester class for representation converter class, which helps to convert pixel values to HSL,
 * and HSL to pixel values.
 */
public class RepresentationConverterTest {

  @Test
  public void testConvertRGBtoHSL() {
    RepresentationConverter rc = new RepresentationConverter();
    Pixel p = new Pixel(100, 100, 100);

    double[] temp = rc.convertRGBtoHSL(p.r / 255.0, p.g / 255.0, p.b / 255.0);

    assertEquals(0.0, temp[0], 0.1); // Hue
    assertEquals(0.0, temp[1], 0.1); // Saturation
    assertEquals(0.39, temp[2], 0.1); // Lightness
  }

  @Test
  public void testConvertHSLtoRGB() {
    RepresentationConverter rc = new RepresentationConverter();

    Pixel p = rc.convertHSLtoRGB(0.0, 0.0, 0.39);

    assertEquals((int) (0.39 * 255.0), p.r, 0.1); // 99.45 -> 99
    assertEquals((int) (0.39 * 255.0), p.g, 0.1); // 99.45 -> 99
    assertEquals((int) (0.39 * 255.0), p.b, 0.1); // 99.45 -> 99
  }

}