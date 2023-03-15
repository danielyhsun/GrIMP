package controller.command;

import controller.CollageCommand;
import model.CollageModel;

public class LoadProject implements CollageCommand {
  String filePath;

  public LoadProject(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void runCommand(CollageModel model) {
    model.load(filePath);
  }

  @Override
  public String getMessage() {
    return filePath + " was successfully loaded.";
  }
}
