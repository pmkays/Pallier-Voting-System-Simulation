package view;

import java.awt.Component;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.border.Border;

public class ChoosePanel extends JPanel
{
	private JButton vaButton;
	private JButton voterButton;
	private JLabel introLabel;
	
	public ChoosePanel(MainFrame mainFrame)
	{
		//panel set up
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		Border innerBorder = BorderFactory.createTitledBorder("Identification");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		//instantiate components used in panel
		vaButton = new JButton("Voting Authority");	
		voterButton = new JButton("Voter");
		introLabel = new JLabel("Please identify yourself:");
		
		organiseLayout(); 
		
		vaButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				mainFrame.getChoosePanel().setVisible(false);
				mainFrame.add(mainFrame.getVALogIn());
				mainFrame.getVALogIn().setVisible(true);
			}
			
		});
		
		voterButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				mainFrame.getChoosePanel().setVisible(false);
				mainFrame.add(mainFrame.getVoterLogIn());
				mainFrame.getVoterLogIn().setVisible(true);
			}
			
		});
	}

	private void organiseLayout() 
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		vaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		voterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		introLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(Box.createVerticalGlue());
		add(introLabel);
		add(Box.createRigidArea(new Dimension(20,20)));
		add(vaButton);
		add(Box.createRigidArea(new Dimension(20,20)));
		add(voterButton);
		add(Box.createVerticalGlue());	
	}
}
