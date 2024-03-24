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
    private Room pub;
        
    /**
     * Create the game and initialise its internal map.
     */

    public Game() 
    {
        createRooms();
        parser = new Parser();
    }
    public static void main(String[] args)
    {
        Game theGame = new Game();
        theGame.play();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, lab,garden,mazeEnt,mazeN,mazeW,mazeE,mazeNW,fountain,mazeS,mazeNE,gazeboPath,gazebo,mazeDeadEnd;
        pub = new Room("in the pub");  
        // create the rooms
        //outside.addItem(new Item("key",4,false,0));
        //room.addItem(new Item("flower",1,true,10));
        outside = new Room("outside the main entrance of a university");
        garden = new Room("in a beautiful guardin with a path leading north into a large Maze");
        mazeEnt = new Room("standing before a large archway leading into the maze, a nearby note reads: 'withen lies the key to the pub,beware of gazebo's'");
        mazeN = new Room("lush green walls of plantlife rise up around you, from here the path splits");
        mazeW = new Room("at a major crossroads with alot of diffrent ways to go");
        mazeE = new Room("at an intersetions but the lush green walls here have a dull faded color");
        mazeNW = new Room("at another intersection you think you hear the sound of running water nearby");
        fountain = new Room("at a beutiful foutain, with flowing red water");
        mazeS = new Room("at a major crossroads with alot of diffrent ways to go");
        mazeNE = new Room("at an intersetions but and lush green walls here are instead a dull faded color");
        gazeboPath = new Room("in a large opening with a grand gazebo sitting to the north");
        gazebo = new Room("carefully sneaking up to the gazebo as not to wake it, carefully looking around");
        mazeDeadEnd = new Room("at a dead end, nothing here");
        theater = new Room("in a movie theater");
        lab = new Room("in a science lab");
        
        // initialise room exits
        outside.setExit("north", garden);
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        
        garden.setExit("north",mazeEnt);
        garden.setExit("south", outside);
        garden.setExit("west",theater);
        garden.addItem(new Item("flower",1,true,3));
        garden.addItem(new Item("rose",1,true,3));
        
        mazeEnt.setExit("north",mazeN);
        mazeEnt.setExit("south",garden);
        mazeEnt.addItem(new Item("apple",3,true,7));
        
        mazeN.setExit("east",mazeE);
        mazeN.setExit("south",mazeEnt);
        mazeN.setExit("west",mazeW);

        mazeW.setExit("north", mazeNW);        
        mazeW.setExit("east", mazeN);
        mazeW.setExit("south", mazeDeadEnd);
        mazeW.setExit("west", mazeE);
        
        mazeE.setExit("north",mazeDeadEnd);
        mazeE.setExit("east",mazeW);
        mazeE.setExit("west",mazeN);
        mazeE.addItem(new Item("deadLeaf",1,true,-1));

        
        mazeNW.setExit("north",mazeN);
        mazeNW.setExit("east",fountain);
        mazeNW.setExit("south",mazeW);
        mazeNW.setExit("west",mazeE);
        mazeNW.addItem(new Item("skeleton",5,true,20));

        
        fountain.setExit("north",mazeS);
        fountain.setExit("east",mazeDeadEnd);
        fountain.setExit("west",mazeNW);
        fountain.addItem(new Item("water",3,true,-5));
        
        mazeS.setExit("north",gazeboPath);        
        mazeS.setExit("east",mazeNE);
        mazeS.setExit("south",fountain);
        mazeS.setExit("west",mazeW);

        mazeNE.setExit("north",mazeDeadEnd);
        mazeNE.setExit("south",mazeE);
        mazeNE.setExit("west",mazeS);
        mazeNE.addItem(new Item("sword",4,true,-5));
        
        gazeboPath.setExit("north",gazebo);
        gazeboPath.setExit("east",mazeNE);
        gazeboPath.setExit("south",mazeS);
        gazeboPath.setExit("west",mazeDeadEnd);
        
        gazebo.setExit("south",gazeboPath);
        gazebo.addItem(new Item("key",0,false,5));
        mazeDeadEnd.addItem(new Item("axe",4,false,2));
        
        theater.setExit("west", outside);
        theater.addItem(new Item("popcorn",2,true,3));
        
        pub.addItem(new Item("beer",2,true,100));
        
        lab.setExit("north", outside);
        lab.addItem(new Item("potion",1,true,5));
        lab.addItem(new Item("poison",1,true,-10));
        
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
        if(thePlayer.currentHealth() <= 0 )
        {
            System.out.println("You ran out of health and have died");
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Non-Sense");
        System.out.println("Here is a new,quick adventure game, where your goal is to get into the pub");
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
                System.out.println("current Health :"+thePlayer.currentHealth());
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
            case DROP:
                drop(command);
                break;
        }
        if(thePlayer.currentHealth() <= 0)
        {
            wantToQuit = true;
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
        if(thePlayer.hasKey()==false)
        {
            System.out.println("eating things will affect health, and you can only eat things in your inventory");
            System.out.println("if your health reaches 0 its game over");
            System.out.println("Hint: Find the key to open the pub");
            System.out.println("it's somewhere in the maze to the north.\n");

            System.out.println("Your command words are:");
            System.out.print("  ");
            parser.showCommands();
        }
        else
        {
            System.out.println("Nice job you found the key");
            System.out.println("The next step get to the pub");
            System.out.println("it's to the west of where you started.\n");
            System.out.println("Your command words are:");
            System.out.print("  ");
            parser.showCommands();
        }
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
        if(nextRoom == pub && thePlayer.hasKey() == false)
        {
            System.out.println("This is the path to the pub however");
            System.out.println("The Door to the pub is locked");
        }
        if(nextRoom == pub && thePlayer.hasKey() == true)
        {
            System.out.println("This is the path to the pub however the door is locked");
            System.out.println("Thankfully you have found the key and unlock the door");
            priorRoom = currentRoom;
            currentRoom = nextRoom;
            System.out.println("You made it! time to sit down have a cold paint and wait for all this to blow over.");
        }
        if (nextRoom == null) {
            System.out.println("There is no path!");
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
                if(currentRoom.inventoryItems.getItem(i).getWeight() + thePlayer.getWeight() <= thePlayer.getMaxWeight())
                {
                    found = true;
                    thePlayer.addWeight(currentRoom.inventoryItems.getItem(i).getWeight());
                    thePlayer.getInventory().addItem(currentRoom.inventoryItems.getItem(i));
                    currentRoom.inventoryItems.removeItem(i);
                    System.out.println("you picked up "+itemToPickup);
                }
                else
                {
                    found = true;
                    System.out.println("that item is to heavy for you to pickup at the moment");
                }
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
            if(thePlayer.getInventory().compareDescriptions(itemToEat,thePlayer.getInventory().getItem(i)) && thePlayer.getInventory().getItem(i).canEat()==true)
            {
                found = true;
                thePlayer.eat(thePlayer.getInventory().getItem(i));
                thePlayer.getInventory().removeItem(i);
                System.out.println("you eat your "+itemToEat);
            }
        }
        if(found == false)
        {
            System.out.println("you cant eat that");
            return;
        }
    }
    private void drop(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("drop what?");
            return;
        }
        String itemToDrop = command.getSecondWord();
        boolean found = false;
        for(int i =0; i<thePlayer.getInventory().getInventorySize(); i++)
        {
            if(thePlayer.getInventory().compareDescriptions(itemToDrop,thePlayer.getInventory().getItem(i)))
            {
                    found = true;
                    currentRoom.inventoryItems.addItem(thePlayer.getInventory().getItem(i));
                    thePlayer.addWeight(-thePlayer.getInventory().getItem(i).getWeight());
                    thePlayer.getInventory().removeItem(i);
                    System.out.println("you dropped up "+itemToDrop);

            }
        }
        if(found == false)
        {
            System.out.println("That item isn't in your inventory here");
            return;
        }
        
    }
}
