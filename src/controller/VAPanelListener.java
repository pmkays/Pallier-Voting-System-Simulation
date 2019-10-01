package controller;

import model.VotingEngine;

public class VAPanelListener 
{
	private VotingEngine votingEngine;

	public VAPanelListener(VotingEngine votingEngine) 
	{
		this.votingEngine = votingEngine; 
	}

	public void vaPanelkeyGeneration() 
	{
		votingEngine.keyGeneration();
	}

	public void vaPanelResults()  
	{
		votingEngine.homomorphicCalculation();
	}
	
	public String getWinners()
	{
		return votingEngine.getWinners();
	}

}
