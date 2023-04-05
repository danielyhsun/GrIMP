package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a Project in a Collage Program consisting of multiple layers with images and filters.
 * Provides methods for manipulation of layers and images.
 * A Project has a fixed canvas size, determined at the time of instantiation,
 * and supports a custom format for saving and loading projects.
 */
public class Project {
  protected LinkedHashMap<String, Layer> layers;
  protected static int MAX_CLAMP = 255;
  private final int canvasHeight;
  private final int canvasWidth;
  private String selectedLayer;

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
    this.layers = new LinkedHashMap<>();
    this.addLayer("bg");
  }

  /**
   * Adds a new Layer to the current project. Maps each Layer to its unique layerName identifier,
   * and gives it the same height and width as the project canvas.
   * If the given layerName is "bg", a new background Layer using the background Layer
   * constructor is created.
   * A Layer must have a unique name, two layers with the same not cannot exist in the same project
   *
   * @param layerName a unique identifier for each layer
   * @throws IllegalArgumentException if a Layer with the given layerName already exists in
   *                                  the project.
   */
  protected void addLayer(String layerName) throws IllegalArgumentException {
    if (layerName.equals("bg")) {
      layers.put("bg", new Layer(canvasHeight, canvasWidth));
    } else if (layers.containsKey(layerName)) {
      throw new IllegalArgumentException("Layer name already taken!");
    } else {
      layers.put(layerName, new Layer(layerName, canvasHeight, canvasWidth));
    }
  }

  /**
   * Adds an image from a specified file path to the layer of the specified name with an offset of
   * x and y from the top left corner of the canvas.
   * The Image must be in ppm format.
   *
   * @param layerName the name of the layer where the image is to be added
   * @param file  the location of the image file
   * @param x         the image's x-offset from the layer's top-left corner
   * @param y         the image's y-offset from the layer's top-left corner
   * @throws IOException              if the path to the image file is invalid
   *                                  OR the image is not in the correct format
   * @throws IllegalArgumentException if a layer with the given layerName does not exist
   */
  protected void addImageToLayer(String layerName, File file, int x, int y) throws IOException,
          IllegalArgumentException {
    if (!layers.containsKey(layerName)) {
      throw new IllegalArgumentException();
    }

    Layer layer = layers.get(layerName);
    Image image = new Image(file);
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
  }

  /**
   * Sets the filter for a given layer with a given filter name. Retrieves the specified
   * layer by its name and attempts to apply the specified filter. If the layer or the filter is
   * invalid, an IllegalArgumentException is thrown.
   *
   * @param layerName    the name of the layer that the filter is being applied onto
   * @param filterOption the name of the filter that is being applied
   * @throws IllegalArgumentException if the layer or filter is invalid
   */
  protected void setFilter(String layerName, String filterOption) throws IllegalArgumentException {
    try {
      Layer layer = layers.get(layerName);
      layer.setFilter(filterOption);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Layer not found!");
    }
  }

  /**
   * Writes the current state of the project to a StringBuilder in a custom collage format.
   * The format includes metadata about the canvas size, layers, filter names, and pixel values.
   * This format is intended for saving the project to a file and loading it back later.
   * <p></p>
   * The format is structured as follows:
   * - First line: "C1"
   * - Second line: canvasWidth and canvasHeight, separated by a space
   * - Third line: the maximum clamp value (MAX_CLAMP)
   * - For each layer:
   * - Layer name and filter name, separated by a space
   * - For each pixel in the layer:
   * - Pixel's red, green, blue, and alpha values, separated by spaces
   * - A newline character at the end of each row of pixels
   *
   * @return a StringBuilder containing the project data in the custom collage format
   */
  protected StringBuilder writeToCollageFormat() {
    StringBuilder content = new StringBuilder();

    content.append("C1\n");
    content.append(canvasWidth).append(" ").append(canvasHeight).append("\n");
    content.append(MAX_CLAMP).append("\n");
    for (Map.Entry<String, Layer> entry : layers.entrySet()) {
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

  /**
   * Loads a project from a given Scanner containing project data in the custom collage format.
   * The method reads the data from the Scanner, recreates the layers with their respective
   * filter names, and populates the layers with pixel data. The project dimensions (height and width)
   * are provided as arguments and should match the dimensions specified in the input data.
   *
   * @param sc     a Scanner containing the project data in the custom collage format
   * @param height the height of the project canvas
   * @param width  the width of the project canvas
   * @throws IllegalStateException    if the data in the Scanner does not follow the expected format
   * @throws IllegalArgumentException if the layer or filter is invalid
   */
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
      Layer layer = layers.get(layerName);
      layer.updatePixels(temp);
      this.setFilter(layerName, filter);
    }
  }

  /**
   * Combines all the layers in the current project into a single Pixel[][] array, which represents
   * the final image. This method initializes the final image array with a transparent background
   * (white color with 0 alpha value) and iterates through the layers, applying their pixel data
   * on top of the final image. This method is called when exporting the final image.
   * <p></p>
   * Note that this method does not consider the filter applied to each layer. Make sure to apply
   * the filters to the layers before calling this method if you want the final image to include
   * the filtered appearance of the layers.
   *
   * @return a Pixel[][] array representing the final image obtained by merging all layers
   */
  protected Pixel[][] layersToImage() {
    Pixel[][] finalImage = new Pixel[canvasHeight][canvasWidth];
    for (int i = 0; i < canvasHeight; i++) {
      for (int j = 0; j < canvasWidth; j++) {
        finalImage[i][j] = new Pixel(255, 255, 255, 0);
      }
    }

    for (Map.Entry<String, Layer> entry : layers.entrySet()) {
      Layer currentLayer = entry.getValue();
      Layer filteredLayer = new Layer(currentLayer.getName(), canvasHeight, canvasWidth);
      filteredLayer.updatePixels(deepCopyPixels(currentLayer.getPixels()));
      filteredLayer.setFilter(currentLayer.getFilterName());

      if (currentLayer.getFilterName().contains("blend")) {
        filteredLayer.addFilter(filteredLayer.getFilterName(), this.getCompositeImage());
      }
      else {
        filteredLayer.addFilter(filteredLayer.getFilterName());
      }

      Pixel[][] layerPixels = filteredLayer.getPixels();

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
            int finalAlpha = Math.max(finalPixel.a, layerPixel.a);
            finalImage[i][j] = new Pixel(r, g, b, finalAlpha);
          }
        }
      }
    }
    return finalImage;
  }

  /**
   * Get values of composite image underneath current layer.
   */
  private Pixel[][] getCompositeImage() {
    Pixel[][] compositeImage = new Pixel[canvasHeight][canvasWidth];
    for (int i = 0; i < canvasHeight; i++) {
      for (int j = 0; j < canvasWidth; j++) {
        compositeImage[i][j] = new Pixel(255, 255, 255, 0);
      }
    }
    for (Map.Entry<String, Layer> entry : layers.entrySet()) {
      if (entry.getValue() != this.layers.get(this.selectedLayer)) { // Skip current layer
        Layer temp = entry.getValue();
        Layer filteredLayer = new Layer(temp.getName(), canvasHeight, canvasWidth);
        filteredLayer.updatePixels(deepCopyPixels(temp.getPixels()));
        filteredLayer.setFilter(temp.getFilterName());
        filteredLayer.addFilter(filteredLayer.getFilterName());
        Pixel[][] layerPixels = filteredLayer.getPixels();
        for (int i = 0; i < canvasHeight; i++) {
          for (int j = 0; j < canvasWidth; j++) {
            Pixel layerPixel = layerPixels[i][j];
            Pixel finalPixel = compositeImage[i][j];
            if (layerPixel.a == 0) {
              continue;
            }
            if (finalPixel == null) {
              compositeImage[i][j] = layerPixel;
            } else {
              double alpha = layerPixel.a / 255.0;
              int r = (int) (finalPixel.r * (1 - alpha) + layerPixel.r * alpha);
              int g = (int) (finalPixel.g * (1 - alpha) + layerPixel.g * alpha);
              int b = (int) (finalPixel.b * (1 - alpha) + layerPixel.b * alpha);
              compositeImage[i][j] = new Pixel(r, g, b);
            }
          }
        }
      }
    }
    return compositeImage;
  }


  // create a deep copy of a Pixel[][] to apply operations without affecting the original
  private Pixel[][] deepCopyPixels(Pixel[][] originalPixels) {
    int height = originalPixels.length;
    int width = originalPixels[0].length;
    Pixel[][] copiedPixels = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel originalPixel = originalPixels[i][j];
        copiedPixels[i][j] = new Pixel(originalPixel.r, originalPixel.g, originalPixel.b, originalPixel.a);
      }
    }

    return copiedPixels;
  }

  /**
   * Returns this project's canvas height.
   *
   * @return the project's canvas height
   */
  protected int getCanvasHeight() {
    return this.canvasHeight;
  }

  /**
   * Returns this project's canvas width.
   *
   * @return the project's canvas width
   */
  protected int getCanvasWidth() {
    return this.canvasWidth;
  }

  protected void setSelectedLayer(String layer) throws IllegalArgumentException {
    if (layers.containsKey(layer)) {
      selectedLayer = layer;
    } else {
      throw new IllegalArgumentException();
    }
  }

  protected String getSelectedLayer() {
    return selectedLayer;
  }

  protected BufferedImage getCollageImage() {
    if (canvasWidth <= 0 || canvasHeight <= 0) {
      return null;
    }

    Pixel[][] finalPixels = layersToImage();
    BufferedImage collageImage = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);

    for (int i = 0; i < canvasHeight; i++) {
      for (int j = 0; j < canvasWidth; j++) {
        Pixel pixel = finalPixels[i][j];
        int argb = (pixel.a << 24) | (pixel.r << 16) | (pixel.g << 8) | pixel.b;
        collageImage.setRGB(j, i, argb);
      }
    }

    System.out.println("hello!");
    return collageImage;
  }

  protected ArrayList<String> getLayersOfLoadedProject() {
    ArrayList<String> loadedLayers = new ArrayList<String>();
    for (Map.Entry<String, Layer> entry : layers.entrySet()) {
      loadedLayers.add(entry.getKey());
    }
    // remove bg layer
    loadedLayers.remove(0);
    return loadedLayers;
  }

}
