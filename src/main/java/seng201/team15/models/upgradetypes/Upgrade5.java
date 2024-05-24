package seng201.team15.models.upgradetypes;
import seng201.team15.models.Upgrade;

/**
 * Class containing information for an upgrade that is available in the pool to be purchased in the shop
 */
public class Upgrade5 extends Upgrade {
    /**
     * Constructor
     */
    public Upgrade5() {
        super("Change Resource Type to Diamond", "Resource Type" , "Diamond", 60, 1.0);
    }
}
