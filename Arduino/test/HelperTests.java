package test;

import org.junit.Assert;
import org.junit.Test;
import main.Arduino;
import org.junit.Before;


public class HelperTests {
	Arduino arduino;
	
	@Before
	public void setUp() {
		arduino = new Arduino();
	}

	//send too big number
	@Test
	public void decTo8BitTest() {
		//send 67
		String actual = arduino.dec_to_8bbin(67);
		String expected = "11111111";
		Assert.assertEquals(actual, expected);
	}
	
	//send value in correct range (-63...63) first bit is sign bit and rest are value
	@Test
	public void decTo8BitTest2() {
		//send 30
		String actual = arduino.dec_to_8bbin(30);
		String expected = "00011110";
		Assert.assertEquals(actual, expected);
	}
	
	//Parity check test
	@Test
	public void parityCheckTest() {
		String actual = arduino.ParityCheck("101");
		String expected = "0";
		Assert.assertEquals(actual, expected);
	}
	
	//test with less than 8 bits
	@Test
	public void bin8ToDecTest() {
		double actual = arduino.bin8_to_dec("1001");
		double expected = 99; // error code
		Assert.assertEquals(actual, expected, 0.001);
		
	}
	
	//test with more than 8 bits
	@Test
	public void bin8ToDecTest2() {
		double actual = arduino.bin8_to_dec("1001001001");
		double expected = 99;//error code
		Assert.assertEquals(actual, expected, 0.001);
		
	}
	
	//Test with 8 bits should work
	@Test
	public void bin8ToDecTest3() {
		double actual = arduino.bin8_to_dec("00010010");
		double expected = 18; 
		Assert.assertEquals(actual, expected, 0.001);
		
	}

}
