package view;

import java.io.IOException;

import model.CollageModel;

/**
 * View implementation of image processing interface. Capable of rending messages using appendable
 * object.
 */
public class CollageViewImpl implements CollageView {
  private Appendable appendable;

  /**
   * Constructor for view implementation that initializes the appendable object to System.out.
   */
  public CollageViewImpl() {

    this.appendable = System.out;
  }

  /**
   * Constructor for view implementation that initializes the appendable object to the given
   * appendable object.
   *
   * @param appendable the appendable object.
   * @throws IllegalArgumentException if given null model or null appendable.
   */
  public CollageViewImpl(Appendable appendable)
          throws IllegalArgumentException {
    if (appendable == null) {
      throw new IllegalArgumentException("Given null inputs.");
    }

    this.appendable = appendable;
  }

  /**
   * Renders message to Appendable object.
   *
   * @param message represents what is being sent to Appendable.
   * @throws IOException if the message cannot be transmitted.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      appendable.append(message).append(System.lineSeparator());
    } catch (Exception e) {
      throw new IOException(e.getMessage());
    }
  }
}
