package test;

import org.junit.Assert;

import org.junit.Test;

import main.Arduino;
import main.Display;
import main.SendSensorData;
import main.UpdateDisplay;

public class SendSensorDataTest {

	@Test
	public void testRunOneIteration() {
		
		Arduino arduino = new Arduino();
		Display display = new Display();
		UpdateDisplay gui = new UpdateDisplay(display);
		
		display.setTorque("-10");
		display.setIR("10.0");
		display.setUltra("10.0");
		
		SendSensorData sendSensorData = new SendSensorData(arduino, gui);
		Thread sendSensorDataThread = new Thread(sendSensorData);
		sendSensorDataThread.start();
		
		try {
			Thread.sleep(2000); // Make sure that sendSensorData has finished
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		sendSensorData.stop();
		
		String expectedOutputBuffer = "/10001010,00001010,00001010,1*";
		String actualOutputBuffer = arduino.outputBuffer;
		
		Assert.assertEquals(expectedOutputBuffer, actualOutputBuffer);
		
	}

}
