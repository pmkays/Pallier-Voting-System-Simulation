package model;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class VotingAuthority 
{
//	BigInteger m; 
	BigInteger p; 
	BigInteger q; 

	BigInteger n; 
	BigInteger g;

	BigInteger lambda;
	BigInteger u; 
	BigInteger k; 
	BigInteger mu; 
//	BigInteger r; 
//	BigInteger C; 
//	BigInteger M;
	
	public void publicKeyGenerator(String pStr, String qStr, String gStr)
	{
		p = new BigInteger(pStr); 
		q = new BigInteger(qStr);
		g = new BigInteger(gStr);
		n = p.multiply(q); 
		System.out.println("n = " + n); 
		System.out.println("Public Key (n,g) = (" + n + ", " + g + ") is sent to ALICE."); 
		
	}
	
	public void privateKeyGenerator()
	{
		BigInteger p_minus_1 = p.subtract(new BigInteger("1")); 
		BigInteger q_minus_1 = q.subtract(new BigInteger("1"));
		
		lambda = p_minus_1.multiply(q_minus_1.divide(p_minus_1.gcd(q_minus_1))); 
		System.out.println("Lambda = " + lambda);
		
		u = g.modPow(lambda, n.multiply(n));
		BigInteger L_of_u = u.subtract(new BigInteger("1")).divide(n); 
		
		k = L_of_u; 
		System.out.println("K = "+k);
		
		mu = k.modInverse(n);
		System.out.println("Mu = "+mu);
		
		System.out.println("BOB's Private Key (Lambda, Mu) = ("+ lambda + ", "+ mu + ")"); 
		System.out.println("ALICE Encrypts the m"); 
	}
	
//	public void encryptMessage(String mStr, String rStr)
//	{
//		m = new BigInteger(mStr);
//		r = new BigInteger(rStr);
//		BigInteger temp1 = g.modPow(m, n.multiply(n)); 
//		BigInteger temp2 = r.modPow(n, n.multiply(n));
//		C = temp1.multiply(temp2).mod(n.multiply(n));
//		System.out.println("Message encrypted!"); 
//		System.out.println("Ciphertext, C = " + C); 
//
//	}
//	
//	public void decryptMessage()
//	{
//		System.out.println("BOB decrypts the Original Message.");
//		BigInteger u1 = C.modPow(lambda, n.multiply(n)); 
//		BigInteger L_of_u1 = u1.subtract(new BigInteger("1")).divide(n); 
//		M = L_of_u1.multiply(mu).mod(n); 
//		System.out.println("Extracted Message, M = "+ M);
//	}
	
	public static void main(String[] args)
	{
		VotingAuthority va = new VotingAuthority();
		VotingBooth vb = new VotingBooth(va);
		CloudVotingServer vs = new CloudVotingServer(va, vb);
		va.publicKeyGenerator("89", "53", "8537");
		va.privateKeyGenerator();
		
		Voter voter1 = new Voter("Voter1", "Alice");
		Voter voter2 = new Voter("Voter2","Alice");
		Voter voter3 = new Voter("Voter3","Alice");
		Voter voter4 = new Voter("Voter4","Bob");
		Voter voter5 = new Voter("Voter5","Bob");
		Voter voter6 = new Voter("Voter6","Bob");
		Voter voter7 = new Voter("Voter7","Bob");
		
		vb.addVoter(voter1);
		vb.addVoter(voter2);
		vb.addVoter(voter3);
		vb.addVoter(voter4);
		vb.addVoter(voter5);
		vb.addVoter(voter6);
		vb.addVoter(voter7);
		
		voter1.setVotingNumberr(new BigInteger("71"));
		voter2.setVotingNumberr(new BigInteger("72"));
		voter3.setVotingNumberr(new BigInteger("73"));
		voter4.setVotingNumberr(new BigInteger("74"));
		voter5.setVotingNumberr(new BigInteger("75"));
		voter6.setVotingNumberr(new BigInteger("76"));
		voter7.setVotingNumberr(new BigInteger("77"));
		
		vb.encryptIndividualVote();
		vs.homomorphicCalculation();		
	}
	
	public BigInteger getP() {
		return p;
	}

	public BigInteger getQ() {
		return q;
	}

	public BigInteger getN() {
		return n;
	}

	public BigInteger getG() {
		return g;
	}

	public BigInteger getLambda() {
		return lambda;
	}

	public BigInteger getU() {
		return u;
	}

	public BigInteger getK() {
		return k;
	}

	public BigInteger getMu() {
		return mu;
	}
	
	
	
	
	
	

}
