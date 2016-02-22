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
				//ReadAnswer answer = arduino.ReadFromBuffer(21);
				//if (answer.errorcode==0) {
					//arduino.inputBuffer = answer.bufferStream;
					String bitstream = arduino.inputBuffer;	
					SpeedAngle sa = arduino.readSpeedAngle();
					if(bitstream != "") {
						gui.setReceivedValues(sa.speed, sa.angle, bitstream);
					}
				//}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void stop() {
		this.running = false;
	}
	
}
