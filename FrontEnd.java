import java.util.Scanner;
import java.util.zip.DataFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;


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
        userCmd = scnr.next().charAt(0);

        // User functions behind Menu ("q" to quit menu)
        while (userCmd != QUIT_COMMAND) {

            switch (userCmd) {
            case PATH_MENU_COMMAND:
                printHeader("Path Menu");
                userCmd = pathMenu(scnr, backend);
                continue;
            case INFO_COMMAND:
                printHeader("WIP");
                userCmd = promptInput(scnr);
                continue;
            case LIST_STATES_COMMAND:
                printHeader("WIP");
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
        println("Press \"" + REMOVE_CITY_COMMAND + "\" to remove a specific state capital from the path generation.");
        println("Press \"" + ADD_CITY_COMMAND + "\" to add back a specific state capital to the path generation.");
        println("Press \"" + HOME_MENU_COMMAND + "\" to return to the Home Menu");
        println("Press \"" + QUIT_COMMAND + "\" to quit the program.");
        println("Enter one of the above options then press <ENTER>");

        // gets input from user
        char localCmd = promptInput(scan);

        // command parsing
        switch (localCmd) {
        case REMOVE_CITY_COMMAND:
            printHeader("WIP");
            return pathMenu(scan, backend);
        case ADD_CITY_COMMAND:
            printHeader("WIP");
            // printRanking.forEach(
            // a -> System.out.println("Rank: " + a.getRank() + " -- restaurant Name: " +
            // a.getRestaurantName()));
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
     * @param scnr the scanner to retrieve input from
     * @return the input from the user.
     */
    public static char promptInput(Scanner scnr){
        print("Enter option: ");
        return scnr.next().charAt(0);
    }

    // #endregion
}