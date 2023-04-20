# GrIMP User Guide

### How to use Text View:

- Using the jar file, or by running the program else where, locate the command line
- Input valid commands into command line
- Here are a list of valid commands:
- new-project (int) (int)
- add-layer (string)
- add-image-to-layer (string) (file) (int) (int)
- set-filter (string) (filter)
- save-image (string)
- save-project (string)
- load-project (string)
- quit
- Here are a list of valid filter options:
- filter-blue
- filter-red
- filter-green
- brighten-value
- brighten-intensity
- brighten-luma
- darken-value
- darken-intensity
- darken-luma
- blend-difference
- blend-multiply
- blend-screen
- Normal

---

### Example on how to use Text View:

- Start by running a command to open a new project "new-project 500 500"
- Add a layer to the project called "grogublue" "add-layer bottom"
- Add an image to the layer "grogublue res/grogu.ppm 0 0"
- Add a filter to the image "set-filter grogublue filter-blue"
- Try a different filter "set-filter grogublue blend-difference"
- Save the image as a png "save-image grogublue.png"
- Save the whole project "save-project grogucollage.clg"
- If you ever need the project again "load-project grogucollage.clg"

---

### How to use GUI:

- The top left button marked 'File' can be clicked to allow for a drop-down with the following options
- From here there are two options that you can hover over: Project or Image.
- Project will allow you to either open, save, load, or quit a project.
- Image with allow to save or add an image to layer.
- Note that starting a project or adding an image will require you to put in dimension or 
- coordinates.
- Once you have a project started, more options will appear on the right side of the screen.
- On the top right there will be an "add layer" button, and on the bottom right there will be the
- options to select a filter.
- Important: Layer must be selected to add image or apply layer.

---

### Example on how to use GUI

- Select "File" button on top left of screen
- Hover over project option
- Select "New Project" option
- Input dimensions of 500, 500
- Select "Add Layer" on the top right of screen
- Name new layer "ONE"
- Underneath the "Add Layer" button, select "ONE"
- After selecting our layer, click "File" on top left of screen
- Hover over image option
- Select "Add Image To Layer" option
- Locate desired image
- Input offset of 100, 100
- Go to filter selection on bottom right to apply filters

---

### Buttons

- File - Access Project and Image options
- Project - Load, Save, New, Quit
- Image - Save, Add Image To Layer
- Add Layer - Input Layer Name
- Filter dropdown - Filter options

### Part Three

- No additional visual components were added. Only the functionality to load and save jpg and 
- png images
