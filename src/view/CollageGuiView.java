package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import controller.Features;

public interface CollageGuiView {
  void addFeatures(Features f);
  void newLayerDialog(Consumer<String> onLayerCreated);
  void newProjectDialog(BiConsumer<Integer, Integer> createProjectCallback);
  void loadProjectChooser(Consumer<File> fileConsumer);
  void addImageToLayerChooser(Consumer<File> fileConsumer);
  void showLayersPanelAndResetLayers();
  void updateImagePanel(BufferedImage image);
  void addLoadedLayersToLayerPane(ArrayList<String> layers);
  void saveProjectChooser(Consumer<File> fileConsumer);
  void initLayerFilterMap();
  void setNewLayerFilterMapNormal(String layerName);

}
