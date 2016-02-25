package test;

import org.junit.Assert;
import org.junit.Test;
import main.Arduino;
import main.USB;

import org.junit.Before;

public class TestSendSensoryData {
	Arduino arduino;

	@Before
    public void setUp() {
		USB usb = new USB();
		arduino = new Arduino(usb);
    }
	
	@Test
	public void testCase1() {
		//Torque is between -30...30, IR_Dist(-1...40), ULTRA_Dist (-1...50)
		//Try with (10,10,10)
		String expected_string = "/10001010,00001010,00001010,1*";
		String received_string = arduino.SendSensoryData(-10, 10, 10);
		//System.out.println(received_string);
		Assert.assertEquals(expected_string, received_string);
	}
	
	@Test
	public void testCase2() {
		//Torque < -30
		//Try with torque -35
		String expected_string = "";
		String received_string = arduino.SendSensoryData(-35, 0, 0);
		Assert.assertEquals(expected_string, received_string);
	}
	
	@Test
	public void testCase3() {
		//Torque > 30
		//Try with torque 35
		String expected_string = "";
		String received_string = arduino.SendSensoryData(35, 0, 0);
		Assert.assertEquals(expected_string, received_string);
	}
	
	@Test
	public void testCase4() {
		//IR_Dist(<-1)
		//Try with IR_Dist=-5
		String expected_string = "";
		String received_string = arduino.SendSensoryData(0, -5, 0);
		Assert.assertEquals(expected_string, received_string);
	}
	
	@Test
	public void testCase5() {
		//IR_Dist(>40)
		//Try with IR_Dist=45
		String expected_string = "";
		String received_string = arduino.SendSensoryData(0, 45, 0);
		Assert.assertEquals(expected_string, received_string);		
	}
	
	@Test
	public void testCase6() {
		//ULTRA_Dist (<-1)
		//Try with ULTRA_Dist=-5
		String expected_string = "";
		String received_string = arduino.SendSensoryData(0, 0, -5);
		Assert.assertEquals(expected_string, received_string);
	}
	
	@Test
	public void testCase7() {
		//ULTRA_Dist (>50)
		//Try with ULTRA_Dist=55
		String expected_string = "";
		String received_string = arduino.SendSensoryData(0, 0, 55);
		Assert.assertEquals(expected_string, received_string);		
	}

}
