package main;

public class SpeedAngle {
	double speed;
	double angle;
	boolean success;
	
	public SpeedAngle () {
		angle = 0;
		speed = 0;
		success = false;
	}
	
	public SpeedAngle(double speed, double angle) {
		this.angle = angle;
		this.speed = speed;
		this.success = true;
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
