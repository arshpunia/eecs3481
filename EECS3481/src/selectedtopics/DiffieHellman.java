package selectedtopics;

import java.math.BigInteger;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class DiffieHellman {
	
	public static void main(String[] args) throws Exception{
		BigInteger p = new BigInteger("1426978031065901624399459");  //prime modulus
		BigInteger g = new BigInteger("142983226354603241203899");   //primitive root
		//p and g are public
		BigInteger aX = new BigInteger("959961809391045627604050");  //Alice's DH private
		BigInteger bY = new BigInteger("1294134553675742195014805"); //Bob's DH public
		
		BigInteger k = bY.modPow(aX, p);
		byte[] kArray = k.toByteArray();
		
		byte[] desArray = new byte[8];
		for(int i=0;i<8;i++) {
			desArray[i] = kArray[i];
		}
		
		String stringct = "B960AD698D73CC05"; //The received DES/ECB/PKCS5Padding ciphertext 0x
		byte[] ct = CryptoTools.hexToBytes(stringct);
		Key secret = new SecretKeySpec(desArray, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secret);
		byte[] bk = cipher.doFinal(ct);
		
		System.out.println(new String(bk));
		
	}

}
