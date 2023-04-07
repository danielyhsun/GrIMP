package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Tester class for representation converter class, which helps to convert pixel values to HSL,
 * and HSL to pixel values.
 */
public class RepresentationConverterTest {
  @Test
  public void testConvertRGBtoHSL() {
    RepresentationConverter rc = new RepresentationConverter();
    Pixel p = new Pixel(100, 100, 100);

    double[] temp = rc.convertRGBtoHSL(p.r, p.g, p.b);
    double[] tempTwo = rc.convertRGBtoHSL(255, 255, 0);

    assertEquals(0.0, temp[0], 0.1); // Hue
    assertEquals(0.0, temp[1], 0.1); // Saturation
    assertEquals(0.39, temp[2], 0.1); // Lightness

    assertEquals(60, tempTwo[0], 0.1);
    assertEquals(1, tempTwo[1], 0.1);
    assertEquals(0.5, tempTwo[2], 0.1);
  }

  @Test
  public void testConvertHSLtoRGB() {
    RepresentationConverter rc = new RepresentationConverter();

    Pixel p = rc.convertHSLtoRGB(0.0, 0.0, 0.39);
    Pixel pTwo = rc.convertHSLtoRGB(60, 1, 0.5);

    assertEquals((int) (0.39 * 255.0), p.r, 0.1); // 99.45 -> 99
    assertEquals((int) (0.39 * 255.0), p.g, 0.1); // 99.45 -> 99
    assertEquals((int) (0.39 * 255.0), p.b, 0.1); // 99.45 -> 99

    assertEquals(255, pTwo.r, 0.1);
    assertEquals(255, pTwo.g, 0.1);
    assertEquals(0, pTwo.b, 0.1);
  }

}