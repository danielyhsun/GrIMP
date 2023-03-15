package controller.command;

import java.io.IOException;

import controller.CollageCommand;
import model.CollageModel;

public class SaveProject implements CollageCommand {
  String filePath;

  public SaveProject(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void runCommand(CollageModel model) throws IOException {
      model.saveProject(filePath);
  }

  @Override
  public String getMessage() {
    return "Project saved to: " + filePath;
  }
}
