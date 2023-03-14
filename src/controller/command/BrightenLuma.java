package controller.command;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Command class for brightening image based on luma component.
 */
public class BrightenLuma implements CollageCommand {

  /**
   * Brightens image based on luma component.
   * @param model represents image processing model object.
   */
  @Override
  public void runCommand(CollageModel model) {
    model.brightenLuma();
  }
}
