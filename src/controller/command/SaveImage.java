package controller.command;

import java.io.IOException;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Command class for save-image command.
 */
public class SaveImage implements CollageCommand {
  String filePath;

  /**
   * Save image command constructor.
   * @param filePath represents file path of image.
   */
  public SaveImage(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Executes save image command.
   * @param model represents model object.
   * @throws IOException if the file path is invalid.
   */
  @Override
  public void runCommand(CollageModel model) throws IOException {
    model.saveImage(filePath);
  }

  /**
   * Builds message for save image command.
   * @return string telling user that image has been saved to file path.
   */
  @Override
  public String getMessage() {
    return "Saved as image to: " + filePath;
  }


}
