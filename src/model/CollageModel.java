package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

/**
 * An interface that represents the model for a Collage Program.
 */
public interface CollageModel {

  /**
   * Creates a new project canvas with a given height and width.
   *
   * @param canvasHeight the height of the project canvas
   * @param canvasWidth  the width of the project canvas
   * @throws IllegalArgumentException if the canvas height or width are invalid
   */
  void newProject(int canvasHeight, int canvasWidth) throws IllegalArgumentException;

  /**
   * Adds a layer to the current project with a given layer name. A layer is associated to a name
   * and a number based on the order in which it was created.
   *
   * @param layerName the name of the layer
   * @throws IllegalStateException if there is no project currently open
   */
  void addLayer(String layerName) throws IllegalStateException;

  /**
   * Adds an image from a specified file path to the layer of the specified name with an offset of
   * x and y from top left corner of the canvas.
   *
   * @param layerName the name of the layer
   * @param filePath  the file location of the image
   * @param x         the x offset from the top left corner of the canvas
   * @param y         the y offset from the top left corner of the canvas
   * @throws IllegalStateException if there is no project currently open
   * @throws IOException           if the image could not be found with the given filepath
   */
  void addImageToLayer(String layerName, String filePath, int x, int y) throws IllegalStateException, IOException;

  void addImageToLayer(String layerName, File file, int x, int y) throws IllegalStateException, IOException;

  /**
   * Sets the filter for a given layer with a given filter name.
   *
   * @param layerName    the name of the layer that the filter is being applied onto
   * @param filterOption the name of the filter that is being applied
   * @throws IllegalStateException    if there is no project currently open
   * @throws IllegalArgumentException if the layer or filter is invalid
   */
  void setFilter(String layerName, String filterOption) throws IllegalStateException, IllegalArgumentException;

  /**
   * Saves the currently open project to a file with the given file location.
   *
   * @param filePath the designated file path for the saved project
   * @throws IOException if the file path is invalid
   */
  void saveProject(String filePath) throws IOException, IllegalStateException;

  void saveProject(File file) throws IOException, IllegalStateException;

  /**
   * Saves the current project collage as an image to a designated file location.
   *
   * @param filePath the designated file path for the saved image
   * @throws IOException if the file path is invalid
   */
  void saveImage(String filePath) throws IOException, IllegalStateException;

  /**
   * Loads a project from a project file with a given file location.
   *
   * @param filePath the location of the project file to open
   * @throws IOException              if the file path is invalid
   * @throws IllegalArgumentException if the file is not a properly formatted project file
   */
  void load(String filePath) throws IOException, IllegalArgumentException;

  void load(File file) throws IOException, IllegalArgumentException;

  /**
   * Quits the currently open project and loses all unsaved work.
   *
   * @throws IllegalStateException if no project is currently open
   */
  void quitProject() throws IllegalStateException;

  void setSelectedLayer(String layer);
  String getSelectedLayer();
  BufferedImage getCollageImage();
  ArrayList<String> getLayersOfLoadedProject();
}
