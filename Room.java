import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Non-Sense" application. 
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Steven Coss
 * @version 2024.03.24
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        
    public Inventory inventoryItems;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        inventoryItems = new Inventory();
        this.description = description;
        exits = new HashMap<>();
    }
    /**
     * Add an item to the rooms inventory
     * @param item the item to be added
     */
    public void addItem(Item item)
    {
       inventoryItems.addItem(item);
    }
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description ;
    }
    /**
     * Return a description of the room in the form:
     *     You are in the kitchen their is a knife here.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        if(inventoryItems.getInventorySize()>0)
        {
            return "You are " + description +" there is a "+inventoryItems.printDescriptions() +"here.\n" + getExitString();
        }
        else
        {
            return "You are " + description +" here.\n" + getExitString();
        }
        
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    
}

