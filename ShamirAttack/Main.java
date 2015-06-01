package ShamirAttack;

import java.math.BigInteger;

import Cryptosystem.MHEncryption;
import Cryptosystem.MHGenerator;

public class Main 
{

	public static void main(String[] args)
	{
		String message = "Attack on dawn";
		long keyLength=(long) message.length();
		keyLength*=8;
		MHGenerator gen = new MHGenerator(keyLength);
		gen.generateKey();
		MHEncryption enc = 
				new MHEncryption(keyLength,
						gen.getPublicKey());
		System.out.println(message);
		BigInteger cipherText=enc.encryptMessage(message);
		System.out.println(cipherText);
		ShamirAttacker shamir = new ShamirAttacker(keyLength,
				gen.getPublicKey());
		String receivedText = shamir.attack(cipherText);
		System.out.println(receivedText);
	}

}
