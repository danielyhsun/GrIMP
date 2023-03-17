package controller.command;

import java.io.IOException;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Command class for set filer command.
 */
public class SetFilter implements CollageCommand {
  String layerName;
  String filterOption;

  /**
   * Set filter command constructor.
   * @param layerName represents name of layer to be filtered.
   * @param filterOption represents what type of filter is being applied to layer.
   */
  public SetFilter(String layerName, String filterOption) {
    this.layerName= layerName;
    this.filterOption = filterOption;
  }

  /**
   * Executes filter command on layer.
   * @param model represents model for current program.
   */
  @Override
  public void runCommand(CollageModel model) {
    model.setFilter(layerName, filterOption);
  }

  /**
   * Builds message for set filter command.
   * @return message describing what filter has been used and to what layer.
   */
  @Override
  public String getMessage() {
    return filterOption + " applied to layer " + layerName;
  }
}
