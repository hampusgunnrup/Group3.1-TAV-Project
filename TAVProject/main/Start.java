package main;

import java.awt.EventQueue;

public class Start {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display window = new Display();
					window.frmGraphicalInterface.setVisible(true);
					UpdateDisplay display = new UpdateDisplay(window);
					Arduino arduino = new Arduino ();
					ReceiveSpeedAngle speedangle = new ReceiveSpeedAngle (arduino, display);
					SendSensorData sensordata = new SendSensorData (arduino, display);
					//arduino.inputBuffer = "/10001111,00001111,1*"; // Testing receive
					(new Thread(display)).start();
					(new Thread(speedangle)).start();
					(new Thread(sensordata)).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
