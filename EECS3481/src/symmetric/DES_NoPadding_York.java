package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class DES_NoPadding_York {
	
	/**
	 * Class for Question 4 from Activity B.The mode of encryption is a variation on CBC. 
	 * The variation however is that the PT is XOR'ed with the negation of the CT from the previous block. 
	 * As to the first block ,it is XOR'ed with the negation of the IV. 
	 * 
	 * When decrypting, you can do the decryption as normal. The obtained PT will just be the bitwise negation of the origional PT. Negate it 
	 * again and you shall have your answer. 
	 *  
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		
		byte[] ky = CryptoTools.hexToBytes("6B79466F724D4F50");
		byte[] iv = CryptoTools.hexToBytes("6976466F724D4F50");
		
		byte[] negatedIV = new byte[iv.length];
		
		
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		byte[] ct = CryptoTools.hexToBytes("437DBAB5607137A5CFC1031114634087");
		
		cipher.init(Cipher.DECRYPT_MODE, secret,aps);
		byte[] negated = cipher.doFinal(ct);
		byte[] bk = new byte[negated.length];
		
		for(int i=0;i<negated.length;i++) {
			bk[i] = (byte) ~negated[i];
		}
		
		System.out.println("BK = " + new String(bk) );
		

	}


}
