package controller.command;

import java.io.IOException;

import controller.CollageCommand;
import model.CollageModel;

public class AddImageToLayer implements CollageCommand {
  String layerName;
  String filePath;
  int x;
  int y;

  public AddImageToLayer(String layerName, String filePath, int x, int y) {
    this.layerName = layerName;
    this.filePath = filePath;
    this.x = x;
    this.y = y;
  }

  /**
   * Runs command on image.
   *
   * @param model
   */
  @Override
  public void runCommand(CollageModel model) throws IOException {
    model.addImageToLayer(layerName, filePath, x, y);
  }

  @Override
  public String getMessage() {
    return "Image from (" + filePath + ") added to " + layerName + " at " + x + " " + y;
  }
}
