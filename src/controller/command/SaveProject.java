package controller.command;

import java.io.IOException;

import controller.CollageCommand;
import model.CollageModel;

public class SaveProject implements CollageCommand {
  String filePath;

  public SaveProject(String filePath) {
    this.filePath = filePath;
  }

  public void runCommand(CollageModel model) {
    try {
      model.saveProject(filePath);
    } catch (IOException e) {
      //
    }
  }

  @Override
  public String getMessage() {
    return "Project saved to: " + filePath;
  }
}
