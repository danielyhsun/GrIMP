package controller.command;

import controller.CollageCommand;
import model.CollageModel;

public class NewProject implements CollageCommand {

  int canvasHeight;
  int canvasWidth;

  public NewProject(int canvasHeight, int canvasWidth) {
    this.canvasHeight = canvasHeight;
    this.canvasWidth = canvasWidth;
  }

  public void runCommand(CollageModel model) {
    model.newProject(canvasHeight, canvasWidth);
  }

  public String getMessage() {
    return "New " + canvasHeight + "x" + canvasWidth + " project created";
  }
}
