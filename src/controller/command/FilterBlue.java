package controller.command;

import controller.CollageCommand;
import model.CollageModel;


/**
 * Command class for filtering blue color attribute on image.
 */
public class FilterBlue implements CollageCommand {

  /**
   * Filters blue attribute on image.
   * @param model represents image processing model object.
   */
  @Override
  public void runCommand(CollageModel model) {
    model.filterBlue();
  }
}
