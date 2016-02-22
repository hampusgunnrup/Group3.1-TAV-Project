package main;

public class ReadAnswer {
	String bufferStream;
	int errorcode;
	
	public ReadAnswer(int errorcode, String bufferStream) {
		this.errorcode=errorcode;
		this.bufferStream = bufferStream;
	}
	
	@Override
    public boolean equals(Object obj) {	
		ReadAnswer other = (ReadAnswer) obj;
		if ((this.bufferStream.equals(other.bufferStream)) && (this.errorcode == other.errorcode)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void print() {
		System.out.println("***NEW***");
		System.out.println("bufferstream= " + bufferStream);
		System.out.println("errorcode= "+ errorcode);
	}

}
