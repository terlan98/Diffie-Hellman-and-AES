import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class MessageExchanger
{
	/** A prime number */
	private static final int P = 11;
	/** Generator */
	private static final int ALPHA = 2;
	
	public static void main(String[] args) throws UnsupportedEncodingException
	{
		System.out.println("----------------------------------------");
		System.out.println("Diffie-Hellman Key Exchange initiated...");
		System.out.println("P = " + P + " Alpha = " + ALPHA);
		
		//-------Getting the private and public keys-------
		DHKeyExchanger dhAlice = new DHKeyExchanger(P, ALPHA);
		System.out.println("\nAlice's Private Key: " + dhAlice.getPrivateKey() + "\nAlice's Public Key: " + dhAlice.getPublicKey());
		
		DHKeyExchanger dhBob = new DHKeyExchanger(P, ALPHA);
		System.out.println("\nBob's Private Key: " + dhBob.getPrivateKey() + "\nBob's Public Key: " + dhBob.getPublicKey());
		
		//-------Getting the secret key-------
		int aliceKey = dhAlice.getSecretKey(dhBob.getPublicKey());
		int bobKey = dhBob.getSecretKey(dhAlice.getPublicKey());
		
		if (aliceKey != bobKey)
			System.err.println("Secret keys are not the same");
		
		System.out.println("\nSecret Key: " + aliceKey);
		System.out.println("----------------------------------------");
		
		CBCOverAES cbc = new CBCOverAES(aliceKey + "");
		
		//-------Scanning input-------
		System.out.println("Please type your message, Alice: ");
		Scanner scanner = new Scanner(System.in);
		String plainText = scanner.nextLine();
		scanner.close();
		
		//----------Encryption---------
		byte[] cipherB = cbc.encrypt(plainText);
		String cipher = new String(cipherB, "UTF-8");
		
		//----------Decryption---------
		byte[] decB = cbc.decrypt(cipherB);
		String dec = new String(decB, "UTF-8");
		
		//-------Printing results-------
		System.out.println("\nPlaintext (Alice sees this): " + plainText);
		System.out.println("Ciphertext (Oscar sees this): " + cipher);
		System.out.println("Decrypted plaintext (Bob sees this): " + dec);
		System.out.println("\nInitial Vector: " + CBCOverAES.getInitVector() + '\n');
	}
}
