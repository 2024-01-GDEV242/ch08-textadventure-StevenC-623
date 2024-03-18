/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room priorRoom;
    private Player thePlayer;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university",new Item("flower",1,true,10));
        outside.addItem(new Item("key",4,false,0));
        theater = new Room("in a lecture theater",new Item("popcorn",2,true,20));
        pub = new Room("in the campus pub",new Item("beer",4,true,20));
        lab = new Room("in a computing lab",new Item("potion",2,true,50));
        office = new Room("in the computing admin office",new Item("stapler", 4,true,20));    
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        theater.setExit("west", outside);
        pub.setExit("east", outside);
        lab.setExit("north", outside);
        lab.setExit("east", office);
        office.setExit("west", lab);
        currentRoom = outside;
        priorRoom = null;// start game outside
    }


    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.       
        boolean finished = false;
        thePlayer = new Player(currentRoom);
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;
            case HELP:
                printHelp();
                break;
            case GO:
                goRoom(command);
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
            case LOOK:
                System.out.println(currentRoom.getLongDescription());
                System.out.println("current Health :"+thePlayer.currentHealth());
                break;
            case EAT:
                eat(command);
                break;
            case BACK:
                goBack(command);
                break;
            case PICKUP:
                pickUp(command);
                break;
            case INVENTORY:
                System.out.println(thePlayer.inventoryDescription());
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.\n");
        System.out.println("Your command words are:");
        System.out.print("  ");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            priorRoom = currentRoom;
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            thePlayer.step();
            System.out.println("current Health :"+thePlayer.currentHealth());
        }
    }
    private void goBack(Command command)
    {
        if(priorRoom != null)
        {
            Room nextRoom = priorRoom;
            priorRoom = currentRoom;
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            thePlayer.step();
            System.out.println("current Health :"+thePlayer.currentHealth());
        }
        else
        {
            System.out.println("You can't go back");
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    private void pickUp(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("pick up what?");
            return;
        }

        String itemToPickup = command.getSecondWord();
        // Try to leave current room.
        //Room nextRoom = currentRoom.getExit(direction);
        boolean found = false;
        for(int i =0; i<currentRoom.inventoryItems.getInventorySize(); i++)
        {
            if(currentRoom.inventoryItems.compareDescriptions(itemToPickup,currentRoom.inventoryItems.getItem(i)))
            {
                found = true;
                thePlayer.getInventory().addItem(currentRoom.inventoryItems.getItem(i));
                currentRoom.inventoryItems.removeItem(i);
                System.out.println("you picked up "+itemToPickup);
            }
        }
        if( found == false)
        {
            System.out.println("That item isn't here");
            return;
        }
    }
    private void eat(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("eat what?");
            return;
        }
        String itemToEat = command.getSecondWord();
        boolean found = false;
        for(int i = 0; i <thePlayer.getInventory().getInventorySize(); i++)
        {
            if(thePlayer.getInventory().compareDescriptions(itemToEat,thePlayer.getInventory().getItem(i)))
            {
                thePlayer.eat(thePlayer.getInventory().getItem(i));
                thePlayer.getInventory().removeItem(i);
                System.out.println("you eat "+itemToEat);
            }
        }
        if( found == false)
        {
            System.out.println("you cant eat that");
            return;
        }
    }
}
