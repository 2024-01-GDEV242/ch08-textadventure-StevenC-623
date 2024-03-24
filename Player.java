
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    private Inventory playerInv;
    private Room currentRoom;
    private int health;
    private int maxWeight;
    private int currentWeight;
    
    public Player(Room spawnRoom)
    {
        currentWeight =0;
        maxWeight = 15;
        health = 20;
        playerInv = new Inventory();
        currentRoom = spawnRoom;
    }
    public int getWeight()
    {
        return currentWeight;
    }
    public int getMaxWeight()
    {
        return maxWeight;
    }
    public void addWeight(int addedWeight)
    {
        currentWeight += addedWeight;
    }
    public String inventoryDescription()
    {
        return "Weight: "+currentWeight+"/"+maxWeight+"\n"+playerInv.printWeightedDescriptions();
    }
    public Inventory getInventory()
    {
        return playerInv;
    }
    public void eat(Item eatMe)
    {
        health += eatMe.getHealthChange();
        currentWeight -= eatMe.getWeight();
        if (health>20)
        {
            health = 20;
        }
    }
    public int currentHealth()
    {
        return health;
    }
    public void step()
    {
        health -=1;
    }
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
