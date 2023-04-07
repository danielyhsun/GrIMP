package controller;

import org.junit.Test;

import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


import model.CollageModel;
import model.CollageModelImpl;
import view.CollageGuiView;
import view.CollageGuiViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for GUI Controller class.
 */
public class CollageGuiControllerTest {

  /**
   * Test new project method.
   */
  @Test
  public void testNewProject() {
    CollageModel model = new CollageModelImpl();
    CollageGuiView view = new CollageGuiViewImpl(model);
    CollageGuiController controller = new CollageGuiController(model, view);

    controller.newProject(500, 500);

    assertEquals(500, model.getCollageImage().getWidth());
    assertEquals(500, model.getCollageImage().getHeight());
  }

  /**
   * Test display collage method.
   */
  @Test
  public void testDisplayCollage() {
    CollageModel model = new CollageModelImpl();
    CollageGuiView view = new CollageGuiViewImpl(model);
    CollageGuiController controller = new CollageGuiController(model, view);

    controller.newProject(500, 500);

    BufferedImage collageImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
    view.updateImagePanel(model.getCollageImage());
    controller.displayCollage();
    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image"));
    JLabel imageLabel = new JLabel(new ImageIcon(collageImage));
    imagePanel.add(imageLabel);

    assertNotNull(collageImage);
  }


  /**
   * Test selected layer functionality.
   */
  @Test
  public void testChangeSelectedLayer() {
    CollageModel model = new CollageModelImpl();
    CollageGuiView view = new CollageGuiViewImpl(model);
    CollageGuiController controller = new CollageGuiController(model, view);

    controller.newProject(500, 500);

    controller.addNewLayer("layer");
    controller.changeSelectedLayer("layer");
    assertEquals(model.getSelectedLayer(), "layer");
  }

}