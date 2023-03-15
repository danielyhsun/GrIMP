package controller.command;

import controller.CollageCommand;
import model.CollageModel;

public class SaveImage implements CollageCommand {
  String filePath;

  public SaveImage(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void runCommand(CollageModel model) {
    model.saveImage(filePath);
  }

  @Override
  public String getMessage() {
    return "Saved as image to: " + filePath;
  }


}
