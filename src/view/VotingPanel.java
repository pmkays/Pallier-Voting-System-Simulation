package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.*;
import javax.swing.border.Border;

import controller.VotingPanelListener;

public class VotingPanel extends JPanel
{
	private VotingPanelListener listener;
	private JButton voteButton;
	private JLabel randomNumberLabel;
	private JTextField randomNumberField;
	private DefaultComboBoxModel<String> candidatesModel;
	private JComboBox<String> candidatesCombo;
	private JLabel candidatesLabel;
	 
	private MainFrame mainFrame; 
	
	public VotingPanel(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
		
		//panel set up
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		Border innerBorder = BorderFactory.createTitledBorder("Voting Panel");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		//instantiate components for panel
		randomNumberLabel = new JLabel("Random Number (r): ");
		randomNumberField = new JTextField(15);
		voteButton = new JButton("Vote"); 
		
		//candidate combobox set-up
		candidatesModel = new DefaultComboBoxModel<String>();
		candidatesModel.addElement("Alice");
		candidatesModel.addElement("Bob");
		candidatesLabel = new JLabel("Candidate: ");
		candidatesCombo = new JComboBox<String>();
		candidatesCombo.setModel(candidatesModel);
		candidatesCombo.setBorder(BorderFactory.createEtchedBorder());
		
		//sets out grid layout and its constraints
		organiseLayout();
		
		voteButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String message = (String) candidatesCombo.getSelectedItem();
				BigInteger randomNumber = new BigInteger(randomNumberField.getText());
				if(listener != null)
				{
					if(!listener.votingPanelEventOccurred(message, randomNumber))
					{
						voteButton.setEnabled(false);
						JOptionPane.showMessageDialog(mainFrame,
						        "Vote could not be processed as maximum amount of votes have been reached", "Voting Error",
						        JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(mainFrame,
						        "Thank you for voting!", "Voting Confirmation",
						        JOptionPane.INFORMATION_MESSAGE);	
						mainFrame.terminateSession();
					}
				}	
			}
		});
		
		
	}
	private void organiseLayout() 
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
		add(randomNumberLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(randomNumberField, gc);
		
		//next row
		gc.gridy++; //on 1
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0; 
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(3,3,3,5);
		add(candidatesLabel, gc);
		
		gc.gridx = 1; 
		gc.anchor = GridBagConstraints.LINE_START;
		add(candidatesCombo, gc);
			
		//final row
		gc.gridy++; //on 2
		gc.weightx = 1;
		gc.weighty = 2;
		
		gc.gridx = 1;
		add(voteButton, gc);
		
	}
	public void setListener(VotingPanelListener votingPanelListener) 
	{
		this.listener = votingPanelListener; 	
	}

}
