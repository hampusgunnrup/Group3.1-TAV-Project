package main;

public class SpeedTorque {
	double speed;
	double torque;
	boolean success;
	
	public SpeedTorque () {
		torque = 0;
		speed = 0;
		success = false;
	}
	
	public SpeedTorque(double speed, double torque) {
		this.torque = torque;
		this.speed = speed;
		this.success = true;
	}
	
	@Override
    public boolean equals(Object obj) {	
		SpeedTorque other = (SpeedTorque) obj;
		if((other == null) || (getClass() != other.getClass())){
	        return false;
	    }
		else if(!(this.success||other.success)) {
			//false false returns true
			return true;
		}
		else if (!(this.success&&other.success)) {
			// T F or F T
			return false;
		}
		else if ((this.speed==other.speed) && (this.torque == other.torque)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void print() {
		System.out.println("***NEW***");
		System.out.println("speed= " + speed);
		System.out.println("torque= "+ torque);
	}
}
