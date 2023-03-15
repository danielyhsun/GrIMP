package model;

import java.io.IOException;

public interface CollageModel {

  void newProject(int canvasHeight, int canvasWidth) throws IllegalArgumentException;

  void addLayer(String layerName) throws IllegalStateException;

  void addImageToLayer(String layerName, String filePath, int x, int y) throws IllegalStateException;

  void setFilter(String layerName, String filterOption) throws IllegalStateException;

  void saveProject(String filePath) throws IOException;

  void saveImage(String filePath) throws IOException;

  void load(String filePath);
}
