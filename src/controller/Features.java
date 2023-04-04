package controller;

import java.io.File;
import java.io.IOException;

public interface Features {
  void newProject(int height, int width);
  void displayCollage();
  void addNewLayer(String layerName);
  void changeSelectedLayer(String layer);
  void loadProjectFromFile(File file) throws IOException;
  void addImageToLayerFromFile(File file, int xOffset, int yOffset) throws IOException;
  void saveProjectToFile(File file) throws IOException;
  void filterPicker(String filterOption, String layerName);
}
