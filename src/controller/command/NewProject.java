package controller.command;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Represents a command object to execute a method to create a new project in the model.
 */
public class NewProject implements CollageCommand {

  int canvasHeight;
  int canvasWidth;

  /**
   * Constructor to create a NewProject object.
   *
   * @param canvasHeight the given canvas height.
   * @param canvasWidth the given canvas width.
   */
  public NewProject(int canvasHeight, int canvasWidth) {
    this.canvasHeight = canvasHeight;
    this.canvasWidth = canvasWidth;
  }

  /**
   * Runs the newProject() method on the model with the given inputs.
   *
   * @param model represents the model.
   */
  public void runCommand(CollageModel model) {
    model.newProject(canvasHeight, canvasWidth);
  }

  /**
   * Returns a string message associated with this command.
   *
   * @return a string message associated with this command.
   */
  public String getMessage() {
    return "New " + canvasHeight + "x" + canvasWidth + " project created";
  }
}
