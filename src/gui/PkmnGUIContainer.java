package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pokemoniv_4.PkmnCmd;

/**
 * Copyright 2016, Stephen Gung, All rights reserved
 * @author Stephen Gung
 */
public class PkmnGUIContainer{
	private final InputContainer input_a;
	private final InputContainer input_b;
	
	private final JButton calculateButton;
	private final JButton resetButton;
	
	private final CheckBoxContainer checkBoxContainer;
	private final StrongWeakContainer strongWeak_a;
	private final StrongWeakContainer strongWeak_b;
	private final StrongWeakContainer strongWeak_ab;
	private final JLabel strongWeak_perfect;
	private final RadioButtonContainer radioButtonContainer;
	private final JLabel rbLabel;
	
	public PkmnGUIContainer(){
		String[] spnrValues = { "0", "1", "2", "3",
			"4", "5", "6", "7",
			"8", "9", "10", "11",
			"12", "13", "14", "15",
			"16", "17", "18", "19",
			"20", "21", "22", "23",
			"24", "25", "26", "27",
			"28", "29", "30", "31"
		};
		String[] itemValues = { PkmnCmd.ITEM_OTHER_TEXT,
			PkmnCmd.ITEM_DESTINYKNOT_TEXT,
			PkmnCmd.ITEM_POWERWEIGHT_TEXT,
			PkmnCmd.ITEM_POWERBRACER_TEXT,
			PkmnCmd.ITEM_POWERBELT_TEXT,
			PkmnCmd.ITEM_POWERLENS_TEXT,
			PkmnCmd.ITEM_POWERBAND_TEXT,
			PkmnCmd.ITEM_POWERANKLET_TEXT
		};
		// Initialize Pokemon A Input guis
		input_a = new InputContainer( spnrValues, itemValues,
					"HP:", "Attack:", "Defense:", "Sp. Attack:", "Sp. Defense:", "Speed:");
		// Just for this program I set the initial index to 1
		input_a.getItemCB().setSelectedIndex(1);
		// Initialize Pokemon B Input guis
		input_b = new InputContainer( spnrValues, itemValues,
					"HP:", "Attack:", "Defense:", "Sp. Attack:", "Sp. Defense:", "Speed:");
		calculateButton = new JButton("Calculate");
		resetButton = new JButton("Reset");
		checkBoxContainer = new CheckBoxContainer( "HP", "Atk", "Def", "SpA", "SpD", "Spe");
		strongWeak_a = new StrongWeakContainer("A");
		strongWeak_b = new StrongWeakContainer("B");
		strongWeak_ab = new StrongWeakContainer("A & B");
		strongWeak_perfect = new JLabel("0 / 0");
		radioButtonContainer = new RadioButtonContainer( "Perfect", "Not perfect", "Either");
		rbLabel = new JLabel("0 / 0");
	}
	
	// Default panel
	public JPanel createDefaultPanel(){
		JPanel panel = new JPanel( new BorderLayout());
		panel.setBorder( BorderFactory.createEtchedBorder());
		// Creating the input panel
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout( new BoxLayout( inputPanel, BoxLayout.Y_AXIS));
		inputPanel.setBorder( BorderFactory.createEtchedBorder());
		inputPanel.add( input_a.createInputPanel("Pokemon (A)"));
		inputPanel.add( input_b.createInputPanel("Pokemon (B)"));
		calculateButton.setAlignmentX( Component.CENTER_ALIGNMENT);
		inputPanel.add( calculateButton);
		// Creating the output panel
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout( new BoxLayout( outputPanel, BoxLayout.Y_AXIS));
		outputPanel.setBorder( BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Offspring Goal"));
		outputPanel.add( createCheckBoxPanel( "Stat Comparison Probabilities"));
		outputPanel.add( createRadioButtonPanel( "Perfect Stat Probabilities"));
		// Consolidate our panels
		panel.add( inputPanel, BorderLayout.LINE_START);
		panel.add( outputPanel, BorderLayout.LINE_END);
		panel.add( resetButton, BorderLayout.PAGE_END);
		return panel;
	}
	
	private JPanel createCheckBoxPanel( String title){
		strongWeak_a.setDefaultLayout();
		strongWeak_b.setDefaultLayout();
		strongWeak_ab.setDefaultLayout();
		// A panel for our messages
		JPanel msgPanel = new JPanel();
		msgPanel.setLayout( new BoxLayout( msgPanel, BoxLayout.Y_AXIS));
		//msgPanel.setAlignmentX( Component.LEFT_ALIGNMENT);
		msgPanel.add( strongWeak_a.getStrongInclusiveMsg());
		msgPanel.add( strongWeak_b.getStrongInclusiveMsg());
		msgPanel.add( strongWeak_ab.getStrongInclusiveMsg());
		msgPanel.add( strongWeak_a.getStrongExclusiveMsg());
		msgPanel.add( strongWeak_b.getStrongExclusiveMsg());
		msgPanel.add( strongWeak_ab.getStrongExclusiveMsg());
		msgPanel.add( strongWeak_a.getWeakInclusiveMsg());
		msgPanel.add( strongWeak_b.getWeakInclusiveMsg());
		msgPanel.add( strongWeak_ab.getWeakInclusiveMsg());
		msgPanel.add( strongWeak_a.getWeakExclusiveMsg());
		msgPanel.add( strongWeak_b.getWeakExclusiveMsg());
		msgPanel.add( strongWeak_ab.getWeakExclusiveMsg());
		msgPanel.add( new JLabel("Perfect stats: "));
		// A panel for where we'll display our results
		JPanel reportPanel = new JPanel();
		reportPanel.setLayout( new BoxLayout( reportPanel, BoxLayout.Y_AXIS));
		//reportPanel.setAlignmentX( Component.LEFT_ALIGNMENT);
		reportPanel.add( strongWeak_a.getStrongInclusive());
		reportPanel.add( strongWeak_b.getStrongInclusive());
		reportPanel.add( strongWeak_ab.getStrongInclusive());
		reportPanel.add( strongWeak_a.getStrongExclusive());
		reportPanel.add( strongWeak_b.getStrongExclusive());
		reportPanel.add( strongWeak_ab.getStrongExclusive());
		reportPanel.add( strongWeak_a.getWeakInclusive());
		reportPanel.add( strongWeak_b.getWeakInclusive());
		reportPanel.add( strongWeak_ab.getWeakInclusive());
		reportPanel.add( strongWeak_a.getWeakExclusive());
		reportPanel.add( strongWeak_b.getWeakExclusive());
		reportPanel.add( strongWeak_ab.getWeakExclusive());
		reportPanel.add( strongWeak_perfect);
		// Put all our panels together
		JPanel panel = new JPanel( new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// Row 0, column 0, width 10
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 10;
		panel.add( new JLabel( "Select the stats you want to consider:"), c);
		// Row 1, column 0, width 10
		c.gridy = 1;
		panel.add( checkBoxContainer.createCheckBoxPanel(), c);
		// Row 2, column 0, width 4
		c.gridy = 2;
		c.gridx = 0;
		c.gridwidth = 4;
		panel.add( msgPanel, c);
		// Row 2, column 4, width 6
		c.gridx = 4;
		c.gridwidth = 6;
		panel.add( reportPanel, c);
		
		/*
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		//panel.add( new JLabel( "Select the stats to consider."), c);
		c.gridy = 1;
		panel.add( checkBoxContainer.createCheckBoxPanel(), c);
		c.gridwidth = 2;
		c.gridy = 2;
		panel.add( msgPanel, c);
		c.gridx = 2;
		panel.add( reportPanel, c);
		*/
		
		if( title != null){
			panel.setBorder( BorderFactory.createTitledBorder(title));
		}
		return panel;
	}
	
	private JPanel createRadioButtonPanel( String title){
		JPanel subPanel = new JPanel( new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// Row 0, column 0, width 10
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 10;
		subPanel.add( new JLabel( "Select the condition (for each stat) you want to know:"), c);
		// Row 1, column 0, width 8
		c.gridy = 1;
		c.gridwidth = 8;
		subPanel.add( radioButtonContainer.createRadioButtonPanel(), c);
		
		JPanel panel = new JPanel();
		panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS));
		panel.add( subPanel);
		panel.add( rbLabel);
		
		
		
		
		/*
		JPanel panel = new JPanel();
		panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS));
		if( title != null){
			panel.setBorder( BorderFactory.createTitledBorder(title));
		}
		panel.add(, Component.LEFT_ALIGNMENT);
		panel.add( rbLabel, Component.CENTER_ALIGNMENT);
		*/
		
		
		
		
		/*
		JPanel panel = new JPanel( new GridBagLayout());
		if( title != null){
			panel.setBorder( BorderFactory.createTitledBorder(title));
		}
		GridBagConstraints c = new GridBagConstraints();
		//c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		panel.add( new JLabel( "Select the condition (for each stat) you want to know."), c);
		c.gridy = 1;
		panel.add( radioButtonContainer.createRadioButtonPanel(), c);
		//c.gridwidth = 2;
		c.gridy = 2;
		panel.add( rbLabel, c);
		*/
		
		if( title != null){
			panel.setBorder( BorderFactory.createTitledBorder(title));
		}
		return panel;
	}
	
	public JButton getCalculateButton(){	return calculateButton;	}
	public JButton getResetButton(){	return resetButton;	}
	public InputContainer getInputContainer_A(){	return input_a;	}
	public InputContainer getInputContainer_B(){	return input_b;	}
	public CheckBoxContainer getCheckBoxContainer(){	return checkBoxContainer;	}
	public StrongWeakContainer getStrongWeakA(){	return strongWeak_a;	}
	public StrongWeakContainer getStrongWeakB(){	return strongWeak_b;	}
	public StrongWeakContainer getStrongWeakAB(){	return strongWeak_ab;	}
	public JLabel getStrongWeakPerfect(){	return strongWeak_perfect;	}
	public RadioButtonContainer getRBContainer(){	return radioButtonContainer;	}
	public JLabel getRBLabel(){	return rbLabel;	}
}