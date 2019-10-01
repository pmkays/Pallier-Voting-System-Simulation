package model;

import java.math.BigInteger;

public class Voter 
{
	private String votingID;
	private String votingMessagem; 
	private BigInteger votingNumberr;
	private BigInteger encryptedVoteC;
	
	public Voter(String votingID, String votingMessagem, BigInteger votingNumberr) 
	{
		this.votingID = votingID;
		this.votingNumberr = votingNumberr;
		
		//converts text-based votes to binary digits
		if (votingMessagem.equals("Alice"))
		{
			this.votingMessagem = "00010000";
		}
		else
		{
			this.votingMessagem = "00000001";
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
	

}
