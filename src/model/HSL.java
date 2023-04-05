package model;


/**
 * Hue-Saturation-Lightness pixel representation.
 */
public class HSL {
  protected int H;
  protected int S;
  protected int L;

  protected HSL (int H, int S, int L) {
    this.H = clampValue(H, 359);
    this.S = clampValue(S, 1);
    this.L = clampValue(L, 1);

  }

  /**
   * Ensures component is within 0 and X.
   * @param val component that is being checked.
   * @return new component that fits parameters.
   */
  private int clampValue(int val, int max) {
    if (val > max) {
      val = max;
    } else if (val < 0) {
      val = 0;
    }
    return val;
  }

}
