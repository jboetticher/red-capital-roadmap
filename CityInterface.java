import java.util.List;

public interface CityInterface {
    // return Capital name
    public String name();

    // return State name
    public String state();

    // HEY WHOEVER IS DOING THIS, YOU GOTTA MAKE THE HASHCODE BASED OFF OF THE NAME
    // SO THAT IT WORKS IN THE HASHTABLE INTERNALLY
    // HASH CODES DEPEND ON THE OBJECT BEING THE SAME, BUT IF THEY'RE BASED OFF OF A
    // STRING, THEN THE CS400Graph's HASHTABLE WILL RETURN THE CORRECT VALUES
    @Override
    public int hashCode();
    
    @Override
    public String toString();
}
