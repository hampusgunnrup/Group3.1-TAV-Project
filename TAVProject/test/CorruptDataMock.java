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

public class CorruptDataMock {
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
		
		display.setIR("10");
		display.setTorque("10");
		display.setUltra("10");
		
		
		Mockito.when(usb.processSensorData(arduino)).thenReturn("/00001111,000011");
		
		
		
		try {
			Thread.sleep(2000); // Make sure that SendSensorData has finished
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		String bitstream = usb.processSensorData(arduino);
		arduino.writeToInputBuffer(21, bitstream);
		display.setReceivedBinary("/00001111,000011");
		
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
		String expectedBitstream = "/00001111,000011";
		
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
		
		display.setIR("14");
		display.setTorque("14");
		display.setUltra("14");
		

		Mockito.when(usb.processSensorData(arduino)).thenReturn("/00011111,00011111,0");
		
		
		
		try {
			Thread.sleep(2000); // Make sure that SendSensorData has finished
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		String bitstream = usb.processSensorData(arduino);
		arduino.writeToInputBuffer(21, bitstream);
		display.setReceivedBinary("/00011111,00011111,0");
		
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
		String expectedBitstream = "/00011111,00011111,0";
		
		String actualSpeed = display.getSpeed();
		String actualAngle = display.getAngle();
		String actualBitstream = display.getReceiveBinary();
		
		Assert.assertEquals(expectedBitstream, actualBitstream);
		Assert.assertEquals(expectedSpeed, actualSpeed);
		Assert.assertEquals(expectedAngle, actualAngle);
	}
	
}
