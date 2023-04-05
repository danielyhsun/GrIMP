package controller.command;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Represents a command object to execute a method to set a layer's filter in the model.
 */
public class SetFilter implements CollageCommand {
  String layerName;
  String filterOption;

  /**
   * Constructor to create a SetFilter object.
   *
   * @param layerName the given layer name.
   * @param filterOption the given filter option.
   */
  public SetFilter(String layerName, String filterOption) {
    this.layerName = layerName;
    this.filterOption = filterOption;
  }

  /**
   * Runs the setFilter() method on the model with the given inputs.
   *
   * @param model represents the model.
   */
  @Override
  public void runCommand(CollageModel model) {
    model.setFilter(layerName, filterOption);
  }

  /**
   * Returns a string message associated with this command.
   *
   * @return a string message associated with this command.
   */
  @Override
  public String getMessage() {
    return filterOption + " applied to layer " + layerName;
  }
}
