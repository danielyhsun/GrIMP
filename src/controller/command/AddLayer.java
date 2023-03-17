package controller.command;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Command class for adding layers to project.
 */
public class AddLayer implements CollageCommand {
  String layerName;

  /**
   * Add layer constructor.
   * @param layerName name of layer to add.
   */
  public AddLayer(String layerName) {
    this.layerName = layerName;
  }

  /**
   * Runs command on image.
   *
   * @param model represents the current model object containing the project.
   */
  @Override
  public void runCommand(CollageModel model) {
    model.addLayer(layerName);
  }

  /**
   * States that this class is for adding layers.
   * @return that a new layer has been added as a string.
   */
  @Override
  public String getMessage() {
    return "New layer (" + layerName + ") added";
  }
}
