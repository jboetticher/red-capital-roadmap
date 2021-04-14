//--== CS400 File Header Information ==--
//Name: Jeremy Boetticher
//Email: boetticher@wisc.edu
//Team: Red
//Group: KF
//TA: Keren Chen
//Lecturer: Gary Dahl
//Notes to Grader: ---

import java.util.Scanner;
import java.util.zip.DataFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.List;
import java.util.NoSuchElementException;

public class FrontEnd {

    static final char QUIT_COMMAND = 'q';
    static final char HOME_MENU_COMMAND = 'h';
    static final char PATH_MENU_COMMAND = 'p';
    static final char INFO_COMMAND = 'i';
    static final char LIST_STATES_COMMAND = 'l';
    static final char SHORTEST_PATH_COMMAND = 's';
    static final char REMOVE_CITY_COMMAND = 'r';
    static final char ADD_CITY_COMMAND = 'a';

    public static void main(String[] args) throws FileNotFoundException, IOException, DataFormatException {
        run();
    }

    /**
     * Runs the program.
     */
    public static void run() {
        // mock backend that doesn't work
        BackendInterface backend = new MockBackend();

        // scanner object
        Scanner scnr = new Scanner(System.in);
        char userCmd = 'x';

        // greeting + home scren
        printHeader("STATE CAPITAL ROUTE SYSTEM");
        println("Find your best route to each state capital with this State Capital GPS!");
        homeMenu();

        // get userButton input from user
        userCmd = promptInput(scnr);

        // User functions behind Menu ("q" to quit menu)
        while (userCmd != QUIT_COMMAND) {

            switch (userCmd) {
            case PATH_MENU_COMMAND:
                userCmd = pathMenu(scnr, backend);
                continue;
            case INFO_COMMAND:
                printHeader("Display State & Capital Info");
                scnr.nextLine();
                String infoInput = promptLineInput(
                        "Enter the initial of the state whose capital you wish to learn about: ", scnr);
                println(backend.getCityInfo(infoInput));
                homeMenu();
                userCmd = promptInput(scnr);
                continue;
            case LIST_STATES_COMMAND:
                printHeader("Displaying All States");
                for (CityInterface city : backend.getAllCities())
                    println(city.state() + " - " + city.name());
                homeMenu();
                userCmd = promptInput(scnr);
                continue;
            case QUIT_COMMAND:
                break;
            default:
                println("That was not a valid option. Returning to Home Menu...");
            case HOME_MENU_COMMAND:
                homeMenu();
                userCmd = promptInput(scnr);
                continue;
            }
        }

        // quitting
        scnr.close();
        printHeader("STATE CAPITAL ROUTE SYSTEM HAS QUIT");
    }

    /**
     * Displays the home screen to the user.
     */
    public static void homeMenu() {
        printHeader("Home Menu");
        println("Press \"" + PATH_MENU_COMMAND + "\" for the Path Menu, to find and configure the best path.");
        println("Press \"" + INFO_COMMAND + "\" to get more information on a specific capital.");
        println("Press \"" + LIST_STATES_COMMAND + "\" to list all available states and their capitals.");
        println("Press \"" + QUIT_COMMAND + "\" to quit the program.\n");
        println("Enter one of the above options then press <ENTER>");
    }

    /**
     * Displays the rank screen.
     */
    private static char pathMenu(Scanner scan, BackendInterface backend) {
        printHeader("Path Menu");
        println("Press \"" + SHORTEST_PATH_COMMAND + "\" to find the shortest path between two capitals.");
        println("Press \"" + REMOVE_CITY_COMMAND + "\" to remove a specific state capital from the path generation.");
        println("Press \"" + ADD_CITY_COMMAND + "\" to add back a specific state capital to the path generation.");
        println("Press \"" + HOME_MENU_COMMAND + "\" to return to the Home Menu");
        println("Press \"" + QUIT_COMMAND + "\" to quit the program.");
        println("Enter one of the above options then press <ENTER>");

        // gets input from user
        char localCmd = promptInput(scan);

        // command parsing
        switch (localCmd) {
        case SHORTEST_PATH_COMMAND:
            List<CityInterface> allCities = backend.getAllCities();
            CityInterface startCity, endCity;

            // ask for input & validate
            try {
                scan.nextLine();
                String start = promptLineInput("Enter the state initials of your starting capital: ", scan);
                startCity = allCities.stream().filter(x -> x.state().compareTo(start) == 0).findFirst().get();
                //scan.nextLine();
                String end = promptLineInput("Enter the state initials of your ending capital: ", scan);
                endCity = allCities.stream().filter(x -> x.state().compareTo(end) == 0).findFirst().get();
            } catch (Exception e) {
                println("City not found. Try again with different input.");
                return pathMenu(scan, backend);
            }

            // gets cost and path list
            int cost;
            List<CityInterface> path;
            try {
                cost = backend.getPathCost(startCity, endCity);
                path = backend.shortestPath(startCity, endCity);
            } catch (Exception e) {
                println("No path available. Try a different start or end.");
                return pathMenu(scan, backend);
            }

            // print out path
            println(startCity.name() + ", " + startCity.state() + " to " + endCity.name() + ", " + endCity.state()
                    + " shortest length: " + cost);
            for(int i = 0; i < path.size(); i++) {
                if(startCity == path.get(i)) print("    ");
                println(path.get(i).name() + ", " + path.get(i).state());
                if(endCity != path.get(i)) print(" -> ");
            }
            println(""); // skip a line

            return pathMenu(scan, backend);
        case REMOVE_CITY_COMMAND:
            scan.nextLine();
            printHeader("Capital Removal");

            // ask for input
            String removeInitials = promptLineInput("Enter the state initials of the capital that you wish to remove: ",
                    scan);

            // successful or not successful
            boolean removeSuccessful = backend.removeCity(removeInitials);
            if (removeSuccessful)
                println("Removal successful.");
            else
                println("Removal unsuccessful. Try again with different input.");
            return pathMenu(scan, backend);
        case ADD_CITY_COMMAND:
            scan.nextLine();
            String[] removed = backend.removedStateAbbreviations();

            // display
            printHeader("Add Back Capitals");
            println("Here is a list of each removed state whose capital is removed:");
            for (String str : removed)
                print(str + " ");
            println("");

            // ask for input
            String addInitials = promptLineInput("Enter the state initials of the capital that you wish to add back: ",
                    scan);

            // successful or not successful
            boolean addSuccessful = backend.addBackCity(addInitials);
            if (addSuccessful)
                println("Addition successful.");
            else
                println("Addition unsuccessful. Try again with different input.");
            return pathMenu(scan, backend);
        default:
            return localCmd;
        }
    }

    // #region Text Output

    static final int MAX_MENU_LENGTH = 100;

    /**
     * A wrapper for System.out.println.
     * 
     * @param str what should be displayed
     */
    public static void println(String str) {
        System.out.println(str);
    }

    /**
     * A wrapper for System.out.print.
     * 
     * @param str what should be displayed
     */
    public static void print(String str) {
        System.out.print(str);
    }

    /**
     * Prints a pretty header into the console.
     */
    public static void printHeader(String str) {
        str = " " + str + " ";
        int textLength = str.length();
        if (textLength > MAX_MENU_LENGTH)
            println(str);
        else {
            int remainingLength = (MAX_MENU_LENGTH - textLength);
            int halfLength = remainingLength / 2;
            boolean odd = remainingLength % 2 == 1;
            for (int i = 0; i < halfLength; i++)
                print("-");
            print(str);
            for (int i = 0; i < halfLength; i++)
                print("-");
            if (odd)
                print("-");
            println("");
        }
    }

    /**
     * Prompts input from the user.
     * 
     * @param scnr the scanner to retrieve input from
     * @return the input from the user.
     */
    public static char promptInput(Scanner scnr) {
        print("Enter option: ");
        return scnr.next().charAt(0);
    }

    /**
     * Prompts multi-character input form the user.
     * 
     * @param prompt the prompt to show the user
     * @param scnr   the scanner to retrieve input from
     * @return the input from the user
     */
    public static String promptLineInput(String prompt, Scanner scnr) {
        print(prompt);
        return scnr.nextLine();
    }

    // #endregion
}