package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Represents implementation of IO functionality. Loading and saving images different file types.
 */
public class CollageIOModelImpl implements CollageIOModel {

  private final CollageModel model;

  /**
   * IO Model Constructor.
   * @param model represents the model object that we will communicate the loading and saving to.
   */
  public CollageIOModelImpl(CollageModel model) {
    this.model = model;
  }


  /**
   * Load file and names it by string input.
   * @param file represents file path location.
   * @throws IOException if file cannot be loaded.
   */
  @Override
  public void load(File file) throws IOException {
    String path = file.getName();
    String fileType = path.substring(path.indexOf("."));

    if (fileType.equalsIgnoreCase(".ppm")) {
      this.model.load(file);
    }
    else {
      BufferedImage current = ImageIO.read(new FileInputStream(file));
      Pixel[][] pixels = new Pixel[current.getHeight()][current.getWidth()];

      for (int i = 0 ; i < current.getHeight() ; i ++) {
        for (int j = 0 ; j < current.getWidth() ; j ++) {
          int rgb = current.getRGB(j, i);
          Color color = new Color(rgb, true);
          int r = color.getRed();
          int b = color.getBlue();
          int g = color.getGreen();
          int a = color.getAlpha();
          pixels[i][j] = new Pixel(r, g, b, a);
        }
      }
    }
  }

  /**
   * Saves file to indicated location.
   * @param filePath represents location where file is to be saved.
   * @throws IOException if file cannot be saved.
   */
  @Override
  public void save(String filePath) throws IOException {
    String fileType = filePath.substring(filePath.indexOf("."));

    if (fileType.equalsIgnoreCase(".ppm")) {
      this.model.saveImage(filePath);
    }
    else {
      BufferedImage temp = this.model.getCollageImage();
      BufferedImage current = new BufferedImage(temp.getWidth(),
              temp.getHeight(), BufferedImage.TYPE_INT_RGB);
      for (int i = 0 ; i < temp.getHeight() ; i ++) {
        for (int j = 0 ; j < temp.getWidth() ; j ++) {
          current.setRGB(j, i, temp.getRGB(j, i));
        }
      }

      File out = new File(filePath);
      ImageIO.write(current, fileType, out);
    }
  }
}
