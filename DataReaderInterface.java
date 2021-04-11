import java.util.List;
import java.util.zip.DataFormatException;
import java.io.IOException;
import java.io.Reader;

public interface DataReaderInterface {
		
	public List<CityInterface> readDataSet(Reader inputFileReader) throws IOException, DataFormatException;

}
