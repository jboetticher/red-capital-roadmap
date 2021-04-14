//--== CS400 File Header Information ==--
//Name: Rudy Banerjee
//Email: ajbanerjee@wisc.edu
//Team: Red
//Group: KF
//TA: Keren Chen
//Lecturer: Florian Heimerl
//Notes to Grader: Assumes that the front end is using the mock backend.

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

public class DataReaderTest {
  
  /**
   * This is just testing that the setup code works.
   */
    @Test
    public static void TestSetup() {
        
        try{
          DataReader reader = new DataReader();
          List<CityInterface> cityList = reader.readDataSet(new FileReader("state_capitals_xy.csv"));
          assertTrue();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method will test that the method returns a list of the correct size
     */
    @Test
    public static void TestListSize() {
      try{
        DataReader reader = new DataReader();
        List<CityInterface> cityList = reader.readDataSet(new FileReader("state_capitals_xy.csv"));
        assertEquals(cityList.size(), 50);
      } catch(Exception e) {
          e.printStackTrace();
      }
    }
    
    /**
     * This test method shows that the readDataSet method does not return a list of the wrong size
     */
    @Test
    public static void TestIncorrectPath() {
      try{
        DataReader reader = new DataReader();
        List<CityInterface> cityList = reader.readDataSet(new FileReader("state_capitals_xy.csv"));
        assertNotEquals(cityList.size(), 51);
      } catch(Exception e) {
          e.printStackTrace();
      }
    }

}
