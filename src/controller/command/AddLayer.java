package controller.command;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Represents a command object to execute a method to add a new layer in the model.
 */
public class AddLayer implements CollageCommand {
  String layerName;

  /**
   * Constructor to create an AddLayer object.
   *
   * @param layerName the given name of the layer to be created.
   */
  public AddLayer(String layerName) {
    this.layerName = layerName;
  }

  /**
   * Runs the addLayer() method on the model with the given input.
   *
   * @param model represents the model.
   */
  @Override
  public void runCommand(CollageModel model) {
    model.addLayer(layerName);
  }

  /**
   * Returns a string message associated with this command.
   *
   * @return a string message associated with this command.
   */
  @Override
  public String getMessage() {
    return "New layer (" + layerName + ") added";
  }
}
