import java.util.Random;

/**
 * 
 * <div style = "color: red"> TEST </div> 
 *
 */
public class DHKeyExchanger
{
	private int p, alpha;
	private int publicKey, privateKey;
	private Random rnd = new Random();
	
	public DHKeyExchanger(int p, int alpha)
	{
		this.alpha = alpha;
		this.p = p;
		
		privateKey = rnd.nextInt(p);
		publicKey = (int) (Math.pow(alpha, privateKey) % p);
		
		System.out.println("Private Key: " + privateKey + " Public Key: " + publicKey);
	}
	
	public int getSecretKey(int publicKey)
	{
		return (int) (Math.pow(publicKey, privateKey) % p);
	}
	
	public int getPublicKey()
	{
		return publicKey;
	}
	
}
