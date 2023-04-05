package controller;

import java.io.File;
import java.io.IOException;

import model.CollageModel;
import view.CollageGuiView;

public class CollageGuiController implements Features {

  CollageModel model;
  CollageGuiView view;

  public CollageGuiController(CollageModel model, CollageGuiView view) {
    this.model = model;
    this.view = view;
    view.addFeatures(this);
  }


  @Override
  public void newProject(int w, int h) {
    model.newProject(h, w);
    view.showLayersPanelAndResetLayers();
    view.updateImagePanel(model.getCollageImage());
    view.initLayerFilterMap();
  }

  @Override
  public void displayCollage() {
    view.updateImagePanel(model.getCollageImage());
  }

  @Override
  public void addNewLayer(String layerName) {
    System.out.println("bye");
    model.addLayer(layerName);
    view.setNewLayerFilterMapNormal(layerName);
  }

  public void quitProject() {
    model.quitProject();
  }

  @Override
  public void changeSelectedLayer(String layer) {
    model.setSelectedLayer(layer);
  }

  @Override
  public void loadProjectFromFile(File file) throws IOException {
    model.load(file);
    view.showLayersPanelAndResetLayers();
    view.addLoadedLayersToLayerPane(model.getLayersOfLoadedProject());
  }

  @Override
  public void addImageToLayerFromFile(File file, int xOffset, int yOffset) throws IOException {
    model.addImageToLayer(model.getSelectedLayer(), file, xOffset, yOffset);
    view.updateImagePanel(model.getCollageImage());
  }

  @Override
  public void saveProjectToFile(File file) throws IOException {
    model.saveProject(file);
  }

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
        System.out.println("Filtering blue");
        break;
      case "Blend Difference":
        filterOption = "blend-difference";
        System.out.println("Blending Difference");
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
