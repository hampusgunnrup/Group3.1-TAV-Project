package test;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import main.Arduino;
import main.Display;
import main.ReceiveSpeedAngle;
import main.SendSensorData;
import main.USB;
import main.UpdateDisplay;

public class ConnectionLostMock {
	
	USB usb = Mockito.mock(USB.class);
	Arduino arduino = new Arduino(usb);
	Display display = new Display();
	UpdateDisplay gui = new UpdateDisplay(display);
	ReceiveSpeedAngle speedangle = new ReceiveSpeedAngle (arduino, gui);
	SendSensorData sensordata = new SendSensorData (arduino, gui);

	
	@Test
	public void testRunOne() {
		
		Mockito.when(usb.processSensorData(arduino)).thenReturn("/00001111,000011");
		Mockito.when(usb.isconnected()).thenReturn(false);
		
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
		
		try {
			Thread.sleep(2000); // Make sure that SendSensorData has finished
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		gui.stop();
		speedangle.stop();
		sensordata.stop();
		
		String expectedBinary = "Connection Lost with USB";
		
		String actualReceivedBitstream = display.getReceiveBinary();
		String actualSentBitstream = display.getSentBinary();
		
		Assert.assertEquals(expectedBinary, actualReceivedBitstream);
		Assert.assertEquals(expectedBinary, actualSentBitstream);
	}
}
