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
    CollageModel model = new CollageModelImpl();
    CollageView view = new CollageViewImpl(model);
    CollageGuiView guiView = new CollageGuiViewImpl(model);
    Features guiController = new CollageGuiController(model, guiView);
    CollageControllerImpl controller = new CollageControllerImpl(model, view);
    controller.run();
  }

}
