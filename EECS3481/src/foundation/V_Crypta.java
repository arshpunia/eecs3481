package foundation;

import java.io.ByteArrayOutputStream;

import util.CryptoTools;

/**
 * Runs a crypta attack against the Vigenere Cipher. 
 * @author owner with input from Dr Roumani
 *
 */
public class V_Crypta {

	public static void main(String[] args) throws Exception {
		
		/**
		 * Step1: Compute ICs for various keylengths to see which one closely resembles English. I have done it for maxKeylength = 49. 
		 */
		byte[] ct = CryptoTools.fileToBytes("data/MSG4.ct");
		for(int i=1;i<50;i++) {
			//We first get the sample array from the sample method and then get IC's from them. 
			byte[] sample = CryptoTools.sample(ct, i);
			double ic = CryptoTools.getIC(sample, 10000);
			
			//System.out.println("Key length: "+i+" IC: "+ic);
		}
		
		/*
		 * The following commented lines help establish the key chars based on the key length. 
		 */
		//Based on the IC analysis, the key size is either i,2i,3i... and so on. 
		//Trying using key length = i
		//byte[] numberOne = CryptoTools.sample(ct, 9,0);
		//byte[] numberTwo = CryptoTools.sample(ct, i,1);
		//byte[] numberThree = CryptoTools.sample(ct,i,2);
		//byte[] numberFour = CryptoTools.sample(ct,i,3);
		//byte[] numberFive = CryptoTools.sample(ct,i,4);
		//byte[] numberSix = CryptoTools.sample(ct,i,5);
		//byte[] numberSeven = CryptoTools.sample(ct,i,6);
		//byte[] numberEight = CryptoTools.sample(ct,i,7);
		//byte[] numberNine = CryptoTools.sample(ct,i,8);
		
		/**
		 * You are now doing a Caesar for every one character of the key now that you know the length. You determine the best candidate by looking at the largest dot product. 
		 */
		
		/**
		for(int key=0;key<26;key++) {
			byte[] test = new byte[numberOne.length];
			double dotProduct=0;
			for(int k=0;k<numberOne.length;k++)
			{
			int tmp = (byte)(numberOne[k] -'A'- key)%26;
			if(tmp<0) { 
				tmp+=26;
			}
			test[k] = (byte)( tmp+'A');
			}
			
		
	
			int[] testFrequencies = new int[26];
			testFrequencies = CryptoTools.getFrequencies(test);
			for(int dot=0;dot<testFrequencies.length;dot++) {
				dotProduct = dotProduct + (testFrequencies[dot]*CryptoTools.ENGLISH[dot]);
			}
			
			System.out.println("Key,"+key+ ","+ dotProduct);
			//System.out.println("test array "+ new String(test)+"\n");
			
		}
		*/
		//Once you have all the keys, you put their indices into the array below and print out the decrypted array. 
		//The variable keyIndex exists so that you can automate the Caesar shift on every nth character of the ct. 
		//The variable ctLength places the elements in the output array. ctLength goes through the whole array but keyIndex resets once the key length has been reached. 
		//PLEASE CHANGE THE keys ARRAY AND THE keyIndex CONDITION. 
		
		int[] keys = {15,7,24,18,8,2,0,11,11,24};
		byte[] decrypted = new byte[ct.length];
		int ctLength = 0;
		int keyIndex=0;
		while(ctLength<ct.length) {
			
			int tmp = (byte)(ct[ctLength] -'A'- keys[keyIndex])%26;
			if(tmp<0) { 
				tmp+=26;
			}
			decrypted[ctLength] = (byte)( tmp+'A');
			//reset keyIndex once it reaches 8, increment otherwise
			if(keyIndex<9) {
				keyIndex++;
			}
			else {
				keyIndex=0;
			}
			ctLength++;
		}
		
		
		
		byte[] firstSeven = new byte[37];
		for(int i=0;i<37;i++) {
			firstSeven[i] = decrypted[i];
		}
		
		
		
		byte[] ogPT = new byte[59];
		int startingElem = 37;
		for(int i=0;i<22;i++) {
			ogPT[i] = decrypted[startingElem];
			startingElem++;
		}
		int lastShifted = 22;
		for(int i=0;i<37;i++)
		{
			ogPT[lastShifted] = firstSeven[i];
			lastShifted++;
		}
		
		System.out.println(new String(ogPT));
		
	}

}
