package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class CollageModelImpl implements CollageModel {

  protected HashMap<String, Image> images;
  private Project currentProject;
  protected static int MAX_CLAMP = 255;

  public CollageModelImpl() {
    this.images = new HashMap<>();
    this.currentProject = null;
  }

  public void newProject(int canvasHeight, int canvasWidth) throws IllegalArgumentException {
    if (canvasHeight < 0 || canvasWidth < 0) {
      throw new IllegalArgumentException("Project dimensions cannot be negative!");
    } else {
      this.currentProject = new Project(canvasHeight, canvasWidth);
    }
  }

  public void addLayer(String layerName) throws IllegalStateException {
    if (currentProject == null) {
      throw new IllegalStateException("No project is currently open");
    } else {
      this.currentProject.addLayer(layerName);
    }
  }

  public void addImageToLayer(String layerName, String filePath, int x, int y)
          throws IllegalStateException {
    if (currentProject == null) {
      throw new IllegalStateException("No project is currently open");
    } else {
      this.currentProject.addImageToLayer(layerName, filePath, x, y);
    }
  }

  public void setFilter(String layerName, String filterOption) {
    this.currentProject.setFilter(layerName, filterOption);
  }

  public void saveProject(String filePath, String fileName) throws IOException {
    try {
      FileWriter textInfo = new FileWriter(filePath);
      StringBuilder text = this.currentProject.writeToCollageFormat();
      textInfo.write(String.valueOf(text));
      textInfo.close();
    } catch (IOException e) {
      throw new IOException("Could not save");
    }
  }

  public void load(String filePath, String fileName) {
    Scanner sc;
    StringBuilder str = new StringBuilder();

    try {
      sc = new Scanner(new FileInputStream(filePath));
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          str.append(s).append(System.lineSeparator());
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("File " + filePath + " not found!");
    }

    sc = new Scanner(str.toString());

    String token;

    token = sc.next();
    if (!token.equals("C1")) {
      System.out.println("Not a valid project file");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();

    newProject(height, width);
    this.currentProject.loadProject(sc, height, width);
  }
}
