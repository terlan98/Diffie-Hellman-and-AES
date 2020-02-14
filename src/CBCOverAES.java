import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.spec.IvParameterSpec;

public class CBCOverAES
{
	private static SecureRandom rnd = new SecureRandom();
	private static byte[] iv = new IvParameterSpec(rnd.generateSeed(16)).getIV();
	private String key;
	
	public CBCOverAES(String key)
	{
		this.key = key;
	}
	
	public String encrypt(String message) throws UnsupportedEncodingException
	{
		String ciphertext = "";
		byte[] plaintext = message.getBytes("UTF-8");
		byte[] ivCopy = iv;
				
		System.out.println("LENGTH:" + plaintext.length);
		
		for (int i = 0; i < plaintext.length; i += 16)
		{
			byte[] block = new byte[16];
			byte[] xorText = new byte[16];
			
			for (int j = i; j < i + 16; j++) // Create block
			{
				block[j % 16] = plaintext[j];
			}
			
//			System.out.println("BLOCK: " + Arrays.toString(block));
			
			xorText = xorArrays(block, ivCopy); //XOR with initial vector
//			System.out.println("xorText size: " + xorText.length);
			
			String result = AES.encrypt(xorText, key);
			ciphertext += result;
			ivCopy = result.getBytes();// TODO: At this point, my iv becomes larger due to large result coming from AES
			
//			System.out.println("result sizE: " + ivCopy.length);
//			System.out.println(result);
//			System.out.println("DECRYPTED:" + AES.decrypt(result, key));
//			System.out.println("-------------------------------");
		}
		
		return ciphertext;
	}
	
	public String decrypt(String message) throws UnsupportedEncodingException
	{
		String plaintext = "";
		byte[] ciphertext = message.getBytes("UTF-8");
		byte[] ivCopy = iv;
		
		System.out.println("CYPHER LENGTH:" + ciphertext.length);

		for (int i = 0; i < ciphertext.length; i += 16)
		{
			byte[] block = new byte[16];
			byte[] xorText = new byte[16];
			byte[] decryptedBlock = new byte[16];
			
			for (int j = i; j < i + 16; j++) // Create block
			{
				block[j % 16] = ciphertext[j];
			}
			
			String blockText = Base64.getEncoder().encodeToString(block);
			String decrypted = AES.decrypt(blockText, key);
			decryptedBlock = decrypted.getBytes();
			
//			xorText = xorArrays(decryptedBlock, ivCopy);
			plaintext += xorText;
			ivCopy = block;
		}
		
		
		return plaintext;
	}
	
	private byte[] xorArrays(byte[] a, byte[] b)
	{
		System.out.println(a.length + " .XOR. " + b.length);
		byte[] c = new byte[a.length];
		
		for (int i = 0; i < a.length; i++)
		{
			byte bt = a[i];
			byte v = b[i];
			
//			System.out.print(bt + " XOR " + v + " = ");
			
			c[i] = (byte) (bt ^ v);
			
//			System.out.println(c[i]);
		}
		
		return c;
	}
	
	public static String getInitVector()
	{
		return Arrays.toString(iv);
	}
}
