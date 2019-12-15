package selectedtopics;

import java.math.BigInteger;
import java.util.Random;

//This class splits a given secret M into W shares such that all of them are needed to reconstruct the secret. 
/**
 * @author Arsh Punia 
 * 
 * The scheme is as follows: 
 * Given secret M. 
 * Choose large N > M. 
 * Generate w-1 random numbers in 0....N
 * Split the W-1 shares. 
 * The w-th share is M - sumOf(all of the W-1 shares)
 *
 */
public class secretSplitting {

	
	public static void main(String[] args) {
		
		BigInteger secret = new BigInteger("291639075201575653178417");
		Random rand = new Random();
		
		//Generating n larger than the secret 
		BigInteger n = new BigInteger(120, rand);
		while(!(n.compareTo(secret)>=0)) {
			n = new BigInteger(120, rand);
		}
		
		//Generating w1..w4 each of length at least 80 bits
		BigInteger w1 = new BigInteger(120, rand);
		while(!(w1.bitLength()>=80) || w1.compareTo(n)>0) {
			 w1 = new BigInteger(120, rand);
		}
		BigInteger w2 = new BigInteger(120, rand);
		while(!(w2.bitLength()>=80) || w2.compareTo(n)>0) {
			 w2 = new BigInteger(120, rand);
		}
		BigInteger w3 = new BigInteger(120, rand);
		while(!(w3.bitLength()>=80) || w3.compareTo(n)>0) {
			 w3 = new BigInteger(120, rand);
		}
		BigInteger w4 = new BigInteger(120, rand);
		while(!(w4.bitLength()>=80) || w4.compareTo(n)>0) {
			 w4 = new BigInteger(120, rand);
		}
		
		//w1,w2,w3,w4 will now be dealt as is. 
		BigInteger shareSum = w1.add(w2).add(w3).add(w4);
		BigInteger w5 = secret.subtract(shareSum);
		
		//w5 is the final share that was distributed
		//System.out.println(w5);
		//System.out.println(w1.add(w2).add(w3).add(w4));
		
		
		
	
		
		
	}
}
