import java.util.List;

public class City implements CityInterface {
    public String stateName;
	public String capitalName;
	public float[] coords;
	
	public City(String state, String capital, float xCoord, float yCoord) {
		capitalName = capital;
		stateName = state;
		coords = new float[2];
		coords[0] = xCoord;
		coords[1] = yCoord;
	}
	
	// return Capital name
    public String name() {
		return capitalName;
	}

    // return State name
    public String state() {
		retrun stateName;
	}
	
	public float[] coords() {
		return coords;
	}
	
    @Override
    public int hashCode() {
		return capitalName.hashCode();
	}
}
