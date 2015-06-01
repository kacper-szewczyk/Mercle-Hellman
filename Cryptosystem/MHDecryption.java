package Cryptosystem;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;


public class MHDecryption {

	public MHDecryption(long keyLength, 
			ArrayList<BigInteger> privateKey, 
			BigInteger q,
			BigInteger r) 
	{
		this.keyLength=keyLength;
		this.privateKey=privateKey;
		this.q=q;
		this.r=r;
	}
	private long keyLength;
	private BigInteger q;
	private BigInteger r;
	private ArrayList<BigInteger> privateKey;
	
	public String decryptMessage(BigInteger cipherText)
	{
		String plainText = null;
		byte[] plainElem = new byte[(int) this.keyLength/8];
		BigInteger ReverseR = findReverseR();
		BigInteger cipherElem = 
				ReverseR.multiply(cipherText).mod(q); 
		int j=plainElem.length-1;
		for (int i = (int) (this.keyLength-1); i >=0 ; i--) 
		{
			if(cipherElem.compareTo(privateKey.get(i))>=0)
			{
				plainElem[j] += (Math.pow(2,i%8));
				cipherElem = 
						cipherElem.subtract(
								privateKey.get(i));
			}
			if(i%8==0)
			{
				j--;
			}
		}
		
		try {
			plainText=new String(plainElem,"ASCII");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return plainText;
	}
	
	public BigInteger findReverseR()
	{
		BigInteger u = BigInteger.ONE;
		BigInteger x = BigInteger.ZERO;
		BigInteger w = r;
		BigInteger z = q;
		BigInteger buffer;
		BigInteger q2;
		while(!w.equals(BigInteger.ZERO))
		{
			if(w.compareTo(z)==-1)
			{
				buffer=u;
				u=x;
				x=buffer;
				buffer=w;
				w=z;
				z=buffer;
			}
			q2=w.divide(z);
			u=u.subtract(q2.multiply(x));
			w=w.subtract(q2.multiply(z));
		}
		if(!z.equals(BigInteger.ONE))
		{
			System.out.println("Couldn'y find r^(-1)");
			return null;
		}
		if(x.compareTo(BigInteger.ZERO)==-1)
		{
			x=x.add(q);
		}
		return x;
	}
	
}
