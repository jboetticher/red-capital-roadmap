import java.util.List;
import java.util.LinkedList;

public class City implements CityInterface {
    public String stateName;
	public String capitalName;
	public float[] coords;
	public List<String> edges;
	
	public City(String state, String capital, float xCoord, float yCoord, List<String> e) {
		capitalName = capital;
		stateName = state;
		coords = new float[2];
		coords[0] = xCoord;
		coords[1] = yCoord;
		edges = e;
	}
	
	// return Capital name
    public String name() {
		return capitalName;
	}

    // return State name
    public String state() {
		return stateName;
	}
	
	public float[] coords() {
		return coords;
	}
	
	public List<String> edges() {
		return this.edges;
	}
	
    @Override
    public int hashCode() {
		return capitalName.hashCode();
	}
}
