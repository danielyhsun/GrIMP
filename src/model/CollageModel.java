package model;

import java.io.IOException;

public interface CollageModel {

  void addLayer(String layerName);

  void setFilter(String layerName, String filterOption);

  void save(String filePath, String fileName) throws IOException;

  void load(String filePath, String fileName);

  StringBuilder writeToCollageFormat();
}
