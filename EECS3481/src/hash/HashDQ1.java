package hash;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import util.CryptoTools;

public class HashDQ1 {

	/**
	 * This class helps us check the integrity of a given message by computing its signature. 
	 * Alice sends Bob Plaintext pt. To establish integrity of the message, she signs the hash of the message with her private key. 
	 * Bob then decrypts the signature using Alice's public key, looks at the hash, and computes the hash of the message
	 * to ensure its integrity. 
	 * Decrypting with Alice's public key helps establish the integrity of the sender. 
	 * The hash ensures that the message was not altered. 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		
		//Part 1: Hashing the damn thing
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] pt = CryptoTools.fileToBytes("data/DQ1.txt");
		byte[] hash = md.digest(pt);
		String cleanHash = CryptoTools.bytesToHex(hash);
		System.out.println("First Hash: "+cleanHash);
		
		//Part2: Encrypting the damn thing 
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
		BigInteger e = new BigInteger("74327");
		BigInteger d = new BigInteger("7289370196881601766768920490284861650464951706793000236386405648425161747775298344104658393385359209126267833888223695609366844098655240542152017354442883676634193191857568369042999854440242050353181703706753485749165295123694487676952198090537385200990850805837963871485320168470788328336240930212290450023");
		RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(n, e);
		RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n,d);
		PublicKey pub = keyFactory.generatePublic(pubSpec);
		PrivateKey priv = keyFactory.generatePrivate(privSpec);
		
		
		Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, priv);
		byte[] ct = cipher.doFinal(hash);
		BigInteger ctTest = new BigInteger(ct);
		System.out.println("Encrypted Hash: "+new String(CryptoTools.bytesToHex(ct)));
		
		
		//Part3: Confirming if the damn thing is legit or nah
		//Cipher encryptedHash = Cipher.getInstance("RSA/ECB/NoPadding");
		//encryptedHash.init(Cipher.DECRYPT_MODE, pub);
		//byte[] decryptedHash = encryptedHash.doFinal(ct);
		byte[] decryptedHash = ctTest.modPow(e, n).toByteArray();
		System.out.println("Decrypted Hash: "+new String(CryptoTools.bytesToHex(decryptedHash)));
	
	}

}
