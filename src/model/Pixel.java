package model;

import static model.CollageModelImpl.MAX_CLAMP;

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
   *
   * @param r the pixel's red value.
   * @param g the pixel's green value.
   * @param b the pixel's blue value.
   */
  protected Pixel(int r, int g, int b) {
    this.r = setColor(r);
    this.g = setColor(g);
    this.b = setColor(b);
  }

  /**
   * Constructor for a 4 component Pixel that takes in a red, green, blue, and alpha component.
   *
   * @param r the pixel's red value.
   * @param g the pixel's green value.
   * @param b the pixel's blue value.
   * @param a the pixel's alpha value.
   */
  protected Pixel(int r, int g, int b, int a) {
    this.r = setColor(r);
    this.g = setColor(g);
    this.b = setColor(b);
    this.a = a;
  }


  /**
   * Ensures component value is between 0 and max clamp value.
   * @param val represents component value.
   * @return new component value.
   */
  private int setColor(int val) {
    if (val > MAX_CLAMP) {
      val = MAX_CLAMP;
    } else if (val < 0) {
      val = 0;
    }
    return val;
  }
}
