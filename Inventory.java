import java.util.ArrayList;
/**
 * Rooms and players have Inventories within the World of NonSense to store their items
 * to be honest this is largly unnessary but i did it anyway.
 * 
 * @author Steven Coss
 * @version 2024-03-24
 */
public class Inventory
{
    public ArrayList<Item> items;
    /**
     * Construtor for Inventory
     */
    public Inventory()
    {
      items = new ArrayList<>();
    }
    /**
     * adds an item to the arrayList of items
     * @param item the item to be added in
     */
    public void addItem(Item item)
    {
        items.add(item);
    }
    /**
     * prints the discritpiton of all items withen the inventory
     * @return a string that concatantates the discription of all the items in the array list items
     */
    public String printDescriptions()
    {
        String toReturn = new String("");
        for (int i=0; i< items.size(); i++)
        {
            if(i>0)
            {
                toReturn = toReturn.concat("and ");
            }
            toReturn = toReturn.concat(items.get(i).getDescription()+" ");
        }
        return toReturn;
    }
    /**
     * prints the same thing as printDescription but add in the weight of the items
     * @return the descriptions and weights of all items in the array list items
     */
    public String printWeightedDescriptions()
    {
        String toReturn = new String("");
        for (int i=0; i< items.size(); i++)
        {
            if(i>0)
            {
                toReturn = toReturn.concat("and ");
            }
            toReturn = toReturn.concat(items.get(i).weightDescription()+" ");
        }
        return toReturn;
    }
    /**
     * gets the size of the inventory
     * @reutrn tells how many items are inside the inventory
     */
    public int getInventorySize()
    {
        return items.size();
    }
    /**
     * compares a string to the discription of the item to see if they match
     * @param itemA string to be compared
     * @param itemB the item who description is trying to be matched
     * @return if they are equal (true) or not 
     */
    public boolean compareDescriptions(String itemA, Item itemB)
    {
        boolean isTrue = false;
        if(itemA.compareTo(itemB.getDescription())==0)
        {
            isTrue = true;
        }
        return isTrue;
    }
    /**
     * gets a specific item out of the inventory
     * @param x index of item to retreave
     * @return the item indexed
     */
    public Item getItem(int x)
    {
        return items.get(x);
    }
    /**
     * removes a specific item from the inventory
     * @param x index of the item needed to be removed
     */
    public void removeItem(int x)
    {
        items.remove(x);
    }
}
