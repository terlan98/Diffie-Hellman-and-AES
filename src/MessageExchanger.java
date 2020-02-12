import java.util.Scanner;

public class MessageExchanger
{
	public static void main(String[] args) throws java.lang.Exception
	{
		Scanner scanner = new Scanner(System.in);
		String plainText = scanner.next();
		scanner.close();
		
		String enc = AES.encrypt(plainText, "key");
		String dec = AES.decrypt(enc, "key");
		
		System.out.println("Ciphertext: " + enc);
		System.out.println("Decrypted plaintext: " + dec + '\n');
		System.out.println("Initial Vector: " + AES.getInitVector() + '\n');
		
		System.out.print("Alice's ");
		DHKeyExchanger dhAlice = new DHKeyExchanger(11, 2);
		
		System.out.print("Bob's ");
		DHKeyExchanger dhBob = new DHKeyExchanger(11, 2);
		
		
		int aliceKey = dhAlice.getSecretKey(dhBob.getPublicKey());
		int bobKey = dhBob.getSecretKey(dhAlice.getPublicKey());
		
		if(aliceKey != bobKey) throw new Exception("Secret keys are not the same");
		
		System.out.println("Secret Key: " + aliceKey);
	}
}
