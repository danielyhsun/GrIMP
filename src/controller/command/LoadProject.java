package controller.command;

import java.io.FileNotFoundException;
import java.io.IOException;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Represents command class for loading project.
 */
public class LoadProject implements CollageCommand {
  String filePath;

  /**
   * Load project constructor.
   * @param filePath represents file path of project.
   */
  public LoadProject(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Loads project into project.
   * @param model represents current model holding project.
   * @throws IOException if the file path is invalid.
   */
  @Override
  public void runCommand(CollageModel model) throws IOException {
    model.load(filePath);
  }

  /**
   * States that the file path was loaded into program.
   * @return string message that file was loaded.
   */
  @Override
  public String getMessage() {
    return filePath + " was successfully loaded.";
  }
}
