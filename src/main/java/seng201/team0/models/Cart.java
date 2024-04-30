package seng201.team0.models;

/**
 * Class for carts that will be used to play each round of the game, filling them up from towers of the same resource type
 * @author Caleb Cooper
 */
public class Cart {
    private Integer size;
    private String resourceType;
    private Integer speed;
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
     * Method to return the capacity of the cart
     * @return cart size
     */
    public Integer getSize() { return size; }

    /**
     * Method to return the type of resource of the cart
     * @return cart resource type
     */
    public String getResourceType() { return resourceType; }

    /**
     * Method to return the speed that the cart will travel
     * @return cart speed
     */
    public Integer getSpeed() { return speed; }

    public Integer getFilledSize() { return filledSize; }
    public void fill(int amount) {
        filledSize += amount;
        if (filledSize > size) {
            filledSize = size;
        }
    }
}
