package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pokemoniv_4.PkmnPresenter;

/**
 * Copyright 2016, Stephen Gung, All rights reserved
 * @author Stephen Gung
 */
public class ButtonListener implements ActionListener{
	private final PkmnPresenter presenter;
	
	public ButtonListener( PkmnPresenter p){
		presenter = p;
	}
	
	@Override
	public void actionPerformed( ActionEvent ae){
		String s = ae.getActionCommand();
		switch (s) {
			case "Calculate":
				presenter.reset();
				presenter.calculate();
				presenter.updateFlagStateKey();
				presenter.updateFlagStateProbability();
				presenter.updateRBStateKey();
				presenter.updateRBStateProbability();
				break;
			case "Reset":
				presenter.reset();
				presenter.resetGUI();
				break;
		}
	}
	
}
