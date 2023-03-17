package view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import model.CollageModel;
import model.CollageModelImpl;
import view.CollageView;
import view.CollageViewImpl;


/**
 * Tester class for view.
 */
public class CollageViewImplTest {

  /**
   * Tester method for view constructor if only given model.
   */
  @Test
  public void viewConstructorWorks() {
    CollageModel model = new CollageModelImpl();
    CollageView view = new CollageViewImpl(model);

    assertNotEquals(null, view);
  }

  /**
   * Tester method for view constructor if given only null model.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testViewConstructorNullModel() {
    CollageModel model = null;
    CollageView view = new CollageViewImpl(model);
  }

  /**
   * Tester method for view constructor if given model and appendable object.
   */
  @Test
  public void viewConstructorTwoWorks() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);

    assertNotEquals(null, view);
  }

  /**
   * Tester method for view constructor if given null model and appendable object.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testViewConstructorTwoGivenNullModel() {
    CollageModel model = null;
    Appendable appendable = new StringBuilder();
    CollageView view = new CollageViewImpl(model, appendable);
  }

  /**
   * Tester method for view constructor if given model and null appendable object.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testViewConstructorTwoGivenNullAppendable() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = null;
    CollageView view = new CollageViewImpl(model, appendable);
  }

  /**
   * Tester for render message.
   */
  @Test
  public void renderMessageWorks() {
    CollageModel model = new CollageModelImpl();
    Appendable appendable = new StringBuilder("Hello");
    CollageView view = new CollageViewImpl(model, appendable);

    try {
      view.renderMessage("Hello");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertEquals("HelloHello\n", appendable.toString());
  }

}