import java.util.concurrent.ThreadLocalRandom;

/**
 * Implements Diffie-Hellman Key Exchange algorithm.
 */
public class DHKeyExchanger
{
	private long p, alpha;
	private long publicKey, privateKey;
	
	/**
	 * Creates an instance of DHKeyExchanger with the given prime number and generator.
	 * @param p - prime number
	 * @param alpha - generator
	 */
	public DHKeyExchanger(long p, long alpha)
	{
		this.alpha = alpha;
		this.p = p;
		
		privateKey = ThreadLocalRandom.current().nextLong(p);
		publicKey =  (long) (Math.pow(alpha, privateKey)) % p;
	}
	
	/**
	 * Calculates and returns the secret key. <br>
	 * This key should be the same for both parties after a successful key exchange.
	 * @param publicKey - public key of the other side
	 */
	public long getSecretKey(long publicKey)
	{
		return (long) (Math.pow(publicKey, privateKey)) % p;
	}
	
	public long getPublicKey()
	{
		return publicKey;
	}

	public long getPrivateKey()
	{
		return privateKey;
	}

	public long getP()
	{
		return p;
	}

	public long getAlpha()
	{
		return alpha;
	}
}
