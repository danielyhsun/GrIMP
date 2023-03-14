package controller;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.command.BrightenIntensity;
import controller.command.BrightenLuma;
import controller.command.BrightenValue;
import controller.command.DarkenIntensity;
import controller.command.DarkenLuma;
import controller.command.DarkenValue;
import controller.command.FilterBlue;
import controller.command.FilterGreen;
import controller.command.FilterRed;
import model.CollageModel;
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
   * @param model represents model object for image processing.
   * @param view represents view object for image processing.
   * @throws IllegalArgumentException if either model or view object are null.
   */
  public CollageControllerImpl(CollageModel model, CollageView view) throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Null objects given.");
    }

    this.model = model;
    this.view = view;
    this.readable = new InputStreamReader(System.in);
  }

  /**
   * Constructor for controller implementation if given model, view, and readable object.
   * @param model represents model object for image processing.
   * @param view represents view object for image processing.
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

    knownCommands.put("brighten-value", s -> new BrightenValue());
    knownCommands.put("brighten-intensity", s -> new BrightenIntensity());
    knownCommands.put("brighten-luma", s -> new BrightenLuma());
    knownCommands.put("darken-value", s -> new DarkenValue());
    knownCommands.put("darken-intensity", s -> new DarkenIntensity());
    knownCommands.put("darken-luma", s -> new DarkenLuma());
    knownCommands.put("filter-red", s -> new FilterRed());
    knownCommands.put("filter-green", s -> new FilterGreen());
    knownCommands.put("filter-blue", s -> new FilterBlue());

    
  }
}
