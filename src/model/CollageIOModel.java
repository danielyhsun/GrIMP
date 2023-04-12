package model;

import java.io.File;
import java.io.IOException;

/**
 * Interface represents the model to handle the IO operations for program like loading and saving.
 */
public interface CollageIOModel {

  /**
   * Load file and names it by string input.
   *
   * @param file represents file that is being loaded.
   * @throws IOException if file cannot be loaded.
   */
  void load(File file) throws IOException;

  /**
   * Saves file to indicated location.
   *
   * @param filePath represents location where file is to be saved.
   * @throws IOException if file cannot be saved.
   */
  void save(String filePath) throws IOException;
}
