package model;

import java.math.BigInteger;

public class Voter 
{
	private String votingID;
	private String votingMessagem; 
	private String voterPass; 
	private BigInteger votingNumberr;
	private BigInteger encryptedVoteC;
	private static String maxBinary = "";
	
	public Voter(String votingID, String votingMessagem, String voterPass, BigInteger votingNumberr) 
	{
		this.votingID = votingID;
		this.votingNumberr = votingNumberr;
		this.voterPass = voterPass;
		int index = maxBinary.length()/2;
		
		//converts text-based votes from user input to binary digits
		if (votingMessagem.equals("Alice"))
		{
			//inserts a 1 in the middle of the string
			this.votingMessagem = maxBinary.substring(0, index-1) + "1" + maxBinary.substring(index);
		}
		else
		{
			//inserts a 1 at the end of the string for Bob
			this.votingMessagem =  maxBinary.substring(0, maxBinary.length()-1) + "1";
		}
		this.encryptedVoteC = new BigInteger("0");
	} 
	
	public String getVotingId() 
	{
		return votingID;
	}
	
	public void setVotingId(String votingID)
	{
		this.votingID = votingID;
	}

	public String getVotingMessagem() 
	{
		return votingMessagem;
	}

	public void setVotingMessagem(String votingMessagem) 
	{
		this.votingMessagem = votingMessagem;
	}

	public BigInteger getEncryptedVoteC() 
	{
		return encryptedVoteC;
	}

	public void setEncryptedVoteC(BigInteger c) 
	{
		this.encryptedVoteC = c;
	}
	
	public BigInteger getVotingNumberr()  
	{
		return votingNumberr;
	}

	public void setVotingNumberr(BigInteger r) 
	{
		this.votingNumberr = r;
	}
	
	public String toString()
	{
		return String.format("VotingID: %s, Vote(binary): %s, r: %d, C: %d", 
				this.votingID, this.votingMessagem, this.votingNumberr, this.encryptedVoteC);
	}

	public String getVoterPass() 
	{
		// TODO Auto-generated method stub
		return this.voterPass;
	}
	
	public static void setMaxBinary(String max)
	{
		maxBinary = max;
	}
	

}
