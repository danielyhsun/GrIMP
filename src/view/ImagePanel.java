package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Represents image panel to help display the buffered image for the program.
 */
public class ImagePanel extends JPanel {

  private BufferedImage image;

  /**
   * Image panel contructor.
   * @param image represents buffered image.
   */
  public ImagePanel(BufferedImage image) {
    this.image = image;
  }

  /**
   * Displays image using graphis.
   * @param g the <code>Graphics</code> object to protect.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(image, 0, 0, null);
  }

  /**
   * Sets image for the image panel.
   * @param i represents buffered image.
   */
  protected void setImage(BufferedImage i) {
    image = i;
    repaint();
  }

}
