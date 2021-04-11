import java.util.List;

// NOTE: SHOULD USE CS400Graph<CityInterface> TO HELP MANAGE THE GRAPH

public interface BackendInterface {
    // Takes a start node and returns an in-order list of capitals (shortest path)
    public List<CityInterface> shortestPath(CityInterface start, CityInterface end);

    // Returns the total miles of the shortest path
    public int getPathCost(CityInterface start, CityInterface end);

    // Finds city from the graph and removes it, based on its abbreviation
    // returns true if it was successful, false if it wasn’t
    public boolean removeCity(String abbr);

    // Adds back cities you do want to visit
    // returns true if it was successful, false if it wasn’t
    public boolean addBackCity(String abbr);

    // Returns a list of the abbrevations of states of cities that have been removed
    public String[] removedStateAbbreviations();

    // --gets the information for a specific capital
    public String getCityInfo(String abbr);

    // --gets a list of all the capital objects
    public List<CityInterface> getAllCities();

    // checks whether there is an edge between two capitals
    public boolean containsEdge(CityInterface source, CityInterface target);
}