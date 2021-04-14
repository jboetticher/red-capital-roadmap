//--== CS400 File Header Information ==--
//Name: Jeremy Boetticher
//Email: boetticher@wisc.edu
//Team: Red
//Group: KF
//TA: Keren Chen
//Lecturer: Gary Dahl
//Notes to Grader: ---

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MockBackend implements BackendInterface {

    @Override
    public List<CityInterface> shortestPath(CityInterface start, CityInterface end) {
        List<CityInterface> interfaces = new LinkedList<CityInterface>();
        interfaces.add(start);
        interfaces.add(new City("IL", "Springfield", 17, 0, Arrays.asList(new String[] {"Wisconsin"})));
        interfaces.add(end);
        return interfaces;
    }

    @Override
    public int getPathCost(CityInterface start, CityInterface end) {
        if(start.state().compareTo("CA") == 0) {
            return 100;
        } else if(start.state().compareTo("WI") == 0) {
            return 500;
        } else return 0;
    }

    @Override
    public boolean removeCity(String abbr) {
        return true;
    }

    @Override
    public boolean addBackCity(String abbr) {
        return true;
    }

    @Override
    public String[] removedStateAbbreviations() {
        return new String[] { "CA", "WI", "IL", "HI" };
    }

    @Override
    public String getCityInfo(String abbr) {
        return "TEST CITY INFO";
    }

    @Override
    public List<CityInterface> getAllCities() {
        List<CityInterface> interfaces = new LinkedList<CityInterface>();
        interfaces.add(new City("CA", "Sacramento", -12, 2, Arrays.asList(new String[] {"Wisconsin"})));
        interfaces.add(new City("WI", "Madison", 6, 8, Arrays.asList(new String[] {"California", "Illinois"})));
        interfaces.add(new City("IL", "Springfield", 17, 0, Arrays.asList(new String[] {"Wisconsin"})));
        interfaces.add(new City("HI", "Honololu", -20, -20, new LinkedList<String>()));
        return interfaces;       
    }

    @Override
    public boolean containsEdge(CityInterface source, CityInterface target) {
        return false;
    }
    
}