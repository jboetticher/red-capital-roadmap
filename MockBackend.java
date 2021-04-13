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
        return null;
    }

    @Override
    public int getPathCost(CityInterface start, CityInterface end) {
        if(start.name().compareTo("California") == 0) {
            return 100;
        } else if(start.name().compareTo("Louisiana") == 0) {
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
        interfaces.add(new City("California", "CA", -12, 2, Arrays.asList(new String[] {"Wisconsin"})));
        interfaces.add(new City("Wisconsin", "WI", 6, 8, Arrays.asList(new String[] {"California", "Illinois"})));
        interfaces.add(new City("Illinois", "IL", 17, 0, Arrays.asList(new String[] {"Wisconsin"})));
        interfaces.add(new City("Hawaii", "HI", -20, -20, new LinkedList<String>()));
        return interfaces;       
    }

    @Override
    public boolean containsEdge(CityInterface source, CityInterface target) {
        // TODO Auto-generated method stub
        return false;
    }
    
}