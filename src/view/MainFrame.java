package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.VAPanelListener;
import controller.VotingPanelListener;
import model.VotingEngine;


public class MainFrame extends JFrame
{
	private VotingEngine votingEngine;
	private VotingPanel votingPanel; 
	private VoterLogIn voterLogIn;
	private VALogIn vaLogIn;
	private VAPanel vaPanel;
	private ChoosePanel choosePanel;
	private JButton terminateButton;
	public MainFrame(VotingEngine votingEngine)
	{
		super("Pallier Voting Engine");
		this.votingEngine = votingEngine;
		
		//mainframe setup
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize (new Dimension(400, 750));
		setSize(500, 800);
		setVisible(true);
		setLayout(new BorderLayout());
		
		//instantiate all components that will be used
		vaPanel = new VAPanel(MainFrame.this);
		votingPanel = new VotingPanel(MainFrame.this);
		voterLogIn = new VoterLogIn(MainFrame.this);
		choosePanel = new ChoosePanel(MainFrame.this);
		vaLogIn = new VALogIn(MainFrame.this);
		terminateButton = new JButton("Terminate Session");
		
		//a sticky terminate button to ensure that there is user freedom for exiting
		terminateButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//takes them back to the choose panel
				terminateSession();			
			}
			
		});
		
		//set up listeners that will be used 
		
		VotingPanelListener votingPanelListener = new VotingPanelListener(votingEngine);
		voterLogIn.setListener(votingPanelListener);
		votingPanel.setListener(votingPanelListener);
		
		VAPanelListener vaPanelListener= new VAPanelListener(votingEngine);
		vaPanel.setListener(vaPanelListener, votingPanelListener);

		add(terminateButton, BorderLayout.NORTH);
		add(choosePanel, BorderLayout.CENTER);
	}
	
	public JPanel getVoterLogIn()
	{
		return voterLogIn;
	}

	public VotingEngine getVotingEngine() 
	{
		return votingEngine;
	}

	public VotingPanel getVotingPanel() 
	{
		return votingPanel;
	}

	public VAPanel getVaPanel() 
	{
		return vaPanel;
	}

	public ChoosePanel getChoosePanel() 
	{
		return choosePanel;
	}
	
	public VALogIn getVALogIn()
	{
		return vaLogIn;
	}
	
	public void terminateSession()
	{
		vaLogIn.setVisible(false);
		vaPanel.setVisible(false);
		votingPanel.setVisible(false);
		voterLogIn.setVisible(false);
		choosePanel.setVisible(true);
	}

}
