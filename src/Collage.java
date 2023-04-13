import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import controller.CollageController;
import controller.CollageControllerImpl;
import controller.CollageGuiController;
import controller.Features;
import model.CollageModel;
import model.CollageModelImpl;
import view.CollageGuiView;
import view.CollageGuiViewImpl;
import view.CollageView;
import view.CollageViewImpl;

/**
 * This class represents an image processing program. Images can be loaded, have various effects
 * applied to them, and those resulting images can be saved to file.
 */
public final class Collage {

  /**
   * This is the main method for the image processing program. A new image processing model, view,
   * and controller is created and the user can type in commands in the command line.
   * @param args the arguments that the program will parse
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      CollageModel model = new CollageModelImpl();
      CollageGuiView guiView = new CollageGuiViewImpl();
      Features guiController = new CollageGuiController(model, guiView);
    } else if (args.length == 1 && args[0].equals("-text")) {
      CollageModel model = new CollageModelImpl();
      CollageView view = new CollageViewImpl(model);
      CollageControllerImpl controller = new CollageControllerImpl(model, view);
      controller.run();
    } else if (args.length == 2 && args[0].equals("-file")) {
      String path = args[1];
      CollageModel model = new CollageModelImpl();
      CollageView view = new CollageViewImpl(model);
      try {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        CollageController controller = new CollageControllerImpl(model, view, reader);
        controller.run();
        reader.close();
      } catch (IOException e) {
        System.err.println("Error reading script file: " + e.getMessage());
        System.exit(1);
      }
      System.exit(0);
    } else {
      System.err.println("Invalid command-line arguments!");
      System.exit(1);
    }
  }
}
