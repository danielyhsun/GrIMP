package controller.command;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Represents a command object to execute a method to quit a project in the model.
 */
public class Quit implements CollageCommand {

  /**
   * Runs the quitProject() method on the model.
   *
   * @param model the model
   */
  public void runCommand(CollageModel model) {
    model.quitProject();
  }

  /**
   * Returns a string message associated with this command.
   *
   * @return a string message associated with this command
   */
  public String getMessage() {
    return "Project has been quit";
  }
}
