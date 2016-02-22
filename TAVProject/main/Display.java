package main;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.apache.commons.lang3.StringUtils;

import java.awt.Font;

public class Display {

	public JFrame frmGraphicalInterface;
	private JTextField irTextField;
	private JTextField ultraTextField;
	private JTextField torqueTextField;
	private JTextField binarySentTextField;
	private JLabel binaryLabel;
	private JLabel sentLabel;
	private JLabel receivedLabel;
	private JTextField binaryRecTextField;
	private JTextField speedTextField;
	private JTextField angleTextField;
	private JLabel lblBinary;
	private JLabel lblSpeed;
	private JLabel lblAngle;

	
	public void setSpeedAngle(double speed, double angle, String binary) {
		speedTextField.setText(String.valueOf(speed));
		angleTextField.setText(String.valueOf(angle));
		binaryRecTextField.setText(binary);
	}
	
	public void setSentBinary(String binary) {
		binarySentTextField.setText(binary);
	}
	
	public double getIR() {
		String IR = irTextField.getText();
		if (StringUtils.isNumericSpace (IR)) {
			return Double.parseDouble(IR);
		}
		else
			return -1;
	}
	
	public double getUltra() {
		String Ultra = ultraTextField.getText();
		if (StringUtils.isNumericSpace (Ultra)) {
			return Double.parseDouble(Ultra);
		}
		else
			return -1;
	}
	
	public double getTorque() {
		String Torque = torqueTextField.getText();
		if (StringUtils.isNumericSpace (Torque)) {
			return Double.parseDouble(Torque);
		}
		else
			return -1;
	}
	
	/**
	 * Create the application.
	 */
	public Display() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGraphicalInterface = new JFrame();
		frmGraphicalInterface.setBackground(new Color(255, 127, 80));
		frmGraphicalInterface.setFont(new Font("Verdana", Font.BOLD, 12));
		frmGraphicalInterface.setForeground(new Color(192, 192, 192));
		frmGraphicalInterface.setTitle("Graphical Interface");
		frmGraphicalInterface.setBounds(100, 100, 450, 300);
		frmGraphicalInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGraphicalInterface.getContentPane().setLayout(null);
		
		
		JLabel irLabel = new JLabel("IR");
		irLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		irLabel.setBounds(35, 68, 46, 14);
		frmGraphicalInterface.getContentPane().add(irLabel);
		
		JLabel ultraLabel = new JLabel("Ultra");
		ultraLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		ultraLabel.setBounds(35, 99, 46, 14);
		frmGraphicalInterface.getContentPane().add(ultraLabel);
		
		JLabel torqueLabel = new JLabel("Torque");
		torqueLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		torqueLabel.setBounds(35, 130, 46, 14);
		frmGraphicalInterface.getContentPane().add(torqueLabel);
		
		binaryLabel = new JLabel("Binary");
		binaryLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		binaryLabel.setBounds(35, 201, 46, 14);
		frmGraphicalInterface.getContentPane().add(binaryLabel);
		
		irTextField = new JTextField();
		irTextField.setHorizontalAlignment(SwingConstants.CENTER);
		irTextField.setBounds(91, 65, 86, 20);
		frmGraphicalInterface.getContentPane().add(irTextField);
		irTextField.setColumns(10);
		irTextField.setText("0");
		
		ultraTextField = new JTextField();
		ultraTextField.setHorizontalAlignment(SwingConstants.CENTER);
		ultraTextField.setBounds(91, 96, 86, 20);
		frmGraphicalInterface.getContentPane().add(ultraTextField);
		ultraTextField.setColumns(10);
		ultraTextField.setText("0");
		
		torqueTextField = new JTextField();
		torqueTextField.setHorizontalAlignment(SwingConstants.CENTER);
		torqueTextField.setBounds(91, 127, 86, 20);
		frmGraphicalInterface.getContentPane().add(torqueTextField);
		torqueTextField.setColumns(10);
		torqueTextField.setText("0");
		
		binarySentTextField = new JTextField();
		binarySentTextField.setHorizontalAlignment(SwingConstants.CENTER);
		binarySentTextField.setColumns(10);
		binarySentTextField.setBounds(78, 198, 264, 20);
		frmGraphicalInterface.getContentPane().add(binarySentTextField);
		binarySentTextField.setText("0");
		
		sentLabel = new JLabel("Sent");
		sentLabel.setForeground(new Color(128, 0, 0));
		sentLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		sentLabel.setBounds(78, 27, 46, 14);
		frmGraphicalInterface.getContentPane().add(sentLabel);
		
		receivedLabel = new JLabel("Received");
		receivedLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		receivedLabel.setForeground(new Color(128, 0, 0));
		receivedLabel.setBounds(281, 27, 86, 14);
		frmGraphicalInterface.getContentPane().add(receivedLabel);
		
		JSeparator verticalSeparator = new JSeparator();
		verticalSeparator.setForeground(new Color(0, 0, 0));
		verticalSeparator.setOrientation(SwingConstants.VERTICAL);
		verticalSeparator.setBounds(214, 28, 2, 187);
		frmGraphicalInterface.getContentPane().add(verticalSeparator);
		
		binaryRecTextField = new JTextField();
		binaryRecTextField.setHorizontalAlignment(SwingConstants.CENTER);
		binaryRecTextField.setColumns(10);
		binaryRecTextField.setBounds(254, 66, 190, 20);
		frmGraphicalInterface.getContentPane().add(binaryRecTextField);
		
		speedTextField = new JTextField();
		speedTextField.setHorizontalAlignment(SwingConstants.CENTER);
		speedTextField.setColumns(10);
		speedTextField.setBounds(312, 97, 86, 20);
		frmGraphicalInterface.getContentPane().add(speedTextField);
		
		angleTextField = new JTextField();
		angleTextField.setHorizontalAlignment(SwingConstants.CENTER);
		angleTextField.setColumns(10);
		angleTextField.setBounds(312, 128, 86, 20);
		frmGraphicalInterface.getContentPane().add(angleTextField);
		
		lblBinary = new JLabel("Binary");
		lblBinary.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblBinary.setBounds(214, 68, 46, 14);
		frmGraphicalInterface.getContentPane().add(lblBinary);
		
		lblSpeed = new JLabel("Speed");
		lblSpeed.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblSpeed.setBounds(236, 100, 46, 14);
		frmGraphicalInterface.getContentPane().add(lblSpeed);
		
		lblAngle = new JLabel("Angle");
		lblAngle.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblAngle.setBounds(236, 133, 46, 14);
		frmGraphicalInterface.getContentPane().add(lblAngle);
	}
}
