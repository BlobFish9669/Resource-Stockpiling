package seng201.team15.models.upgradetypes;
import seng201.team15.models.Upgrade;

/**
 * Class containing information for an upgrade that is available in the pool to be purchased in the shop
 */
public class Upgrade4 extends Upgrade {
    /**
     * Constructor
     */
    public Upgrade4() {
        super("Increase selling price by 1.5x", "Price", 50, 1.5);
    }
}