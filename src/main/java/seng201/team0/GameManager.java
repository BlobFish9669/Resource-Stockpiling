package seng201.team0;

import java.util.function.Consumer;

/**
 * Manages screens within the game and transitions between them within the application. - Used Tut2 for reference
 * @author Caleb Cooper
 */
public class GameManager {

    private final Consumer<GameManager> menuScreenLauncher;
    private final Consumer<GameManager> mainScreenLauncher;
    private final Consumer<GameManager> shopScreenLauncher;
    private final Consumer<GameManager> inventoryScreenLauncher;
    private final Runnable clearScreen;
    /**
     * Constructor
     * @param menuScreenLauncher Action to execute to display the menu screen.
     * @param mainScreenLauncher Action to execute to display the main screen.
     * @param shopScreenLauncher Action to execute to display the shop screen.
     * @param inventoryScreenLauncher Action to execute to display the inventory screen.
     * @param clearScreen Action to execute to clear the screen
     */
    public GameManager(Consumer<GameManager> menuScreenLauncher, Consumer<GameManager> mainScreenLauncher, Consumer<GameManager> shopScreenLauncher, Consumer<GameManager> inventoryScreenLauncher, Runnable clearScreen) {
        this.menuScreenLauncher = menuScreenLauncher;
        this.mainScreenLauncher = mainScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.clearScreen = clearScreen;
        resetAndLaunchMenuScreen();
    }

    /**
     * Method to call to clear the current screen and then open the menu screen
     */
    public void resetAndLaunchMenuScreen() {
        clearScreen.run();
        menuScreenLauncher.accept(this);
    }
    /**
     * Method to call to clear the current screen and then open the main screen
     */
    public void resetAndLaunchMainScreen() {
        clearScreen.run();
        mainScreenLauncher.accept(this);
    }
    /**
     * Method to call to clear the current screen and then opens the shop screen
     */
    public void resetAndOpenShopScreen() {
        clearScreen.run();
        shopScreenLauncher.accept(this);
    }
    /**
     * Method to call to clear the current screen and then opens the inventory screen
     */
    public void resetAndOpenInventoryScreen() {
        clearScreen.run();
        inventoryScreenLauncher.accept(this);
    }
}
