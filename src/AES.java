import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * A class that performs AES Encryption and Decryption. <br>
 * The code was taken from HowToDoItInJava. Some parts of the code are modified.
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
		MessageDigest sha = null;
		try
		{
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}
	
	public static String encrypt(byte[] bytesToEncrypt, String secret)
	{
		try
		{
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			
			return Base64.getEncoder().encodeToString(cipher.doFinal(bytesToEncrypt));
		}
		catch (Exception e)
		{
			System.out.println("Error while encrypting: " + e.toString());
		}
		
		return null;
	}
	
	public static String decrypt(String strToDecrypt, String secret)
	{
		try
		{
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		}
		catch (Exception e)
		{
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}
}