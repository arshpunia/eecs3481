package foundation;

import util.CryptoTools;

/**
 * Runs a cryptanalytic attack against Caesar Cipher
 * @author owner
 *
 */
public class C_Crypta {
	
	//In the answer to this problem, you will notice that A has the highest frequencies, implying it is the result of shift of E. 
	//Index of A: 0
	//Index of E: 4
	//Answer: 26-4-0 = 22 and that's your answer. 
	
	public static void main(String[] args) throws Exception{
		/**
		 * The methodology here is to compute the frequencies of the letters and then divide them by the size of the CT. The largest number in that array will be the shifted 'e'. 
		 */
		byte[] ct = CryptoTools.fileToBytes("data/MSG2.ct");
		int[] rawFq = CryptoTools.getFrequencies(ct);
		double[] frequencies = new double[rawFq.length];
		
		for(int i=0;i<rawFq.length;i++) {
			
			frequencies[i] = ((double)rawFq[i]/ct.length);
		}
		
		for(int k=0;k<frequencies.length;k++) {
			System.out.println(frequencies[k]);
		}
		
		byte[] pt = new byte[ct.length];
		int correctKey=22;
		
		for(int k=0;k<ct.length;k++)
		{
		int tmp = (byte)(ct[k] -'A'- correctKey)%26;
		if(tmp<0) { 
			tmp+=26;
		}
		pt[k] = (byte)( tmp+'A');
		}
		
		System.out.println(new String(pt));
	
	
	}
	

}
