
public class MessageExchanger
{
	public static void main(String[] args)
	{
		String enc = AES.encrypt("ADA is the best", "key");
		String dec = AES.decrypt(enc, "key");
		
		System.out.println(enc);
		System.out.println(dec);
		System.out.println("IV: " + AES.getInitVector());
	}
}
