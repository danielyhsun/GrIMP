package controller;

import org.junit.Test;

import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;


import model.CollageModel;
import model.CollageModelImpl;
import view.CollageGuiView;
import view.CollageGuiViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
    CollageGuiView view = new CollageGuiViewImpl();
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
    CollageGuiView view = new CollageGuiViewImpl();
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
    CollageGuiView view = new CollageGuiViewImpl();
    CollageGuiController controller = new CollageGuiController(model, view);

    controller.newProject(500, 500);

    controller.addNewLayer("layer");
    controller.changeSelectedLayer("layer");
    assertEquals(model.getSelectedLayer(), "layer");
  }

  /**
   * Test loading a ppm image.
   */
  @Test
  public void testLoadPPM() {
    CollageModel model = new CollageModelImpl();
    CollageGuiView view = new CollageGuiViewImpl();
    CollageGuiController controller = new CollageGuiController(model, view);

    controller.newProject(500, 500);
    controller.addNewLayer("layer");
    controller.changeSelectedLayer("layer");

    File file = new File("/Users/codychan/Desktop/OOD_Projects/Collage/res/grogubl.ppm");
    try {
      BufferedImage before = model.getCollageImage();
      controller.addImageToLayerFromFile(file, 100, 100);
      BufferedImage after = model.getCollageImage();

      assertNotEquals(before, after);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Test loading a jpeg image.
   */
  @Test
  public void testLoadJPEG() {
    CollageModel model = new CollageModelImpl();
    CollageGuiView view = new CollageGuiViewImpl();
    CollageGuiController controller = new CollageGuiController(model, view);

    controller.newProject(500, 500);
    controller.addNewLayer("layer");
    controller.changeSelectedLayer("layer");

    File file = new File(
            "/Users/codychan/Desktop/OOD_Projects/Collage/res/groguEggs.jpeg");

    try {
      BufferedImage before = model.getCollageImage();
      controller.addImageToLayerFromFile(file, 100, 100);
      BufferedImage after = model.getCollageImage();

      assertNotEquals(before, after);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Test loading a png image.
   */
  @Test
  public void testLoadPNG() {
    CollageModel model = new CollageModelImpl();
    CollageGuiView view = new CollageGuiViewImpl();
    CollageGuiController controller = new CollageGuiController(model, view);

    controller.newProject(500, 500);
    controller.addNewLayer("layer");
    controller.changeSelectedLayer("layer");

    File file = new File(
            "/Users/codychan/Desktop/OOD_Projects/Collage/res/groguEggsTwo.png");

    try {
      BufferedImage before = model.getCollageImage();
      controller.addImageToLayerFromFile(file, 100, 100);
      BufferedImage after = model.getCollageImage();

      assertNotEquals(before, after);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Test saving a PPM image.
   */
  @Test
  public void testSavePPM() {
    CollageModel model = new CollageModelImpl();
    CollageGuiView view = new CollageGuiViewImpl();
    CollageGuiController controller = new CollageGuiController(model, view);

    controller.newProject(500, 500);
    controller.addNewLayer("layer");
    controller.changeSelectedLayer("layer");

    File file = new File("/Users/codychan/Desktop/OOD_Projects/Collage/res/testSave.ppm");
    try {
      controller.saveImageToFile(file);
      assertTrue(new File(
              "/Users/codychan/Desktop/OOD_Projects/Collage/res/testSave.ppm").exists());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Test saving a JPG image.
   */
  @Test
  public void testSaveJPG() {
    CollageModel model = new CollageModelImpl();
    CollageGuiView view = new CollageGuiViewImpl();
    CollageGuiController controller = new CollageGuiController(model, view);

    controller.newProject(500, 500);
    controller.addNewLayer("layer");
    controller.changeSelectedLayer("layer");

    File file = new File("/Users/codychan/Desktop/OOD_Projects/Collage/res/testSave.jpg");
    try {
      controller.saveImageToFile(file);
      assertEquals(true, new File(
              "/Users/codychan/Desktop/OOD_Projects/Collage/res/testSave.jpg").exists());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Test saving a PNG image.
   */
  @Test
  public void testSavePNG() {
    CollageModel model = new CollageModelImpl();
    CollageGuiView view = new CollageGuiViewImpl();
    CollageGuiController controller = new CollageGuiController(model, view);

    controller.newProject(500, 500);
    controller.addNewLayer("layer");
    controller.changeSelectedLayer("layer");

    File file = new File("/Users/codychan/Desktop/OOD_Projects/Collage/res/testSave.PNG");
    try {
      controller.saveImageToFile(file);
      assertEquals(true, new File(
              "/Users/codychan/Desktop/OOD_Projects/Collage/res/testSave.PNG").exists());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Test loading and saving using different image formats.
   * PPM -> JPG.
   */
  @Test
  public void testPPMtoJPG() {
    CollageModel model = new CollageModelImpl();
    CollageGuiView view = new CollageGuiViewImpl();
    CollageGuiController controller = new CollageGuiController(model, view);

    controller.newProject(500, 500);
    controller.addNewLayer("layer");
    controller.changeSelectedLayer("layer");

    File ppmFile = new File(
            "/Users/codychan/Desktop/OOD_Projects/Collage/res/grogubl.ppm");
    File jpgFile = new File(
            "/Users/codychan/Desktop/OOD_Projects/Collage/res/grogubl.jpg");

    try {
      controller.addImageToLayerFromFile(ppmFile, 100, 100);
      controller.saveImageToFile(jpgFile);

      assertTrue(new File(
              "/Users/codychan/Desktop/OOD_Projects/Collage/res/grogubl.jpg").exists());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Test loading and saving using different image formats.
   * JPG -> PNG.
   */
  @Test
  public void testJPGtoPNG() {
    CollageModel model = new CollageModelImpl();
    CollageGuiView view = new CollageGuiViewImpl();
    CollageGuiController controller = new CollageGuiController(model, view);

    controller.newProject(500, 500);
    controller.addNewLayer("layer");
    controller.changeSelectedLayer("layer");

    File jpgFile = new File(
            "/Users/codychan/Desktop/OOD_Projects/Collage/res/groguEggs.jpeg");
    File pngFile = new File(
            "/Users/codychan/Desktop/OOD_Projects/Collage/res/grogubl.png");

    try {
      controller.addImageToLayerFromFile(jpgFile, 100, 100);
      controller.saveImageToFile(pngFile);

      assertTrue(new File(
              "/Users/codychan/Desktop/OOD_Projects/Collage/res/grogubl.png").exists());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Test loading and saving using different image formats.
   * PNG -> PPM.
   */
  @Test
  public void testPNGtoPPM() {
    CollageModel model = new CollageModelImpl();
    CollageGuiView view = new CollageGuiViewImpl();
    CollageGuiController controller = new CollageGuiController(model, view);

    controller.newProject(500, 500);
    controller.addNewLayer("layer");
    controller.changeSelectedLayer("layer");

    File pngFile = new File(
            "/Users/codychan/Desktop/OOD_Projects/Collage/res/groguEggsTwo.png");
    File ppmFile = new File(
            "/Users/codychan/Desktop/OOD_Projects/Collage/res/groguEggsTwo.ppm");

    try {
      controller.addImageToLayerFromFile(pngFile, 100, 100);
      controller.saveImageToFile(ppmFile);

      assertEquals(true, new File(
                      "/Users/codychan/Desktop/OOD_Projects/Collage/res/groguEggsTwo.ppm").
              exists());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}