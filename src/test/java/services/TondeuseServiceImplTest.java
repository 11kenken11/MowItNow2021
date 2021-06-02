package services;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.TondeuseException;

import static org.junit.Assert.*;

public class TondeuseServiceImplTest {
	
	private TondeuseService tondeuseService;
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@Before
	public void setup() {
		tondeuseService = new TondeuseServiceImpl();
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@Test
	public void startProgramWithProperInput() throws Exception {
		tondeuseService.startProgram("testProperInput");
		assertEquals("1 3 N\r\n5 1 E", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void startProgramWithLowerCaseProperInput() throws Exception {
		tondeuseService.startProgram("testLowerCaseProperInput");
		assertEquals("1 3 N\r\n5 1 E", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void startProgramWithMowerOutOfGrassInstructionsInput() throws Exception {
		tondeuseService.startProgram("testMowerOutOfGrassInstructionsInput");
		assertEquals("5 5 N\r\n5 1 E", outputStreamCaptor.toString().trim());
	}
	
	@Test(expected = NullPointerException.class)
	public void startProgramWithNullFile() throws Exception {
		tondeuseService.startProgram(null);
	}

	@Test(expected = TondeuseException.class)
	public void startProgramWithEmptyFile() throws Exception {
		tondeuseService.startProgram("empty");
	}
	
	@Test(expected = TondeuseException.class)
	public void startProgramWithEvenLinesNumberFile() throws Exception {
		tondeuseService.startProgram("testEven");
	}
	
	@Test(expected = TondeuseException.class)
	public void startProgramWithInvalidGrassCoordFile() throws Exception {
		tondeuseService.startProgram("testInvalidGrassCoords");
	}

	@Test(expected = TondeuseException.class)
	public void startProgramWithInvalidMowerInput() throws Exception {
		tondeuseService.startProgram("testInvalidMowerInput");
	}
	
	@Test(expected = TondeuseException.class)
	public void startProgramWithInvalidMowerOrientationInput() throws Exception {
		tondeuseService.startProgram("testInvalidMowerOrientationInput");
	}
	
	@Test(expected = TondeuseException.class)
	public void startProgramWithInvalidInstrutctionsInput() throws Exception {
		tondeuseService.startProgram("testInvalidInstructionsInput");
	}
	
	@After
	public void tearDown() {
		System.setOut(standardOut);
	}
	
}
