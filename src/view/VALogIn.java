package view;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controller.VotingPanelListener;

public class VALogIn extends JPanel
{
	private VotingPanelListener listener;
	private JButton loginButton; 
	private JLabel vaIdLabel;
	private JTextField vaIdField;
	private JLabel vaPassLabel;
	private JPasswordField vaPassField;
	private MainFrame mainFrame;
	
	public VALogIn(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
		
		//set dimensions of JPanel
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		Border innerBorder = BorderFactory.createTitledBorder("Voting Authority Login");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		//instantiate panel components
		loginButton = new JButton("Log in");	
		vaIdLabel = new JLabel("Voter Authority ID: "); 
		vaIdField = new JTextField(15);
		vaPassLabel = new JLabel("Password:");
		vaPassField = new JPasswordField(15);
		
		organiseLayout();
		
		loginButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String id = vaIdField.getText();
				String password = String.valueOf(vaPassField.getPassword()); 
	
				//ensures that the voter has an id
				if(!id.isEmpty() && !password.isEmpty())
				{
					JOptionPane.showMessageDialog(mainFrame,
					        "Voting Authority: " + id + " login successful.", "Successful login",
					        JOptionPane.INFORMATION_MESSAGE);	
					
					//set up next panels
					mainFrame.getVALogIn().setVisible(false);
					mainFrame.add(mainFrame.getVaPanel());
					mainFrame.getVaPanel().setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(mainFrame,
					        "Please enter your Voting Authority unique ID","Error",
					        JOptionPane.ERROR);	
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
		add(vaIdLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(vaIdField, gc);
		
		//next row
		gc.gridy++; 
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(3,3,3,5);
		add(vaPassLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(vaPassField, gc);
		
		//final row
		gc.gridy++; //on 2
		gc.weightx = 0;
		gc.weighty = 1;
		
		gc.gridx = 1;
		add(loginButton, gc);
	}

	public void setListener(VotingPanelListener votingPanelListener) 
	{
		this.listener = votingPanelListener;
		
	}
	
	

}
