package model;

public class Layer {
  private String name;
  private String filterName;
  private Pixel[][] pixels;

  public Layer(String name, int height, int width) {
    this.name = name;
    this.filterName = "Normal";
    this.pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = new Pixel(0, 0, 0, 0);
      }
    }
  }

  public String getName() {
    return this.name;
  }

  public void changeName(String newName) {
    this.name = newName;
  }

  public String getFilterName() {
    return this.filterName;
  }

  public Pixel[][] getPixels() {
    return this.pixels;
  }

  public void updatePixels(Pixel[][] newPixels) {
    this.pixels = newPixels;
  }

  public void brightenValue() {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        int val = this.getValue(pixels[i][j].r, pixels[i][j].g, pixels[i][j].b);
        int r = pixels[i][j].r + val;
        int g = pixels[i][j].g + val;
        int b = pixels[i][j].b + val;
        pixels[i][j] = new Pixel(r, g, b);
      }
    }
    this.filterName = "brighten-value";
  }

  public void brightenIntensity() {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        int avg = this.getIntensity(pixels[i][j].r, pixels[i][j].g, pixels[i][j].b);
        int r = pixels[i][j].r + avg;
        int g = pixels[i][j].g + avg;
        int b = pixels[i][j].b + avg;
        pixels[i][j] = new Pixel(r, g, b);
      }
    }
    this.filterName = "brighten-intensity";
  }

  public void brightenLuma() {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        int luma = this.getLuma(pixels[i][j].r, pixels[i][j].g, pixels[i][j].b);
        int r = pixels[i][j].r + luma;
        int g = pixels[i][j].g + luma;
        int b = pixels[i][j].b + luma;
        pixels[i][j] = new Pixel(r, g, b);
      }
    }
    this.filterName = "brighten-luma";
  }

  public void darkenValue() {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        int val = this.getValue(pixels[i][j].r, pixels[i][j].g, pixels[i][j].b);
        int r = pixels[i][j].r - val;
        int g = pixels[i][j].g - val;
        int b = pixels[i][j].b - val;
        pixels[i][j] = new Pixel(r, g, b);
      }
    }
    this.filterName = "darken-value";
  }

  public void darkenIntensity() {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        int avg = this.getIntensity(pixels[i][j].r, pixels[i][j].g, pixels[i][j].b);
        int r = pixels[i][j].r - avg;
        int g = pixels[i][j].g - avg;
        int b = pixels[i][j].b - avg;
        pixels[i][j] = new Pixel(r, g, b);
      }
    }
    this.filterName = "darken-intensity";
  }

  public void darkenLuma() {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        int luma = this.getLuma(pixels[i][j].r, pixels[i][j].g, pixels[i][j].b);
        int r = pixels[i][j].r - luma;
        int g = pixels[i][j].g - luma;
        int b = pixels[i][j].b - luma;
        pixels[i][j] = new Pixel(r, g, b);
      }
    }
    this.filterName = "darken-luma";
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

  public void filterRed() {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        pixels[i][j] = new Pixel(pixels[i][j].r, 0, 0);
      }
    }
    this.filterName = "filter-red";
  }

  public void filterGreen() {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        pixels[i][j] = new Pixel(0, pixels[i][j].g, 0);
      }
    }
    this.filterName = "filter-green";
  }

  public void filterBlue() {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        pixels[i][j] = new Pixel(0, 0, pixels[i][j].b);
      }
    }
    this.filterName = "filter-blue";
  }

  public void removeFilter() {

  }
}
