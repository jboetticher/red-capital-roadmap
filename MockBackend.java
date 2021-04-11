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
    public String[] removedCities() {
        return new String[] { "Sacramento", "Madison", "Springfield" };
    }

    @Override
    public String getCityInfo(String abbr) {
        return "TEST CITY INFO";
    }

    @Override
    public List<CityInterface> getAllCities() {
        return null;
    }

    @Override
    public boolean containsEdge(CityInterface source, CityInterface target) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
