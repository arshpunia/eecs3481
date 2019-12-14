package foundation;

import util.CryptoTools;

public class otp {

	public static void main(String[] args) {
		byte[] ct = CryptoTools.hexToBytes("06071C07454903410C1E13472A4E1148261C4D264139130B");
		//System.out.println(ct.length);
		//Next we would get the last 6 bytes, and do the requisite shifts on them such that P-->C and A-->A and R-->I and so on. 
		//We would then convert it all to a hex string and sent it to Bob 
		
		ct[19] = 2;
		int temp = ct[21];
		ct[21] = ct[22];
		ct[22] = (byte)temp;
		
		
	}

}
