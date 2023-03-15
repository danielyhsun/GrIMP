package controller.command;

import java.io.IOException;

import controller.CollageCommand;
import model.CollageModel;

public class SaveProject implements CollageCommand {
  String filePath;
  String fileName;

  public SaveProject(String filePath, String fileName) {
    this.filePath = filePath;
    this.fileName = fileName;
  }

  public void runCommand(CollageModel model) {
    try {
      model.saveProject(filePath, fileName);
    } catch (IOException e) {
      //
    }
  }

  @Override
  public String getMessage() {
    return null;
  }
}
