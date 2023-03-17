package controller.command;

import java.io.FileNotFoundException;
import java.io.IOException;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Represents a command object to execute a method to load a project file in the model.
 */
public class LoadProject implements CollageCommand {
  String filePath;

  /**
   * Constructor to create a LoadProject object.
   *
   * @param filePath the location of the project file to be loaded
   */
  public LoadProject(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Runs the load() method on the model with the given input.
   *
   * @param model the model
   */
  @Override
  public void runCommand(CollageModel model) throws IOException {
    model.load(filePath);
  }

  /**
   * Returns a string message associated with this command.
   *
   * @return a string message associated with this command
   */
  @Override
  public String getMessage() {
    return filePath + " was successfully loaded.";
  }
}
