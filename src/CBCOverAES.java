import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

import javax.crypto.spec.IvParameterSpec;

/**
 * Performs Cipher Block Chaining mode AES encryption and decryption.
 */
public class CBCOverAES
{
	private static SecureRandom rnd = new SecureRandom();
	/**A randomly-generated initial vector */
	private static byte[] iv = new IvParameterSpec(rnd.generateSeed(16)).getIV();
	private String key;
	
	/**
	 * Creates an instance of CBCOverAES with the given key.
	 * @param key
	 */
	public CBCOverAES(String key)
	{
		this.key = key;
	}
	
	/**
	 * Encrypts the given String.
	 * @param message - String to be encrypted
	 * @return Encrypted byte array
	 * @throws UnsupportedEncodingException
	 */
	public byte[] encrypt(String message) throws UnsupportedEncodingException
	{
		byte[] plaintext = message.getBytes("UTF-8");
		ArrayList<Byte> ciphertext = new ArrayList<Byte>();
		
		byte[] ivCopy = iv;
		byte[] resultBytes = null;
		
		if(plaintext.length < 16) // padding for input
		{
			plaintext = addPadding(plaintext);
		}
		
		for (int i = 0; i < plaintext.length; i += 16)
		{
			byte[] block = new byte[16];
			byte[] xorText = new byte[16];
			
			for (int j = i; j < i + 16; j++) // Create a block
			{
				if (j >= plaintext.length)
				{
					block[j % 16] = 0;
				}
				else
				{
					block[j % 16] = plaintext[j];
				}
			}
			
			if(block.length < 16) // padding for block
			{
				block = addPadding(block);
			}
			
			xorText = xorArrays(block, ivCopy); // XOR with the initial vector
			
			resultBytes = AES.encrypt(xorText, key);
			
			for (int j = 0; j < resultBytes.length; j++) // append the obtained result to ciphertext
			{
				ciphertext.add(resultBytes[j]);
			}
			
			ivCopy = resultBytes;
		}
		
		return toPrimitiveBytes(ciphertext.toArray());
	}
	
	/**
	 * Decrypts the given byte array.
	 * @param message - byte array to be encrypted
	 * @return Decrypted byte array
	 * @throws UnsupportedEncodingException
	 */
	public byte[] decrypt(byte[] message) throws UnsupportedEncodingException
	{
		ArrayList<Byte> plaintext = new ArrayList<Byte>();
		byte[] ciphertext = message;
		byte[] ivCopy = iv;
		byte[] xorText = null;
		
		for (int i = 0; i < ciphertext.length; i += 16)
		{
			byte[] block = new byte[16];
			byte[] resultBytes;
			xorText = new byte[16];

			for (int j = i; j < i + 16; j++) // Create a block
			{
				if (j >= ciphertext.length)
				{
					block[j % 16] = 0;
				}
				else 
				{
					block[j % 16] = ciphertext[j];
				}
			}
			
			resultBytes = AES.decrypt(block, key);
			
			xorText = xorArrays(resultBytes, ivCopy); // XOR with the initial vector
						
			for (int j = 0; j < xorText.length; j++) // append the obtained result to plaintext
			{
				plaintext.add(xorText[j]);
			}
			
			ivCopy = block;
		}
		
		return toPrimitiveBytes(plaintext.toArray());
	}
	
	/**
	 * Perform logical XOR operation on the given byte arrays.
	 * @param a - byte array #1
	 * @param b - byte array #2
	 * @return A new array containing the result of the XOR operation.
	 */
	private byte[] xorArrays(byte[] a, byte[] b)
	{
		byte[] c = new byte[a.length];
		
		for (int i = 0; i < a.length; i++)
		{
			byte bt = a[i];
			byte v = b[i];
						
			c[i] = (byte) (bt ^ v);
		}
		
		return c;
	}
	
	/**
	 * Appends zeros to the end of the given array.
	 * @param array
	 * @return
	 */
	private byte[] addPadding(byte[] array)
	{
		byte[] padded = new byte[16];
		
		for (int i = 0; i < 16; i++)
		{
			if(i >= array.length)
			{
				padded[i] = 0;
			}
			else
			{
				padded[i] = array[i];
			}
		}
		
		return padded;
	}
	
	/**
	 * Converts Object[] (which is actually expected to be Byte[]) to byte[].
	 * @param arr - array of Bytes
	 */
	private byte[] toPrimitiveBytes(Object[] arr)
	{	
		byte[] result = new byte[arr.length];
		
		for (int i = 0; i < arr.length; i++)
		{
			result[i] = (byte) arr[i];
		}
		
		return result;
	}
	
	/**
	 * Returns the Initial Vector used for the Cipher Block Chaining algorithm.
	 */
	public static String getInitVector()
	{
		return Arrays.toString(iv);
	}
}
