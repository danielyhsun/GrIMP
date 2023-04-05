package model;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Test class for project class.
 */
public class ProjectTest {

  @Test
  public void testProjectCreation() {
    Project project = new Project(600, 600);
    assertNotNull(project);
    assertNotNull(project.layers);
    assertEquals(600, project.getCanvasHeight());
    assertEquals(400, project.getCanvasWidth());
    assertNotNull(project.layers.get("bg"));
  }

  @Test
  public void testAddLayer() {
    Project project = new Project(500, 500);
    project.addLayer("Layer1");
    assertEquals(1, project.layers.size());

    project.addLayer("Layer2");
    assertEquals(2, project.layers.size());
  }

  @Test
  public void testAddImageToLayer() throws IOException {
    Project project = new Project(800, 600);
    project.addLayer("Layer1");

    try {
      project.addImageToLayer("Layer1", new File("grogu.ppm"), 0, 0);
      Image image = new Image(new File("grogu.ppm"));
      image.readPPM();
      assertEquals(project.layers.get("Layer1").getPixels(), image.getPixels());
    } catch (IOException e) {
      fail();
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testAddImageToLayerWithInvalidLayer() throws IOException {
    Project project = new Project(500, 500);
    project.addImageToLayer("InvalidLayer", new File("path/to/image/file"), 10, 10);
  }

  @Test
  public void testSetFilter() {
    Project project = new Project(500, 500);
    project.addLayer("Layer1");

    try {
      project.setFilter("Layer1", "filter-red");
      assertEquals("filter-red", project.layers.get("Layer1").getFilterName());
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testSetFilterWithInvalidLayer()
          throws IllegalStateException, IllegalArgumentException {
    Project project = new Project(500, 500);
    project.setFilter("InvalidLayer", "darken-luma");
  }
}
