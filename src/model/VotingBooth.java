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
		this.va = va;
	} 
	
	public void addVoter(Voter voter) 
	{
		voters.add(voter);
	}
	
	public void encryptIndividualVote(CloudVotingServer vs, Voter voter)
	{
//		vs = new CloudVotingServer(va, this);
		
		BigInteger g = va.getG();
		BigInteger n = va.getN();
		BigInteger m;  
		BigInteger C; 
		
		System.out.println("\nVoting Booth encrypting votes...");
		
		System.out.println("This is the most recent vote: ");
		
		//gets the binary representation of each candidate's vote and converts to decimal
		String candidateMessage = voter.getVotingMessagem();
		String convertedMessage = Integer.toString(ConversionUtilities.convertToDecimal(candidateMessage));		
		m = new BigInteger(convertedMessage);
		
		//encryption process as detailed out by equations
		BigInteger temp1 = g.modPow(m, n.multiply(n)); 
		BigInteger temp2 = voter.getVotingNumberr().modPow(n, n.multiply(n));
		C = temp1.multiply(temp2).mod(n.multiply(n));
		
		voter.setEncryptedVoteC(C);
		
		/*adds encrypted votes to voting server's array of encrypted votes for 
		later homomorphic calculations*/
		vs.addEncryptedVotes(C);
		
		System.out.println(voter); 
		}
		
//		vs.homomorphicCalculation();	
	

	public ArrayList<Voter> getVoters() 
	{
		return voters;
	}

}
