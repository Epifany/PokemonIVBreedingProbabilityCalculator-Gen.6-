/**
 * Copyright 2016, Stephen Gung, All rights reserved
 */

package org.epifany.pkmnbreedprbltycalc.gui;

import java.awt.Component;
import javax.swing.JLabel;

/**
 * @author Stephen Gung
 */
public class StrongWeakContainer{
	private final JLabel strongInclusive_msg;
	private final JLabel strongExclusive_msg;
	private final JLabel weakInclusive_msg;
	private final JLabel weakExclusive_msg;
	private final JLabel strongInclusive;
	private final JLabel strongExclusive;
	private final JLabel weakInclusive;
	private final JLabel weakExclusive;
	
	public StrongWeakContainer( String subject){
		strongInclusive = new JLabel("0 / 0");
		strongExclusive = new JLabel("0 / 0");
		weakInclusive = new JLabel("0 / 0");
		weakExclusive = new JLabel("0 / 0");
		strongInclusive_msg = new JLabel("Stronger (inclusively) than " + subject + ": ");
		strongExclusive_msg = new JLabel("Stronger than " + subject + ": ");
		weakInclusive_msg = new JLabel("Weaker (inclusively) than " + subject + ": ");
		weakExclusive_msg = new JLabel("Weaker than " + subject + ": ");
	}
	
	// Optional default layout for all components
	public void setDefaultLayout(){
		strongInclusive.setAlignmentX( Component.LEFT_ALIGNMENT);
		strongExclusive.setAlignmentX( Component.LEFT_ALIGNMENT);
		weakInclusive.setAlignmentX( Component.LEFT_ALIGNMENT);
		weakExclusive.setAlignmentX( Component.LEFT_ALIGNMENT);
		strongInclusive_msg.setAlignmentX( Component.LEFT_ALIGNMENT);
		strongExclusive_msg.setAlignmentX( Component.LEFT_ALIGNMENT);
		weakInclusive_msg.setAlignmentX( Component.LEFT_ALIGNMENT);
		weakExclusive_msg.setAlignmentX( Component.LEFT_ALIGNMENT);
	}
	
	public void setStrongInclusiveText( String text){	strongInclusive.setText(text);	}
	public void setStrongExclusiveText( String text){	strongExclusive.setText(text);	}
	public void setWeakInclusiveText( String text){	weakInclusive.setText(text);	}
	public void setWeakExclusiveText( String text){	weakExclusive.setText(text);	}
	public void setStrongInclusiveMsg( String text){	strongInclusive_msg.setText(text);	}
	public void setStrongExclusiveMsg( String text){	strongExclusive_msg.setText(text);	}
	public void setWeakInclusiveMsg( String text){	weakInclusive_msg.setText(text);	}
	public void setWeakExclusiveMsg( String text){	weakExclusive_msg.setText(text);	}
	
	public JLabel getStrongInclusive(){	return strongInclusive;	}
	public JLabel getStrongExclusive(){	return strongExclusive;	}
	public JLabel getWeakInclusive(){	return weakInclusive;	}
	public JLabel getWeakExclusive(){	return weakExclusive;	}
	public JLabel getStrongInclusiveMsg(){	return strongInclusive_msg;	}
	public JLabel getStrongExclusiveMsg(){	return strongExclusive_msg;	}
	public JLabel getWeakInclusiveMsg(){	return weakInclusive_msg;	}
	public JLabel getWeakExclusiveMsg(){	return weakExclusive_msg;	}
}