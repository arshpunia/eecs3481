package asymmetric;

import java.math.BigInteger;

/**
 * Solves a given problem using the Chinese remainder theorem. 
 * The algorithm works as follows: 
 * Given n(i): i=1,2 are pairwise co-prime and: 
 * c(i) = x%n(i)
 * 
 * x = summationOf[c(i)*P(i)*(1/P(i)) %n(i)]mod(n(1)*n(2))
 * P(i) = Product of all n's except n(i). 
 * @author owner
 *
 */
public class RSACQ4 {
	
	public static void main(String[] args) {
		BigInteger n1 = new BigInteger("1055827021987");
		BigInteger n2 = new BigInteger("973491987203");
		
		BigInteger c1 = new BigInteger("365944767426");
		BigInteger c2 = new BigInteger("698856040412");
		
		BigInteger invP1 = n2.modInverse(n1);
		BigInteger invP2 = n1.modInverse(n2);
		BigInteger product = n1.multiply(n2);
		BigInteger firstTerm= (c1.multiply(n2).multiply(invP1));
		BigInteger secondTerm = (c2.multiply(n1).multiply(invP2));
		BigInteger answer = (firstTerm.add(secondTerm)).mod(product);
		
		System.out.println(answer);
		
		
		
		
		
	}

}
