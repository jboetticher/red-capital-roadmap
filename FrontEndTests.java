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
		InputStream originalInput = System.in;

		// simulated input
		String simIn = "i" + System.lineSeparator() + "NC" + System.lineSeparator() + "q" + System.lineSeparator();
		ByteArrayInputStream in = new ByteArrayInputStream(simIn.getBytes());
		System.setIn(in);

		// stream output reader
		PrintStream originalOutput = System.out;
		ByteArrayOutputStream ops = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(ops);
		System.setOut(outputStream);

		FrontEnd.run();

		// end output
		System.setOut(originalOutput);
		System.out.println(ops);

		// check for quit output
		assertTrue(ops.toString().contains("TEST CITY INFO"));

		// reset
		System.setIn(originalInput);
	}

	@Test
	public void AssertQuit() {
		// starts the front end
		InputStream originalInput = System.in;

		// simulated input
		String simIn = "q" + System.lineSeparator();
		ByteArrayInputStream in = new ByteArrayInputStream(simIn.getBytes());
		System.setIn(in);

		// stream output reader
		PrintStream originalOutput = System.out;
		ByteArrayOutputStream ops = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(ops);
		System.setOut(outputStream);

		FrontEnd.run();

		// end output
		System.setOut(originalOutput);
		System.out.println(ops);

		// check for quit output
		assertTrue(ops.toString().contains("STATE CAPITAL ROUTE SYSTEM HAS QUIT"));

		// reset
		System.setIn(originalInput);
	}

	@Test
	public void AssertWrongOutput() {
		// starts the front end
		InputStream originalInput = System.in;

		// simulated input
		String simIn = "uuudfasfasdfa" + System.lineSeparator() + "q" + System.lineSeparator();
		ByteArrayInputStream in = new ByteArrayInputStream(simIn.getBytes());
		System.setIn(in);

		// stream output reader
		PrintStream originalOutput = System.out;
		ByteArrayOutputStream ops = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(ops);
		System.setOut(outputStream);

		FrontEnd.run();

		// end output
		System.setOut(originalOutput);
		System.out.println(ops);

		// check for quit output
		//assertTrue(ops.toString().contains("That was not a valid option. Returning to Home Menu..."));

		// reset
		System.setIn(originalInput);
	}

	public static void main(String[] args) {
		FrontEndTests test = new FrontEndTests();
		test.AssertWrongOutput();
	}
}
