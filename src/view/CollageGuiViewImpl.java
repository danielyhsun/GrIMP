package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;

import controller.Features;
import model.CollageModel;

/**
 * An implementation of the CollageGuiView interface. Represents an application GUI window
 * for a Collage program. Contains all the GUI elements required for a user to navigate and
 * use a Collage program and its features.
 */
public class CollageGuiViewImpl extends JFrame implements CollageGuiView {

  private JMenuBar menuBar;
  private JMenu fileMenu, projectMenu, imageMenu;
  private JMenuItem newProject, loadProject, saveProject, quitProject, saveImage, addImage;
  private JPanel mainPanel, layerListPanel, interactionPanel, imagePanel, filterPanel;
  private JComboBox<String> filterDropdown;
  private JScrollPane layerScrollPane, imageScrollPane;
  private JButton addLayerButton;
  private JList<String> layerList;
  private DefaultListModel<String> layerListModel;
  private JDialog addLayerDialog, newProjectDialog;
  private JFileChooser fileChooser, imageChooser;
  private JLabel imageLabel, filterLabel;
  private HashMap<String, Integer> layerAndFilterMap;

  /**
   * Constructor for a CollageGuiViewImpl. Takes in a CollageModel, initializes GUI Components,
   * and sets itself as visible.
   * @param model the model.
   */
  public CollageGuiViewImpl(CollageModel model) {
    initComponents();

    this.setVisible(true);
  }

  /**
   * Add listeners for GUI Elements
   * @param f the Features object
   */
  public void addFeatures(Features f) {
    // new project listener
    newProject.addActionListener(e -> {
      newProjectDialog(f::newProject);
      filterPanel.setVisible(true);
    });
    // layer selection listener
    layerList.addListSelectionListener(e -> {
      f.changeSelectedLayer(layerList.getSelectedValue());
      filterDropdown.setSelectedIndex(layerAndFilterMap.get(layerList.getSelectedValue()));
    });
    // add layer button listener
    addLayerButton.addActionListener(e -> newLayerDialog(layerName -> {
      try {
        f.addNewLayer(layerName);
        addLayerToLayerPane(layerName);
      } catch (IllegalArgumentException ex) {
        // error message popup if user tries to add layer with a name that already exists
        JOptionPane.showMessageDialog(null, "Could not add layer: Layer with " +
                "given name already exists!", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }));
    loadProject.addActionListener(e -> loadProjectChooser(file -> {
      try {
        f.loadProjectFromFile(file);
      } catch (IOException | IllegalArgumentException ex) {
        //
      }
    }));
    addImage.addActionListener(e -> addImageToLayerChooser(file -> {
      addImageXYOffset((xOffset, yOffset) -> {
        try {
          f.addImageToLayerFromFile(file, xOffset, yOffset);
        } catch (IOException ex) {
          //
        } catch (IllegalArgumentException ex) {
          // error message popup if user tries to add an image without selecting a layer
          JOptionPane.showMessageDialog(null, "Image could not be added: " +
                  "No layer selected!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
          // error message popup if user tries to add an image without a project open
          JOptionPane.showMessageDialog(null, "Image could not be added: " +
                  "No project open!", "Error", JOptionPane.ERROR_MESSAGE);
        }
      });
    }));
    saveImage.addActionListener(e -> saveImageChooser(fileName -> {
      try {
        f.saveImageToFile(fileName);
      } catch (IOException ex) {
        //
      } catch (IllegalStateException ex) {
        // error message popup if user tries to save image when no project is open
        JOptionPane.showMessageDialog(null,
                "Could not save image: No project open!", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }));
    saveProject.addActionListener(e -> saveProjectChooser(file -> {
      try {
        f.saveProjectToFile(file);
      } catch (IOException ex) {
        //
      } catch (IllegalStateException ex) {
        // error message popup if user tries to save project when no project is open
        JOptionPane.showMessageDialog(null,
                "Could not save project: No project open!", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }));
    filterDropdown.addActionListener(e -> {
      f.filterPicker(filterDropdown.getItemAt(filterDropdown.getSelectedIndex()),
              layerList.getSelectedValue());
      layerAndFilterMap.put(layerList.getSelectedValue(), filterDropdown.getSelectedIndex());
    });
  }

  /**
   * Shows a dialog when the Add Layer button is pressed. Contains a text field
   * to take a string input for the new layer's name, and a "Create" button that
   * closes the dialog. Takes in a string Consumer that accepts the dialog's input.
   * @param onLayerCreated a String consumer
   */
  public void newLayerDialog(Consumer<String> onLayerCreated) {
    addLayerDialog = new JDialog(this, "New Layer", true);
    addLayerDialog.setLayout(new BorderLayout());
    addLayerDialog.setSize(300, 150);
    JPanel dialogPane = new JPanel(new GridLayout(2, 2));
    JLabel label = new JLabel("New Layer:");
    JTextField input = new JTextField();
    dialogPane.add(label);
    dialogPane.add(input);
    addLayerDialog.add(dialogPane, BorderLayout.CENTER);
    JPanel buttons = new JPanel();
    JButton createButton = new JButton("Create");
    createButton.addActionListener(e -> {
      onLayerCreated.accept(input.getText());
      addLayerDialog.dispose();
    });
    buttons.add(createButton);
    addLayerDialog.add(buttons, BorderLayout.SOUTH);
    addLayerDialog.setVisible(true);
  }

  /**
   * Shows a dialog when the New Project menu item is pressed. Contains two text fields
   * to take in height and width input for the project canvas dimensions, and a "Create" button
   * that closes the dialog. Takes in a BiConsumer that accepts the height and width inputs.
   * @param createProjectCallback the BiConsumer
   */
  public void newProjectDialog(BiConsumer<Integer, Integer> createProjectCallback) {
    newProjectDialog = new JDialog(this, "New Project", true);
    newProjectDialog.setLayout(new BorderLayout());
    newProjectDialog.setSize(300, 150);

    JPanel inputPanel = new JPanel(new GridLayout(2, 2));
    JLabel widthLabel = new JLabel("Width:");
    JTextField widthInput = new JTextField();
    JLabel heightLabel = new JLabel("Height:");
    JTextField heightInput = new JTextField();
    inputPanel.add(widthLabel);
    inputPanel.add(widthInput);
    inputPanel.add(heightLabel);
    inputPanel.add(heightInput);

    newProjectDialog.add(inputPanel, BorderLayout.CENTER);

    JPanel buttonsPanel = new JPanel();
    JButton createButton = new JButton("Create");
    createButton.addActionListener(e -> {
      try {
        int width = Integer.parseInt(widthInput.getText());
        int height = Integer.parseInt(heightInput.getText());
        createProjectCallback.accept(width, height);
        newProjectDialog.dispose();
      } catch (NumberFormatException ex) {
        System.out.println("Dimensions cannot be empty!");
      }
    });
    buttonsPanel.add(createButton);
    newProjectDialog.add(buttonsPanel, BorderLayout.SOUTH);

    newProjectDialog.setVisible(true);
  }

  /**
   * Shows the layers panel on the right edge of the frame and removes any elements in the
   * list of layers.
   */
  public void showLayersPanelAndResetLayers() {
    layerListPanel.setVisible(true);
    layerListModel.removeAllElements();
  }

  /**
   * Shows load project option.
   * @param fileConsumer represents file intake for project.
   */
  public void loadProjectChooser(Consumer<File> fileConsumer) {
    fileChooser = new JFileChooser();
    FileFilter filter = new FileFilter() {
      private final String[] allowedExtensions = {".clg", ".txt"};

      @Override
      public boolean accept(File file) {
        return fileExtensionHelper(file, allowedExtensions);
      }

      @Override
      public String getDescription() {
        return "Collage Files (*.clg)";
      }
    };

    fileChooser.setFileFilter(filter);
    int returnValue = fileChooser.showOpenDialog(this);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      fileConsumer.accept(selectedFile);
    }
  }

  /**
   * Assists in adding image to layer according to file.
   * @param fileConsumer represents object to take in file.
   */
  public void addImageToLayerChooser(Consumer<File> fileConsumer) {
    imageChooser = new JFileChooser();
    FileFilter filter = new FileFilter() {
      private final String[] allowedExtensions = {".ppm"};

      @Override
      public boolean accept(File file) {
        return fileExtensionHelper(file, allowedExtensions);
      }

      @Override
      public String getDescription() {
        return "PPM Files (*.ppm)";
      }
    };

    imageChooser.setFileFilter(filter);
    int returnValue = imageChooser.showOpenDialog(this);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = imageChooser.getSelectedFile();
      fileConsumer.accept(selectedFile);
    }
  }

  /**
   * Helper function for file with extension.
   * @param file represents file object.
   * @param allowedExtensions represents string that is being appended.
   * @return if file has correct extension.
   */
  private boolean fileExtensionHelper(File file, String[] allowedExtensions) {
    if (file.isDirectory()) {
      return true;
    }

    String fileName = file.getName().toLowerCase();
    for (String extension : allowedExtensions) {
      if (fileName.endsWith(extension.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Method to display and take in dimensions to place image.
   * @param xyConsumer represents dimensions on where image is going to be placed.
   */
  public void addImageXYOffset(BiConsumer<Integer, Integer> xyConsumer) {
    JDialog xyOffsetDialog = new JDialog(this, "Add Image", true);
    xyOffsetDialog.setLayout(new BorderLayout());
    xyOffsetDialog.setSize(300, 150);

    JPanel inputPanel = new JPanel(new GridLayout(2, 2));
    JLabel xOffsetLabel = new JLabel("X Offset:");
    JTextField xOffsetInput = new JTextField();
    JLabel yOffsetLabel = new JLabel("Y Offset:");
    JTextField yOffsetInput = new JTextField();
    inputPanel.add(xOffsetLabel);
    inputPanel.add(xOffsetInput);
    inputPanel.add(yOffsetLabel);
    inputPanel.add(yOffsetInput);
    xyOffsetDialog.add(inputPanel);
    JPanel buttons = new JPanel();
    JButton okButton = new JButton("OK");
    okButton.addActionListener(e -> {
      int xOffset = Integer.parseInt(xOffsetInput.getText());
      int yOffset = Integer.parseInt(yOffsetInput.getText());
      xyConsumer.accept(xOffset, yOffset);
      xyOffsetDialog.dispose();
    });
    buttons.add(okButton);
    xyOffsetDialog.add(buttons, BorderLayout.SOUTH);
    xyOffsetDialog.setVisible(true);
  }

  /**
   * Initial components for program.
   */
  private void initComponents() {
    this.setSize(1280, 720);
    setupMenuBar();
    setupPanels();
  }

  /**
   * Establishes dimensions, layout, and buttons of the program.
   */
  private void setupPanels() {
    mainPanel = new JPanel(new BorderLayout());

    imagePanel = new ImagePanel(null);
    imageScrollPane = new JScrollPane(imagePanel);
    imagePanel.setPreferredSize(new Dimension(800, 600));
    mainPanel.add(imageScrollPane, BorderLayout.CENTER);

    layerListModel = new DefaultListModel<>();

    layerList = new JList<>(layerListModel);
    layerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    layerScrollPane = new JScrollPane(layerList);
    layerScrollPane.setPreferredSize(new Dimension(200, 600));

    addLayerButton = new JButton("Add Layer");
    layerListPanel = new JPanel(new BorderLayout());
    layerListPanel.add(addLayerButton, BorderLayout.NORTH);
    layerListPanel.add(layerScrollPane, BorderLayout.CENTER);
    interactionPanel = new JPanel(new BorderLayout());
    mainPanel.add(interactionPanel, BorderLayout.EAST);
    interactionPanel.add(layerListPanel, BorderLayout.NORTH);
    filterPanel = new JPanel(new FlowLayout());
    String[] filters = {"Normal", "Brighten Value", "Brighten Intensity", "Brighten Luma",
                        "Darken Value", "Darken Intensity", "Darken Luma",
                        "Filter Red", "Filter Blue", "Filter Green", "Blend Difference" ,
                        "Blend Multiply", "Blend Screen"};
    filterDropdown = new JComboBox<>(filters);
    filterPanel.add(filterDropdown);
    filterPanel.setVisible(false);
    interactionPanel.add(filterPanel, BorderLayout.SOUTH);
    layerListPanel.setVisible(false);

    getContentPane().add(mainPanel);
  }

  /**
   * Sets up the top left menu bar that holds buttons for the project and image functionality.
   */
  private void setupMenuBar() {
    menuBar = new JMenuBar();
    fileMenu = new JMenu("File");
    projectMenu = new JMenu("Project");
    imageMenu = new JMenu("Image");
    fileMenu.add(projectMenu);
    fileMenu.add(imageMenu);
    saveImage = new JMenuItem("Save Image");
    addImage = new JMenuItem("Add Image To Layer");
    imageMenu.add(saveImage);
    imageMenu.add(addImage);
    newProject = new JMenuItem("New Project");
    loadProject = new JMenuItem("Load Project");
    saveProject = new JMenuItem("Save Project");
    quitProject = new JMenuItem("Quit Project");
    projectMenu.add(newProject);
    projectMenu.add(loadProject);
    projectMenu.add(saveProject);
    projectMenu.add(quitProject);
    menuBar.add(fileMenu);
    this.setJMenuBar(menuBar);
  }

  /**
   * Updates image panel to keep the program refreshed and up to date.
   * @param i represents image that is being broadcasted through the program.
   */
  public void updateImagePanel(BufferedImage i) {
    if (i == null) {
      System.out.println("null image!");
      throw new IllegalArgumentException("Image cannot be null!");
    }

    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image"));

    /*
     * test output of the image to a file
    try {
      File output = new File("output.png");
      ImageIO.write(i, "png", output);
      System.out.println("image saved to: " + output.getAbsolutePath());
    } catch (IOException e) {
      System.out.println("rip");
    }
     */

    imageLabel = new JLabel(new ImageIcon(i));
    imagePanel.add(imageLabel);
    imagePanel.revalidate();
    imagePanel.repaint();
    imageScrollPane.setViewportView(imagePanel);
    imageScrollPane.setPreferredSize(new Dimension(500, 500));
    imageScrollPane.setVisible(true);
    System.out.println("Image updated!");
  }

  /**
   * Adds layer to program.
   * @param s represents name of layer.
   */
  private void addLayerToLayerPane(String s) {
    layerListModel.add(0, s);
  }

  /**
   * Adds multiple layers to program holder.
   * @param layers names of layers to add.
   */
  public void addLoadedLayersToLayerPane(ArrayList<String> layers) {
    for (String layer : layers) {
      layerListModel.add(0, layer);
      System.out.println(layer);
    }
  }

  /**
   * Chooser method for saving an Image.
   * @param fileConsumer file location for saving image.
   */
  public void saveImageChooser(Consumer<File> fileConsumer) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Image");
    int userSelection = fileChooser.showSaveDialog(null);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();
      String desiredExtension = ".ppm";
      fileToSave = addFileExtensionIfNeeded(fileToSave, desiredExtension);
      fileConsumer.accept(fileToSave);
    }
  }

  /**
   * Chooser method for saving a project.
   * @param fileConsumer file location for saving project.
   */
  public void saveProjectChooser(Consumer<File> fileConsumer) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Project");
    int userSelection = fileChooser.showSaveDialog(null);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();
      String desiredExtension = ".clg";
      fileToSave = addFileExtensionIfNeeded(fileToSave, desiredExtension);
      fileConsumer.accept(fileToSave);
    }
  }

  /**
   * Checks and adds file extension if needed.
   * @param file file that might need extension.
   * @param extension potential extension to add.
   * @return File with extension.
   */
  private File addFileExtensionIfNeeded(File file, String extension) {
    String fileName = file.getAbsolutePath();
    if (!fileName.toLowerCase().endsWith(extension.toLowerCase())) {
      fileName += extension;
      file = new File(fileName);
    }
    return file;
  }

  /**
   * Establishes hashmap for layers and filters.
   */
  public void initLayerFilterMap() {
    layerAndFilterMap = new HashMap<>();
  }

  /**
   * Establishes start of hashmap by adding initial layer.
   * @param layerName represents name of inital layer.
   */
  public void setNewLayerFilterMapNormal(String layerName) {
    layerAndFilterMap.put(layerName, 0);
  }

}

