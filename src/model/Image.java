package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents an Image that is composed of a 2D array of pixels, created by reading the pixel data
 * from a PPM image file of a given file path.
 */
public class Image {
  private File file;
  private Pixel[][] pixels;

  /**
   * Constructor to create a new Image object with a given image filePath as a String.
   *
   * @param file the image's file location
   */
  protected Image(File file) {
    this.file = file;
  }

  /**
   * Returns the layer's 2D array of pixels.
   *
   * @return the layer's 2D array of pixels.
   */
  protected Pixel[][] getPixels() {
    return pixels;
  }

  /**
   * Reads a PPM image file from the specified file path and stores the pixel data in the 'pixels'
   * array. The method expects a plain P3 PPM format and ignores comment lines. An IOException is
   * thrown if the file is not found or if there is an issue with reading the file.
   *
   * @throws IOException if the file is not found or if there is an issue with reading the file
   */
  protected void readPPM() throws IOException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(file));
    }
    catch (FileNotFoundException e) {
      throw new IOException("File not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0)!='#') {
        builder.append(s+System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: "+width);
    int height = sc.nextInt();
    System.out.println("Height of image: "+height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    this.pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = new Pixel(0, 0, 0, 0);
      }
    }

    while (sc.hasNextInt()) {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          pixels[i][j].r = sc.nextInt();
          pixels[i][j].g = sc.nextInt();
          pixels[i][j].b = sc.nextInt();
          pixels[i][j].a = 255;
        }
      }
    }
  }

}
