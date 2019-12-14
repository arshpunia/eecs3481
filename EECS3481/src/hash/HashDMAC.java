package hash;

import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class HashDMAC {

	/**
	 * This class computes the MAC of any given message with the given secret key. 
	 * We hash the given message and then encrypt is using the given key. In this case, we used AES.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		//Part 1:Hashing the damn thing
		String ptMessage = "No one can make you feel inferior without your consent.";
		byte[] messageBytes = ptMessage.getBytes();
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageHash = md.digest(messageBytes);
		System.out.println(CryptoTools.bytesToHex(messageHash));
		
		//Part 2: Encrypting the damn thing. 
		byte[] key = "centercenterprof".getBytes();
		System.out.println("key: "+CryptoTools.bytesToHex(key));
		Key secretKey = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedHash = cipher.doFinal(messageHash);
		System.out.println("CT = " + CryptoTools.bytesToHex(encryptedHash));
	}
	
	
}
