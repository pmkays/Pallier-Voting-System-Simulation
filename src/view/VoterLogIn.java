package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import controller.VotingPanelListener;

public class VoterLogIn extends JPanel
{
	private VotingPanelListener listener;
	private JButton loginButton; 
	private JLabel voterIdLabel;
	private JTextField voterIdField;
	private JLabel voterPassLabel;
	private JPasswordField voterPassField;
	private MainFrame mainFrame;
	private VotingPanel votingPanel; 
	
	public VoterLogIn(MainFrame mainFrame) 
	{
		this.mainFrame = mainFrame;
		
		//sets up panel
		this.votingPanel = new VotingPanel(mainFrame);
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		Border innerBorder = BorderFactory.createTitledBorder("Voter Login");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		//instantiate components for panel
		loginButton = new JButton("Log in");
		voterIdLabel = new JLabel("Voter Unique ID: "); 
		voterIdField = new JTextField(15);
		voterPassLabel = new JLabel("Password: "); 
		voterPassField = new JPasswordField(15);
		
		organiseLayout();
		
		loginButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String id = voterIdField.getText();
				String password = String.valueOf(voterPassField.getPassword()); 
				if(listener != null)
				{
					String searchResult = listener.setVoterID(id, password);
					//checks if voter has voted before
					switch(searchResult)
					{
					case "password incorrect":
						JOptionPane.showMessageDialog(mainFrame,
						        "Voter: " + id + "'s password is incorrect","Error",
						        JOptionPane.ERROR_MESSAGE);	
						break;
					case "already voted":
						JOptionPane.showMessageDialog(mainFrame,
						        "Voter: " + id + " has already voted","Error",
						        JOptionPane.ERROR_MESSAGE);	
						break;
					case "pass":
						JOptionPane.showMessageDialog(mainFrame,
						        "Voter: " + id + " login successful. Please remember "
						        		+ "you can only vote once.", "Successful Login",
						        JOptionPane.INFORMATION_MESSAGE);	
						mainFrame.getVoterLogIn().setVisible(false);
						mainFrame.add(mainFrame.getVotingPanel(), BorderLayout.CENTER);
						votingPanel.setListener(listener);
						mainFrame.getVotingPanel().setVisible(true);
						break;	
					}
				}	
			}
		});
	}

	public void setListener(VotingPanelListener votingPanelListener) 
	{
		this.listener = votingPanelListener;
		
	}
	
	public void organiseLayout()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		//first row
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0; 
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(3,3,3,5);
		add(voterIdLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(voterIdField, gc);
		
		//next row
		gc.gridy++; 
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(3,3,3,5);
		add(voterPassLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(voterPassField, gc);
		
		//final row
		gc.gridy++;
		gc.weightx = 0;
		gc.weighty = 1;
		
		gc.gridx = 1;
		add(loginButton, gc);
	}

}
