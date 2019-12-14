package asymmetric;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import util.CryptoTools;

public class RSADecrypt {

	/**
	 * This class decrypts a given message. 
	 * n = modulus 
	 * e = public exponent 
	 * d = private exponent
	 * ct = given ct
	 * 
	 * We simply raise the ct to d and mod it with n. 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		BigInteger p = new BigInteger("264208679307705732524907225971531207681");
		BigInteger q = new BigInteger("200181170185227101268806368199715987557");
		BigInteger n = p.multiply(q);
		BigInteger e = new BigInteger("1031");
		BigInteger pMinusOne = p.subtract(BigInteger.ONE);
		BigInteger qMinusOne = q.subtract(BigInteger.ONE);
		BigInteger phi = pMinusOne.multiply(qMinusOne);
		BigInteger d = e.modInverse(phi);
		
		
		BigInteger ct = new BigInteger("46903772711485649870400600542340635647113782148471559341585401119110429267342");
		byte[] fct = ct.toByteArray();
		byte[] pt = ct.modPow(d, n).toByteArray();
		System.out.println(new String(pt));
		
	}

}
