package controller.command;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Command class for new project command.
 */
public class NewProject implements CollageCommand {

  int canvasHeight;
  int canvasWidth;

  /**
   * New project constructor.
   * @param canvasHeight represents height of project.
   * @param canvasWidth represents width of project.
   */
  public NewProject(int canvasHeight, int canvasWidth) {
    this.canvasHeight = canvasHeight;
    this.canvasWidth = canvasWidth;
  }

  /**
   * Executes new project command.
   * @param model represents model that is running program.
   */
  public void runCommand(CollageModel model) {
    model.newProject(canvasHeight, canvasWidth);
  }

  /**
   * Builds message describing that project has been created.
   * @return string message stating project has been created.
   */
  public String getMessage() {
    return "New " + canvasHeight + "x" + canvasWidth + " project created";
  }
}
