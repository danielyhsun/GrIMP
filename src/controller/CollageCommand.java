package controller;

import java.io.IOException;

import model.CollageModel;

/**
 * Interface represents commands for image processing.
 */
public interface CollageCommand {

  /**
   * Runs command on image.
   */
  void runCommand(CollageModel model) throws IOException;

  /**
   * Returns a message to be shown after a command is run.
   * @return the message content
   */
  String getMessage();

}
