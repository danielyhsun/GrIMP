package model;

import static model.Project.MAX_CLAMP;

/**
 * Represents a Pixel with a red, green, blue, and alpha component.
 */
public class Pixel {
  protected int r;
  protected int g;
  protected int b;
  protected int a;

  /**
   * Constructor for a 3 component Pixel that takes in a red, green, and blue component.
   * The values are clamped to the range of 0 to 255 to ensure that they are within the valid
   * range for a color value.
   *
   * @param r the pixel's red value
   * @param g the pixel's green value
   * @param b the pixel's blue value
   */
  protected Pixel(int r, int g, int b) {
    this.r = clampValue(r);
    this.g = clampValue(g);
    this.b = clampValue(b);
  }

  /**
   * Constructor for a 4 component Pixel that takes in a red, green, blue, and alpha component.
   * The values are clamped to the range of 0 to 255 to ensure that they are within the valid
   * range for a color value.
   *
   * @param r the pixel's red value
   * @param g the pixel's green value
   * @param b the pixel's blue value
   * @param a the pixel's alpha value
   */
  protected Pixel(int r, int g, int b, int a) {
    this.r = clampValue(r);
    this.g = clampValue(g);
    this.b = clampValue(b);
    this.a = clampValue(a);
  }

  // makes sure that each component value is between 0 and the max clamp value
  private int clampValue(int val) {
    if (val > MAX_CLAMP) {
      val = MAX_CLAMP;
    } else if (val < 0) {
      val = 0;
    }
    return val;
  }
}
