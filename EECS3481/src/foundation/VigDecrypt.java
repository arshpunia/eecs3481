package foundation;

import java.io.ByteArrayOutputStream;

import util.CryptoTools;

public class VigDecrypt
{

	public static void main(String[] args) throws Exception
	{
		byte[] ct = CryptoTools.fileToBytes("data/Election.ct");
		for (int k = 1; k < 50; k++)
		{
			byte[] sample = sample(ct, k);
			double ic = CryptoTools.getIC(sample, 10000);
			System.out.printf("Key length %2d --> IC = %.3f\n", k, ic);
		}
		
		
		

	}
	
	public static byte[] sample(byte[] ar, int skip)
	{
		// byte[] result = new byte[1 + ar.length / skip];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		//int pos = 0;
		for (int i = 0; i < ar.length; i = i + skip)
		{
			baos.write(ar[i]);
			//result[pos] = ar[i];
			//pos++;
		}
		//return result;
		return baos.toByteArray();
	}
	
	
	

}
