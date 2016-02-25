package test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import main.Arduino;
import main.ReadAnswer;
import main.USB;

public class ReadFromBufferTest {
	Arduino arduino;

	@Before
    public void setUp() {
		USB usb = new USB();
		arduino = new Arduino(usb);
    }
	
	@Test
	public void testCase1() {
		//n>0
		//s>=n
		arduino.outputBuffer = "1000111111";
		ReadAnswer expected = new ReadAnswer(0,"10001111"); // expected errorcode 0 for successful and bitstream
		ReadAnswer received = arduino.ReadFromBuffer(8);
		Assert.assertEquals(expected, received);
		String expectedBuffer = "11";
		Assert.assertEquals(expectedBuffer,arduino.outputBuffer);
	}
	
	@Test
	public void testCase2() {
		//n>0
		//s<n
		arduino.outputBuffer = "100011";
		ReadAnswer expected = new ReadAnswer(1,"100011");
		ReadAnswer received = arduino.ReadFromBuffer(8);
		Assert.assertEquals(expected, received);
		String expectedBuffer = "";
		Assert.assertEquals(expectedBuffer,arduino.outputBuffer);
	}
	
	@Test
	public void testCase3() {
		//n<0
		//s>n
		arduino.outputBuffer = "";
		ReadAnswer expected = new ReadAnswer(2,"");
		ReadAnswer received = arduino.ReadFromBuffer(-1);
		Assert.assertEquals(expected, received);
	}
}