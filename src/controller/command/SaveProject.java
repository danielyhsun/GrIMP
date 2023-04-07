package controller.command;

import java.io.IOException;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Represents a command object to execute a method to save a project in the model.
 */
public class SaveProject implements CollageCommand {
  private String filePath;

  /**
   * Constructor to create a SaveProject object.
   *
   * @param filePath the location to save the project.
   */
  public SaveProject(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Runs the saveProject() method on the model with the given inputs.
   *
   * @param model represents the model.
   * @throws IOException if the filePath is invalid.
   */
  @Override
  public void runCommand(CollageModel model) throws IOException {
    model.saveProject(filePath);
  }

  /**
   * Returns a string message associated with this command.
   *
   * @return a string message associated with this command.
   */
  @Override
  public String getMessage() {
    return "Project saved to: " + filePath;
  }
}
