package controller.command;

import controller.CollageCommand;
import model.CollageModel;

public class AddLayer implements CollageCommand {
  String layerName;

  public AddLayer(String layerName) {
    this.layerName = layerName;
  }

  /**
   * Runs command on image.
   *
   * @param model
   */
  @Override
  public void runCommand(CollageModel model) {
    model.addLayer(layerName);
  }

  @Override
  public String getMessage() {
    return "New layer (" + layerName + ") added";
  }
}
