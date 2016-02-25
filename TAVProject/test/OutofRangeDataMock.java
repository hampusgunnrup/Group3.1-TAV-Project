package test;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import main.Arduino;
import main.Display;
import main.USB;
import main.ReceiveSpeedAngle;
import main.SendSensorData;
import main.UpdateDisplay;

public class OutofRangeDataMock {
	USB usb = Mockito.mock(USB.class);
	Arduino arduino = new Arduino(usb);
	Display display = new Display();
	UpdateDisplay gui = new UpdateDisplay(display);
	ReceiveSpeedAngle speedangle = new ReceiveSpeedAngle (arduino, gui);
	SendSensorData sensordata = new SendSensorData (arduino, gui);
	

	@Test
	public void testRunOne() {
		display.frmGraphicalInterface.setVisible(true);
		try {
			Thread.sleep(2000); // Make sure that GUI is up
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		(new Thread(gui)).start();
		(new Thread(speedangle)).start();
		(new Thread(sensordata)).start();
		
		display.setIR("-10");
		display.setTorque("-10");
		display.setUltra("-10");
		
		
		Mockito.when(usb.processSensorData(arduino)).thenReturn("");
		
		
		try {
			Thread.sleep(2000); // Make sure that SendSensorData has finished
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		String bitstream = usb.processSensorData(arduino);
		arduino.writeToInputBuffer(21, bitstream);
		
		try {
			Thread.sleep(2000); // Make sure that readSpeadAngle has finished
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		gui.stop();
		speedangle.stop();
		sensordata.stop();
		
		String expectedSpeed = "";
		String expectedAngle = "";
		String expectedBitstream = "";
		
		String actualSpeed = display.getSpeed();
		String actualAngle = display.getAngle();
		String actualBitstream = display.getReceiveBinary();
		
		Assert.assertEquals(expectedBitstream, actualBitstream);
		Assert.assertEquals(expectedSpeed, actualSpeed);
		Assert.assertEquals(expectedAngle, actualAngle);
	}
	
	@Test
	public void testRuntwo() {
		display.frmGraphicalInterface.setVisible(true);
		try {
			Thread.sleep(2000); // Make sure that GUI is up
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		(new Thread(gui)).start();
		(new Thread(speedangle)).start();
		(new Thread(sensordata)).start();
		
		display.setIR("100");
		display.setTorque("100");
		display.setUltra("100");
		
		Mockito.when(usb.processSensorData(arduino)).thenReturn("");
		
		
		try {
			Thread.sleep(2000); // Make sure that SendSensorData has finished
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		String bitstream = usb.processSensorData(arduino);
		arduino.writeToInputBuffer(21, bitstream);
		
		try {
			Thread.sleep(2000); // Make sure that readSpeadAngle has finished
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		gui.stop();
		speedangle.stop();
		sensordata.stop();
		
		String expectedSpeed = "";
		String expectedAngle = "";
		String expectedBitstream = "";
		
		String actualSpeed = display.getSpeed();
		String actualAngle = display.getAngle();
		String actualBitstream = display.getReceiveBinary();
		
		Assert.assertEquals(expectedBitstream, actualBitstream);
		Assert.assertEquals(expectedSpeed, actualSpeed);
		Assert.assertEquals(expectedAngle, actualAngle);
	}
	
}
