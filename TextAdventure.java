import java.util.*;

public class TextAdventure {
    FancyConsole console;
    Scanner inScanner;
    Player ourHero;
    Random rand; // Random number generator
    boolean visitedMarket = false;
    boolean exploredAlley = false;
    boolean collectedHerbs = false;
    boolean foughtMonster = false;
    boolean exploredRuins = false;
    boolean restedInMountains = false;
    boolean fishedAtLake = false;
    boolean swamAtLake = false;
    boolean searchedDesert = false;
    boolean restedInDesert = false;

    public TextAdventure() {
        console = new FancyConsole("Great Text Adventure!", 600, 600);
        inScanner = new Scanner(System.in);
        ourHero = new Player("Bob", 100, 0);
        rand = new Random(); // Initialize the random number generator
    }

    public void play() {
        String input;
        console.setImage("city.jpg");

        System.out.println("What is your name?\n");
        input = inScanner.nextLine();
        ourHero.changeName(input);

        System.out.println("You wake up to find yourself on the edge of a shadowy forest with the sun nearly set. \nYou see what looks like a city in the distance. \nWhat would you like to do? \ncity: go towards the city\nforest: turn around and re-enter the forest\nnap: go back to sleep\n" + ourHero.getName() + ": ");
        input = inScanner.nextLine().toLowerCase();

        if (input.equals("city")) {
            enterZone1();
        } else if (input.equals("forest")) {
            enterZone2();
        } else if (input.equals("nap")) {
            System.out.println("You go back to sleep. Game over.");
            gameEnd();
        } else {
            System.out.println("Invalid choice. Please try again.");
            play();
        }
    }

    // Zone 1: City
    private void enterZone1() {
        console.setImage("city.jpg");
        System.out.println("You enter the city, bustling with activity.");
        System.out.println("What would you like to do?");

        if (!visitedMarket) {
            System.out.println("1: Talk to a merchant");
        }
        if (!exploredAlley) {
            System.out.println("2: Explore the alleys");
        }
        System.out.println("3: Go to the forest");
        System.out.println("4: Head to the ruins");

        String choice = inScanner.nextLine();
        if (choice.equals("1") && !visitedMarket) {
            System.out.println("You approach the merchant and buy some supplies.");
            int goldGained = 10; // Amount of gold gained
            ourHero.setGold(ourHero.getGold() + goldGained);
            System.out.println("You gained " + goldGained + " gold!");
            visitedMarket = true; // Mark the market as visited
        } else if (choice.equals("2") && !exploredAlley) {
            System.out.println("You explore the alleys and find some hidden treasures!");
            int treasure = rand.nextInt(50); // Random number for treasure
            ourHero.setGold(ourHero.getGold() + treasure);
            System.out.println("You found " + treasure + " gold!");
            exploredAlley = true; // Mark the alley as explored
        } else if (choice.equals("3")) {
            enterZone2(); // Go to forest
            return; // Return to avoid entering Zone 1 again
        } else if (choice.equals("4")) {
            enterZone5(); // Go to ruins
            return; // Return to avoid entering Zone 1 again
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        enterZone1(); // Return to city after the choice
    }

    // Zone 2: Forest
    private void enterZone2() {
        console.setImage("forest.jpg");
        System.out.println("You find yourself deep in the forest, surrounded by tall trees.");
        System.out.println("What would you like to do?");

        if (!foughtMonster) {
            System.out.println("1: Search for a monster");
        }
        if (!collectedHerbs) {
            System.out.println("2: Collect herbs");
        }
        System.out.println("3: Go to the city");
        System.out.println("4: Head to the lake");

        String choice = inScanner.nextLine();
        if (choice.equals("1") && !foughtMonster) {
            System.out.println("You search for a monster and encounter a wild beast!");
            if (ourHero.getHealth() > 20 && rand.nextInt(100) > 50) { // Random success/failure
                System.out.println("You defeat the monster!");
                ourHero.defeatMonster();
                foughtMonster = true; // Mark the monster as fought
            } else {
                System.out.println("You are too weak or unlucky to fight. You take 20 damage.");
                ourHero.setHealth(ourHero.getHealth() - 20);
                System.out.println("You now have " + ourHero.getHealth() + " health remaining.");
            }
        } else if (choice.equals("2") && !collectedHerbs) {
            System.out.println("You collect some herbs and gain health.");
            int healthGained = 10; // Amount of health gained
            ourHero.setHealth(ourHero.getHealth() + healthGained);
            System.out.println("You gained " + healthGained + " health! You now have " + ourHero.getHealth() + " health.");
            collectedHerbs = true; // Mark herbs as collected
        } else if (choice.equals("3")) {
            enterZone1(); // Go to city
            return; // Return to avoid entering Zone 2 again
        } else if (choice.equals("4")) {
            enterZone3(); // Go to lake
            return; // Return to avoid entering Zone 2 again
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        enterZone2(); // Return to forest after the choice
    }

    // Zone 3: Lake
    private void enterZone3() {
        console.setImage("lake.jpg");
        System.out.println("You find yourself at a serene lake.");
        System.out.println("What would you like to do?");

        if (!fishedAtLake) {
            System.out.println("1: Fish");
        }
        if (!swamAtLake) {
            System.out.println("2: Swim");
        }
        System.out.println("3: Head to the forest");
        System.out.println("4: Head to the desert");

        String choice = inScanner.nextLine();
        if (choice.equals("1") && !fishedAtLake) {
            System.out.println("You catch a fish!");
            int goldGained = 5; // Amount of gold gained
            ourHero.setGold(ourHero.getGold() + goldGained);
            System.out.println("You gained " + goldGained + " gold!");
            fishedAtLake = true; // Mark fishing as done
        } else if (choice.equals("2") && !swamAtLake) {
            System.out.println("You swim and enjoy the refreshing water.");
            int healthGained = 5; // Amount of health gained
            ourHero.setHealth(ourHero.getHealth() + healthGained);
            System.out.println("You gained " + healthGained + " health! You now have " + ourHero.getHealth() + " health.");
            swamAtLake = true; // Mark swimming as done
        } else if (choice.equals("3")) {
            enterZone2(); // Go to forest
            return; // Return to avoid entering Zone 3 again
        } else if (choice.equals("4")) {
            enterZone4(); // Go to desert
            return; // Return to avoid entering Zone 3 again
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        enterZone3(); // Return to lake after the choice
    }

    // Zone 4: Desert
    private void enterZone4() {
        console.setImage("desert.jpg");
        System.out.println("You find yourself in a vast desert.");
        System.out.println("What would you like to do?");

        if (!searchedDesert) {
            System.out.println("1: Search for an oasis");
        }
        if (!restedInDesert) {
            System.out.println("2: Explore the dunes");
        }
        System.out.println("3: Head to the lake");
        System.out.println("4: Head to the ruins");

        String choice = inScanner.nextLine();
        if (choice.equals("1") && !searchedDesert) {
            System.out.println("You find a hidden oasis and drink some refreshing water.");
            int healthGained = 10; // Amount of health gained
            ourHero.setHealth(ourHero.getHealth() + healthGained);
            System.out.println("You gained " + healthGained + " health! You now have " + ourHero.getHealth() + " health.");
            searchedDesert = true; // Mark oasis search as done
        } else if (choice.equals("2") && !restedInDesert) {
            System.out.println("You explore the dunes but find nothing of interest.");
            restedInDesert = true; // Mark resting as done
        } else if (choice.equals("3")) {
            enterZone3(); // Go to lake
            return; // Return to avoid entering Zone 4 again
        } else if (choice.equals("4")) {
            enterZone5(); // Go to ruins
            return; // Return to avoid entering Zone 4 again
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        enterZone4(); // Return to desert after the choice
    }

    // Zone 5: Ruins
    private void enterZone5() {
        console.setImage("ruins.jpg");
        System.out.println("You arrive at some ancient ruins.");
        System.out.println("What would you like to do?");

        if (!exploredRuins) {
            System.out.println("1: Explore the ruins");
        }
        System.out.println("2: Head to the city");
        System.out.println("3: Head to the mountains");

        String choice = inScanner.nextLine();
        if (choice.equals("1") && !exploredRuins) {
            System.out.println("You find an ancient artifact!");
            int goldGained = 20; // Amount of gold gained
            ourHero.setGold(ourHero.getGold() + goldGained);
            System.out.println("You gained " + goldGained + " gold!");
            exploredRuins = true; // Mark ruins as explored
        } else if (choice.equals("2")) {
            enterZone1(); // Go to city
            return; // Return to avoid entering Zone 5 again
        } else if (choice.equals("3")) {
            enterZone6(); // Go to mountains
            return; // Return to avoid entering Zone 5 again
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        enterZone5(); // Return to ruins after the choice
    }

    // Zone 6: Mountains
    private void enterZone6() {
        console.setImage("mountains.jpg");
        System.out.println("You climb the mountains and enjoy the view.");
        System.out.println("What would you like to do?");

        if (!restedInMountains) {
            System.out.println("1: Rest in the mountains");
        }
        System.out.println("2: Head to the ruins");
        System.out.println("3: Head back to the city");

        String choice = inScanner.nextLine();
        if (choice.equals("1") && !restedInMountains) {
            System.out.println("You rest and regain your strength.");
            int healthGained = 15; // Amount of health gained
            ourHero.setHealth(ourHero.getHealth() + healthGained);
            System.out.println("You gained " + healthGained + " health! You now have " + ourHero.getHealth() + " health.");
            restedInMountains = true; // Mark resting as done
        } else if (choice.equals("2")) {
            enterZone5(); // Go to ruins
            return; // Return to avoid entering Zone 6 again
        } else if (choice.equals("3")) {
            enterZone1(); // Go to city
            return; // Return to avoid entering Zone 6 again
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        enterZone6(); // Return to mountains after the choice
    }

    // Game over
    private void gameEnd() {
        System.out.println("Game over. Thank you for playing!");
        System.exit(0);
    }

    // Main method
    public static void main(String[] args) {
        TextAdventure game = new TextAdventure();
        game.play();
    }
}
