package controller.command;

import java.io.IOException;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Command class for save project command.
 */
public class SaveProject implements CollageCommand {
  String filePath;

  /**
   * Constructor for save project command.
   * @param filePath represents file path for saved project.
   */
  public SaveProject(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Executes save project command.
   * @param model represents current model to execute command.
   * @throws IOException if the file path is invalid.
   */
  @Override
  public void runCommand(CollageModel model) throws IOException {
      model.saveProject(filePath);
  }

  /**
   * Builds message for save project command.
   * @return string message letting user know project has been saved.
   */
  @Override
  public String getMessage() {
    return "Project saved to: " + filePath;
  }
}
