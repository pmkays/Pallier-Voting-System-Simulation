package model;

import java.math.BigInteger;
import java.util.ArrayList;

public class VotingEngine 
{
	private VotingAuthority va;
	private VotingBooth vb;
	private CloudVotingServer vs;
	public VotingEngine()
	{
		va = new VotingAuthority();
		vb = new VotingBooth(va);
		vs = new CloudVotingServer(va, vb);
	} 
	
	public void addVoter(String voterID, String voterPass, String message, BigInteger random)
	{
		Voter voter = new Voter(voterID,voterPass, message, random);
		vb.addVoter(voter);
		vb.encryptIndividualVote(vs, voter);
	}
	 
	public void keyGeneration()
	{
		//hard-coded values as derived from A2Q1
		va.publicKeyGenerator("89", "53", "8537"); 
		va.privateKeyGenerator();
	}
	
	public ArrayList<Voter> getVoters() 
	{
		return vb.getVoters();
	}
	
	public String getWinners()
	{
		return va.getWinner();
	}

	public void homomorphicCalculation() 
	{
		vs.homomorphicCalculation();	
	}

	public void setVoterAmount(BigInteger amount) 
	{
		String maxBinary = ConversionUtilities.convertToBinaryInitial(amount);
		Voter.setMaxBinary(maxBinary) ;
		
	}
}
