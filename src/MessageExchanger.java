import java.util.Scanner;

public class MessageExchanger
{
	public static void main(String[] args) throws java.lang.Exception
	{
		CBCOverAES cbc = new CBCOverAES("111111111111");
		
		Scanner scanner = new Scanner(System.in);
		String plainText = scanner.next();
		scanner.close();
		
		
		String cipher = cbc.encrypt(plainText);
		String dec = cbc.decrypt(cipher);
		
		System.out.println("Ciphertext: " + cipher);
		System.out.println("Decrypted plaintext: " + dec + '\n');
		System.out.println("Initial Vector: " + CBCOverAES.getInitVector() + '\n');
		
		System.out.print("Alice's ");
		DHKeyExchanger dhAlice = new DHKeyExchanger(11, 2);
		
		System.out.print("Bob's ");
		DHKeyExchanger dhBob = new DHKeyExchanger(11, 2);
		
		
		long aliceKey = dhAlice.getSecretKey(dhBob.getPublicKey());
		long bobKey = dhBob.getSecretKey(dhAlice.getPublicKey());
		
		if(aliceKey != bobKey) throw new Exception("Secret keys are not the same");
		
		System.out.println("Secret Key: " + aliceKey);
	}
}
