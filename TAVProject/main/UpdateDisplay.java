package main;

public class UpdateDisplay implements Runnable {
	Display gui;
	private boolean running = false;
	
	public UpdateDisplay(Display gui)  {
		this.gui = gui;
		this.running = true;
	}

	public void run () {
		while (running) {
			
		}
	}
	
	public void setReceivedValues(Double speed, Double angle, String binary) {
		gui.setSpeedAngle(speed, angle, binary);
	}
	
	public void setReceivedBinary (String binary) {
		gui.setReceivedBinary(binary);
	}
	
	public void setSentBinary(String binary) {
		gui.setSentBinary(binary);
	}
	
	public double getIR() {
		return gui.getIR();
	}
	public double getUltra() {
		return gui.getUltra();
	}
	public double getTorque() {
		return gui.getTorque();
	}
	public void stop() {
		running = false;
	}
}
