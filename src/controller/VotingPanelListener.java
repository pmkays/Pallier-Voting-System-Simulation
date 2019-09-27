package controller;

import java.math.BigInteger;
import java.util.ArrayList;

import model.Voter;
import model.VotingEngine;

public class VotingPanelListener 
{
	private VotingEngine votingEngine;
	private String voterID;

	public VotingPanelListener(VotingEngine votingEngine) 
	{
		this.votingEngine = votingEngine;
	}
	
	public boolean setVoterID(String name) 
	{
		ArrayList<Voter> voters = votingEngine.getVoters();
		
		//check if the voterID exists, i.e. if they have voted before
		for(Voter voter: voters)
		{
			if(voter.getVotingId().equals(name))
			{
				return false;
			} 
			else
			{
				this.voterID = name;
			}
		}
		
		//if the voter is the first one to ever vote, then sets voterID automatically
		if(voters.isEmpty())
		{
			this.voterID = name;
		}
		return true; 
	}

	public void votingPanelEventOccurred(String message, BigInteger random) 
	{
		votingEngine.addVoter(voterID, message, random);
	}

}
