package asymmetric;

import java.math.BigInteger;
import java.util.Random;

public class RSACQ5 {
	
	/**
	 * 
	 * @param args
	 * https://crypto.stanford.edu/pbc/notes/numbertheory/millerrabin.html
	 * 
	 */
	public static void main(String[] args) {
		//Number that we need to test for primality 
		BigInteger n = new BigInteger("101");
		
		//Doing n-1
		BigInteger nMinus1 = n.subtract(BigInteger.ONE);
		BigInteger q = nMinus1;
		BigInteger two = BigInteger.TWO;
		
		//Finding Q s.t. (2^s)*q = nMinus1
		int s=0;
		while(q.mod(two).equals(BigInteger.ZERO)) {
			s++;
			//copy = copy.divide(two);
			q = q.divide(two);
		}
		System.out.println("S: "+s);
		//Generating Random Number a such that 1<a<n
		 Random rand = new Random();
		 
		 BigInteger a = new BigInteger(nMinus1.bitLength(), rand);
		    while( a.compareTo(nMinus1) >= 0 || a.compareTo(BigInteger.ONE)<=0) {
		        a = new BigInteger(n.bitLength(), rand);
		    }
		    
		System.out.println("a: "+a);
		  
		
		int state = 0;
		//here we proceed to check if (a^q)%n = 1, then we have a possible prime
		if(a.modPow(q, n).equals(BigInteger.ONE)) {
			  System.out.println("First Test: "+a.modPow(q, n));
			  state =1;
			  System.out.println("1. prime/inconclusive");
		   }
		//If the above test fails, then we run a loop that goes from i=0...s-1 to see if a^((2^i)*q) % n = -1 or 1. if so, we break and declare n to be a probable prime. If not, we have a composite number
		   else {
			   
			   System.out.println("q: "+q);
		 
			   for(int i=0;i<s;i++) {
			 
			 BigInteger exponent = q.multiply(two.pow(i)); 
			 int exponentCopy = exponent.intValue();
			 BigInteger power = a.pow(exponentCopy);
			 
			 System.out.println("Exponent: "+exponent+" Power: "+power+" PowAndRemainder: "+power.remainder(n));
			 
			 if(power.remainder(n).equals(BigInteger.valueOf(-1)) || power.remainder(n).equals(BigInteger.ONE)) {
				 state = 1; 
		
				 System.out.println("2. prime/inconclusive");
				 break;
			 }
		   }
		   
		   if(state==0) {
			   System.out.println("composite");
		   }
		   }
	}
}
