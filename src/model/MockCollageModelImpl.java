package model;

public class MockCollageModelImpl extends CollageModelImpl {
  private int layersAdded = 0;
  private boolean isOpenProject = false;

  @Override
  public void newProject(int height, int width) {
    super.newProject(height, width);
    isOpenProject = true;
  }
  @Override
  public void addLayer(String layerName) throws IllegalStateException {
    super.addLayer(layerName);
    layersAdded++;
  }

  public int getLayersAdded() {
    return layersAdded;
  }

  public boolean isOpenProject() {
    return isOpenProject;
  }
}
