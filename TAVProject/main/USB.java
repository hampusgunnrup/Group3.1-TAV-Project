package main;

public class USB{
	boolean connected;

	
	public USB () {
		connected = true;
	}
	
	public String processSensorData(Arduino arduino) {
		return "";	
	}
	
	public void connect () {
		connected =  true;
	}
	
	public boolean isconnected() {
		return connected;
	}
	
	public void disconnect () {
		connected = false;
	}
}
