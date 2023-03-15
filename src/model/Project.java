package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a Project in a Collage Program.
 * Projects can have Layers and each Project has a set canvas height and width.
 */
public class Project {

  protected HashMap<Layer, Integer> layers;
  protected HashMap<String, Layer> layerNames;
  protected static int MAX_CLAMP = 255;
  private final int canvasHeight;
  private final int canvasWidth;

  /**
   * Constructor for the Project class.
   * Sets the canvas height and width with the given height and width and initializes the
   * hashmap fields with empty hashmaps.
   *
   * @param canvasHeight the given canvas height.
   * @param canvasWidth  the given canvas width
   */
  protected Project(int canvasHeight, int canvasWidth) {
    this.canvasHeight = canvasHeight;
    this.canvasWidth = canvasWidth;
    this.layers = new HashMap<>();
    this.layerNames = new HashMap<>();
  }

  /**
   * Adds
   * @param layerName
   */
  protected void addLayer(String layerName) throws IllegalArgumentException {
    if (layerNames.containsKey(layerName)) {
      throw new IllegalArgumentException("Layer name already taken!");
    }
    int layerNum = layers.size();

    layers.put(new Layer(layerName, canvasHeight, canvasWidth), layerNum);
    for (Map.Entry<Layer, Integer> entry : layers.entrySet()) {
      if (entry.getValue() == layerNum) {
        layerNames.put(layerName, entry.getKey());
      }
    }
  }

  protected void addImageToLayer(String layerName, String filePath, int x, int y) throws IOException,
          IllegalArgumentException {
    if (!layerNames.containsKey(layerName)) {
      throw new IllegalArgumentException();
    }

    Layer layer = layerNames.get(layerName);
    Image image = new Image(filePath);
    image.readPPM();

    Pixel[][] layerPixels = layer.getPixels();
    Pixel[][] imagePixels = image.getPixels();

    for (int i = 0; i < imagePixels.length; i++) {
      for (int j = 0; j < imagePixels[0].length; j++) {
        if (i + x < layerPixels.length && j + y < layerPixels[0].length) {
          layerPixels[i + x][j + y] = imagePixels[i][j];
        }
      }
    }

    layer.updatePixels(layerPixels);
    layerNames.replace(layerName, layer);
  }

  protected void setFilter(String layerName, String filterOption) throws IllegalArgumentException {
    try {
      Layer temp = layerNames.get(layerName);
      temp.addFilter(filterOption);
      layerNames.replace(layerName, temp);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Layer not found!");
    }
  }

  protected StringBuilder writeToCollageFormat() {
    StringBuilder content = new StringBuilder();

    content.append("C1\n");
    content.append(canvasWidth).append(" ").append(canvasHeight).append("\n");
    content.append(MAX_CLAMP).append("\n");
    for (Map.Entry<String, Layer> entry : layerNames.entrySet()) {
      Layer layer = entry.getValue();
      content.append(entry.getKey()).append(" ").append(layer.getFilterName()).append("\n");
      Pixel[][] tempPixels = layer.getPixels();
      for (int i = 0; i < tempPixels.length; i++) {
        for (int j = 0; j < tempPixels[0].length; j++) {
          Pixel pixel = tempPixels[i][j];
          content.append(pixel.r).append(" ");
          content.append(pixel.g).append(" ");
          content.append(pixel.b).append(" ");
          content.append(pixel.a).append(" ");
        }
        content.append("\n");
      }
    }
    return content;
  }

  protected void loadProject(Scanner sc, int height, int width) {
    while (sc.hasNext()) {
      String layerName = sc.next();
      this.addLayer(layerName);
      String filter = sc.next();
      Pixel[][] temp = new Pixel[height][width];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          temp[i][j] = new Pixel(255, 255, 255, 0);
        }
      }
      while (sc.hasNextInt()) {
        for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
            int r = sc.nextInt();
            int g = sc.nextInt();
            int b = sc.nextInt();
            int a = sc.nextInt();
            temp[i][j] = new Pixel(r, g, b, a);
          }
        }
      }
      Layer layer = layerNames.get(layerName);
      int layerNum = layers.get(layerNames.get(layerName));
      layer.updatePixels(temp);
      this.setFilter(layerName, filter);
      layers.replace(layer, layerNum);
    }
  }

  protected Pixel[][] layersToImage() {
    Pixel[][] finalImage = new Pixel[canvasHeight][canvasWidth];
    for (int i = 0; i < canvasHeight; i++) {
      for (int j = 0; j < canvasWidth; j++) {
        finalImage[i][j] = new Pixel(255, 255, 255, 0);
      }
    }

    for (Map.Entry<Layer, Integer> entry : layers.entrySet()) {
      Layer currentLayer = entry.getKey();
      Pixel[][] layerPixels = currentLayer.getPixels();

      for (int i = 0; i < canvasHeight; i++) {
        for (int j = 0; j < canvasWidth; j++) {
          Pixel layerPixel = layerPixels[i][j];
          Pixel finalPixel = finalImage[i][j];

          if (layerPixel.a == 0) {
            continue;
          }

          if (finalPixel == null) {
            finalImage[i][j] = layerPixel;
          } else {
            double alpha = layerPixel.a / 255.0;
            int r = (int) (finalPixel.r * (1 - alpha) + layerPixel.r * alpha);
            int g = (int) (finalPixel.g * (1 - alpha) + layerPixel.g * alpha);
            int b = (int) (finalPixel.b * (1 - alpha) + layerPixel.b * alpha);
            finalImage[i][j] = new Pixel(r, g, b);
          }
        }
      }
    }
    return finalImage;
  }

  protected int getCanvasHeight() {
    return this.canvasHeight;
  }

  protected int getCanvasWidth() {
    return this.canvasWidth;
  }

}
