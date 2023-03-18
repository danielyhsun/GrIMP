import org.junit.Before;

import java.io.InputStreamReader;

import controller.CollageControllerImpl;
import model.CollageModel;
import model.CollageModelImpl;
import view.CollageView;
import view.CollageViewImpl;

public class CollageTest {

  private CollageModel model;
  private CollageView view;
  private Readable read;
  private CollageControllerImpl controller;

  @Before
  public void init() {
    model = new CollageModelImpl();
    view = new CollageViewImpl(model, System.out);
    read = new InputStreamReader(System.in);
    controller = new CollageControllerImpl(model, view, read);
  }

}
