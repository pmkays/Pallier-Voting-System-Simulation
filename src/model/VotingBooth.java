package model;

import java.math.BigInteger;
import java.util.ArrayList;

public class VotingBooth 
{
	private ArrayList<Voter> voters = new ArrayList<Voter>();
	private VotingAuthority va;
	private CloudVotingServer vs;

	
	public VotingBooth(VotingAuthority va)
	{
//		this.m = m;
//		this.C = C;
//		this.M = M;
		this.va = va;
		vs = new CloudVotingServer(va, this);
	}
	
	public void addVoter(Voter voter)
	{
		voters.add(voter);
	}
	
	public void encryptIndividualVote()
	{
		BigInteger g = va.getG();
		BigInteger n = va.getN();
		BigInteger m;  
		BigInteger C; 

		for(Voter voter : voters)
		{
			
			//gets the binary representation of each candidate's vote and converts to decimal in String form
			String candidateMessage = voter.getVotingMessagem();
			String convertedMessage = Integer.toString(vs.convertToDecimal(candidateMessage));		
			m = new BigInteger(convertedMessage);
			
			BigInteger temp1 = g.modPow(m, n.multiply(n)); 
			BigInteger temp2 = voter.getVotingNumberr().modPow(n, n.multiply(n));
			C = temp1.multiply(temp2).mod(n.multiply(n));
			
			voter.setEncryptedVoteC(C);
			vs.addEncryptedVotes(C);
			
			System.out.println(voter); 
		}
	}

	public ArrayList<Voter> getVoters() 
	{
		return voters;
	}

}
