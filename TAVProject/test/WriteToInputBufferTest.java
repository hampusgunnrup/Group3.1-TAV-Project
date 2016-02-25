package test;
 
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import main.Arduino;
import main.USB;
 
public class WriteToInputBufferTest {

	Arduino arduino;
	
	@Before
    public void setUp() {
		USB usb = new USB();
		arduino = new Arduino(usb);
    }
	
    @Test
    public void tc1() {
    	//N > 0
    	//S >= N
    	String s = "11110000";
        int n = 8;
        int expected = 0; // expected error code 0 for successful
        int actual = arduino.writeToInputBuffer(n, s);
        String expectedBuffer = "11110000";
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedBuffer,arduino.inputBuffer);
    }
     
    @Test
    public void tc2() {
    	//N > 0
    	//S < N
    	String s = "001100";
        int n = 8;
        int expected = 1;
        int actual = arduino.writeToInputBuffer(n, s);
        String expectedBuffer = "001100";
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedBuffer, arduino.inputBuffer);
    }
     
    @Test
    public void tc3() {
    	//N < 0
    	String s = "11110000";
        int n = -1;
        int expected = 2;
        int actual = arduino.writeToInputBuffer(n, s);
        Assert.assertEquals(expected, actual);
    }
}