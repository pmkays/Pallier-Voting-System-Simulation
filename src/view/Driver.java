package view;

import javax.swing.SwingUtilities;

import model.VotingEngine;

public class Driver 
{

	public static void main(String[] args) 
	{
		//runs GUI on a separate thread
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() 
			{
				VotingEngine votingEngine = new VotingEngine();
				new MainFrame(votingEngine); 

			}
		}); 
	}

}

