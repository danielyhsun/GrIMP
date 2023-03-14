package controller.command;

import controller.CollageCommand;
import model.CollageModel;


/**
 * Command class for filtering red color attribute on image.
 */
public class FilterRed implements CollageCommand {

  /**
   * Filters red attribute on image.
   * @param model represents image processing model object.
   */
  @Override
  public void runCommand(CollageModel model) {
    model.filterRed();
  }
}
