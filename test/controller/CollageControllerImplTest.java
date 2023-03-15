package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;

import model.CollageModel;
import model.CollageModelImpl;
import view.CollageView;
import view.CollageViewImpl;

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
   * Tester method for rum method.
   */
  @Test
  public void runWorks() {

  }

}