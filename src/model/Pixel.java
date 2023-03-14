package model;

import static model.CollageModelImpl.MAX_CLAMP;

public class Pixel {
  public int r;
  public int g;
  public int b;
  public int a;

  public Pixel(int r, int g, int b) {
    this.r = setColor(r);
    this.g = setColor(g);
    this.b = setColor(b);
  }
  public Pixel(int r, int g, int b, int a) {
    this.r = setColor(r);
    this.g = setColor(g);
    this.b = setColor(b);
    this.a = a;
  }

  private int setColor(int val) {
    if (val > MAX_CLAMP) {
      val = MAX_CLAMP;
    } else if (val < 0) {
      val = 0;
    }
    return val;
  }
}
