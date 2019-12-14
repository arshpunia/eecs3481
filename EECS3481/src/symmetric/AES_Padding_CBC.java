package symmetric;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class AES_Padding_CBC {
	
	
	
	public static void main(String args[]) throws Exception {
		
		byte[] key = CryptoTools.hexToBytes("6A6B6C6D6E6F7071727475767778797A");
		byte[] iv = CryptoTools.hexToBytes("31323334353637383930717765727479");
		
		//Formalizes the key to fit the AES function 
		Key secretKey = new SecretKeySpec(key,"AES");
		
		//Gets the encryption algorithm from the appropriate library 
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		//Formalizes the Initial Value
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		
		//CT Array
		byte[] ct = CryptoTools.hexToBytes("531D5041A12019ACE603BAB33B7A63DF");
		//Up until this point, I am just encrypting it again to get the padding right
		//byte[] Obtainedct = "531D5041A12019ACE603BAB33B7A63DF".getBytes();
		
		
		
		//Initializes the decryption and finishes it by putting it into the byte array
		cipher.init(Cipher.DECRYPT_MODE, secretKey,aps);
		byte[] firstLevelCT = cipher.doFinal(ct);
		
		byte[] desKey = "Strongly".getBytes();
		
		Key secret = new SecretKeySpec(desKey, "DES");
		Cipher desCipher = Cipher.getInstance("DES/ECB/NoPadding");
		
		desCipher.init(Cipher.DECRYPT_MODE, secret);
		byte[] bk = desCipher.doFinal(firstLevelCT);
		System.out.println("BK = " + new String(bk) + "<");
	
	}

}
