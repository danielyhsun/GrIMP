package controller.command;

import controller.CollageCommand;
import model.CollageModel;
import model.CollageModelImpl;

public class Quit implements CollageCommand {

  public void runCommand(CollageModel model) {
    model.quitProject();
  }

  public String getMessage() {
    return "Project has been quit";
  }
}
