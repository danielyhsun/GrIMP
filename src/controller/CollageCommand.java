package controller;

import model.CollageModel;

/**
 * Interface represents commands for image processing.
 */
public interface CollageCommand {

  /**
   * Runs command on image.
   */
  void runCommand(CollageModel model);

  String getMessage();

}
