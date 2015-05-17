package Cryptosystem;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class MHGenerator {

	public MHGenerator(long keyLength) {
		this.keyLength=keyLength;
		publicKey=new ArrayList<BigInteger>();
		this.q=BigInteger.ZERO;
		this.r=BigInteger.ZERO;
	}

	ArrayList<BigInteger> publicKey;
	ArrayList<BigInteger> privateKey;
	long keyLength;
	BigInteger q;
	BigInteger r;	
	
	public void generateKey()
	{
		ArrayList<BigInteger> superIncreasing=new ArrayList<BigInteger>();
		Random randomNumber = new Random();
		Long number = randomNumber.nextLong()%10;
		number = Math.abs(number);
		BigInteger elem = new BigInteger(number.toString());
		superIncreasing.add(elem);
		for(int i=1;i<keyLength;i++)
		{
			number = randomNumber.nextLong()%10;
			number = Math.abs(number);
			BigInteger elem2 = new BigInteger(number.toString());
			BigInteger elem3 = elem.add(elem.add(elem2));
			superIncreasing.add(elem3);
			elem = elem3;
		}
		privateKey=superIncreasing;
		number=randomNumber.nextLong();
		number = Math.abs(number);
		q = new BigInteger(number.toString());
		q = q.add(elem.add(elem));
		number = (long) 3;
		r = new BigInteger(number.toString());
		while(!(r.gcd(q).equals(BigInteger.ONE)))
		{
			r = r.add(BigInteger.ONE);				
		}
		for(int i=0;i<keyLength;i++)
		{
			elem=(r.multiply(superIncreasing.get(i))).mod(q);
			publicKey.add(elem);
		}
		
	}

	public ArrayList<BigInteger> getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(ArrayList<BigInteger> privateKey) {
		this.privateKey = privateKey;
	}

	public long getKeyLength() {
		return keyLength;
	}

	public void setKeyLength(long keyLength) {
		this.keyLength = keyLength;
	}

	public ArrayList<BigInteger> getPublicKey() {
		return publicKey;
	}
	
	public BigInteger getQ() {
		return q;
	}

	public void setQ(BigInteger q) {
		this.q = q;
	}

	public BigInteger getR() {
		return r;
	}

	public void setR(BigInteger r) {
		this.r = r;
	}

}
