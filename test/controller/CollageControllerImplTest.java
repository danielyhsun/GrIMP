package controller;

import org.junit.Test;

import java.io.InputStreamReader;
import java.io.StringReader;

import model.CollageModel;
import model.CollageModelImpl;
import view.CollageView;
import view.CollageViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class for controller implementation.
 */
public class CollageControllerImplTest {

  /**
   * Tester method for controller implementation if given only model and view.
   */
  @Test
  public void controllerImplementationConstructorWorksOne() {
    CollageModel model = new CollageModelImpl();
    CollageView view = new CollageViewImpl(model);
    CollageController controller = new CollageControllerImpl(model, view);

    assertNotEquals(null, model);
    assertNotEquals(null, view);
  }

  /**
   * Tester method for controller implementation if given null model.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testControllerImplementationConstructorModelExceptionOne() {
    CollageModel model = null;
    CollageView view = new CollageViewImpl(model);
    CollageController controller = new CollageControllerImpl(model, view);
  }

  /**
   * Tester method for controller implementation if given null view.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testControllerImplementationConstructorViewExceptionOne() {
    CollageModel model = new CollageModelImpl();
    CollageView view = null;
    CollageController controller = new CollageControllerImpl(model, view);
  }

  /**
   * Tester method for controller implementation when given model, view, and readable object.
   */
  @Test
  public void controllerImplementationConstructorWorksTwo() {
    CollageModel model = new CollageModelImpl();
    CollageView view = new CollageViewImpl(model);
    Readable readable = new InputStreamReader(System.in);
    CollageController controller = new CollageControllerImpl(model, view, readable);

    assertNotEquals(null, model);
    assertNotEquals(null, view);
    assertNotEquals(null, readable);
  }

  /**
   * Tester method for controller implementation if given null model.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testControllerImplementationConstructorModelExceptionTwo() {
    CollageModel model = null;
    CollageView view = new CollageViewImpl(model);
    Readable readable = new InputStreamReader(System.in);
    CollageController controller = new CollageControllerImpl(model, view, readable);
  }

  /**
   * Tester method for controller implementation if given null view.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testControllerImplementationConstructorViewExceptionTwo() {
    CollageModel model = new CollageModelImpl();
    CollageView view = null;
    Readable readable = new InputStreamReader(System.in);
    CollageController controller = new CollageControllerImpl(model, view, readable);
  }

  /**
   * Tester method for controller implementation if given null appendable object.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testControllerImplementationConstructorAppendableExceptionTwo() {
    CollageModel model = new CollageModelImpl();
    CollageView view = new CollageViewImpl(model);
    Readable readable = null;
    CollageController controller = new CollageControllerImpl(model, view, readable);
  }

  /**
   * Test if new-project command works.
   */
  @Test
  public void newProjectWorks() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("New 100x100 project created\n", appendable.toString());
  }

  /**
   * Test if new-project command is called but given invalid dimensions.
   */
  @Test
  public void testNewProject() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 -100 new-project -100 100 new-project 100 -100");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("Project dimensions can't be negative!\nProject dimensions can't be negative!" +
            "\nProject dimensions can't be negative!\n", appendable.toString());
  }

  /**
   * Test if add-layer command works.
   */
  @Test
  public void addLayerWorks() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 add-layer layer");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("New 100x100 project created\nNew layer (layer) added\n", appendable.toString());
  }

  /**
   * Test if add-layer command is called before project has started.
   */
  @Test
  public void testAddLayerBeforeProjectStart() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("add-layer layer");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("Could not add layer: No project currently open!\n", appendable.toString());
  }

  /**
   * Test add-image-to-layer command.
   */
  @Test
  public void addImageToLayerWorks() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 add-layer layer add-image-to-layer layer res/grogu.ppm 0 0");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("New 100x100 project created\nNew layer (layer) added\nImage from (res/grogu.ppm) added to layer at 0 0\n", appendable.toString());
  }

  /**
   * Test if add-image-to-layer command is called with incorrect image input.
   */
  @Test
  public void testAddImageToLayerWithBadImage() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 add-layer layer add-image-to-layer layer random 0 0");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("New 100x100 project created\nNew layer (layer) added\nImage not found!\n", appendable.toString());
  }

  /**
   * Test if add-image-to-layer command is called with incorrect inputs.
   */
  @Test
  public void testAddImageToLayerWithBadInputs() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 add-layer layer add-image-to-layer 0 0 layer res/grogu.ppm");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("New 100x100 project created\nNew layer (layer) added\nInvalid Inputs!\nlayer is not a known command.\n" +
            "res/grogu.ppm is not a known command.\n", appendable.toString());
  }

  /**
   * Test if set-filter command works.
   */
  @Test
  public void setFilterWorks() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 add-layer layer set-filter layer brighten");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("New 100x100 project created\nNew layer (layer) added\nbrighten applied to layer layer\n", appendable.toString());
  }

  /**
   * Test if set-filter command works.
   */
  @Test
  public void setFilterColorWorks() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 add-layer layer set-filter layer filter-green");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("New 100x100 project created\nNew layer (layer) added\nfilter-green applied to layer layer\n", appendable.toString());
  }

  /**
   * testSetFilterOnInvalidLayer
   */
  @Test
  public void testSetFilterOnInvalidLayer() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 add-layer layer1 set-filter layer2 filter-green");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("New 100x100 project created\nNew layer (layer1) added\nInvalid layer/filter!\n", appendable.toString());
  }

  /**
   * Test if set-filer command is called before project has started.
   */
  @Test
  public void testSetFilterBeforeProjectStart() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("set-filter layer green");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("Could not set filter: No project currently open!\n", appendable.toString());
  }

  /**
   * Test if given unknown command.
   */
  @Test
  public void testUnknownCommand() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 randomness");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("New 100x100 project created\nrandomness is not a known command.\n", appendable.toString());
  }



  /**
   * Tests quit functionality of run method.
   */
  @Test
  public void testQuit() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 quit add-layer bing");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("New 100x100 project created\nProgram quit\n", appendable.toString());
  }

  /**
   * Test save-image command works.
   */
  @Test
  public void saveImageWorks() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 save-image grogudi.ppm");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("New 100x100 project created\nSaved as image to: grogudi.ppm\n", appendable.toString());
  }

  /**
   * Test save-image if called incorrectly.
   */
  @Test
  public void testSaveImageBad() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 save-image /michelin/encore");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("New 100x100 project created\nUnable to save image!\n", appendable.toString());
  }

  /**
   * Test save-project command works.
   */
  @Test
  public void saveProjectWorks() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 add-layer layer1 save-project CollageProj");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("New 100x100 project created\nNew layer (layer1) added\nProject saved to: CollageProj\n", appendable.toString());
  }

  /**
   * Test save-project command given bad input.
   */
  @Test
  public void testSaveProjectBad() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("save-project CollageProj");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("Could not save project: No project currently open!\n", appendable.toString());
  }

  /**
   * Test load-project command works.
   */
  @Test
  public void loadProjectWorks() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 add-layer layer1 save-project CollageProj load-project CollageProj");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("New 100x100 project created\nNew layer (layer1) added\nProject saved to: CollageProj\nCollageProj was successfully loaded.\n", appendable.toString());
  }

  /**
   * Test load-project command works given invalid file
   */
  @Test
  public void testloadProjectBad() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 save-image grogudi.ppm load-project grogudi.ppm");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("New 100x100 project created\nSaved as image to: grogudi.ppm\nNot a valid project file!\n", appendable.toString());
  }

  /**
   * Test tryRenderMsg method.
   */
  @Test
  public void tryRenderMsgWorks() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
    Readable readable = new StringReader("new-project 100 100 new-project 100 100 new-project 100 100");
    CollageController controller = new CollageControllerImpl(model, view, readable);

    controller.run();

    assertEquals("New 100x100 project created\nNew 100x100 project created\nNew 100x100 project created\n", appendable.toString());
  }

}