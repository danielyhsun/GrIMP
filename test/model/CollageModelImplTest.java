package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import controller.CollageController;
import controller.CollageControllerImpl;
import view.CollageView;
import view.CollageViewImpl;

/**
 * Tester class for model.
 */
public class CollageModelImplTest {

  /**
   * Tester class for model constructor.
   */
  @Test
  public void modelConstructorWorks() {
    CollageModel model = new CollageModelImpl();
    assertNotEquals(null, model);
  }

  /**
   * Tester method for new project method.
   */
  @Test
  public void newProjectWorks() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.newProject(100, 100);

    assertEquals("New 100x100 project created\n", appendable.toString());
  }

  /**
   * Tester method for new project if given invalid dimensions.
   */
  @Test
  public void testNewProjectBadDimensions() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 -100");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.newProject(100, -100);

    assertEquals("Project dimensions cannot be negative!\n", appendable.toString());
  }

  /**
   * Tester method for addLayer method.
   */
  @Test
  public void addLayerWorks() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 add-layer layer");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.addLayer("layer1");

    assertEquals("New 100x100 project created\nNew layer (layer) added\n", appendable.toString());
  }

  /**
   * Tester method if layer method is already taken.
   */
  @Test
  public void layerMethodAlreadyTaken() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 add-layer layer");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.addLayer("layer");

    assertEquals("New 100x100 project created\nNew layer (layer) added\n", appendable.toString());
  }

  /**
   * Tester method if addLayer is called and project hasn't started.
   */
  @Test (expected = IllegalStateException.class)
  public void testAddLayerProjectNoStart() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("add-layer layer");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.addLayer("layer1");
  }

  /**
   * Tester method for add image to layer method.
   */
  @Test
  public void addImageToLayerWorks() throws IOException {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 add-layer layer add-image-to-layer layer tako.ppm 0 0");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.addImageToLayer("layer", "tako.ppm", 1, 1);

    assertEquals("New 100x100 project created\nNew layer (layer) added\nImage from (tako.ppm) added to layer at 0 0\n", appendable.toString());
  }

  /**
   * Tester method for add image to layer method if project hasn't started.
   */
  @Test (expected = IllegalStateException.class)
  public void testAddImageTolayerProjectNotStarted() throws IOException {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("add-image-to-layer layer tako.ppm 0 0");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.addImageToLayer("layer", "tako.ppm", 1, 1);

    assertEquals("No project is currently open!\n", appendable.toString());
  }

  /**
   * Tester method for setFilter method.
   */
  @Test
  public void setFilterWorks() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 add-layer layer set-filter layer brighten");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.setFilter("layer", "brighten");

    assertEquals("New 100x100 project created\nNew layer (layer) added\nbrighten applied to layer layer\n", appendable.toString());
  }

  /**
   * Tester method for setFiler method if project hasn't started.
   */
  @Test (expected = IllegalStateException.class)
  public void testSetFilterProjectHasNotStarted() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("set-filter layer brighten");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.setFilter("layer", "brighten");

    assertEquals("No project is currently open!\n", appendable.toString());
  }

  /**
   * Tester method for save project method.
   */
  @Test
  public void saveProjectWorks() throws IOException {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 add-layer layer1 save-project CollageProj");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.saveProject("CollageProj");

    assertEquals("New 100x100 project created\nNew layer (layer1) added\nProject saved to: CollageProj\n", appendable.toString());
  }

  /**
   * Tester method for save project if project hasn't started.
   */
  @Test (expected = IllegalStateException.class)
  public void testSaveProjectHasNotStarted() throws IOException {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("save-project CollageProj");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.saveProject("CollageProj");

    assertEquals("No project is currently open!\n", appendable.toString());
  }

  /**
   * Tester method for save image method.
   */
  @Test
  public void saveImageWorks() throws IOException {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 save-image takodi.ppm");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.saveImage("takodi.ppm");

    assertEquals("New 100x100 project created\nSaved as image to: takodi.ppm\n", appendable.toString());
  }

  /**
   * Tester method for saving image given bad input.
   */
  @Test (expected = IOException.class)
  public void testSaveBadImage() throws IOException {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 save-image /michelin/encore");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.saveImage("/michelin/encore");

    assertEquals("Unable to save image!\n", appendable.toString());
  }

  /**
   * Tester method for save image if project hasn't started.
   */
  @Test (expected = IllegalStateException.class)
  public void testSaveImageProjectNotStarted() throws IOException {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("save-image takodi.ppm");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.saveImage("takodi.ppm");

    assertEquals("No project is currently open!\n", appendable.toString());
  }

  /**
   * Tester method for load method.
   */
  @Test
  public void loadWorks() throws IOException {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 add-layer layer1 save-project CollageProj load-project CollageProj");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.load("CollageProj");

    assertEquals("New 100x100 project created\nNew layer (layer1) added\nProject saved to: CollageProj\nCollageProj was successfully loaded.\n", appendable.toString());
  }

  /**
   * Tester method for load method given bad file.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testLoadBadFile() throws IOException {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 save-image takodi.ppm load-project takodi.ppm");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.load("takodi.ppm");

    assertEquals("New 100x100 project created\nSaved as image to: takodi.ppm\nNot a valid project file!\n", appendable.toString());
  }

  /**
   * Tester method for quit function.
   */
  @Test
  public void quitWorks() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 quit add-layer bing");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.quitProject();

    assertEquals("New 100x100 project created\nProgram quit\n", appendable.toString());
  }

  /**
   * Tester method for quit function if project hasn't started.
   */
  @Test (expected = IllegalStateException.class)
  public void testQuitProjectNotStarted() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("quit");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();
    model.quitProject();

    assertEquals("No project is currently open!\n", appendable.toString());
  }

}