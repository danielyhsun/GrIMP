package controller.command;

import controller.CollageCommand;
import model.CollageModel;


/**
 * Command class for filtering green color attribute on image.
 */
public class FilterGreen implements CollageCommand {

  /**
   * Filters green attribute on image.
   * @param model represents image processing model object.
   */
  @Override
  public void runCommand(CollageModel model) {
    model.filterGreen();
  }
}
