package controller.command;

import java.io.IOException;

import controller.CollageCommand;
import model.CollageModel;

public class SetFilter implements CollageCommand {
  String layerName;
  String filterOption;

  public SetFilter(String layerName, String filterOption) {
    this.layerName= layerName;
    this.filterOption = filterOption;
  }

  @Override
  public void runCommand(CollageModel model) {
    model.setFilter(layerName, filterOption);
  }

  @Override
  public String getMessage() {
    return filterOption + " applied to layer " + layerName;
  }
}
