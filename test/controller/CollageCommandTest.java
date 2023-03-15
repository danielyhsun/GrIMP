package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import controller.command.AddImageToLayer;
import controller.command.AddLayer;
import controller.command.LoadProject;
import controller.command.NewProject;
import controller.command.SaveImage;
import controller.command.SaveProject;
import controller.command.SetFilter;
import model.CollageModel;
import model.CollageModelImpl;

public class CollageCommandTest {
  private CollageModel model;

  @Before
  public void setUp() {
    model = new CollageModelImpl();
    model.newProject(100, 100);
  }

  // SetFilter command tests
  @Test
  public void testSetFilter() throws IOException {
    model.addLayer("layer1");
    CollageCommand command = new SetFilter("layer1", "brighten-value");
    command.runCommand(model);
    assertEquals("brighten-value applied to layer layer1", command.getMessage());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetFilterInvalidLayer() throws IOException {
    CollageCommand command = new SetFilter("nonexistentLayer", "brighten-value");
    command.runCommand(model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetFilterInvalidFilter() throws IOException {
    model.addLayer("layer1");
    CollageCommand command = new SetFilter("layer1", "invalidFilter");
    command.runCommand(model);
  }

  // SaveProject command tests
  @Test
  public void testSaveProject() throws IOException {
    model.newProject(400, 300);
    String filePath = "test_project_save.txt";
    CollageCommand command = new SaveProject(filePath);
    command.runCommand(model);
    assertEquals("Project saved to: " + filePath, command.getMessage());
    assertTrue(new File(filePath).exists());
    new File(filePath).delete();
  }

  @Test(expected = IOException.class)
  public void testSaveProjectInvalidPath() throws IOException {
    model.newProject(400, 300);
    String filePath = "/invalid_directory/test_project_save.collage";
    CollageCommand command = new SaveProject(filePath);
    command.runCommand(model);
  }

  // AddLayer command tests
  @Test
  public void testAddLayer() throws IOException {
    CollageCommand command = new AddLayer("layer1");
    command.runCommand(model);
    assertEquals("New layer (layer1) added", command.getMessage());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddLayerDuplicateName() throws IOException {
    CollageCommand command = new AddLayer("layer1");
    command.runCommand(model);
    CollageCommand command2 = new AddLayer("layer1");
    command2.runCommand(model);
  }

  // Controller tests
  @Test
  public void testControllerRun() {
    // You can implement this test using a mock or a specific input stream.
  }

  // NewProject command tests
  @Test
  public void testNewProject() throws IOException {
    CollageCommand command = new NewProject(400, 300);
    command.runCommand(model);
    assertEquals("New 400x300 project created", command.getMessage());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNewProjectInvalidDimensions() throws IOException {
    CollageCommand command = new NewProject(-400, 300);
    command.runCommand(model);
  }

  // SaveImage command tests
  @Test
  public void testSaveImage() throws IOException {
    model.newProject(400, 300);
    String filePath = "test_image_save.png";
    CollageCommand command = new SaveImage(filePath);
    command.runCommand(model);
    assertEquals("Saved as image to: " + filePath, command.getMessage());
    assertTrue(new File(filePath).exists());
    new File(filePath).delete();
  }

  @Test(expected = IOException.class)
  public void testSaveImageInvalidPath() throws IOException {
    model.newProject(400, 300);
    String filePath = "/invalid_directory/test_image_save.png";
    CollageCommand command = new SaveImage(filePath);
    command.runCommand(model);
  }

  // LoadProject command tests
  @Test
  public void testLoadProject() throws IOException {
    // First, create and save a project
    model.newProject(400, 300);
    String filePath = "test_project_load.collage";
    CollageCommand saveCommand = new SaveProject(filePath);
    saveCommand.runCommand(model);

    // Now, load the project
    CollageCommand loadCommand = new LoadProject(filePath);
    loadCommand.runCommand(model);
    assertEquals(filePath + " was successfully loaded.", loadCommand.getMessage());

    // Clean up the test file
    new File(filePath).delete();
  }

  @Test(expected = IOException.class)
  public void testLoadProjectNonexistentFile() throws IOException {
    String filePath = "nonexistent_file.collage";
    CollageCommand command = new LoadProject(filePath);
    command.runCommand(model);
  }

  // AddImageToLayer command tests
  @Test
  public void testAddImageToLayer() throws IOException {
    model.addLayer("layer1");
    String imagePath = "tako.ppm";
    CollageCommand command = new AddImageToLayer("layer1", imagePath, 0, 0);
    command.runCommand(model);
    assertEquals("Image from (" + imagePath + ") added to layer1 at 0 0", command.getMessage());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageToLayerInvalidLayer() throws IOException {
    String imagePath = "tako.ppm";
    CollageCommand command = new AddImageToLayer("nonexistentLayer", imagePath, 0, 0);
    command.runCommand(model);
  }

  @Test(expected = IOException.class)
  public void testAddImageToLayerInvalidImagePath() throws IOException {
    model.addLayer("layer1");
    String imagePath = "/invalidpath/image";
    CollageCommand command = new AddImageToLayer("layer1", imagePath, 0, 0);
    command.runCommand(model);
  }

}
