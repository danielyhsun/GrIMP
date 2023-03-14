package controller.command;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Command class for darkening image based on value component.
 */
public class DarkenValue implements CollageCommand {

  /**
   * Darkens image based on value component.
   * @param model represents image processing model object.
   */
  @Override
  public void runCommand(CollageModel model) {
    model.darkenValue();
  }
}
