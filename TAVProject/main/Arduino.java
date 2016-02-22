package main;

public class Arduino {
	public String inputBuffer;
	public String outputBuffer;
	public String bitstream;
	
	int MaxTorque = 30;
	int MinTorque = -30;
	int Max_IR_Dist = 40;
	int Min_IR_Dist = -1;
	int Max_Ultra_Dist = 50;
	int Min_Ultra_Dist = -1;
	String start_Del = "/";
	String end_Del = "*";
	String comma = ",";
	int minbitstreamsize = 21;
	
	public Arduino() {
		inputBuffer = "";
		outputBuffer = "";
	}
	
	/**

	  Description: write to input buffer

	  Pre-condition: n > 0 and bitstream "s" == 21 bytes

	  Post-condition: return 0 if successful operation otherwise a constant non-zero denoting an error code
	  
	  Test-cases: 1.(N > 0 S >= N) 2.(N > 0 S < N) 3. (N < 0)

	*/
	//Write to Input Buffer
	public int writeToInputBuffer(int n, String s) {
		if (n<0) {  //TC3
			return 2; // error code for trying to write negative amounts of bits
		}
		else if (s.length() < n) { //TC2
			inputBuffer = inputBuffer + s;
			return 1; // indicate not enough bits received
		}
		else { //TC1 N > 0 & S >= N
			inputBuffer = inputBuffer + s.substring(0,n);
			return 0; // successful test
		}
	}
	
	/**

	  Description: read from output buffer

	  Pre-condition: n > 0 (then removes these "n" bits from beginning of output buffer stream)

	  Post-condition: return object containing error code (0 = success) and a bitstream with "n" bits
	  
	  Test-cases: 1.(n>0 s>=n) 2.(n>0 s<n) 3.(n<0 s>n)

	*/
	//Read From Output Buffer
	public ReadAnswer ReadFromBuffer(int n) {
		ReadAnswer answer;
		if (n < 0) { //TC3 trying to read negative bits
			answer = new ReadAnswer(2, ""); //2 indicating negative bits
		}
		else if (outputBuffer.length() < n) { //TC2 //n>0 s<n
			String returnbuffer= "";
			for (int i = 0;i < outputBuffer.length(); i++) { //adds the amount of bits that are in the stream
				char c = outputBuffer.charAt(i);
				returnbuffer = returnbuffer + c;
			}
			outputBuffer = "";
			answer = new ReadAnswer(1,returnbuffer); //1 indicating not enough bits in stream to be read
		}
		else { //TC1 n>0 s>=n
			String returnbuffer= "";
			for (int i = 0;i < n; i++) {
				char c = outputBuffer.charAt(i);
				returnbuffer = returnbuffer + c;
			}
			outputBuffer = outputBuffer.substring(n);
			answer = new ReadAnswer(0, returnbuffer); // 0 for successful
		}
		return answer; //object with error code and the bufferstream
	}

	/**

	  Description: Read speed & torque

	  Pre-condition: binary packet of 21 bytes.

	  Post-condition: object with 2 attributes speed & torque
	  
	  Test-cases: ReadSpeedAndTorqueTest.java Test cases 1-8 (Too many to specify here)

	*/
	//Read Speed and Torque
	public SpeedTorque ReadSpeedTorque() {
		SpeedTorque answer;
		if (bitstream.length() < minbitstreamsize) {//TC8
			answer = new SpeedTorque();
		}
		else if (bitstream.indexOf(start_Del)<0) {//TC7
			answer = new SpeedTorque();
		}
		else {
			int start = bitstream.indexOf(start_Del);//TC2,TC1
			int end = start + minbitstreamsize;
			if (end-1 >= bitstream.length()) {//TC8
				answer = new SpeedTorque();
			}
			else
			{
				String packet = bitstream.substring(start, end);
				if (packet.charAt(20)!=end_Del.charAt(0)) {//TC6
					answer = new SpeedTorque();
					bitstream = bitstream.substring(start+1);
				}
				else if (packet.charAt(9)!=comma.charAt(0)) {//TC5
					answer = new SpeedTorque();
					bitstream = bitstream.substring(start+1);
				}
				else if (packet.charAt(18)!=comma.charAt(0)) {//TC4
					answer = new SpeedTorque();
					bitstream = bitstream.substring(start+1);
				}
				else if (ParityCheck(packet.substring(0,18)).charAt(0) != packet.charAt(19)) { //TC3 parity check failed
					answer = new SpeedTorque();
					bitstream = bitstream.substring(start+1);
				}
				else {//TC1,TC2
					String speedStr = packet.substring(1,9);
					String torqueStr = packet.substring(10,18);
					double speed = bin8_to_dec(speedStr);
					double torque = bin8_to_dec(torqueStr);
					answer = new SpeedTorque(speed, torque);
					bitstream = bitstream.substring(end);
				}
			}
		}
		return answer; //object containing speed & torque
	}
	/**

	  Description: send sensor data

	  Pre-condition: (torque, IR distance, ultra - distance) x-y, n-m, a-b

	  Post-condition: bitstream(binary) format(/[8],[8],[8],[1]*) 30 bit package
	  
	  Test-cases: TestSensoryData.java test cases 1-8

	*/
	//Send Sensory Data
	public String SendSensoryData (double Torque, double IR_Dist, double ULTRA_Dist) {
		String answer="";
		String T = dec_to_8bbin(Torque);
		String IR = dec_to_8bbin(IR_Dist);
		String Ultra = dec_to_8bbin(ULTRA_Dist);
		if (((MinTorque <= Torque && Torque <= MaxTorque) && (Min_IR_Dist <= IR_Dist && IR_Dist <= Max_IR_Dist)) && (Min_Ultra_Dist <= ULTRA_Dist && ULTRA_Dist<= Max_Ultra_Dist)) {
			answer = start_Del + T + comma + IR + comma + Ultra + comma + ParityCheck(T+IR+Ultra) + end_Del;
		}
		return answer;
	}

	
//**************************** HELP FUNCTIONS ****************************//
	
	//Change decimal to 8bit binary
	public String dec_to_8bbin(double Value) {
		if(Value <= 63 && Value >= -63) {
			String answer = "";
			String binaryStr = "";
			int valueCopy = (int) Value;
			while (valueCopy!=0) {
				int mod = Math.abs(valueCopy%2);
				int rem = valueCopy/2;
				binaryStr = mod + binaryStr;
				valueCopy=rem;
			}
			answer = String.format("%7s", binaryStr).replace(' ', '0');
			if (Value < 0) {
				answer = "1" + answer;
			}
			else{
				answer = "0" + answer;
			}
			return answer;
		}
		else {
			return "11111111";
		}
	}	
	
	//Check Parity for the packet
	public String ParityCheck (String string) {
		int total = 0;
		for (int i = 0; i < string.length(); i++) {
		    char c = string.charAt(i);        
		    if (c=='1') {
		    	total = total +1;
		    }
		}
		if ((total%2)==0) {
			return "0";
		}
		else {
			return "1";
		}
	}
	
	//Change 8bit to decimal
	public double bin8_to_dec(String bin) {
		if(bin.length() == 8) {
			double answer = 0;
			int exp = 1;
			for (int i=7;i>0;i--) {
				answer = answer + exp*Character.getNumericValue(bin.charAt(i));
				exp = exp*2;
			}
			if (bin.charAt(0) == '1') {
				answer = answer*-1;
			}
			return answer;
		}
		else {
			return 99;
		}
	}
}
