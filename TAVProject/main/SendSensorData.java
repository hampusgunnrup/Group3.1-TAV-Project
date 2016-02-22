package main;

public class SendSensorData implements Runnable {
	Arduino arduino;
	UpdateDisplay gui;
	private boolean running = false;
	
	public SendSensorData (Arduino arduino, UpdateDisplay gui) {
		this.arduino = arduino;
		this.gui = gui;
		this.running = true;
	}
	
	public void run () {
		while (running) {
			try {
				double IR=0;
				double Ultra=0;
				double Torque=0;
				IR= (Double) gui.getIR();
				Ultra= (Double) gui.getUltra();
				Torque= (Double) gui.getTorque();
				String answer = arduino.SendSensoryData(Torque, IR, Ultra);
				gui.setSentBinary(answer);
				Thread.sleep(2000);
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
