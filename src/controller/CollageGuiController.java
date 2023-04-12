package controller;

import java.io.File;
import java.io.IOException;

import model.CollageIOModel;
import model.CollageIOModelImpl;
import model.CollageModel;
import view.CollageGuiView;

/**
 * Handles user interactions with the Gui view and updates the model accordingly. Handles the
 * actions or features that user is using. Such actions are like starting a new project.
 */
public class CollageGuiController implements Features {

  private CollageModel model;
  private CollageGuiView view;

  /**
   * Constructor for GUI controller.
   * @param model represents model object.
   * @param view represents view object.
   */
  public CollageGuiController(CollageModel model, CollageGuiView view) {
    this.model = model;
    this.view = view;
    view.addFeatures(this);
  }

  /**
   * Starts a new project.
   * @param w represents width.
   * @param h represents height.
   */
  @Override
  public void newProject(int w, int h) {
    model.newProject(h, w);
    view.showLayersPanelAndResetLayers();
    view.updateImagePanel(model.getCollageImage());
    view.initLayerFilterMap();
  }

  /**
   * Displays full collage image.
   */
  @Override
  public void displayCollage() {
    view.updateImagePanel(model.getCollageImage());
  }

  /**
   * Adds new layer.
   * @param layerName name of new layer.
   */
  @Override
  public void addNewLayer(String layerName) {
    System.out.println("bye");
    model.addLayer(layerName);
    view.setNewLayerFilterMapNormal(layerName);
  }

  /**
   * Quits program action.
   */
  public void quitProject() {
    model.quitProject();
  }

  /**
   * Changes selected layer.
   * @param layer new selected layer.
   */
  @Override
  public void changeSelectedLayer(String layer) {
    model.setSelectedLayer(layer);
  }

  /**
   * Loads project from file given.
   * @param file file that is given.
   * @throws IOException if invalid file.
   */
  @Override
  public void loadProjectFromFile(File file) throws IOException {
    model.load(file);
    view.showLayersPanelAndResetLayers();
    view.updateImagePanel(model.getCollageImage());
    view.addLoadedLayersToLayerPane(model.getLayersOfLoadedProject());
    view.initLayerFilterMap();
  }

  /**
   * Adds image to layer from a file.
   * @param file file that is given.
   * @param xOffset x placement of the new image on layer.
   * @param yOffset y placement of the new image on layer.
   * @throws IOException if image file is invalid.
   */
  @Override
  public void addImageToLayerFromFile(File file, int xOffset, int yOffset) throws IOException {
    model.addImageToLayer(model.getSelectedLayer(), file, xOffset, yOffset);
    view.updateImagePanel(model.getCollageImage());
  }

  /**
   * Saves project to a file location.
   * @param file file location.
   * @throws IOException if file location is bad.
   */
  @Override
  public void saveProjectToFile(File file) throws IOException {
    model.saveProject(file);
  }

  /**
   * Saves image to a file location.
   * @param fileName name of file location.
   * @throws IOException if file location is invalid.
   */
  @Override
  public void saveImageToFile(File fileName) throws IOException {
    model.saveImage(fileName.toString());
  }

  /**
   * Contains all potential filters that can be applied to a layer.
   * @param filterDropdownSelection represents dropdown selection.
   * @param layerName name of layer to be filtered.
   */
  @Override
  public void filterPicker(String filterDropdownSelection, String layerName) {
    String filterOption;
    switch (filterDropdownSelection) {
      case "Brighten Value":
        filterOption = "brighten-value";
        break;
      case "Brighten Intensity":
        filterOption = "brighten-intensity";
        break;
      case "Brighten Luma":
        filterOption = "brighten-luma";
        break;
      case "Darken Value":
        filterOption = "darken-value";
        break;
      case "Darken Intensity":
        filterOption = "darken-intensity";
        break;
      case "Darken Luma":
        filterOption = "darken-luma";
        break;
      case "Filter Red":
        filterOption = "filter-red";
        break;
      case "Filter Green":
        filterOption = "filter-green";
        break;
      case "Filter Blue":
        filterOption = "filter-blue";
        break;
      case "Blend Difference":
        filterOption = "blend-difference";
        break;
      case "Blend Multiply":
        filterOption = "blend-multiply";
        break;
      case "Blend Screen":
        filterOption = "blend-screen";
        break;
      default:
        filterOption = "Normal";
    }
    model.setFilter(layerName, filterOption);
    view.updateImagePanel(model.getCollageImage());
  }

}
