package main;

public class ReceiveSpeedAngle implements Runnable {
	private Arduino arduino;
	private UpdateDisplay gui;
	private boolean running;
	
	public ReceiveSpeedAngle (Arduino arduino, UpdateDisplay gui) {
		this.arduino = arduino;
		this.gui = gui;
		this.running = true;
	}
	
	public void run () {
		while (running) {
			try {
				if (arduino.usb.isconnected()) {
					String bitstream = arduino.inputBuffer;	
					if(!bitstream.isEmpty()) {
						SpeedAngle sa = arduino.readSpeedAngle();
						if (sa.success)
							gui.setReceivedValues(sa.speed, sa.angle, bitstream);
					}
				}
				else {
					gui.setReceivedBinary("Connection Lost with USB");
					gui.setSentBinary("Connection Lost with USB");
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop() {
		this.running = false;
	}
	
}