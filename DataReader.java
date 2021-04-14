import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

//--== CS400 File Header Information ==--
//Name: Rudy Banerjee
//Email: ajbanerjee@wisc.edu
//Team: Red
//Group: KF
//TA: Keren Chen
//Lecturer: Florian Heimerl
//Notes to Grader: none

public class DataReader implements DataReaderInterface {
  
  /*
    public static void main(String[] args) throws FileNotFoundException, IOException {
      
      
      DataReader reader = new DataReader();
      try {
        
        File f = new File("C:\\Users\\rudyb\\eclipse-workspace\\CS252\\src\\state_capitals_xy.csv");
        File f1 = new File("state_capitals_xy.csv");
        
        // Check if the specified file
        // Exists or not
        if (f.exists())
            System.out.println("Exists");
        else
            System.out.println("Does not Exists");
        
        List<CityInterface> values = reader.readData(new FileReader("state_capitals_xy.csv"));
        System.out.println(values.get(0).state());
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      
    }
    */
  
    // this is the header of the csv file, and represent the fields that will go into each Restaurant object
    private String header[] = new String[] {"State", "Capital", "X", "Y", "Highways"};
    
    /**
     * This method reads the data from the csv file into strings and organizes them in such a way that they can be used to create RestaurantInterface objects
     * @param inputFileReader is a Reader object that reads from the csv
     * @return a list of CityInterface objects based on the csv file inputted
     * @throws IOException if there is a problem reading from the file or to a string
     * @throws DataFormatException if data is not formatted properly (i.e. in this case usually if the data being read in don't match properly with the fields specified in header)
     */
    
    
    @Override
    public List<CityInterface> readDataSet(Reader inputFileReader) throws IOException, DataFormatException {
      LinkedList<CityInterface> cityList = new LinkedList<CityInterface>();
      //LinkedList<String> rawData = new LinkedList<String>();
      char nextChar;
      // test the reader
      try {
          nextChar = (char)inputFileReader.read();
      } catch (Exception e) {
          throw new IOException("Invalid input: " + e.getMessage());
      }
      
      int count = 0; int count2 = 0;
      
      String nextStr = "";
      boolean quotationLock = false;
      // skip past the header
      while(!isEndCharacter(nextChar)) {
          nextChar = (char) inputFileReader.read();
      }
      
      
      nextChar = (char) NextParsableCharacter(inputFileReader);
      while((int)nextChar != 65535 && (int)(nextChar) != -1) { // while nextChar is not null
        
        /*if (count == 48)
        { System.out.println("Time2Start!"); }
        if (count == 49)
        { System.out.println("My man"); count2 = 0; }*/
        
        //LinkedList<String> rawData = new LinkedList<>();
        String[] rawData = new String[5];
        int index = 0;
        
        while(!isEndCharacter(nextChar)) {
              
              //if (rawData.size() == 4)
                //System.out.println("It is time");
              /*if (count == 48) { System.out.println(count2); count2++; }
              if (count == 49) { System.out.println(nextStr); count2++; }*/
              
              if (nextChar == '"') { // make sure commas within quotations aren't noted as delimiters
                  quotationLock = !quotationLock;
                  if (rawData[4] == null && rawData[3] != null && !quotationLock) 
                  { rawData[index] = nextStr; index++; nextStr = ""; }
              }
              else if(nextChar == ',' && !quotationLock) { // delimit by strings
                  rawData[index] = nextStr; index++;
                  nextStr = "";
              } else { // otherwise, continue building the string
                  nextStr = nextStr + nextChar;
              }
              // read the next character
              nextChar = (char)inputFileReader.read();
          }
          // add the string, clear nextStr, and move past the end character (to the next line)
          
          if (count == 48)
          { System.out.println(nextStr); }
          
          //rawData.add(nextStr);
          nextStr = "";
          
          LinkedList<String> neighbors = new LinkedList<String>();
          
          if (!rawData[4].equals("")) {
            String[] n = rawData[4].split(",");
            for (String neighbor : n) { neighbors.add(neighbor); }
          }
          
          City city = new City(
              rawData[0], 
              rawData[1], 
              Double.parseDouble(rawData[2]), 
              Double.parseDouble(rawData[3]), 
              neighbors);
          
          cityList.add(city);
          
          nextChar = (char)NextParsableCharacter(inputFileReader);
          //System.out.println(count); count++;
          /*if ((int) (nextChar) == -1)
            System.out.println("We did it boys");*/
      }
      
      //System.out.println("DoneReading");
      
      // save resources once inputFileReader isn't needed
      inputFileReader.close();
      return cityList;
    }

    
    /**
     * helper method to determine whether a character is an end character
     * @param character is the current character
     * @return true if character is an end character and false otherwise
     * @throws IOException if there is a problem reading from the file
     */
    private boolean isEndCharacter(char character) throws IOException {
        if(
            character == '\n' || 
            character == '\r' || 
            (int) (character) == -1 || 
            (int) (character) == 65535) { // if it's a newline or carriage return character
            return true;
        }
        return false;
    }
    
    /**
     * helper method gets the next character from the reader that is not an end character
     * @param reader is the same as readDataSet's reader
     * @return the next non-end character in int format
     * @throws IOException if there is a problem reading from the file
     */
    private int NextParsableCharacter(Reader reader) throws IOException {
        int next = reader.read();
        
        // while it's not BS, NL, CR
        while(next == (int)'\r' || next == (int)'\n' || next == 10) {
            next = reader.read();
        }
        
        return next;
    }

}
