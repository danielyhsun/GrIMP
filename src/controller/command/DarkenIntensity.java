package controller.command;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Command class for darking image based on intensity component.
 */
public class DarkenIntensity implements CollageCommand {

  /**
   * Darkens image based on intensity component.
   * @param model represents image processing model object.
   */
  @Override
  public void runCommand(CollageModel model) {
    model.darkenIntensity();
  }
}
