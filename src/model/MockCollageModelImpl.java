package model;

/**
 * Mock class for model implementation.
 */
public class MockCollageModelImpl extends CollageModelImpl {
  private int layersAdded = 0;
  private boolean isOpenProject = false;

  /**
   * Mock model constructor.
   * @param height the height of the project canvas.
   * @param width the width of the project canvas.
   */
  @Override
  public void newProject(int height, int width) {
    super.newProject(height, width);
    isOpenProject = true;
  }

  /**
   * Adds layer and increments.
   * @param layerName the name of the layer.
   * @throws IllegalStateException if project hasn't started.
   */
  @Override
  public void addLayer(String layerName) throws IllegalStateException {
    super.addLayer(layerName);
    layersAdded++;
  }

  /**
   * Count of layers added.
   * @return number of layers.
   */
  public int getLayersAdded() {
    return layersAdded;
  }

  /**
   * Check if project is open.
   * @return boolean if project is open.
   */
  public boolean isOpenProject() {
    return isOpenProject;
  }
}
