package main;

public class UpdateDisplay implements Runnable {
	Display gui;
	
	public UpdateDisplay(Display gui)  {
		this.gui = gui;
	}

	public void run () {
	}
	
	public void setReceivedValues(Double speed, Double angle, String binary) {
		gui.setSpeedAngle(speed, angle, binary);
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
}
