package ShamirAttack;

import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ShamirAttacker 
{
	private long keyLength;
	private ArrayList<BigInteger> publicKey;

	public ShamirAttacker(long keyLength,
			ArrayList<BigInteger> publicKey) 
	{
		this.keyLength = keyLength;
		this.publicKey = publicKey; 
	}
	
	public String attack(BigInteger cipherText)
	{
		String decodedText = null;
		ArrayList<Boolean> x = new ArrayList<Boolean>();
		BigInteger qCandidate = BigInteger.ZERO;
		for(int i=0;i<keyLength;i++)
		{
			if(qCandidate.compareTo(publicKey.get(i))==-1)
			{
				qCandidate = publicKey.get(i);
			}
		}
		BigInteger n = new BigInteger(((Long) keyLength).toString());
		BigInteger d = BigInteger.ONE.divide(n)
				.multiply(
						log2(n.multiply(n.multiply(qCandidate))));
		Long g = d.add(BigInteger.ONE).add(BigInteger.ONE).longValue();
		if(g<5)
		{
			g=(long) 5;
		}
		ArrayList<BigInteger> superIncreasing = findSuperIncreasingSequence(g);
		for(int i=0;i<superIncreasing.size();i++)
		{
			System.out.println(superIncreasing.get(i));
		}
		return decodedText;
	}
	private ArrayList<BigInteger> findSuperIncreasingSequence(Long g) 
	{
		ArrayList<BigInteger> superIncreasing = new ArrayList<BigInteger>();
		BigInteger elem = publicKey.get(0);
		for(int i=1;i<keyLength;i++)
		{
			if(elem.compareTo(publicKey.get(i))==1)
			{
				elem=publicKey.get(i);
			}
		}
		superIncreasing.add(elem);
		BigInteger sumOfAll = BigInteger.ZERO;
		for(int j=0;j<g-1;j++)
		{
			sumOfAll=sumOfAll.add(superIncreasing.get(j));
			elem = sumOfAll.multiply(sumOfAll);
			
			for(int i=1;i<keyLength;i++)
			{
				if(sumOfAll.compareTo(publicKey.get(i))==-1)
				{
					if(elem.compareTo(publicKey.get(i))==1)
					{
						elem=publicKey.get(i);
					}
				}

			}
			superIncreasing.add(elem);
		}
		return superIncreasing;
	}

	private BigInteger log2(BigInteger a)
	{
		BigInteger b = BigInteger.ONE;
		BigInteger counter = BigInteger.ZERO;
		while(b.compareTo(a)==-1)
		{
			b = b.multiply(BigInteger.ONE.add(BigInteger.ONE));
			counter = counter.add(BigInteger.ONE);
		}
		if(b.compareTo(a)==1)
		{
			counter = counter.subtract(BigInteger.ONE);
		}
		return counter;
	}
}
