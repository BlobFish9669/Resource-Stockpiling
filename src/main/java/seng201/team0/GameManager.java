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
    private final ShopAvailabilityService shopAvailabilityService;
    /**
     * Constructor
     * @param menuScreenLauncher Action to execute to display the menu screen
     * @param mainScreenLauncher Action to execute to display the main screen
     * @param shopScreenLauncher Action to execute to display the shop screen
     * @param inventoryScreenLauncher Action to execute to display the inventory screen
     * @param clearScreen Action to execute to clear the screen
     * @param difficultyService Service for managing the selection of game difficulty
     * @param nameService Service for managing the player's name
     * @param roundsService Service for managing the selection of game rounds
     * @param towerService Service for managing the selection of towers
     * @param moneyService Service for managing the player's money
     * @param currentRoundService Service for managing the current round of the game
     * @param shopAvailabilityService Service for managing the availability of items in the shop
     */
    public GameManager(Consumer<GameManager> menuScreenLauncher, Consumer<GameManager> mainScreenLauncher, Consumer<GameManager> shopScreenLauncher, Consumer<GameManager> inventoryScreenLauncher, Runnable clearScreen, DifficultySelectionService difficultyService, NameInputService nameService, RoundsSelectionService roundsService, TowerSelectionService towerService, MoneyBalanceService moneyService, CurrentRoundService currentRoundService, ShopAvailabilityService shopAvailabilityService) {
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
        this.shopAvailabilityService = shopAvailabilityService;
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
    /**
     * Method to retrieve the instance of DifficultySelectionService to track difficulty throughout the game
     * @return instance of DifficultySelectionService
     */
    public DifficultySelectionService getDifficultyService() {
        return difficultyService;
    }
    /**
     * Method to retrieve the instance of NameInputService to track name input throughout the game
     * @return instance of NameInputService
     */
    public NameInputService getNameService() {
        return nameService;
    }
    /**
     * Method to retrieve the instance of RoundsSelectionService to track round input throughout the game
     * @return instance of RoundsSelectionService
     */
    public RoundsSelectionService getRoundsService() {
        return roundsService;
    }
    /**
     * Method to retrieve the instance of TowerSelectionService to track players chosen tower input throughout the game
     * @return instance of TowerSelectionService
     */
    public TowerSelectionService getTowerService() {
        return towerService;
    }
    /**
     * Method to retrieve the instance of MoneyBalanceService to track players money throughout the game
     * @return instance of MoneyBalanceService
     */
    public MoneyBalanceService getMoneyService() {
        return moneyService;
    }
    /**
     * Method to retrieve the instance of CurrentRoundService to track number of current round throughout the game
     * @return instance of CurrentRoundService
     */
    public CurrentRoundService getCurrentRoundService() {
        return currentRoundService;
    }
    /**
     * Method to retrieve the instance of ShopAvailabilityService to track shop availability throughout the round
     * @return instance of ShopAvailabilityService
     */
    public ShopAvailabilityService getShopAvailabilityService() {
        return shopAvailabilityService;
    }
}