package model;

import java.math.BigInteger;
import java.util.ArrayList;

public class CloudVotingServer 
{
	ArrayList<BigInteger> encryptedVotes; 
	private VotingAuthority va;
	private VotingBooth vb;
	
	public CloudVotingServer(VotingAuthority va, VotingBooth vb)
	{
		this.va = va;
		this.vb = vb;
		encryptedVotes = new ArrayList<BigInteger>(); 
	}
	
	
	public void homomorphicCalculation()
	{ 
		BigInteger totalC = new BigInteger("1");
		BigInteger C = new BigInteger("1");
		ArrayList<Voter> voters = vb.getVoters();
		BigInteger n = va.getN();
		for(Voter voter : voters)	
		{
			C = C.multiply(voter.getEncryptedVoteC());
			System.out.println();
			System.out.println(voter);
			System.out.println(C);
		}
		
		totalC = C.mod(n.multiply(n));
		System.out.println("C = " + totalC);
		countVotes(totalC);
	}
	
	public void countVotes(BigInteger C)
	{
		BigInteger lambda = va.getLambda();
		BigInteger n = va.getN();
		BigInteger mu = va.getMu();
		BigInteger M;
		
		System.out.println(C);
		System.out.println("BOB decrypts the Original Message.");
		BigInteger u1 = C.modPow(lambda, n.multiply(n)); 
		System.out.println("Inside of L(u)" + u1);
		BigInteger L_of_u1 = u1.subtract(new BigInteger("1")).divide(n); 
		System.out.println("L(u)" + L_of_u1);
		M = L_of_u1.multiply(mu).mod(n); 
		System.out.println("Extracted Message, M = "+ M);
		String binary = this.convertToBinary(M);
		System.out.println(binary);
		
		int halfOfBinary = binary.length()/2; 
		String firstHalf = binary.substring(0, halfOfBinary);
		System.out.println(firstHalf);
		String secondHalf = binary.substring(halfOfBinary);
		System.out.println(secondHalf);
		
		int aliceVotes = this.convertToDecimal(firstHalf);
		int bobVotes = this.convertToDecimal( secondHalf);
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
	
	public String convertToBinary(BigInteger decimal)
	{
		String binary =  Integer.toString(decimal.intValueExact(),2);
		String returnedBinary;
		if(binary.length()%2!= 0)
		{
			returnedBinary = "0" + binary;
		}
		else
		{
			returnedBinary = binary;
		}
		return returnedBinary;
	}
	
	public int convertToDecimal(String binary)
	{
		//returns decimal number as a string for easy conversion into BigInteger
		return Integer.parseInt(binary,2);
	}

	public void addEncryptedVotes(BigInteger c) 
	{
		encryptedVotes.add(c);
		
	}
	

}
