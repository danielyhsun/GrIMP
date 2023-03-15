package controller.command;

import controller.CollageCommand;
import model.CollageModel;

public class LoadProject implements CollageCommand {
  String filePath;
  String fileName;

  public LoadProject(String filePath, String fileName) {
    this.filePath = filePath;
    this.fileName = fileName;
  }

  @Override
  public void runCommand(CollageModel model) {
    model.load(filePath, fileName);
  }

  @Override
  public String getMessage() {
    return null;
  }
}
