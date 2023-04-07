package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import controller.Features;

/**
 * CollageGuiView interface. Represents an application GUI window
 * for a Collage program. Contains all the GUI elements required for a user to navigate and
 * use a Collage program and its features.
 */
public interface CollageGuiView {

  /**
   * Add listeners for GUI Elements.
   * @param f the Features object.
   */
  void addFeatures(Features f);

  /**
   * Shows a dialog when the Add Layer button is pressed. Contains a text field
   * to take a string input for the new layer's name, and a "Create" button that
   * closes the dialog. Takes in a string Consumer that accepts the dialog's input.
   * @param onLayerCreated a String consumer
   */
  void newLayerDialog(Consumer<String> onLayerCreated);

  /**
   * Shows a dialog when the New Project menu item is pressed. Contains two text fields
   * to take in height and width input for the project canvas dimensions, and a "Create" button
   * that closes the dialog. Takes in a BiConsumer that accepts the height and width inputs.
   * @param createProjectCallback the BiConsumer
   */
  void newProjectDialog(BiConsumer<Integer, Integer> createProjectCallback);

  /**
   * Shows load project option.
   * @param fileConsumer represents file intake for project.
   */
  void loadProjectChooser(Consumer<File> fileConsumer);

  /**
   * Assists in adding image to layer according to file.
   * @param fileConsumer represents object to take in file.
   */
  void addImageToLayerChooser(Consumer<File> fileConsumer);

  /**
   * Shows the layers panel on the right edge of the frame and removes any elements in the
   * list of layers.
   */
  void showLayersPanelAndResetLayers();

  /**
   * Updates image panel to keep the program refreshed and up to date.
   * @param image represents image that is being broadcasted through the program.
   */
  void updateImagePanel(BufferedImage image);

  /**
   * Adds multiple layers to program holder.
   * @param layers names of layers to add.
   */
  void addLoadedLayersToLayerPane(ArrayList<String> layers);

  /**
   * Chooser method for saving a project.
   * @param fileConsumer file location for saving project.
   */
  void saveProjectChooser(Consumer<File> fileConsumer);

  /**
   * Establishes hashmap for layers and filters.
   */
  void initLayerFilterMap();

  /**
   * Establishes start of hashmap by adding initial layer.
   * @param layerName represents name of inital layer.
   */
  void setNewLayerFilterMapNormal(String layerName);

}
