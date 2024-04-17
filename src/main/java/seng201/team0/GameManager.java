package seng201.team0;

import seng201.team0.services.*;

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

    private final DifficultySelectionService difficultyService;
    private final NameInputService nameService;
    private final RoundsSelectionService roundsService;
    private final TowerSelectionService towerService;
    private final MoneyBalanceService moneyService;
    private final CurrentRoundService currentRoundService;
    /**
     * Constructor
     * @param menuScreenLauncher Action to execute to display the menu screen.
     * @param mainScreenLauncher Action to execute to display the main screen.
     * @param shopScreenLauncher Action to execute to display the shop screen.
     * @param inventoryScreenLauncher Action to execute to display the inventory screen.
     * @param clearScreen Action to execute to clear the screen
     */
    public GameManager(Consumer<GameManager> menuScreenLauncher, Consumer<GameManager> mainScreenLauncher, Consumer<GameManager> shopScreenLauncher, Consumer<GameManager> inventoryScreenLauncher, Runnable clearScreen, DifficultySelectionService difficultyService, NameInputService nameService, RoundsSelectionService roundsService, TowerSelectionService towerService, MoneyBalanceService moneyService, CurrentRoundService currentRoundService) {
        this.menuScreenLauncher = menuScreenLauncher;
        this.mainScreenLauncher = mainScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.clearScreen = clearScreen;
        this.difficultyService = difficultyService;
        this.nameService = nameService;
        this.roundsService = roundsService;
        this.towerService = towerService;
        this.moneyService = moneyService;
        this.currentRoundService = currentRoundService;
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

    public DifficultySelectionService getDifficultyService() {
        return difficultyService;
    }

    public NameInputService getNameService() {
        return nameService;
    }

    public RoundsSelectionService getRoundsService() {
        return roundsService;
    }

    public TowerSelectionService getTowerService() {
        return towerService;
    }

    public MoneyBalanceService getMoneyService() {
        return moneyService;
    }

    public CurrentRoundService getCurrentRoundService() {
        return currentRoundService;
    }
}
