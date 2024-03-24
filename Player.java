
/**
 * The Player Class is the active agent the user controls to navigate threw the world of NonSense
 *
 * @author Steven Coss
 * @version 2024-03-24
 */
public class Player
{
    private Inventory playerInv;
    private Room currentRoom;
    private int health;
    private int maxWeight;
    private int currentWeight;
    /**
     * Constructor for the class Player
     * sets the players starting paramaters up
     * and creates the players inventory
     * @param spawnRoom this is where the player object starts
     */
    
    public Player(Room spawnRoom)
    {
        currentWeight =0;
        maxWeight = 15;
        health = 20;
        playerInv = new Inventory();
        currentRoom = spawnRoom;
    }
    /**
     * gets the currentWeight of objects in inventory
     * @return the players currentweight
     */
    public int getWeight()
    {
        return currentWeight;
    }
    /**
     * gets the maximum weight the player can carry 
     * @return the maxWeight
     */
    public int getMaxWeight()
    {
        return maxWeight;
    }
    /**
     * adds an ammount of weight to the player
     * @addedWeight the weight of an item to be added
     */
    public void addWeight(int addedWeight)
    {
        currentWeight += addedWeight;
    }
    /**
     * a description of the items held in the inventory in addition to their assossiated weights
     * @return string containing the current/maxweight aswell as a discription of all items currently in the players inventory
     */
    public String inventoryDescription()
    {
        return "Weight: "+currentWeight+"/"+maxWeight+"\n"+playerInv.printWeightedDescriptions();
    }
    /**
     * gets the players inventory
     * @return the inventory the players has
     */
    public Inventory getInventory()
    {
        return playerInv;
    }
    /**
     * eats an item in the  players inventory changing their health and weight but not removing it from the inventory
     * @param eatMe the item to be eaten
     */
    public void eat(Item eatMe)
    {
        health += eatMe.getHealthChange();
        currentWeight -= eatMe.getWeight();
        if (health>20)
        {
            health = 20;
        }
    }
    /**
     * the players current health
     * @return the players current health total
     */
    public int currentHealth()
    {
        return health;
    }
    /**
     * decreases the players health by 1
     */
    public void step()
    {
        health -=1;
    }
    /**
     * checks to see if the key is within the players inventory
     * @return if the key was found
     */
    public boolean hasKey()
    {
        boolean found = false;
        for(int i =0 ; i< playerInv.getInventorySize(); i++)
        {
            if(playerInv.compareDescriptions("key",playerInv.getItem(i)))
            {
                found = true;
            }
        }
        return found;
    }
}
