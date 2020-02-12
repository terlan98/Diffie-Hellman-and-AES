import java.util.Random;

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
		
		System.out.println(privateKey + " " + publicKey);
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
