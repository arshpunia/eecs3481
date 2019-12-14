package asymmetric;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import util.CryptoTools;

public class RSACQ2 {
	
	/*
	 * We have been given Bob's public key and n
	 * Bob's p leaked, from which we can calculate q, then phi, and  from  e and phi, we get d. 
	 * Once we have d, we can easily decrypt the message. 
	 */
	public static void main(String[] args) throws Exception{
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
		BigInteger e = new BigInteger("74327");
		BigInteger p = new BigInteger("10358344307803887695931304169230543785620607743682421994532795393937342395753127888522373061586445417642355843316524942445924294144921649080401518286829171");
		BigInteger q = n.divide(p);
		BigInteger pMinusOne = p.subtract(BigInteger.ONE);
		BigInteger qMinusOne = q.subtract(BigInteger.ONE);
		BigInteger phi = pMinusOne.multiply(qMinusOne);
		BigInteger d = e.modInverse(phi);
		RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(n, e);
		RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n,d);
		PublicKey pub = keyFactory.generatePublic(pubSpec);
		PrivateKey priv = keyFactory.generatePrivate(privSpec);
		BigInteger ct = new BigInteger("10870101966939556606443697147757930290262227730644958783498257036423105365610629529910525828464329792615002602782366786531253275463358840412867833406256467153345139501952173409955322129689670345445632775574301781800376545448990332608558103266831217073027652061091790342124418143422318965525239492387183438956");
		
		byte[] pt = ct.modPow(d, n).toByteArray();
		System.out.println(pt.toString());
		System.out.println(new String(pt));
		//System.out.println(CryptoTools.bytesToHex(pt));
		/*
		 * byte[] fct = ct.toByteArray(); Cipher cipher =
		 * Cipher.getInstance("RSA/ECB/NoPadding"); cipher.init(Cipher.DECRYPT_MODE,
		 * priv); byte[] pt = cipher.doFinal(fct); System.out.println(new String(pt));
		 */
	}

}
