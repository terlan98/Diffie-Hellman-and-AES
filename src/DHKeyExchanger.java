import java.util.Random;

/**
 * 
 * <div style = "color: red"> TEST </div> 
 *
 */
public class DHKeyExchanger
{
	private long p, alpha;
	private long publicKey, privateKey;
	private Random rnd = new Random();
	
	public DHKeyExchanger(int p, int alpha)
	{
		this.alpha = alpha;
		this.p = p;
		
		privateKey = rnd.nextInt(p);
		publicKey = (long) (Math.pow(alpha, privateKey) % p);
		
		System.out.println("Private Key: " + privateKey + " Public Key: " + publicKey);
	}
	
	public long getSecretKey(long publicKey)
	{
		return (long) (Math.pow(publicKey, privateKey) % p);
	}
	
	public long getPublicKey()
	{
		return publicKey;
	}
	
}
