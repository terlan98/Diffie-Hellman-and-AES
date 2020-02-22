import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class MessageExchanger
{
	/** A prime number */
	private static int p;
	/** Generator */
	private static int alpha;
	
	public static void main(String[] args) throws UnsupportedEncodingException
	{
		//-------Scanning input-------
		System.out.println("Please enter the the prime number (p) for the key exchange algorithm: ");
		Scanner scanner = new Scanner(System.in);
		p = scanner.nextInt();
		System.out.println("Please enter the the generator (alpha) for the key exchange algorithm: ");
		alpha = scanner.nextInt();
		scanner.nextLine(); // this line is needed so that the next scan doesn't fail
		
		//-------Printing the key exchange related info-------
		System.out.println("----------------------------------------");
		System.out.println("Diffie-Hellman Key Exchange initiated...");
		System.out.println("P = " + p + " Alpha = " + alpha);
		
		//-------Getting the private and public keys-------
		DHKeyExchanger dhAlice = new DHKeyExchanger(p, alpha);
		System.out.println("\nAlice's Private Key: " + dhAlice.getPrivateKey() + "\nAlice's Public Key: " + dhAlice.getPublicKey());
		
		DHKeyExchanger dhBob = new DHKeyExchanger(p, alpha);
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
		scanner.reset();
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
