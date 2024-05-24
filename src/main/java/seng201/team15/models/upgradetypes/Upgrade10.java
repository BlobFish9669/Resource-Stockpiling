package seng201.team15.models.upgradetypes;
import seng201.team15.models.Upgrade;

/**
 * Class containing information for an upgrade that is available in the pool to be purchased in the shop
 */
public class Upgrade10 extends Upgrade {
    /**
     * Constructor
     */
    public Upgrade10() {
        super("Change Resource Type to Gold", "Resource Type" , "Gold", 40, 1.0);
    }
}
