package view;

import java.io.IOException;

import model.CollageModel;

/**
 * View implementation of image processing interface. Capable of rending messages using appendable
 * object.
 */
public class CollageViewImpl implements CollageView {
  private CollageModel model;
  private Appendable appendable;

  /**
   * Constructor for view implementation that takes in only the model.
   *
   * @param model represents image processing model.
   * @throws IllegalArgumentException if given null model.
   */
  public CollageViewImpl(CollageModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model given is null.");
    }

    this.model = model;
    this.appendable = System.out;
  }

  /**
   * Constructor for view implementation if given both model and appendable object.
   *
   * @param model represents image processing model.
   * @param appendable represents appendable object.
   * @throws IllegalArgumentException if given null model or null appendable.
   */
  public CollageViewImpl(CollageModel model, Appendable appendable)
          throws IllegalArgumentException {
    if (model == null || appendable == null) {
      throw new IllegalArgumentException("Given null inputs.");
    }

    this.model = model;
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
