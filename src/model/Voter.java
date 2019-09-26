package model;

import java.math.BigInteger;

public class Voter 
{
	private String votingID;
	private String votingMessagem; 
	private BigInteger votingNumberr;
	private BigInteger encryptedVoteC;
	
	public Voter(String votingID, String votingMessagem)
	{
		this.votingID = votingID;
		if (votingMessagem.equals("Alice"))
		{
			this.votingMessagem = "001000";
		}
		else
		{
			this.votingMessagem = "000001";
		}
		this.encryptedVoteC = new BigInteger("0");
		this.votingNumberr = new BigInteger("0");
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
		return this.votingMessagem+ " " + this.votingNumberr + " " + this.encryptedVoteC; 
	}
	

}
