package foundation;

import java.math.BigInteger;

import util.CryptoTools;

/**
 * Runs an exhaustive attack against Affine. 
 * @author owner
 *
 */
public class A_Exhaustive {

	public static void main(String[] args) throws Exception{
		
		//The following values are the only ones possible for a (in mod26) because there are no mod 26 inverses for other numbers. 
		int[] aValues = {1,3,5,7,9,11,15,17,19,21,23,25};
		byte[] ct = CryptoTools.fileToBytes("data/MSG3.ct");
		System.out.println("alpha,beta,frequency");
		//Loop to exhaustively test for all the possible values of a and b. 
		for(int i=0;i<aValues.length;i++) {
				int a = aValues[i];
				
				byte[] testPT = new byte[ct.length];
				//The next 3 lines effectively compute the mod26 inverse of a
				BigInteger bigInt = BigInteger.valueOf(a);
				BigInteger twentySix = BigInteger.valueOf(26);
				BigInteger bigModInv = bigInt.modInverse(twentySix);
				int modInv = bigModInv.intValue();
				
				//Loop to loop through 0-25. 
				for(int j=0;j<=26;j++) {
					double dotProduct = 0;
					for(int k=0;k<ct.length;k++) {
						//Effectively doing Caesar for various values of B
						int temp = (byte) ((modInv*(ct[k]-'A'-j))%26);
						if(temp<0) {
							temp=temp+26;
						
						}
						
						testPT[k] = (byte)(temp+'A');
						
					}
					//Frequency array to find maximum dot product	
					int[] testFreq = new int[26];
					testFreq = CryptoTools.getFrequencies(testPT);
					for(int m=0;m<testFreq.length;m++) {
						dotProduct = dotProduct+testFreq[m]*CryptoTools.ENGLISH[m];
					}
					
					//Outputs all the various parameters to stdout and a csv file in the home directory.
					//Open the CSV file in excel and then sort by the freqeuncy. 
					//Then uncomment the following code to output the PT. 
					System.out.println(a+ ","+j+","+dotProduct);
					/**
					if(a==5 && j==14) {
						System.out.println(new String(testPT));
					}
					*/
				}
				
				
				
			
			
		}
		
	}

}
