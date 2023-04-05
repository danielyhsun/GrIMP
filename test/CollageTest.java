import org.junit.Before;

import java.io.InputStreamReader;

import controller.CollageControllerImpl;
import model.CollageModel;
import model.CollageModelImpl;
import view.CollageView;
import view.CollageViewImpl;

/**
 * Collage program test class.
 */
public class CollageTest {

  @Before
  public void init() {
    CollageModel model;
    CollageView view;
    Readable read;
    CollageControllerImpl controller;


    model = new CollageModelImpl();
    view = new CollageViewImpl(model, System.out);
    read = new InputStreamReader(System.in);
    controller = new CollageControllerImpl(model, view, read);
  }

}
