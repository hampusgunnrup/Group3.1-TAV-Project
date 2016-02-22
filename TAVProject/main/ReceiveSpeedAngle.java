package main;

public class ReceiveSpeedAngle implements Runnable {
	Arduino arduino;
	UpdateDisplay gui;
	
	public ReceiveSpeedAngle (Arduino arduino, UpdateDisplay gui) {
		this.arduino = arduino;
		this.gui = gui;
	}
	public void run () {
		while (true) {
			try {
				ReadAnswer answer = arduino.ReadFromBuffer(21);
				if (answer.errorcode==0) {
					arduino.bitstream = answer.bufferStream;
					SpeedAngle sa = arduino.readSpeedAngle();
					gui.setReceivedValues(sa.speed, sa.angle, answer.bufferStream);
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
