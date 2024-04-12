package seng201.team0;

import java.util.function.Consumer;

public class GameManager {

    private final Consumer<GameManager> menuScreenLauncher;
    private final Consumer<GameManager> mainScreenLauncher;
    private final Consumer<GameManager> shopScreenLauncher;
    private final Consumer<GameManager> inventoryScreenLauncher;
    private final Runnable clearScreen;


    public GameManager(Consumer<GameManager> menuScreenLauncher, Consumer<GameManager> mainScreenLauncher, Consumer<GameManager> shopScreenLauncher, Consumer<GameManager> inventoryScreenLauncher, Runnable clearScreen) {
        this.menuScreenLauncher = menuScreenLauncher;
        this.mainScreenLauncher = mainScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.clearScreen = clearScreen;
        resetAndLaunchMenuScreen();
    }

    public void resetAndLaunchMenuScreen() {
        clearScreen.run();
        menuScreenLauncher.accept(this);
    }

    public void resetAndLaunchMainScreen() {
        clearScreen.run();
        mainScreenLauncher.accept(this);
    }

    public void resetAndOpenShopScreen() {
        clearScreen.run();
        shopScreenLauncher.accept(this);
    }

    public void resetAndOpenInventoryScreen() {
        clearScreen.run();
        inventoryScreenLauncher.accept(this);
    }
}
