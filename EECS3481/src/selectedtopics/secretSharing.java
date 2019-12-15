package selectedtopics;

import java.util.Random;

/**
 * Class that'll split a secret m into w shares such that any T of them should be sufficient to reconstruct the secret. 
 * One cannot reconstruct the secret with less than T shares and the entropy of know T-1 is the same as knowing the entropy
 * of none of them. 
 * 
 * The scheme is as follows: 
 * 1. Generate a large prime modulus P
 * 2. Generate T-1 random numbers S(i) and construct y = M + (s1)x + (s2)x^2 and so on and so forth
 * 3. Generate W such distinct shares (xi, f(xi))
 * 4. To reconstruct the secret, just compute f(0)
 * @author owner
 *
 */
public class secretSharing {

	
		public static void main(String[] args) {
			int m = 44; 
			int n = 23; 
			Random rand = new Random();
			int min = 10;
			int s1 = (rand.nextInt(100 - min)+min)%n;
			int s2 = (rand.nextInt(100 - min)+min)%n;
			int s3 = (rand.nextInt(100 - min)+min)%n;
			int s4 = (rand.nextInt(100 - min)+min)%n;
			//int x = 0;
			System.out.println(s1);
			System.out.println(s2);
			System.out.println(s3);
			System.out.println(s4);
			
			for(int i=0;i<6;i++) {
				int x = i+1;
				int y = (int) ((m + s1*x + s2*Math.pow(x, 2) + s3*Math.pow(x, 3) + s4*Math.pow(x, 4))%n);
				System.out.println(x+" "+y);
			}
		}
}
