package controller;

import java.io.File;
import java.io.IOException;

/**
 * Features interface. Holds actions for the GUI controller to handle.
 */
public interface Features {

  /**
   * Starts a new project.
   * @param height represents height.
   * @param width represents width.
   */
  void newProject(int height, int width);

  /**
   * Displays full collage image.
   */
  void displayCollage();

  /**
   * Adds new layer.
   * @param layerName name of new layer.
   */
  void addNewLayer(String layerName);

  /**
   * Changes selected layer.
   * @param layer new selected layer.
   */
  void changeSelectedLayer(String layer);

  /**
   * Loads project from file given.
   * @param file file that is given.
   * @throws IOException if invalid file.
   */
  void loadProjectFromFile(File file) throws IOException;

  /**
   * Adds image to layer from a file.
   * @param file file that is given.
   * @param xOffset x placement of the new image on layer.
   * @param yOffset y placement of the new image on layer.
   * @throws IOException if image file is invalid.
   */
  void addImageToLayerFromFile(File file, int xOffset, int yOffset) throws IOException;

  /**
   * Saves project to a file location.
   * @param file file location.
   * @throws IOException if file location is bad.
   */
  void saveProjectToFile(File file) throws IOException;

  /**
   * Saves image to a file location.
   * @param fileName name of file location.
   * @throws IOException if file location is invalid.
   */
  void saveImageToFile(File fileName) throws IOException;

  /**
   * Contains all potential filters that can be applied to a layer.
   * @param filterOption represents dropdown selection.
   * @param layerName name of layer to be filtered.
   */
  void filterPicker(String filterOption, String layerName);
}
