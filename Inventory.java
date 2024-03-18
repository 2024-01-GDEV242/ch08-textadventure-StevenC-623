import java.util.ArrayList;
/**
 * Write a description of class Inventory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Inventory
{
    // instance variables - replace the example below with your own
    public ArrayList<Item> items;
    
    public Inventory()
    {
      items = new ArrayList<>();
    }
    public void addItem(Item item)
    {
        items.add(item);
    }
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
    public int getInventorySize()
    {
        return items.size();
    }
    public boolean compareDescriptions(String itemA, Item itemB)
    {
        boolean isTrue = false;
        if(itemA.compareTo(itemB.getDescription())==0)
        {
            isTrue = true;
        }
        return isTrue;
    }
    public Item getItem(int x)
    {
        return items.get(x);
    }
    public void removeItem(int x)
    {
        items.remove(x);
    }
}
