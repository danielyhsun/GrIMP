package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for pixel class.
 */
public class PixelTest {

  @Test
  public void testThreeComponentConstructor() {
    Pixel pixel = new Pixel(100, 150, 200);
    assertEquals(100, pixel.r);
    assertEquals(150, pixel.g);
    assertEquals(200, pixel.b);
    Pixel pixel2 = new Pixel(500, 200, -150);
    assertEquals(255, pixel2.r);
    assertEquals(200, pixel2.g);
    assertEquals(0, pixel2.b);
  }

  @Test
  public void testFourComponentConstructor() {
    Pixel pixel = new Pixel(100, 150, 200, 255);
    assertEquals(100, pixel.r);
    assertEquals(150, pixel.g);
    assertEquals(200, pixel.b);
    assertEquals(255, pixel.a);
    Pixel pixel2 = new Pixel(500, 255, 100, -25);
    assertEquals(255, pixel2.r);
    assertEquals(255, pixel2.g);
    assertEquals(100, pixel2.b);
    assertEquals(0, pixel2.a);
  }

  @Test
  public void testValueClamping() {
    Pixel pixel = new Pixel(300, -50, 0);
    assertEquals(Project.MAX_CLAMP, pixel.r);
    assertEquals(0, pixel.g);
    assertEquals(0, pixel.b);
  }
}