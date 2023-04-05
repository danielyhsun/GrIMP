package controller;

import org.junit.Test;

import java.io.InputStreamReader;
import java.io.StringReader;

import model.CollageModel;
import model.CollageModelImpl;
import view.CollageGuiView;
import view.CollageGuiViewImpl;
import view.CollageView;
import view.CollageViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class for GUI Controller class.
 */
public class CollageGuiControllerTest {

  /**
   *
   */
  public void testNewProject() {
    CollageModel model = new CollageModelImpl();
    CollageGuiView view = new CollageGuiViewImpl(model);
    CollageGuiController controller = new CollageGuiController(model, view);

    controller.newProject(500, 500);

  }

  public void testDisplayCollage() {
  }

  public void testAddNewLayer() {
  }

  public void testQuitProject() {
  }

  public void testChangeSelectedLayer() {
  }

  public void testLoadProjectFromFile() {
  }

  public void testAddImageToLayerFromFile() {
  }

  public void testSaveProjectToFile() {
  }

  public void testFilterPicker() {
  }
}