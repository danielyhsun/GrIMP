package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.command.AddImageToLayer;
import controller.command.AddLayer;
import controller.command.LoadProject;
import controller.command.NewProject;
import controller.command.Quit;
import controller.command.SaveImage;
import controller.command.SaveProject;
import controller.command.SetFilter;
import model.CollageModel;
import model.CollageModelImpl;
import view.CollageView;

/**
 * Represents implementation of controller interface.
 */
public class CollageControllerImpl implements CollageController {

  private final CollageModel model;
  private final CollageView view;
  private final Readable readable;

  /**
   * Constructor for controller implementation if given only model and view.
   *
   * @param model represents model object for image processing.
   * @param view  represents view object for image processing.
   * @throws IllegalArgumentException if either model or view object are null.
   */
  public CollageControllerImpl(CollageModel model, CollageView view)
          throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Null objects given.");
    }

    this.model = model;
    this.view = view;
    this.readable = new InputStreamReader(System.in);
  }

  /**
   * Constructor for controller implementation if given model, view, and readable object.
   *
   * @param model    represents model object for image processing.
   * @param view     represents view object for image processing.
   * @param readable represents input object.
   * @throws IllegalArgumentException if either model, view, or readable object are null.
   */
  public CollageControllerImpl(CollageModel model, CollageView view, Readable readable) {
    if (model == null || view == null || readable == null) {
      throw new IllegalArgumentException("Null objects given.");
    }

    this.model = model;
    this.view = view;
    this.readable = readable;
  }

  /**
   * Commences method to take in user input and determine what commands or operations to run.
   */
  @Override
  public void run() {
    Scanner scan = new Scanner(readable);

    Map<String, Function<Scanner, CollageCommand>> knownCommands = new HashMap<>();

    knownCommands.put("set-filter", s -> new SetFilter(s.next(), s.next()));
    knownCommands.put("load-project", s -> new LoadProject(s.next()));
    knownCommands.put("new-project", s -> new NewProject(s.nextInt(), s.nextInt()));
    knownCommands.put("add-layer", s -> new AddLayer(s.next()));
    knownCommands.put("add-image-to-layer", s -> new AddImageToLayer(s.next(), s.next(),
            s.nextInt(), s.nextInt()));
    knownCommands.put("save-project", s -> new SaveProject(s.next()));
    knownCommands.put("save-image", s -> new SaveImage(s.next()));
    knownCommands.put("quit", s -> new Quit());

    while (scan.hasNext()) {
      String s = scan.next();
      if (s.equalsIgnoreCase("q") || s.equalsIgnoreCase("quit")) {
        tryRenderMsg("Program quit");
        break;
      }
      CollageCommand c;
      Function<Scanner, CollageCommand> command = knownCommands.getOrDefault(s, null);
      if (command == null) {
        tryRenderMsg(s + " is not a known command.");
      } else {
        try {
          c = command.apply(scan);
          c.runCommand(this.model);
          tryRenderMsg(c.getMessage());
        } catch (IllegalStateException e) {
          if (command == knownCommands.get("add-layer")) {
            tryRenderMsg("Could not add layer: No project currently open!");
          }
          if (command == knownCommands.get("set-filter")) {
            tryRenderMsg("Could not set filter: No project currently open!");
          }
          if (command == knownCommands.get("save-project")) {
            tryRenderMsg("Could not save project: No project currently open!");
          }
          if (command == knownCommands.get("save-image")) {
            tryRenderMsg("Could not save image: No project currently open!");
          }
          if (command == knownCommands.get("add-image-to-layer")) {
            tryRenderMsg("Could not add image to layer: No project currently open!");
          }
          if (command == knownCommands.get("quit")) {
            tryRenderMsg("Could not quit project: No project currently open!");
          }
        } catch (IllegalArgumentException e) {
          if (command == knownCommands.get("new-project")) {
            tryRenderMsg("Project dimensions can't be negative!");
          }
          if (command == knownCommands.get("load-project")) {
            tryRenderMsg("Not a valid project file!");
          }
          if (command == knownCommands.get("set-filter")) {
            tryRenderMsg("Invalid layer/filter!");
          }
        } catch (IOException e) {
          if (command == knownCommands.get("load-project")) {
            tryRenderMsg("File not found!");
          }
          if (command == knownCommands.get("add-image-to-layer")) {
            tryRenderMsg("Image not found!");
          }
          if (command == knownCommands.get("save-project")) {
            tryRenderMsg("Unable to save project!");
          }
          if (command == knownCommands.get("save-image")) {
            tryRenderMsg("Unable to save image!");
          }
        } catch (InputMismatchException e) {
          tryRenderMsg("Invalid Inputs!");
        }
      }

    }
  }

  private void tryRenderMsg(String message) throws RuntimeException {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new RuntimeException("Can not transmit output\n");
    }
  }
}
