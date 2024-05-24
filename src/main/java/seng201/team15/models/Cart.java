package seng201.team15.models;

/**
 * Class for carts that will be used to play each round of the game, filling them up from towers of the same resource type
 * @author Caleb Cooper
 */
public class Cart {
    private final Integer size;
    private final String resourceType;
    private final Integer speed;
    private Integer filledSize;

    /**
     * Constructor
     * @param size the capacity of the cart that must be filled
     * @param resourceType the type of resource that the cart can contain
     * @param speed in m/s of how fast the cart will travel along the track
     */
    public Cart(Integer size, String resourceType, Integer speed) {
        this.size = size;
        this.resourceType = resourceType;
        this.speed = speed;
        this.filledSize = 0;
    }

    /**
     * Returns the capacity of the cart
     * @return cart size
     */
    public Integer getSize() { return size; }

    /**
     * Returns the type of resource of the cart
     * @return cart resource type
     */
    public String getResourceType() { return resourceType; }

    /**
     * Returns the speed that the cart will travel
     * @return cart speed
     */
    public Integer getSpeed() { return speed; }

    /**
     * Returns how full the cart is
     * @return the filled size of the cart
     */
    public Integer getFilledSize() { return filledSize; }

    /**
     * Used to fill a cart up each round with resources
     * @param amount of resources to fill cart with
     */
    public void fill(int amount) {
        filledSize += amount;
        if (filledSize > size) {
            filledSize = size;
        }
    }
}
