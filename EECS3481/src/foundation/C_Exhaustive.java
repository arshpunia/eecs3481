package foundation;

import util.CryptoTools;

/**
 * Class to run an exhaustive attack against the Caesar cipher. 
 * @author owner and input from Prof. Roumani
 *
 */
public class C_Exhaustive {

	public static void main(String[] args) throws Exception {
		
		byte[] ct = CryptoTools.fileToBytes("data/MSG2.ct");
		
		/**
		 * Loop that tries every possible shift
		 */
		for(int key=1;key<26;key++) {
			byte[] test = new byte[ct.length];
			double dotProduct=0;
			
			//Inner for loop to shift all CT's as per given test shift.
			for(int k=0;k<ct.length;k++)
			{
			int tmp = (byte)(ct[k] -'A'- key)%26;
			if(tmp<0) { 
				tmp+=26;
			}
			test[k] = (byte)( tmp+'A');
			}

		
	
			int[] testFrequencies = new int[26];
			//Holds the frequencies of all the letters in the test array
			testFrequencies = CryptoTools.getFrequencies(test);
			
			//Given loop finds the dot product of the testArray. 
			for(int dot=0;dot<testFrequencies.length;dot++) {
				dotProduct = dotProduct + (testFrequencies[dot]*CryptoTools.ENGLISH[dot]);
			}
			
			System.out.println("Key,"+key+ ","+ dotProduct);
			System.out.println("test array "+ new String(test)+"\n");
			
		}
		
	}

}
