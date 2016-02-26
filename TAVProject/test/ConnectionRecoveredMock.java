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

public class ConnectionRecoveredMock {
	USB usb = Mockito.mock(USB.class);
	Arduino arduino = new Arduino(usb);
	Display display = new Display();
	UpdateDisplay gui = new UpdateDisplay(display);
	ReceiveSpeedAngle speedangle = new ReceiveSpeedAngle (arduino, gui);
	SendSensorData sensordata = new SendSensorData (arduino, gui);
	
	/*
	 * Tests the full integration with the connection being lost and the recovered.
	 * The system should work as soon as the connection is recovered.
	 * The usb is mocked, with the connection first returning false, and then returning true.
	 * */
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
		
		
		Mockito.when(usb.processSensorData(arduino)).thenReturn("/00001111,00001111,0*");
		Mockito.when(usb.isconnected()).thenReturn(false);
		
		try {
			Thread.sleep(2000); // Make sure that SendSensorData has finished
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		String expectedSpeed = "";
		String expectedAngle = "";
		String expectedBitstream = "Connection Lost with USB";
		
		String actualSpeed = display.getSpeed();
		String actualAngle = display.getAngle();
		String actualBitstream = display.getReceiveBinary();
		
		Assert.assertEquals(expectedBitstream, actualBitstream);
		Assert.assertEquals(expectedSpeed, actualSpeed);
		Assert.assertEquals(expectedAngle, actualAngle);
		
		
		Mockito.when(usb.isconnected()).thenReturn(true);
		
		try {
			Thread.sleep(2000); // Make sure that SendSensorData has finished
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		String bitstream = usb.processSensorData(arduino);
		arduino.writeToInputBuffer(21, bitstream);
		display.setReceivedBinary("/00001111,00001111,0*");
		
		try {
			Thread.sleep(2000); // Make sure that readSpeadAngle has finished
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		gui.stop();
		speedangle.stop();
		sensordata.stop();
		
		expectedSpeed = "15.0";
		expectedAngle = "15.0";
		expectedBitstream = "/00001111,00001111,0*";
		
		actualSpeed = display.getSpeed();
		actualAngle = display.getAngle();
		actualBitstream = display.getReceiveBinary();
		
		Assert.assertEquals(expectedBitstream, actualBitstream);
		Assert.assertEquals(expectedSpeed, actualSpeed);
		Assert.assertEquals(expectedAngle, actualAngle);
	}

}
