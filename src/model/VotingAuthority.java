package model;
import java.math.BigInteger;

public class VotingAuthority 
{
	BigInteger p; 
	BigInteger q; 

	BigInteger n; 
	BigInteger g;

	BigInteger lambda;
	BigInteger u; 
	BigInteger k; 
	BigInteger mu; 
	
	private int aliceVotes;
	private int bobVotes;
	
	public void publicKeyGenerator(String pStr, String qStr, String gStr)
	{
		p = new BigInteger(pStr); 
		q = new BigInteger(qStr);
		g = new BigInteger(gStr);
		n = p.multiply(q); 
		System.out.println("Public Key (n,g) = (" + n + ", " + g + ") is sent by "
				+ "Voting Authority to Cloud Voting Server."); 
		
	}
	
	public void privateKeyGenerator()
	{
		BigInteger p_minus_1 = p.subtract(new BigInteger("1")); 
		BigInteger q_minus_1 = q.subtract(new BigInteger("1"));
		
		lambda = p_minus_1.multiply(q_minus_1.divide(p_minus_1.gcd(q_minus_1))); 

		
		u = g.modPow(lambda, n.multiply(n));
		BigInteger L_of_u = u.subtract(new BigInteger("1")).divide(n); 
		
		k = L_of_u; 
		
		mu = k.modInverse(n);
		
		System.out.println("Voting Authority stores their Private Key (Lambda, Mu) "
				+ "= ("+ lambda + ", "+ mu + ") for later decryption"); 
	}
	
	public void countVotes(BigInteger C)
	{
		
		System.out.println("Voting Authority decrypts C:" + C);
		System.out.println("Voting Authority decrypting... \n");
		
		//decryption process as detailed out in the formula
		BigInteger m; 
		BigInteger u1 = C.modPow(lambda, n.multiply(n)); 
		BigInteger L_of_u1 = u1.subtract(new BigInteger("1")).divide(n);  
		m = L_of_u1.multiply(mu).mod(n); 

		String binary = ConversionUtilities.convertToBinary(m);

		//splits up the binary string to determine how many votes each candidate has
		int halfOfBinary = binary.length()/2; 
		String firstHalf = binary.substring(0, halfOfBinary);
		String secondHalf = binary.substring(halfOfBinary);
		
		aliceVotes = ConversionUtilities.convertToDecimal(firstHalf);
		bobVotes = ConversionUtilities.convertToDecimal(secondHalf);
		
		System.out.println("Alice: " + aliceVotes + " Bob: " + bobVotes);
		if(aliceVotes > bobVotes)
		{
			System.out.println("Alice is the winner");
		}
		else
		{
			System.out.println("Bob is the winner");
		}
		
	}
	
	public String getWinner()
	{
		//retrieves the results for later GUI message
		String totalVotes = "Alice: " + aliceVotes + " Bob: " + bobVotes;
		if(aliceVotes > bobVotes)
		{
			return (totalVotes + "\n Alice is the winner!" );
		}
		else
		{
			return (totalVotes + "\n Bob is the winner!" );
		}
	}
	
	public BigInteger getP() 
	{
		return p;
	}

	public BigInteger getQ() 
	{
		return q;
	}

	public BigInteger getN() 
	{
		return n;
	}

	public BigInteger getG() 
	{
		return g;
	}

	public BigInteger getLambda() 
	{
		return lambda;
	}

	public BigInteger getU() 
	{
		return u;
	}

	public BigInteger getK() 
	{
		return k;
	}

	public BigInteger getMu() 
	{
		return mu;
	}
	
	
	
	
	
	

}
