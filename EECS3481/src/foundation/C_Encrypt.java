package foundation;

import util.CryptoTools;


/**
 * Class to encrypt given PT to CT using the given key. 
 * @author owner with inputs from Prof. Roumani 
 *
 */
public class C_Encrypt {
	
	public static void main(String[] args) throws Exception {
		//Given key for encryption 
		int key = 19;
		byte[] pt = CryptoTools.fileToBytes("data/MSG1.pt");
		byte[] cleanPT = CryptoTools.clean(pt);
		byte[] ct = new byte[cleanPT.length];
		CryptoTools.bytesToFile(cleanPT, "data/MSG1.clean");
		for(int i=0; i<cleanPT.length;i++) {
			ct[i] =  (byte) ((cleanPT[i] - 'A' + key) % 26 + 'A');
		}
		CryptoTools.bytesToFile(ct,"data/MSG1.ct");
		System.out.println(CryptoTools.getMD5(ct));
		System.out.println(CryptoTools.getIC(pt,"data/MSG1.pt"));
		
		
	}

}
