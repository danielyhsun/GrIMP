# Collage Program

## Part 1:

### Main:
Collage class: Contains the main method where the program is started.

---

### Model:
**Pixel class:** Represents a 4-component RGBA value composed of integers representing the red, 
green, and alpha channels of a pixel. 

**Image class:** The model's representation of an image made of a 2D array of Pixels. 

**Layer class:** Represents a layer in a Collage Project. A layer has a name to identify it, a 2D 
array of Pixels, and a filter option to specify image processing operations done to its Pixel array
when saving as an image or displaying an Image representation in the view. By default, a 
non-background Layer will be white/transparent, with all Pixel values being (255, 255, 255, 0).

**Project class:** Represents a project in a Collage program. A Project is composed of a collection
of layers, and contain methods for manipulating them. By default, Projects always have at least
one white background Layer. 

**CollageModel interface:** An interface representing the core model for a Collage program, 
which defines the methods for creating and managing projects, layers, and images. A CollageModel 
contains methods for manipulation of a Collage program's projects and layers, as well as file 
storage and retrieval for projects and images.

**CollageModelImpl class:** A class implementing the CollageModel interface, which handles the 
actual image processing tasks, including loading and saving projects, managing layers, and applying
filters to images.

---

### Controller:
**CollageController interface:** An interface representing the controller for a Collage program, 
defining the method for processing user input and executing commands.

**CollageControllerImpl class:** A class implementing the CollageController interface, which reads
user input and executes the corresponding commands. The class contains a run method that manages
the application's main loop and a knownCommands map that stores available commands.

**CollageCommand interface:** An interface representing commands for the image processing 
application, defining methods for running the command on the model and retrieving a message
associated with the command.

Various command classes implementing the CollageCommand interface, such as AddImageToLayer, 
AddLayer, LoadProject, NewProject, Quit, SaveImage, SaveProject, and SetFilter.

**Features interface:** An interface representing the functions to execute when an action is
performed by the user with the graphical user interface. 

**CollageGuiController class:** A class implementing the Features interface, which handles user 
interactions with the graphical user interface, and updates the model accordingly. For example, when
the "New Project" menu item is clicked in the GUI, an action event calls the newProject
method in this class to be run, which initializes a new project in the model, and calls other
relevant methods in the model and view when a new project is to be created.

---

### View:
**CollageView interface:** An interface representing the view for image processing, which defines 
the method for rendering messages to the user.

**CollageViewImpl class:** A class implementing the CollageView interface, which displays messages 
to the user via the Appendable object. 

**CollageGuiView interface:** An interface representing a graphical user interface representation of
the program, which allows users to interact with the program through UI components.

**CollageGuiViewImpl class:** A class implementing the CollageGuiView interface, which displays a
graphical user interface window to the user. The window has a menu bar for interacting with files
and images, like creating, loading, or saving projects, and adding or saving images. When a project
is open, the GUI window displays an image of the current collage, and has a sidebar which allows
the user to create and select layers, and a dropdown menu to apply filters to those layers.

---

### How To Use:
To use the collage program, run the main() method in the Collage class. When the program runs,
you can either type or copy and paste the script into the console. View script for list of 
valid commands.
---

### Part Three Additions:

For the additional functionality of loading and saving png and jpg images, we were able to implement
functionality by directly adding the code into the load and save functions in the project and 
model implementation class. This way the handling of ppms, jpgs, and pngs are all in one place.

### Decoupling:

- CollageView : Text View interface
- CollageViewImpl : Text View implementation
- CollageModel : Model interface
- For our text view you need to send the CollageModel interface because it requires it in order to 
compile.
- CollageGuiView : Gui View interface
- CollageGuiViewImpl : Gui View implementation
- Features : Actions Interface
- For the GUI view you only need to send the Features interface because the GUI components run
methods in the Features when an action event occurs.

For both of these views only interfaces are required in order to compile without errors. 

### Citations:
**Grogu Image:**
TVLine. (2020). Luke Skywalker and Grogu in The Mandalorian Finale. [Digital image]. 
Retrieved March 18, 2023, from 
https://tvline.com/wp-content/uploads/2020/12/mandalorian-finale-luke-skywalker-grogu.png?w=620&h=420&crop=1