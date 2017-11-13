package listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import pokemoniv_4.PkmnPresenter;

/**
 * Copyright 2016, Stephen Gung, All rights reserved
 * @author Stephen Gung
 */
public class RadioButtonListener implements ItemListener{
	private final PkmnPresenter presenter;
	
	public RadioButtonListener( PkmnPresenter p){
		presenter = p;
	}
	
	@Override
	public void itemStateChanged( ItemEvent ie){
		AbstractButton button = (AbstractButton)ie.getSource();
		String command = button.getActionCommand();
		boolean flag = ( ie.getStateChange() == ItemEvent.SELECTED);
		System.out.println( command + " " + flag);
		if( flag){
			presenter.updateRBState( command, flag);
			presenter.updateRBStateKey();
			presenter.updateRBStateProbability();
		}
	}
}
