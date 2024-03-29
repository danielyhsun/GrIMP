package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import static model.Project.MAX_CLAMP;

/**
 * Represents an implementation of a Collage Program model.
 * It provides the methods to creating the project canvas and adding new layers. Communicates to
 * the project object to run the actions.
 */
public class CollageModelImpl implements CollageModel {
  private Project currentProject;
  File tempFile;

  /**
   * The constructor for a CollageModelImpl.
   */
  public CollageModelImpl() {
    this.currentProject = null;
  }

  /**
   * Creates a new project canvas with a given height and width. If a project is already open,
   * the new project replaces it as the current project.
   *
   * @param canvasHeight the height of the project canvas.
   * @param canvasWidth the width of the project canvas.
   * @throws IllegalArgumentException if the canvas height or width are less than 0
   */
  @Override
  public void newProject(int canvasHeight, int canvasWidth) throws IllegalArgumentException {
    if (canvasHeight < 0 || canvasWidth < 0) {
      throw new IllegalArgumentException("Project dimensions cannot be negative!");
    } else {
      this.currentProject = new Project(canvasHeight, canvasWidth);
    }
  }

  /**
   * Adds a layer to the current project with a given layer name. A layer is associated to a name.
   * and a number based on the order in which it was created.
   *
   * @param layerName the name of the layer.
   * @throws IllegalStateException if there is no project currently open.
   */
  @Override
  public void addLayer(String layerName) throws IllegalStateException {
    if (currentProject == null) {
      throw new IllegalStateException("No project is currently open");
    } else if (layerName.equals("bg")) {
      // do nothing
    } else {
      this.currentProject.addLayer(layerName);
    }
  }

  /**
   * Adds an image from a specified file path to the layer of the specified name with an offset of.
   * x and y from top left corner of the canvas.
   *
   * @param layerName the name of the layer.
   * @param filePath the file location of the image.
   * @param x the x offset from the top left corner of the canvas.
   * @param y the y offset from the top left corner of the canvas.
   * @throws IllegalStateException if there is no project currently open.
   * @throws IOException if the image could not be found with the given filepath.
   */
  @Override
  public void addImageToLayer(String layerName, String filePath, int x, int y)
          throws IllegalStateException, IOException {
    File file = new File(filePath);
    addImageToLayer(layerName, file, x, y);
  }

  /**
   * Adds an image from a specified file path to the layer of the specified name with an offset of.
   * x and y from top left corner of the canvas.
   *
   * @param layerName the name of the layer.
   * @param x the x offset from the top left corner of the canvas.
   * @param y the y offset from the top left corner of the canvas.
   * @throws IllegalStateException if there is no project currently open.
   * @throws IOException if the image could not be found with the given filepath.
   */
  public void addImageToLayer(String layerName, File file, int x, int y)
          throws IllegalStateException, IOException {
    if (currentProject == null) {
      throw new IllegalStateException("No project is currently open!");
    } else {
      currentProject.addImageToLayer(layerName, file, x, y);
    }
  }


  /**
   * Sets the filter for a given layer with a given filter name.
   *
   * @param layerName the name of the layer that the filter is being applied onto.
   * @param filterOption the name of the filter that is being applied.
   * @throws IllegalStateException if there is no project currently open.
   * @throws IllegalArgumentException if the layer or filter is invalid.
   */
  @Override
  public void setFilter(String layerName, String filterOption) throws IllegalStateException,
          IllegalArgumentException {
    if (currentProject == null) {
      throw new IllegalStateException("No project is currently open!");
    }
    currentProject.setFilter(layerName, filterOption);
  }

  /**
   * Saves the currently open project to a formatted file with the given file location.
   *
   * @param filePath the designated file path for the saved project.
   * @throws IOException if the file path is invalid.
   */
  @Override
  public void saveProject(String filePath) throws IOException, IllegalStateException {
    File file = new File(filePath);
    saveProject(file);
  }

  @Override
  public void saveProject(File file) throws IOException, IllegalStateException {
    if (currentProject == null) {
      throw new IllegalStateException("No project is currently open!");
    }
    try {
      FileWriter writer = new FileWriter(file);
      StringBuilder text = currentProject.writeToCollageFormat();
      writer.write(String.valueOf(text));
      writer.close();
    } catch (IOException e) {
      throw new IOException("Could not save project");
    }
  }


  /**
   * Saves the current project collage as a ppm formatted image to a designated file location.
   *
   * @param filePath the designated file path for the saved image.
   * @throws IOException if the file path is invalid.
   */
  @Override
  public void saveImage(String filePath) throws IOException, IllegalStateException {
    if (currentProject == null) {
      throw new IllegalStateException("No project is currently open!");
    }
    try {
      String fileType = filePath.substring(filePath.indexOf(".") + 1);

      if (fileType.equalsIgnoreCase("ppm")) {
        int height = currentProject.getCanvasHeight();
        int width = currentProject.getCanvasWidth();

        FileWriter writer = new FileWriter(filePath);
        writer.write("P3\n");
        writer.write(width + " " + height + "\n");
        writer.write(MAX_CLAMP + "\n");

        Pixel[][] finalImage = currentProject.layersToImage();

        for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
            Pixel pixel = finalImage[i][j];
            writer.write(pixel.r + " " + pixel.g + " " + pixel.b + " ");
          }
          writer.write("\n");
        }
        writer.close();
      }
      else {
        BufferedImage temp = this.getCollageImage();
        BufferedImage current = new BufferedImage(temp.getWidth(),
                temp.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < temp.getHeight(); i++) {
          for (int j = 0; j < temp.getWidth(); j++) {
            current.setRGB(j, i, temp.getRGB(j, i));
          }
        }

        File out = new File(filePath);
        ImageIO.write(current, fileType, out);
      }
    } catch (IOException e) {
      throw new IOException("Unable to save image!");
    }
  }

  /**
   * Temporarily saves image.
   * @throws IOException if image or file save is invalid.
   */
  public void tempSaveImage() throws IOException {
    try {
      int height = currentProject.getCanvasHeight();
      int width = currentProject.getCanvasWidth();

      tempFile = File.createTempFile("tempImage_", ".ppm");

      FileWriter writer = new FileWriter(tempFile);
      writer.write("P3\n");
      writer.write(width + " " + height + "\n");
      writer.write(MAX_CLAMP + "\n");

      Pixel[][] finalImage = currentProject.layersToImage();

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Pixel pixel = finalImage[i][j];
          writer.write(pixel.r + " " + pixel.g + " " + pixel.b + " ");
        }
        writer.write("\n");
      }
      writer.close();

    } catch (IOException e) {
      throw new IOException("Unable to save image!");
    }
  }

  /**
   * Deletes temp files.
   */
  public void deleteTempFile() {
    if (tempFile.delete()) {
      System.out.println("File deleted");
    } else {
      System.out.println("Unable to delete temp");
    }
  }

  /**
   * Loads a project from a project file with a given file location.
   *
   * @param filePath the location of the project file to open.
   * @throws IOException if the file path is invalid.
   * @throws IllegalArgumentException if the file is not a properly formatted project file.
   */
  @Override
  public void load(String filePath) throws IOException, IllegalArgumentException {
    File file = new File(filePath);
    load(file);
  }

  /**
   * Loads a project from a project file with a given file location.
   * @param file represents file location of project.
   * @throws IOException if file is bad.
   * @throws IllegalArgumentException if file location is invalid.
   */
  public void load(File file) throws IOException, IllegalArgumentException {
    Scanner sc;
    StringBuilder str = new StringBuilder();

    try {
      sc = new Scanner(new FileInputStream(file));
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          str.append(s).append(System.lineSeparator());
        }
      }
    } catch (FileNotFoundException e) {
      throw new IOException();
    }

    sc = new Scanner(str.toString());

    String token;

    token = sc.next();
    if (!token.equals("C1")) {
      throw new IllegalArgumentException("Not a valid project file");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    MAX_CLAMP = sc.nextInt();

    newProject(height, width);
    currentProject.loadProject(sc, height, width);
  }

  /**
   * Quits the currently open project by setting the current project to null.
   * and loses all unsaved work.
   *
   * @throws IllegalStateException if no project is currently open.
   */
  public void quitProject() throws IllegalStateException {
    if (currentProject == null) {
      throw new IllegalStateException("No project is currently open!");
    }
    currentProject = null;
  }

  /**
   * Changes selected layer.
   * @param layer represents name of new selected layer.
   */
  public void setSelectedLayer(String layer) {
    currentProject.setSelectedLayer(layer);
  }

  /**
   * Gets selected layer.
   * @return String of selected layer.
   */
  public String getSelectedLayer() {
    return currentProject.getSelectedLayer();
  }

  /**
   * Gets collage image.
   * @return collage image as a buffered image.
   */
  public BufferedImage getCollageImage() {
    return currentProject.getCollageImage();
  }

  /**
   * Gets layers of a loaded project.
   * @return string list representing layers.
   */
  public ArrayList<String> getLayersOfLoadedProject() {
    return currentProject.getLayersOfLoadedProject();
  }

}
