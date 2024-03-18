
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
    
    public Player(Room spawnRoom)
    {
        health = 100;
        playerInv = new Inventory();
        currentRoom = spawnRoom;
    }
    public void pickUp(Item toPickUp)
    {
        playerInv.addItem(toPickUp);
    }
    public String inventoryDescription()
    {
        return playerInv.printDescriptions();
    }
    public Inventory getInventory()
    {
        return playerInv;
    }
    public void eat(Item eatMe)
    {
        health += eatMe.getHealthChange();
    }
    public int currentHealth()
    {
        return health;
    }
    public void step()
    {
        health -=1;
    }
}
