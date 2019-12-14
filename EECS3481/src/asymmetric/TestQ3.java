package asymmetric;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class TestQ3 {
	
	public static void main(String[] args) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		BigInteger n = new BigInteger("3132403053003939537259120511215652669000209802124007390698026746711761911350452648337514579811695547619");
		BigInteger e = new BigInteger("101");
		BigInteger d = new BigInteger("837375073575310569366299518949623998366519985909693982657541642622662024250651023885704470531859949101");
		/**
		RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(n, e);
		RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n, d);
		PublicKey pub = keyFactory.generatePublic(pubSpec);
		PrivateKey priv = keyFactory.generatePrivate(privSpec);
		*/
		
		BigInteger p = new BigInteger("35403117426763959545009569");
		BigInteger q = new BigInteger("88478170304740264669647003974090185787271433598412796748462452574232970728451");
		System.out.println(p.multiply(q));
		
		
	}

}
