import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * A class that performs AES Encryption and Decryption. <br>
 * The code was taken from HowToDoItInJava. Most parts of the code are modified.
 * 
 * @author Lokesh Gupta
 * @see <a href=
 *      "https://howtodoinjava.com/security/java-aes-encryption-example/">HowToDoItInJava</a>
 */
public class AES
{
	private static SecretKeySpec secretKey;
	private static byte[] key;
	
	public static void setKey(String myKey)
	{
		try
		{
			key = myKey.getBytes("UTF-8");
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}
	
	public static byte[] encrypt(byte[] bytesToEncrypt, String secret)
	{
		try
		{
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			
			byte[] resultArray = cipher.doFinal(bytesToEncrypt);
			
			return resultArray;
		}
		catch (Exception e)
		{
			System.out.println("Error while encrypting: " + e.toString());
		}
		
		return null;
	}
	
	public static byte[] decrypt(byte[] strToDecrypt, String secret)
	{
		try
		{
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			
			return cipher.doFinal(strToDecrypt);
		}
		catch (Exception e)
		{
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}
}