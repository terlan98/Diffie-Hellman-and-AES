import java.util.Random;

/**
 * Implements Diffie-Hellman Key Exchange algorithm.
 */
public class DHKeyExchanger
{
	private int p, alpha;
	private int publicKey, privateKey;
	private Random rnd = new Random();
	
	/**
	 * Creates an instance of DHKeyExchanger with the given prime number and generator.
	 * @param p - prime number
	 * @param alpha - generator
	 */
	public DHKeyExchanger(int p, int alpha)
	{
		this.alpha = alpha;
		this.p = p;
		
		privateKey = rnd.nextInt(p);
		publicKey =  (int) (Math.pow(alpha, privateKey) % p);
	}
	
	/**
	 * Calculates and returns the secret key. <br>
	 * This key should be the same for both parties after a successful key exchange.
	 * @param publicKey - public key of the other side
	 */
	public int getSecretKey(int publicKey)
	{
		return (int) (Math.pow(publicKey, privateKey) % p);
	}
	
	public int getPublicKey()
	{
		return publicKey;
	}

	public int getPrivateKey()
	{
		return privateKey;
	}

	public int getP()
	{
		return p;
	}

	public int getAlpha()
	{
		return alpha;
	}
}
