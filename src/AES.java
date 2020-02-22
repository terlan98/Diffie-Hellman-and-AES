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
	
	/**
	 * Sets the key for the AES-128 algorithm.\n
	 * Padding is applied if the key is less than 16 bytes.
	 * @param myKey
	 */
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
	
	/**
	 * Encrypts the given byte array.
	 * @param bytesToEncrypt - plaintext byte array
	 * @param secret - key
	 * @return encrypted byte array
	 */
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
	
	/**
	 * Decrypts the given byte array.
	 * @param strToDecrypt - ciphertext byte array
	 * @param secret - key
	 * @return decrypted byte array
	 */
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