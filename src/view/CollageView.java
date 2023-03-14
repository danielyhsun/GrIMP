package view;

import java.io.IOException;

/**
 * Represents View interface for image processing.
 */
public interface CollageView {

  /**
   * Renders message to Appendable object.
   * @param message represents what is being sent to Appendable.
   * @throws IOException if cannot be transmitted.
   */
  void renderMessage(String message) throws IOException;
}
