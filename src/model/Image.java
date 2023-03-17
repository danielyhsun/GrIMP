package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents image class.
 */
public class Image {
  private String filePath;
  private Pixel[][] pixels;

  /**
   * Image object constructor.
   * @param filePath
   */
  protected Image(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Get method for data structure holding pixels for image object.
   * @return image represented by array of array of pixels.
   */
  protected Pixel[][] getPixels() {
    return pixels;
  }

  /**
   * Reads in PPM file to create image using pixels.
   * @throws IOException if file cannot be found.
   */
  protected void readPPM() throws IOException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filePath));
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
