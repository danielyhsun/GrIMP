package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents an implementation of a Collage Program model.
 */
public class CollageModelImpl implements CollageModel {
  private Project currentProject;
  protected static int MAX_CLAMP = 255;

  /**
   * The constructor for a CollageModelImpl.
   */
  public CollageModelImpl() {
    this.currentProject = null;
  }

  /**
   * Creates a new project canvas with a given height and width. If a project is already open,
   * the new project replaces it as the current project.
   * @param canvasHeight the height of the project canvas.
   * @param canvasWidth the width of the project canvas.
   * @throws IllegalArgumentException if the canvas height or width are less than 0.
   */
  @Override
  public void newProject(int canvasHeight, int canvasWidth) throws IllegalArgumentException {
    if (canvasHeight < 0 || canvasWidth < 0) {
      throw new IllegalArgumentException("Project dimensions cannot be negative!");
    } else {
      this.currentProject = new Project(canvasHeight, canvasWidth);
    }
  }

  /**
   * Adds a layer to the current project with a given layer name. A layer is associated to a name,
   * and a number based on the order in which it was created.
   * @param layerName the name of the layer.
   * @throws IllegalStateException if there is no project currently open.
   */
  @Override
  public void addLayer(String layerName) throws IllegalStateException {
    if (currentProject == null) {
      throw new IllegalStateException("No project is currently open");
    } else {
      this.currentProject.addLayer(layerName);
    }
  }

  /**
   * Adds an image from a specified file path to the layer of the specified name with an offset of.
   * x and y from top left corner of the canvas.
   * @param layerName the name of the layer.
   * @param filePath the file location of the image.
   * @param x the x offset from the top left corner of the canvas.
   * @param y the y offset from the top left corner of the canvas.
   * @throws IllegalStateException if there is no project currently open.
   * @throws IOException if the image could not be found with the given filepath.
   */
  @Override
  public void addImageToLayer(String layerName, String filePath, int x, int y)
          throws IllegalStateException, IOException {
    if (currentProject == null) {
      throw new IllegalStateException("No project is currently open!");
    } else {
      currentProject.addImageToLayer(layerName, filePath, x, y);
    }
  }

  /**
   * Sets the filter for a given layer with a given filter name.
   * @param layerName the name of the layer that the filter is being applied onto.
   * @param filterOption the name of the filter that is being applied.
   * @throws IllegalStateException if there is no project currently open.
   * @throws IllegalArgumentException if the layer or filter is invalid.
   */
  @Override
  public void setFilter(String layerName, String filterOption) throws IllegalStateException,
          IllegalArgumentException {
    if (currentProject == null) {
      throw new IllegalStateException("No project is currently open!");
    }
    currentProject.setFilter(layerName, filterOption);
  }

  /**
   * Saves the currently open project to a formatted file with the given file location.
   * @param filePath the designated file path for the saved project.
   * @throws IOException if the file path is invalid.
   */
  @Override
  public void saveProject(String filePath) throws IOException, IllegalStateException {
    if (currentProject == null) {
      throw new IllegalStateException("No project is currently open!");
    }
    try {
      FileWriter writer = new FileWriter(filePath);
      StringBuilder text = currentProject.writeToCollageFormat();
      writer.write(String.valueOf(text));
      writer.close();
    } catch (IOException e) {
      throw new IOException("Could not save project");
    }
  }

  /**
   * Saves the current project collage as a ppm formatted image to a designated file location.
   * @param filePath the designated file path for the saved image.
   * @throws IOException if the file path is invalid.
   */
  @Override
  public void saveImage(String filePath) throws IOException, IllegalStateException {
    if (currentProject == null) {
      throw new IllegalStateException("No project is currently open!");
    }
    try {
      int height = currentProject.getCanvasHeight();
      int width = currentProject.getCanvasWidth();

      FileWriter writer = new FileWriter(filePath);
      writer.write("P3\n");
      writer.write(width + " " + height + "\n");
      writer.write(MAX_CLAMP + "\n");

      Pixel[][] finalImage = currentProject.layersToImage();

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Pixel pixel = finalImage[i][j];
          writer.write(pixel.r + " " + pixel.g + " " + pixel.b + " ");
        }
        writer.write("\n");
      }
      writer.close();

    } catch (IOException e) {
      throw new IOException("Unable to save image!");
    }
  }

  /**
   * Loads a project from a project file with a given file location.
   * @param filePath the location of the project file to open.
   * @throws IOException if the file path is invalid.
   * @throws IllegalArgumentException if the file is not a properly formatted project file.
   */
  @Override
  public void load(String filePath) throws IOException, IllegalArgumentException {
    Scanner sc;
    StringBuilder str = new StringBuilder();

    try {
      sc = new Scanner(new FileInputStream(filePath));
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          str.append(s).append(System.lineSeparator());
        }
      }
    } catch (FileNotFoundException e) {
      throw new IOException();
    }

    sc = new Scanner(str.toString());

    String token;

    token = sc.next();
    if (!token.equals("C1")) {
      throw new IllegalArgumentException("Not a valid project file");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    MAX_CLAMP = sc.nextInt();

    newProject(height, width);
    currentProject.loadProject(sc, height, width);
  }

  /**
   * Quits the currently open project by setting the current project to null
   * and loses all unsaved work.
   * @throws IllegalStateException if no project is currently open
   */
  public void quitProject() throws IllegalStateException {
    if (currentProject == null) {
      throw new IllegalStateException("No project is currently open!");
    }
    currentProject = null;
  }
}
