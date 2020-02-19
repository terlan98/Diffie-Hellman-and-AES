import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MessageExchanger
{
	public static void main(String[] args) throws java.lang.Exception
	{
		CBCOverAES cbc = new CBCOverAES("111111111111");
		
		//-------Scanning input-------
		Scanner scanner = new Scanner(System.in);
		String plainText = scanner.nextLine();
		scanner.close();
		//-----------------------------
		
//		String cipher = cbc.encrypt(plainText);
//		String dec = cbc.decrypt(cipher);
		
		byte[] cipherB = cbc.encrypt(plainText);
		String cipher = new String(cipherB, "UTF-8");
		
		byte[] decB = cbc.decrypt(cipherB);
		
		String dec = new String(decB, "UTF-8");
		
		
		System.out.println("Ciphertext: " + cipher);
		System.out.println("Decrypted plaintext: " + dec + '\n');
		System.out.println("\nInitial Vector: " + CBCOverAES.getInitVector() + '\n');
		
		System.out.println("\n\n---Diffie-Hellman---");
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
