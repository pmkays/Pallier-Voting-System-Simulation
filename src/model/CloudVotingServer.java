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
		System.out.println("\nCloud Voting Server calculating...");
		BigInteger totalC = new BigInteger("1");
		BigInteger C = new BigInteger("1");
		BigInteger n = va.getN(); 
		
		//multiplies encrypted vote of users with each other
		for(BigInteger encryptedVote : encryptedVotes)	
		{
			C = C.multiply(encryptedVote);
		}
		
		totalC = C.mod(n.multiply(n));
		System.out.println("Cloud Voting Server sends C: " + totalC + " to Voting Authority for decryption \n");
		
		//sends to voting authority for decryption
		va.countVotes(totalC);
	}


	public void addEncryptedVotes(BigInteger c) 
	{
		encryptedVotes.add(c);
		
	}
	

}
