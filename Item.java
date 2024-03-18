
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
     */
    public Item(String description, int weight,boolean eatable,int healthEffect)
    {
         this.description = description;
         this.weight = weight;
         canEat = eatable;
         healthChange = healthEffect;
    }
    public String getDescription()
    {
        return description;
    }
    public int getWeight()
    {
        return weight;
    }
    public void setWeight(int newWeight)
    {
        weight = newWeight;
    }
    public void setDescription(String newDescription)
    {
        description = newDescription;
    }
    public int getHealthChange()
    {
        return healthChange;
    }
}
