package model;

public class Layer {
  private final String name;
  private String filterName;
  private Pixel[][] pixels;

  /**
   * Constructor to create a Layer with a given name, canvas height, and canvas width.
   * Creates a transparent white layer.
   *
   * @param name the name of the layer
   * @param height the height of the canvas
   * @param width the width of the canvas
   */
  protected Layer(String name, int height, int width) {
    this.name = name;
    this.filterName = "Normal";
    this.pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = new Pixel(255, 255, 255, 0);
      }
    }
  }

  /**
   * Constructor to create a default background Layer.
   * Creates an opaque white layer.
   *
   * @param height the height of the canvas
   * @param width the width of the canvas
   */
  protected Layer(int height, int width) {
    this.name = "bg";
    this.filterName = "Normal";
    this.pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = new Pixel(255, 255, 255, 255);
      }
    }
  }

  /**
   * Returns the name of this layer.
   *
   * @return the name of the layer
   */
  protected String getName() {
    return name;
  }

  /**
   * Returns the filter associated with this layer.
   *
   * @return the filter associated with this layer
   */
  protected String getFilterName() {
    return filterName;
  }

  /**
   * Returns the layer's 2D array of pixels.
   *
   * @return the layer's 2D array of pixels.
   */
  protected Pixel[][] getPixels() {
    return pixels;
  }

  /**
   * Updates the layer's 2D array of pixels by setting it to the new given 2D array of pixels.
   *
   * @param newPixels the new 2D array of pixels
   */
  protected void updatePixels(Pixel[][] newPixels) {
    pixels = newPixels;
  }

  /**
   * Sets the filter associated with the layer to the given filter.
   *
   * @param filterOption the name of the given filter.
   * @throws IllegalArgumentException if the given filter is invalid.
   */
  protected void setFilter(String filterOption) throws IllegalArgumentException {
    if (!(filterOption.contains("brighten") || filterOption.contains("darken")
            || filterOption.contains("filter") || filterOption.contains("Normal"))) {
      throw new IllegalArgumentException();
    }
    filterName = filterOption;
  }

  /**
   * Applies a filter effect to the layer's pixels based on the specified filter option.
   * A filter option can be a combination of a brightness adjustments (brighten/darken) with a
   * calculation method (value/intensity/luma) or a color channel isolation (red/green/blue).
   *
   * @param filterOption the filter option to be applied to the layer's pixels
   */
  protected void addFilter(String filterOption) {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        int inc = 0;
        if (filterOption.contains("value")) {
          inc = this.getValue(pixels[i][j].r, pixels[i][j].g, pixels[i][j].b);
        } else if (filterOption.contains("intensity")) {
          inc = this.getIntensity(pixels[i][j].r, pixels[i][j].g, pixels[i][j].b);
        } else if (filterOption.contains("luma")) {
          inc = this.getLuma(pixels[i][j].r, pixels[i][j].g, pixels[i][j].b);
        }

        int r = pixels[i][j].r;
        int g = pixels[i][j].g;
        int b = pixels[i][j].b;

        if (filterOption.contains("brighten")) {
          r = pixels[i][j].r + inc;
          g = pixels[i][j].g + inc;
          b = pixels[i][j].b + inc;
        } else if (filterOption.contains("darken")) {
          r = pixels[i][j].r - inc;
          g = pixels[i][j].g - inc;
          b = pixels[i][j].b - inc;
        }

        if (filterOption.contains("red")) {
          g = 0;
          b = 0;
        } else if (filterOption.contains("green")) {
          r = 0;
          b = 0;
        } else if (filterOption.contains("blue")) {
          r = 0;
          g = 0;
        }

        pixels[i][j] = new Pixel(r, g, b, pixels[i][j].a);
      }
    }
  }

  /**
   * Calculates the value of a pixel based on its red, green, and blue color values.
   * The value is the maximum of the three color values.
   *
   * @param r the red color value of the pixel
   * @param g the green color value of the pixel
   * @param b the blue color value of the pixel
   * @return the value of the pixel
   */
  private int getValue(int r, int g, int b) {
    return Math.max(Math.max(r, g), b);
  }

  /**
   * Calculates the intensity of a pixel based on its red, green, and blue color values.
   * The intensity is the average of the three color values.
   *
   * @param r the red color value of the pixel
   * @param g the green color value of the pixel
   * @param b the blue color value of the pixel
   * @return the intensity of the pixel
   */
  private int getIntensity(int r, int g, int b) {
    return (r + g + b) / 3;
  }

  /**
   * Calculates the luma of a pixel based on its red, green, and blue color values.
   * The luma is a weighted sum of the color values that represents the perceived brightness
   * of the pixel.
   *
   * @param r the red color value of the pixel
   * @param g the green color value of the pixel
   * @param b the blue color value of the pixel
   * @return the luma of the pixel
   */
  private int getLuma(int r, int g, int b) {
    double luma = r * 0.2126 + g * 0.7152 + b * 0.0722;
    return (int) luma;
  }
}
