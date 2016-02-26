package main;

public class SpeedAngle {
	double speed;
	double angle;
	boolean success;
	private double maxAngle = 90; //Degrees/2 --> Used this notation because we needed to fit 180 degrees and -180 degrees in 8 bits
	private double minAngle =  -90; //Degrees/2 --> Used this notation because we needed to fit 180 degrees and -180 degrees in 8 bits
	private double maxSpeed = 100;
	private double minSpeed = -50; //cars can not go back as fast as they go forward.
	
	public SpeedAngle () {
		angle = 0;
		speed = 0;
		success = false;
	}
	
	public SpeedAngle(double speed, double angle) {
		if ((speed <= maxSpeed && speed >= minSpeed) &&
			(angle <= maxAngle && angle >= minAngle)) {
			this.angle = angle;
			this.speed = speed;
			this.success = true;
		}
		else {
			angle = 0;
			speed = 0;
			success = false;
		}
	}
	
	@Override
    public boolean equals(Object obj) {	
		SpeedAngle other = (SpeedAngle) obj;
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
		else if ((this.speed==other.speed) && (this.angle == other.angle)) {
			return true;
		}
		else {
			return false;
		}
	}
}
