package asymmetric;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import util.CryptoTools;

/**
 * We have been given phi, q, e and c. 
 * From phi and q, we can get p-1 and then p. 
 * With p and q, we can get our hands on n. 
 * With phi and e, we can also get our hands on d. 
 * And once we have the d, we can easily decrypt the message.   
 * @author owner
 *
 */
public class RSACQ3 {
	public static void main(String[] args) throws Exception{
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		BigInteger phi = new BigInteger("8584037913642434144111279062847405921823163865842701785008602377400681495147541519557274092429073976252689387304835782258785521935078205581766754116919200");
		BigInteger q = new BigInteger("87020952829623092932322362936864583897972618059974315662422560067745889600571");
		BigInteger e = new BigInteger("65537");
		BigInteger QMinusOne = q.subtract(BigInteger.ONE);
		BigInteger pMinusOne = phi.divide(QMinusOne);
		BigInteger p = pMinusOne.add(BigInteger.ONE);
		BigInteger n = p.multiply(q);
		BigInteger d = e.modInverse(phi);
		RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(n, e);
		RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n,d);
		PublicKey pub = keyFactory.generatePublic(pubSpec);
		PrivateKey priv = keyFactory.generatePrivate(privSpec);
		
		BigInteger ct = new BigInteger("1817487313698347891034157970684926175211834109573277793102901854482611726141560963120214926234448852417078321539316776648109260519063106558303669880226359");
		byte[] pt = ct.modPow(d, n).toByteArray();
		//System.out.println(pt.toString());
		System.out.println(new String(pt));
		
	}
}
