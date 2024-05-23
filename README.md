# Team 15 SENG201 Project
## Authors
- SENG201 Teaching Team, Caleb Cooper, Quinn Le Lievre

## Running the jar
1. After building the application, open a terminal into the folder where the jar is located.
2. Run `java -jar seng201_team15-1.0-SNAPSHOT.jar` to open the application

## Building the application to a Jar file
1. Open a terminal into the folder where the repository is saved and run `./gradlew jar` to create a packaged Jar file.

**Note:** *The Jar file will be located at `build/libs/seng201_team15-1.0-SNAPSHOT.jar`*

## Importing the application into IntelliJ
1. Open intellij and from the landing page, open `Get from VCS`
2. Ensure `git` is selected for the Version Control. 
3. Under the blue `Code` dropdown on the GitLab repository, copy the `Clone with HTTPS` address and use that in the `url` textbox on Intellij. 
4. Select a folder to save it in and then clone the repository.

## Credit
- Minecart Icons - https://www.freeimages.com/clipart/gold-lorry-5298117
- Cog Icon - https://creazilla.com/nodes/3151369-cog-clipart
- Tower Icons - https://clipart-library.com/clipart/free-cliparts-ore_15.htm
- Pickaxe Sound Effect - https://freesound.org/people/WolfOWI/sounds/588306/
- Game Background - Used Adobe Stock AI to generate with prompt: `I would like a picture with the perspective of looking down a mine where there are gems in the walls to each side and lit up by lanterns. Maybe have a pickaxe and a helmet with a torch laying against the wall. Don't include any people and have a minecart track going deeper in the mine. Do this in a cartoony style.`



## Notes
Remember you are required to commit your code to the **main** branch of your repository before the deadline.

This project contains default naming of `team0` throughout.
If you are interested you can update this to reflect your team number, however it is **not required**.
This can be done by renaming any instance of `team0` with `team<x>` (where x is your number).
IntelliJ can help with this using `ctrl+shift+f` to find all instances of a string, and `ctrl+shift+r` to replace them, though make sure to change the package names as well.



## Prerequisites
- JDK >= 17 [click here to get the latest stable OpenJDK release (as of writing this README)](https://jdk.java.net/18/)
- *(optional)* Gradle [Download](https://gradle.org/releases/) and [Install](https://gradle.org/install/)


## What's Included
This project comes with some basic examples of the following (including dependencies in the build.gradle file):
- JavaFX
- Junit 5

We have also included a basic setup of the Gradle project and Tasks required for the course including:
- Required dependencies for the functionality above
- Test Coverage with JaCoCo
- Build plugins:
    - JavaFX Gradle plugin for working with (and packaging) JavaFX applications easily

You are expected to understand the content provided and build your application on top of it. If there is anything you
would like more information about please reach out to the tutors.

## Importing Project (Using IntelliJ)
IntelliJ has built-in support for Gradle. To import your project:

- Launch IntelliJ and choose `Open` from the start-up window.
- Select the project and click open
- At this point in the bottom right notifications you may be prompted to 'load gradle scripts', If so, click load

**Note:** *If you run into dependency issues when running the app or the Gradle pop up doesn't appear then open the Gradle sidebar and click the Refresh icon.*

## Run Project 
1. Open a command line interface inside the project directory and run `./gradlew run` to run the app.
2. The app should then open a new window, this may not be displayed over IntelliJ but can be easily selected from the taskbar

## Build and Run Jar
1. Open a command line interface inside the project directory and run `./gradlew jar` to create a packaged Jar. The Jar file is located at build/libs/seng201_team0-1.0-SNAPSHOT.jar
2. Navigate to the build/libs/ directory (you can do this with `cd build/libs`)
3. Run the command `java -jar seng201_team0-1.0-SNAPSHOT.jar` to open the application.

## Run Tests
1. Open a command line interface inside the project directory and run `./gradlew test` to run the tests.
2. Test results should be printed to the command line

**Note:** *This Jar is **NOT** cross-platform, so you **must** build the jar you submit on Linux.* 
