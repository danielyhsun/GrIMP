package controller.command;

import java.io.IOException;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Represents a command object to execute a method to save an image in the model.
 */
public class SaveImage implements CollageCommand {
  private String filePath;

  /**
   * Constructor to create a SaveImage object.
   *
   * @param filePath the location to save the image.
   */
  public SaveImage(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Runs the saveImage() method on the model with the given inputs.
   *
   * @param model represents the model object.
   * @throws IOException if the filePath is invalid.
   */
  @Override
  public void runCommand(CollageModel model) throws IOException {
    model.saveImage(filePath);
  }

  /**
   * Returns a string message associated with this command.
   *
   * @return a string message associated with this command.
   */
  @Override
  public String getMessage() {
    return "Saved as image to: " + filePath;
  }


}
