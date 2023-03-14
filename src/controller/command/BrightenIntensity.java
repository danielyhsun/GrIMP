package controller.command;

import controller.CollageCommand;
import model.CollageModel;
import model.CollageModel;

/**
 * Command class for brightening image based on intensity component.
 */
public class BrightenIntensity implements CollageCommand {

  /**
   * Brightens image based on intensity component.
   * @param model represents image processing model object.
   */
  @Override
  public void runCommand(CollageModel model) {
    model.setFilter(, "brighten-intensity");
  }
}
