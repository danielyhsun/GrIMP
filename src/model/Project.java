package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Project {

  protected HashMap<Layer, Integer> layers;
  protected HashMap<String, Layer> layerNames;
  protected static int MAX_CLAMP = 255;
  private final int canvasHeight;
  private final int canvasWidth;

  public Project(int canvasHeight, int canvasWidth) {
    this.canvasHeight = canvasHeight;
    this.canvasWidth = canvasWidth;
    this.layers = new HashMap<>();
    this.layerNames = new HashMap<>();
  }

  public void addLayer(String layerName) {
    int layerNum = layers.size();

    layers.put(new Layer(layerName, canvasHeight, canvasWidth), layerNum);
    for(Map.Entry<Layer, Integer> entry : layers.entrySet()) {
      if (entry.getValue() == layerNum) {
        layerNames.put(layerName, entry.getKey());
      }
    }
  }

  public void addImageToLayer(String layerName, String filePath, int x, int y) {
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

  public void setFilter(String layerName, String filterOption) {
    Layer temp = layerNames.get(layerName);
    switch (filterOption) {
      case "brighten-value":
        temp.brightenValue();
        break;
      case "brighten-intensity":
        temp.brightenIntensity();
        break;
      case "brighten-luma":
        temp.brightenLuma();
        break;
      case "darken-value":
        temp.darkenValue();
        break;
      case "darken-intensity":
        temp.darkenIntensity();
        break;
      case "darken-luma":
        temp.darkenLuma();
        break;
      case "filter-red":
        temp.filterRed();
        break;
      case "filter-green":
        temp.filterGreen();
        break;
      case "filter-blue":
        temp.filterBlue();
        break;
      default:
    }
    layerNames.replace(layerName, temp);
  }

  public StringBuilder writeToCollageFormat() {
    StringBuilder content = new StringBuilder();

    content.append("C1\n");
    content.append(canvasWidth).append(" ").append(canvasHeight).append("\n");
    content.append(MAX_CLAMP).append("\n");
    for(Map.Entry<String, Layer> entry : layerNames.entrySet()) {
      Layer layer = entry.getValue();
      content.append(entry.getKey()).append(" ").append(layer.getFilterName()).append("\n");
      Pixel[][] tempPixels = layer.getPixels();
      for (int i = 0; i < tempPixels.length; i++) {
        for (int j = 0; j < tempPixels[0].length; j++) {
          content.append(tempPixels[i][j].r).append(" ");
          content.append(tempPixels[i][j].g).append(" ");
          content.append(tempPixels[i][j].b).append(" ");
          content.append(tempPixels[i][j].a).append(" ");
        }
        content.append("\n");
      }
    }
    return content;
  }

  public void loadProject(Scanner sc, int height, int width) {
    while (sc.hasNext()) {
      String layerName = sc.next();
      this.addLayer(layerName);
      String filter = sc.next();
      Pixel[][] temp = new Pixel[height][width];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          int a = sc.nextInt();
          temp[i][j] = new Pixel(r, g, b, a);
        }
      }
      Layer layer = layerNames.get(layerName);
      int layerNum = layers.get(layerNames.get(layerName));
      layer.updatePixels(temp);
      this.setFilter(layerName, filter);
      layers.replace(layer, layerNum);
    }
  }

}
