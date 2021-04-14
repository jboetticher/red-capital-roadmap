//--== CS400 File Header Information ==--
//Name: Jeremy Boetticher
//Email: boetticher@wisc.edu
//Team: Red
//Group: KF
//TA: Keren Chen
//Lecturer: Gary Dahl
//Notes to Grader: Assumes that the front end is using the mock backend.

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

public class FrontEndTests {

	@AfterEach
	public void EndFrontEnd() {
		InputStream simulatedInput = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream("q\r".getBytes());
		System.setIn(in);

		// reset
		System.setIn(simulatedInput);
	}

	@Test
	public void AssertGetCityInfo() {
		// starts the front end
		FrontEnd.run();

		// simulated input
		InputStream simulatedInput = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream("i\r".getBytes());
		System.setIn(in);

		// stream output reader
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String appOutput = outputStream.toString();

		// second step input
		ByteArrayInputStream inAfter = new ByteArrayInputStream("NC\r".getBytes());
		System.setIn(inAfter);

		// check for test city output
		assertTrue(appOutput.compareTo("TEST CITY INFO") == 0);

		// reset
		System.setIn(simulatedInput);
	}

	@Test
	public void AssertQuit() {
		// starts the front end
		FrontEnd.run();

		// simulated input
		InputStream simulatedInput = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream("q\r".getBytes());

		// stream output reader
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String appOutput = outputStream.toString();

		// second step input
		System.setIn(in);

		// check for quit output
		assertTrue(appOutput.contains("STATE CAPITAL ROUTE SYSTEM HAS QUIT"));

		// reset
		System.setIn(simulatedInput);
	}

	@Test
	public void AssertWrongOutput() {
		// starts the front end
		FrontEnd.run();

		// simulated input
		InputStream simulatedInput = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream("dkiwef\r".getBytes());

		// stream output reader
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String appOutput = outputStream.toString();

		// second step input
		System.setIn(in);

		// check for incorrect output string
		assertTrue(appOutput.startsWith("That was not a valid option. Returning to Home Menu..."));

		// reset
		System.setIn(simulatedInput);
	}

}
