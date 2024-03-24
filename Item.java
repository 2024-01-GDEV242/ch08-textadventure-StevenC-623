
/**
 * a Class shared by all items within the World of NonSense
 *
 * @author Steven Coss
 * @version 2024-03-24
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String description;
    private int weight;
    private boolean canEat;
    private int healthChange;

    /**
     * Constructor for objects of class Item
     * sets up the items paramaters
     * @param description the name of the item
     * @param weight how much the item weights
     * @param if it can be eaten
     * @param what effect the item has on health when eaten
     */
    public Item(String description, int weight,boolean eatable,int healthEffect)
    {
         this.description = description;
         this.weight = weight;
         canEat = eatable;
         healthChange = healthEffect;
    }
    /**
     * gets the name of the item
     * @return the name of the item
     */
    public String getDescription()
    {
        return description;
    }
    /**
     * gets the name of the item and its weight
     * @reurn name of item + weight
     */
    public String weightDescription()
    {
        return description + " ("+weight+")";
    }
    /**
     * gets the weight of an item
     * @return how much it weights
     */
    public int getWeight()
    {
        return weight;
    }
    /**
     * sets the weight of an item
     * @newWeight a weight to be assigned to an item
     */
    public void setWeight(int newWeight)
    {
        weight = newWeight;
    }
    /**
     * sets a description of the item
     * @param newDescription a new name for the item
     */
    public void setDescription(String newDescription)
    {
        description = newDescription;
    }
    /**
     * gets the ammount that the health will change by
     * @return the items effect on health
     */
    public int getHealthChange()
    {
        return healthChange;
    }
    /**
     * asks if the item is eatable
     * @return if the item can be eaten
     */
    public boolean canEat()
    {
        return canEat;
    }
}
