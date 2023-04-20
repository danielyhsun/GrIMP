package controller;

import java.io.IOException;

import model.CollageModel;

/**
 * Interface represents commands for image processing.
 * Example is "new-project" command.
 */
public interface CollageCommand {

  /**
   * Runs a method on the model using given inputs.
   */
  void runCommand(CollageModel model) throws IOException;

  /**
   * Returns a string message associated with this command.
   *
   * @return a string message associated with this command.
   */
  String getMessage();

}
