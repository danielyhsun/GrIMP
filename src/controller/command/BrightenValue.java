package controller.command;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Command class for brightening image based on value component.
 */
public class BrightenValue implements CollageCommand {

  /**
   * Brightens image based on value component.
   * @param model represents image processing model object.
   */
  @Override
  public void runCommand(CollageModel model) {
    model.brightenValue();
  }
}
