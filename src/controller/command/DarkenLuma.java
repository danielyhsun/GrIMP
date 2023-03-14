package controller.command;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Command class for darking image based on luma component.
 */
public class DarkenLuma implements CollageCommand {

  /**
   * Darkens image based on luma component.
   * @param model represents image processing model object.
   */
  @Override
  public void runCommand(CollageModel model) {
    model.darkenLuma();
  }
}
