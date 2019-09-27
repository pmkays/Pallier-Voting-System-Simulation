package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import controller.VAPanelListener;

public class VAPanel extends JPanel
{
	private JButton generateButton;
	private JButton resultsButton;
	private VAPanelListener listener; 
	public VAPanel(MainFrame mainFrame)
	{
		//panel set up
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim); 
		Border innerBorder = BorderFactory.createTitledBorder("Voting Authority Functions");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		//instantiate components
		generateButton = new JButton("Generate Public and Private keys");
		resultsButton = new JButton("View Results"); 
		
		organiseLayout();
			
		generateButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(listener != null)
				{
					listener.vaPanelkeyGeneration();
					JOptionPane.showMessageDialog(mainFrame,
					        "Public and private keys generated.", "Key Generation Confirmation",
					        JOptionPane.INFORMATION_MESSAGE);
				}
			}	
		});	

		resultsButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(listener != null)
				{
					listener.vaPanelResults();
					String winner = listener.getWinners();
					JOptionPane.showMessageDialog(mainFrame,
					        winner, "Winner Announcement",
					        JOptionPane.INFORMATION_MESSAGE);			
				}
			}	
		});	
	}
	
	private void organiseLayout() 
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		generateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		resultsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(Box.createVerticalGlue());
		add(generateButton);		
		add(Box.createRigidArea(new Dimension(20,20)));
		add(resultsButton);
		add(Box.createVerticalGlue());
	}

	public void setListener(VAPanelListener listener)
	{
		this.listener = listener;
	}
}
