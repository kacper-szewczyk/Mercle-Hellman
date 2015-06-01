package Cryptosystem;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;

public class MHEncryption 
{
	public MHEncryption(long keyLength, 
			ArrayList<BigInteger> publicKey) 
	{
		this.keyLength=keyLength;
		this.publicKey=publicKey;

	}
	ArrayList<BigInteger> publicKey;
	private long keyLength;
	
	public BigInteger encryptMessage(String plainText)
	{
		BigInteger cipherText = BigInteger.ZERO;
		byte[] array = null;
		try {
			array = plainText.getBytes("ASCII");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		BigInteger devider=BigInteger.ONE;
		devider=devider.add(BigInteger.ONE);
		for (int i = 0; i < array.length; i++) 
		{
			Byte temp = array[i];
			byte bitAsker = 1;
			for(int j = 0;j < 8;j++)
			{
				if((temp&bitAsker) == bitAsker)
				{
					cipherText =
							cipherText.add(
									publicKey.get((int)(i*8+j)));
				}
				bitAsker*=2;
			}
		}

		return cipherText;
	}
}
