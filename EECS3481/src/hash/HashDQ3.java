package hash;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;

import util.CryptoTools;

/**
 * This class computes the HMAC of given message and secret key. 
 * One has to make sure to pad the key on the right with 0's to make it 64 byte long for SHA-1. 
 * 
 * One also has to make sure that the given keys for hashing, iPad and oPad are properly padded repeatedly in the 64 byte array.
 * 
 *  We then define two 64-byte arrays called oPadKey = secretKey^oPad and iPadkey = secretKey^iPad
 *  
 *  Upon the first run, we concatenate the message with iPadKey and hash it with SHA-1. 
 *  We then concatenate this with oPadKey and hash it once again to get the final hash. 
 * @author owner
 *
 */
public class HashDQ3 {
	public static void main(String[] args) throws Exception{
		
		//Part1: Getting the messages
		byte[] message1 = "Meet at 6:30 pm on 20.".getBytes();
		byte[] message2 = "Buy 270 RY at MarketP.".getBytes();
		byte[] message3 = "Temperature 28 in YYZ.".getBytes();
		byte[] message4 = "Approach runway 24 SW.".getBytes();
		
		byte[] key = CryptoTools.hexToBytes("7CEB25A45B2435BF457AAE");
		
		ByteArrayOutputStream concat = new ByteArrayOutputStream();
		for(int i=0;i<key.length;i++) {
			concat.write(key[i]);
		}
		
		for(int i=0;i<message3.length;i++) {
			concat.write(message3[i]);
		}
		
		for(int i=0;i<key.length;i++) {
			concat.write(key[i]);
		}
		
		byte[] finalConcat = concat.toByteArray();
		/**
		ByteArrayOutputStream padding = new ByteArrayOutputStream();
		for(int i=0;i<rawKey.length;i++) {
			padding.write(rawKey[i]);
		}
		for(int i=0;i<64-rawKey.length;i++) {
			padding.write(0);
		}
		
		secretKey = padding.toByteArray();
		//Part2: Padding the iPad and the oPad
		byte[] rawIpad = CryptoTools.hexToBytes("36");
		byte[] iPad = new byte[64];
		for(int i=0;i<64;i++) {
			iPad[i] = rawIpad[0];
		}
		
		byte[] rawOpad = CryptoTools.hexToBytes("5c");
		byte[] oPad = new byte[64];
		for(int i=0;i<64;i++) {
			oPad[i] = rawOpad[0];
		}
		
		byte[] iPadKey = new byte[64];
		byte[] oPadKey = new byte[64];
		
		for(int i=0;i<64;i++) {
			iPadKey[i] = (byte) (secretKey[i]^iPad[i]);
			oPadKey[i] = (byte) (secretKey[i]^oPad[i]);
		}
		
		//Part3: Concatenating the iKeyPad and the message
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		for(int i=0;i<iPadKey.length;i++) {
			bos.write(iPadKey[i]);
		}
		for(int k=0;k<message.length;k++) {
			bos.write(message[k]);
		}
		byte[] levelOnePT = bos.toByteArray();
		System.out.println("message 0: "+message[2]+" "+levelOnePT[66]);
		
		*/
		//Part4: Hashing the levelOnePT
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] finalHash = md.digest(finalConcat);
		
		//Part5: Concatenating the innerHash with the oPadKey
		/**
		ByteArrayOutputStream outerBos = new ByteArrayOutputStream();
		for(int i=0;i<oPadKey.length;i++) {
			outerBos.write(oPadKey[i]);
		}
		for(int k=0;k<innerHash.length;k++) {
			outerBos.write(innerHash[k]);
		}
		byte[] levelTwoPT = outerBos.toByteArray();
		
		//Part6: Hashing the levelTwoPT
		MessageDigest finalHash = MessageDigest.getInstance("SHA-1");
		byte[] outerHash = finalHash.digest(levelTwoPT);
		*/
		System.out.println("Answer: "+CryptoTools.bytesToHex(finalHash));
		
		
	}

}
