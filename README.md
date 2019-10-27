# auiLab
Java swing dummy app for Uni

Developed using InteliJ IDEA (might be easier to open it up there).

### Lab3 Notes

Changelog:

* Separated the shapes into separate classes
* Added shapes selection
* Added multiple shape selection with Shift-click
* Can change the colors of selected shapes
* Can move selected shapes
* Using the status controller with the photo functions
* Double-clicking is now showing/hiding the drawings instead of flipping

### Lab2 Notes

The important bits added in the Lab2 are in `src/com/razvanilin/auiLab/photo`. The rest of the application was mostly built in `Lab1` so I still need to refactor some of that previous code.

The Releases can be found [here](https://github.com/razvanilin/auiLab/releases).

Features added:

* Image import & delete
* Image flip
* Drawing:
    * Draw using a line path
    * Draw ellipses
    * Draw Rectangles
* Adding text when clicking on the flipped image
* Ability to delete the text while typing
* Implemented text wrap that is also aware of the height of the image