package controller;

import java.math.BigInteger;
import java.util.ArrayList;

import model.ConversionUtilities;
import model.Voter;
import model.VotingEngine;

public class VotingPanelListener 
{
	private VotingEngine votingEngine;
	private String voterID;
	private String voterPass;
	private int maxVotes;
	private int voteCount = 0;

	public VotingPanelListener(VotingEngine votingEngine) 
	{
		this.votingEngine = votingEngine;
	}
	
	public String setVoterID(String name, String pass) 
	{
		ArrayList<Voter> voters = votingEngine.getVoters();
		String toReturn = "";
		
		//check if the voterID exists, i.e. if they have voted before
		for(Voter voter: voters)
		{
			if(voter.getVotingId().equals(name))
			{
				if(voter.getVoterPass().equals(pass))
				{
					return "already voted";
					
				}
				else
				{
					return "password incorrect"; 
				}	
			}
			else
			{
				this.voterID = name;
				this.voterPass = pass; 
			}
		}
		
		//if the voter is the first one to ever vote, then sets voterID and password automatically
		if(voters.isEmpty())
		{
			this.voterID = name;
			this.voterPass = pass;
		}
		return "pass";
	}

	public boolean votingPanelEventOccurred(String message, BigInteger random) 
	{
		voteCount++; 
		
		if(voteCount > maxVotes)
		{
			return false;
		}
		votingEngine.addVoter(voterID, message, voterPass, random);
		return true; 
	}
	
	public void setMaxVotes(BigInteger maxVotes)
	{
		this.maxVotes = maxVotes.intValueExact();
	}

}
