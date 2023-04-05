package model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Model implementation test class.
 */
public class CollageModelImplTest {
  private CollageModelImpl model;

  @Before
  public void setUp() {
    model = new CollageModelImpl();
  }

  @Test
  public void testNewProject() {
    MockCollageModelImpl mock = new MockCollageModelImpl();
    mock.newProject(100, 100);
    assertTrue(mock.isOpenProject());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNewProjectInvalidDimensions() {
    model.newProject(-100, 100);
  }

  @Test(expected = IllegalStateException.class)
  public void testAddLayerNoProject() {
    model.addLayer("testLayer");
  }

  @Test
  public void testAddLayer() {
    MockCollageModelImpl mock = new MockCollageModelImpl();
    mock.newProject(100, 100);
    assertEquals(0, mock.getLayersAdded());
    mock.addLayer("testLayer");
    assertEquals(1, mock.getLayersAdded());
    mock.addLayer("testLayer2");
    assertEquals(2, mock.getLayersAdded());
  }

  @Test(expected = IllegalStateException.class)
  public void testAddImageToLayerNoProject() throws IOException {
    model.addImageToLayer("testLayer", "path/to/image", 0, 0);
  }

  @Test(expected = IOException.class)
  public void testAddImageToLayerInvalidPath() throws IOException {
    model.newProject(100, 100);
    model.addLayer("testLayer");
    model.addImageToLayer("testLayer", "invalid/path", 0, 0);
  }

  @Test(expected = IllegalStateException.class)
  public void testSetFilterNoProject() {
    model.setFilter("testLayer", "brighten-value");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetFilterInvalidLayer() {
    model.newProject(100, 100);
    model.setFilter("invalidLayer", "filter-red");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetFilterInvalidFilter() {
    model.newProject(100, 100);
    model.addLayer("testLayer");
    model.setFilter("testLayer", "invalidFilter");
  }

  @Test(expected = IllegalStateException.class)
  public void testSaveProjectNoProject() throws IOException {
    model.saveProject("project");
  }

  @Test(expected = IOException.class)
  public void testSaveProjectInvalidPath() throws IOException {
    model.newProject(100, 100);
    model.saveProject("invalid/path");
  }

  @Test(expected = IllegalStateException.class)
  public void testSaveImageNoProject() throws IOException {
    model.saveImage("image.ppm");
  }

  @Test(expected = IOException.class)
  public void testSaveImageInvalidPath() throws IOException {
    model.newProject(100, 100);
    model.saveImage("invalid/path");
  }

  @Test(expected = IOException.class)
  public void testLoadInvalidPath() throws IOException {
    model.newProject(100, 100);
    model.load("invalid/path");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadInvalidProjectFile() throws IOException {
    model.newProject(100, 100);
    model.load("res/grogu.ppm");
  }

  @Test(expected = IllegalStateException.class)
  public void testQuitProjectNoProject() {
    model.quitProject();
  }

  /*
  @Test
  public void testQuitProject() {
    model.newProject(100, 100);
    model.quitProject();
  }
   */
}