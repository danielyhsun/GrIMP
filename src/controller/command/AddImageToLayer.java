package controller.command;

import java.io.IOException;

import controller.CollageCommand;
import model.CollageModel;

/**
 * Represents a command object to execute a method to add an image to a layer in the model.
 */
public class AddImageToLayer implements CollageCommand {
  private String layerName;
  private String filePath;
  private int x;
  private int y;

  /**
   * Constructor to create a SetFilter object.
   *
   * @param layerName the given layer name.
   * @param filePath the location of the image.
   * @param x the x offset from the top-left corner of the layer.
   * @param y the y offset from the top-left corner of the layer.
   */
  public AddImageToLayer(String layerName, String filePath, int x, int y) {
    this.layerName = layerName;
    this.filePath = filePath;
    this.x = x;
    this.y = y;
  }

  /**
   * Runs the addImageToLayer() method on the model with the given inputs.
   *
   * @param model represents the model.
   */
  @Override
  public void runCommand(CollageModel model) throws IOException {
    model.addImageToLayer(layerName, filePath, x, y);
  }

  /**
   * Returns a string message associated with this command.
   *
   * @return a string message associated with this command.
   */
  @Override
  public String getMessage() {
    return "Image from (" + filePath + ") added to " + layerName + " at " + x + " " + y;
  }
}
