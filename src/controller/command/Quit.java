package controller.command;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Command class for quit command.
 */
public class Quit implements CollageCommand {

  /**
   * Executes quit command.
   * @param model represents model for program.
   */
  public void runCommand(CollageModel model) {
    model.quitProject();
  }

  /**
   * Builds message for quit command.
   * @return message letting user know that program has been quit.
   */
  public String getMessage() {
    return "Project has been quit";
  }
}
