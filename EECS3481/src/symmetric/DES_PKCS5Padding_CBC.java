package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class DES_PKCS5Padding_CBC
{

	public static void main(String[] args) throws Exception
	{
		
		//Question 3 for Activity B
		
		//The logic here is as follows: 
		//We do not have the first block of CT. The CBC mode will not allow us to then decrypt the first block
		//or the second block. However, we can use the second block as IV to decrypt the second block.
		//Using that I get SEL_11A as my answer, which makes little sense, but if the message is large enough, we
		//might be able to salvage the first two blocks.  
		
		
		//byte[] pt = "EECS3481 Applied Cryptography".getBytes();
		//byte[] ky = CryptoTools.hexToBytes("34567abcdef03211");
		byte[] key = "CSE@YORK".getBytes();
		byte[] iv = CryptoTools.hexToBytes("4E51297B424F90D8");
		
		Key secret = new SecretKeySpec(key, "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
	
		byte[] ct = CryptoTools.hexToBytes("B2ACD6ADF010DDC4");
		//System.out.println("CT = " + CryptoTools.bytesToHex(ct));
		
		cipher.init(Cipher.DECRYPT_MODE, secret,aps);
		byte[] bk = cipher.doFinal(ct);
		System.out.println("BK = " + new String(bk));
		

	}
	
	

}
