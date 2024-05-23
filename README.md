# Team 15 SENG201 Project
## Authors
- SENG201 Teaching Team, Caleb Cooper, Quinn Le Lievre

## Importing the Application Into IntelliJ
1. Open intellij and from the landing page, open `Get from VCS`
2. Ensure `git` is selected for the Version Control.
3. Under the blue `Code` dropdown on the GitLab repository, copy the `Clone with HTTPS` address and use that in the `url` textbox on Intellij.
4. Select a folder to save it in and then clone the repository.
5. (Optional) If you would like to run the project from before packaging it to a Jar, open a command line interface inside the project directory and run the command `./gradlew run`.

## Building the Application to a Jar
1. Open a new terminal into the folder where the repository is saved to and run `./gradlew jar` to create a packaged Jar file.

**Note:** *The Jar file will be located at `build/libs/seng201_team15-1.0-SNAPSHOT.jar`*

## Running the Jar
1. After building the application, open a new terminal into the folder where the jar is saved to.
2. Run the command `java -jar seng201_team15-1.0-SNAPSHOT.jar` to open the application.


## Credit
- Minecart Icons - https://www.freeimages.com/clipart/gold-lorry-5298117
- Cog Icon - https://creazilla.com/nodes/3151369-cog-clipart
- Tower Icons - https://clipart-library.com/clipart/free-cliparts-ore_15.htm
- Pickaxe Sound Effect - https://freesound.org/people/WolfOWI/sounds/588306/
- Game Background - Used Adobe Stock AI to generate with prompt: `I would like a picture with the perspective of looking down a mine where there are gems in the walls to each side and lit up by lanterns. Maybe have a pickaxe and a helmet with a torch laying against the wall. Don't include any people and have a minecart track going deeper in the mine. Do this in a cartoony style.`

**Note:** *This Jar is **NOT** cross-platform*