package test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import main.Arduino;
import main.SpeedTorque;

public class ReadSpeedTorqueTest {
	Arduino arduino;

	@Before
    public void setUp() {
		arduino = new Arduino();
    }
	
	@Test
	public void testCase1() {
	//Amount of bits ok
	//Start found
	//End found
	//First comma found
	//Second comma found
	//parity check ok
	//NO BITS IN FRONT
		arduino.bitstream = "/10001111,00001111,1*";
		SpeedTorque expected = new SpeedTorque(-15,15);
		SpeedTorque received = arduino.ReadSpeedTorque();
		Assert.assertEquals(expected, received);
		String expectedBitstream = "";
		Assert.assertEquals(expectedBitstream, arduino.bitstream);
	}
	
	@Test
	public void testCase2() {
	//Amount of bits ok
	//Start found
	//End found
	//First comma found
	//Second comma found
	//parity check ok
	//BITS IN FRONT
		arduino.bitstream = "1101/10001111,00001111,1*";
		SpeedTorque expected = new SpeedTorque(-15,15);
		SpeedTorque received = arduino.ReadSpeedTorque();
		Assert.assertEquals(expected, received);
		String expectedBitstream = "";
		Assert.assertEquals(expectedBitstream, arduino.bitstream);
	}
	
	@Test
	public void testCase3() {
	//Amount of bits ok
	//Start found
	//End found
	//First comma found
	//Second comma found
	//Failed parity check
		arduino.bitstream = "/11110000,11110000,1*";
		SpeedTorque expected = new SpeedTorque();
		SpeedTorque received = arduino.ReadSpeedTorque();
		Assert.assertEquals(expected, received);
		String expectedBitstream = "11110000,11110000,1*";
		Assert.assertEquals(expectedBitstream, arduino.bitstream);
	}
	
	@Test
	public void testCase4() {
	//Amount of bits ok
	//Start found
	//End found
	//First comma found
	//Second comma not found
		arduino.bitstream = "/11110000,1111000010*";
		SpeedTorque expected = new SpeedTorque();
		SpeedTorque received = arduino.ReadSpeedTorque();
		Assert.assertEquals(expected, received);
		String expectedBitstream = "11110000,1111000010*";
		Assert.assertEquals(expectedBitstream, arduino.bitstream);
	}
	
	@Test
	public void testCase5() {
	//Amount of bits ok
	//Start found
	//End found
	//First comma not found
		arduino.bitstream = "/11110000111110000,0*";
		SpeedTorque expected = new SpeedTorque();
		SpeedTorque received = arduino.ReadSpeedTorque();
		Assert.assertEquals(expected, received);
		String expectedBitstream = "11110000111110000,0*";
		Assert.assertEquals(expectedBitstream, arduino.bitstream);
	}
	
	@Test
	public void testCase6() {
	//Amount of bits ok
	//Start found
	//End not found
		arduino.bitstream = "/11110000,11110000,01";
		SpeedTorque expected = new SpeedTorque();
		SpeedTorque received = arduino.ReadSpeedTorque();
		Assert.assertEquals(expected, received);
		String expectedBitstream = "11110000,11110000,01";
		Assert.assertEquals(expectedBitstream, arduino.bitstream);
	}
	
	@Test
	public void testCase7() {
	//Amount of bits ok
	//Start not found
		arduino.bitstream = "111110000,11110000,0*";
		SpeedTorque expected = new SpeedTorque();
		SpeedTorque received = arduino.ReadSpeedTorque();
		Assert.assertEquals(expected, received);
		String expectedBitstream = "111110000,11110000,0*";
		Assert.assertEquals(expectedBitstream, arduino.bitstream);
	}
	
	@Test
	public void testCase8() {
	//      012345678901234567890	
	//ok# = /11110000,11110000,0*
	//Amount of bits not ok
		arduino.bitstream = "";
		SpeedTorque expected = new SpeedTorque();
		SpeedTorque received = arduino.ReadSpeedTorque();
		Assert.assertEquals(expected, received);
		String expectedBitstream = "";
		Assert.assertEquals(expectedBitstream, arduino.bitstream);
	}
}