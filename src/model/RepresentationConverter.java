package model;

/**
 * This class contains utility methods to convert an RGB representation
 * to HSL and back and print those results.
 */
public class RepresentationConverter {
  /**
   * Convers an RGB representation in the range 0-1 into an HSL representation where.
   * <ul>
   * <li> 0 &lt;= H &lt; 360</li>
   * <li> 0 &lt;= S &lt;= 1</li>
   * <li> 0 &lt;= L &lt;= 1</li>
   * </ul>
   *
   * @param r red value of the RGB between 0 and 1.
   * @param g green value of the RGB between 0 and 1.
   * @param b blue value of the RGB between 0 and 1.
   */
  public static double[] convertRGBtoHSL(double r, double g, double b) {
    r = r / 255.0;
    g = g / 255.0;
    b = b / 255.0;
    double componentMax = Math.max(r, Math.max(g, b));
    double componentMin = Math.min(r, Math.min(g, b));
    double delta = componentMax - componentMin;

    double lightness = (componentMax + componentMin) / 2;
    double hue;
    double saturation;
    if (delta == 0) {
      hue = 0;
      saturation = 0;
    } else {
      saturation = delta / (1 - Math.abs(2 * lightness - 1));
      hue = 0;
      if (componentMax == r) {
        hue = (g - b) / delta;
        hue = hue % 6;
      } else if (componentMax == g) {
        hue = (b - r) / delta;
        hue += 2;
      } else if (componentMax == b) {
        hue = (r - g) / delta;
        hue += 4;
      }

      hue = hue * 60;
    }

    // System.out.println("RGB (" + r + "," + g + "," + b +") to HSL =>
    // (" + hue + "," + saturation + "," + lightness + ")");

    double[] temp = new double[3];
    temp[0] = hue;
    temp[1] = saturation;
    temp[2] = lightness;

    return temp;
  }


  /**
   * Converts an HSL representation where.
   * <ul>
   * <li> 0 &lt;= H &lt; 360</li>
   * <li> 0 &lt;= S &lt;= 1</li>
   * <li> 0 &lt;= L &lt;= 1</li>
   * </ul>
   * into an RGB representation where each component is in the range 0-1.
   *
   * @param hue        hue of the HSL representation.
   * @param saturation saturation of the HSL representation.
   * @param lightness  lightness of the HSL representation.
   */
  public static Pixel convertHSLtoRGB(double hue, double saturation, double lightness) {
    double r = convertFn(hue, saturation, lightness, 0) * 255;
    double g = convertFn(hue, saturation, lightness, 8) * 255;
    double b = convertFn(hue, saturation, lightness, 4) * 255;
    // System.out.println("HSL (" + hue + "," + saturation + "," + lightness +") to RGB =>
    // (" + r + "," + g + "," + b + ")");
    return new Pixel((int) r, (int) g, (int) b);
  }

  /**
   * Helper method that performs the translation from the HSL polygonal
   * model to the more familiar RGB model.
   **/
  private static double convertFn(double hue, double saturation, double lightness, int n) {
    double k = (n + (hue / 30)) % 12;
    double a = saturation * Math.min(lightness, 1 - lightness);

    return lightness - a * Math.max(-1, Math.min(k - 3, Math.min(9 - k, 1)));
  }

}
