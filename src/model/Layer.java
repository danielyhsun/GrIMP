package model;

public class Layer {
  private final String name;
  private String filterName;
  private Pixel[][] pixels;

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

  protected String getName() {
    return this.name;
  }

  protected String getFilterName() {
    return this.filterName;
  }

  protected Pixel[][] getPixels() {
    return this.pixels;
  }

  protected void updatePixels(Pixel[][] newPixels) {
    this.pixels = newPixels;
  }

  protected void addFilter(String filterOption) throws IllegalArgumentException {
    if (!(filterOption.contains("brighten") || filterOption.contains("darken")
            || filterOption.contains("filter"))) {
      throw new IllegalArgumentException();
    }

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
    this.filterName = filterOption;
  }

  private int getValue(int r, int g, int b) {
    return Math.max(Math.max(r, g), b);
  }

  private int getIntensity(int r, int g, int b) {
    return (r + g + b) / 3;
  }

  private int getLuma(int r, int g, int b) {
    double luma = r * 0.2126 + g * 0.7152 + b * 0.0722;
    return (int) luma;
  }
}
