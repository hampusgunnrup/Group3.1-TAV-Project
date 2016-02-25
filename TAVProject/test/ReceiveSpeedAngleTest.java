package test;

import org.junit.Assert;

import org.junit.Test;

import main.Arduino;
import main.Display;
import main.ReceiveSpeedAngle;
import main.USB;
import main.UpdateDisplay;

public class ReceiveSpeedAngleTest {

	@Test
	public void testRunOneIteration() {
		USB usb = new USB();
		Arduino arduino = new Arduino(usb);
		Display display = new Display();
		UpdateDisplay gui = new UpdateDisplay(display);
		
		String bitstream = "/10001111,00001111,1*";
		arduino.writeToInputBuffer(21, bitstream);
		
		ReceiveSpeedAngle receiveSpeedAngle = new ReceiveSpeedAngle(arduino, gui);
		Thread receiveSpeedAngleThread = new Thread(receiveSpeedAngle);
		receiveSpeedAngleThread.start();
		
		try {
			Thread.sleep(1000); // Make sure that receiveSpeedAngle has finished
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		receiveSpeedAngle.stop();
		
		String expectedSpeed = "-15.0";
		String expectedAngle = "15.0";
		
		String actualSpeed = display.getSpeed();
		String actualAngle = display.getAngle();
		String actualBitstream = display.getReceiveBinary();
		
		Assert.assertEquals(bitstream, actualBitstream);
		Assert.assertEquals(expectedSpeed, actualSpeed);
		Assert.assertEquals(expectedAngle, actualAngle);
		
	}
	
	@Test
	public void testRunSeveralIterations() {
		USB usb = new USB();
		Arduino arduino = new Arduino(usb);
		Display display = new Display();
		UpdateDisplay gui = new UpdateDisplay(display);
		
		ReceiveSpeedAngle receiveSpeedAngle = new ReceiveSpeedAngle(arduino, gui);
		Thread receiveSpeedAngleThread = new Thread(receiveSpeedAngle);
		receiveSpeedAngleThread.start();
		
		String[] bitstream = {"/10001111,00001111,1*", "/00000010,00000101,1*"};
		String[] expectedSpeed = {"-15.0", "2.0"};
		String[] expectedAngle = {"15.0", "5.0"};
		
		for(int i = 0; i < 2; i++) {
			
			arduino.writeToInputBuffer(21, bitstream[i]);
			
			try {
				Thread.sleep(2000); // Make sure that receiveSpeedAngle has finished
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			String actualSpeed = display.getSpeed();
			String actualAngle = display.getAngle();
			String actualBitstream = display.getReceiveBinary();
			
			Assert.assertEquals(bitstream[i], actualBitstream);
			Assert.assertEquals(expectedSpeed[i], actualSpeed);
			Assert.assertEquals(expectedAngle[i], actualAngle);
		
		}
		
		receiveSpeedAngle.stop();
		
	}

}
