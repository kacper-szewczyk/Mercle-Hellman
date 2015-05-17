package Cryptosystem;

import java.math.BigInteger;

public class MHMaintenance 
{

	public MHMaintenance() 
	{
	}
	
	public void Test(String message)
	{
		long keyLength=(long) message.length();
		keyLength*=8;
		MHGenerator gen = new MHGenerator(keyLength);
		gen.generateKey();
		MHEncryption enc = 
				new MHEncryption(keyLength,
						gen.getPublicKey());
		MHDecryption dec = 
				new MHDecryption(keyLength,
						gen.getPrivateKey(),
						gen.getQ(), gen.getR());
		System.out.println(message);
		BigInteger cipherText=enc.encryptMessage(message);
		System.out.println(cipherText);
		String receivedText=dec.decryptMessage(cipherText);
		System.out.println(receivedText);
	}

	

}
