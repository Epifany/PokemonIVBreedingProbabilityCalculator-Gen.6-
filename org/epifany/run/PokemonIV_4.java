/**
 * Copyright 2016, Stephen Gung, All rights reserved
 */

package org.epifany.run;

import org.epifany.pokemonbreedprobabilitycalculator.PokemonManager;
import org.epifany.pokemonbreedprobabilitycalculator.PokemonPresenter;
import org.epifany.pokemonbreedprobabilitycalculator.PokemonApplication;
import org.epifany.pokemonbreedprobabilitycalculator.gui.PokemonGUIContainer;
import org.epifany.pokemonbreedprobabilitycalculator.listener.ButtonListener;
import org.epifany.pokemonbreedprobabilitycalculator.listener.CheckBoxListener;
import org.epifany.pokemonbreedprobabilitycalculator.listener.RadioButtonListener;

/**
 * This class contains the main method
 * 
 * @author Stephen Gung
 */
public class PokemonIV_4 {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args){
		PokemonApplication application = new PokemonApplication();
		application.createUI();
		PokemonGUIContainer container;
		
		// Left side
		container = application.getContainer_l();
		PokemonPresenter presenter_l = new PokemonPresenter( new PokemonManager(), container);
		
		ButtonListener bListener_l = new ButtonListener( presenter_l);
		container.getCalculateButton().addActionListener( bListener_l);
		container.getResetButton().addActionListener( bListener_l);
		
		CheckBoxListener cbListener_l = new CheckBoxListener( presenter_l);
		container.getCheckBoxContainer().getHPCheckBox().addItemListener( cbListener_l);
		container.getCheckBoxContainer().getAtkCheckBox().addItemListener( cbListener_l);
		container.getCheckBoxContainer().getDefCheckBox().addItemListener( cbListener_l);
		container.getCheckBoxContainer().getSpACheckBox().addItemListener( cbListener_l);
		container.getCheckBoxContainer().getSpDCheckBox().addItemListener( cbListener_l);
		container.getCheckBoxContainer().getSpeCheckBox().addItemListener( cbListener_l);
		
		RadioButtonListener rbListener_l = new RadioButtonListener( presenter_l);
		container.getRBContainer().getHPIncludeRB().addItemListener( rbListener_l);
		container.getRBContainer().getHPOptionalRB().addItemListener( rbListener_l);
		container.getRBContainer().getHPExcludeRB().addItemListener( rbListener_l);
		container.getRBContainer().getAtkIncludeRB().addItemListener( rbListener_l);
		container.getRBContainer().getAtkOptionalRB().addItemListener( rbListener_l);
		container.getRBContainer().getAtkExcludeRB().addItemListener( rbListener_l);
		container.getRBContainer().getDefIncludeRB().addItemListener( rbListener_l);
		container.getRBContainer().getDefOptionalRB().addItemListener( rbListener_l);
		container.getRBContainer().getDefExcludeRB().addItemListener( rbListener_l);
		container.getRBContainer().getSpAIncludeRB().addItemListener( rbListener_l);
		container.getRBContainer().getSpAOptionalRB().addItemListener( rbListener_l);
		container.getRBContainer().getSpAExcludeRB().addItemListener( rbListener_l);
		container.getRBContainer().getSpDIncludeRB().addItemListener( rbListener_l);
		container.getRBContainer().getSpDOptionalRB().addItemListener( rbListener_l);
		container.getRBContainer().getSpDExcludeRB().addItemListener( rbListener_l);
		container.getRBContainer().getSpeIncludeRB().addItemListener( rbListener_l);
		container.getRBContainer().getSpeOptionalRB().addItemListener( rbListener_l);
		container.getRBContainer().getSpeExcludeRB().addItemListener( rbListener_l);
		
		
		// Right side
		container = application.getContainer_r();
		PokemonPresenter presenter_r = new PokemonPresenter( new PokemonManager(), container);
		
		ButtonListener bListener_r = new ButtonListener( presenter_r);
		container.getCalculateButton().addActionListener( bListener_r);
		container.getResetButton().addActionListener( bListener_r);
		
		CheckBoxListener cbListener_r = new CheckBoxListener( presenter_r);
		container.getCheckBoxContainer().getHPCheckBox().addItemListener( cbListener_r);
		container.getCheckBoxContainer().getAtkCheckBox().addItemListener( cbListener_r);
		container.getCheckBoxContainer().getDefCheckBox().addItemListener( cbListener_r);
		container.getCheckBoxContainer().getSpACheckBox().addItemListener( cbListener_r);
		container.getCheckBoxContainer().getSpDCheckBox().addItemListener( cbListener_r);
		container.getCheckBoxContainer().getSpeCheckBox().addItemListener( cbListener_r);
		
		RadioButtonListener rbListener_r = new RadioButtonListener( presenter_r);
		container.getRBContainer().getHPIncludeRB().addItemListener( rbListener_r);
		container.getRBContainer().getHPOptionalRB().addItemListener( rbListener_r);
		container.getRBContainer().getHPExcludeRB().addItemListener( rbListener_r);
		container.getRBContainer().getAtkIncludeRB().addItemListener( rbListener_r);
		container.getRBContainer().getAtkOptionalRB().addItemListener( rbListener_r);
		container.getRBContainer().getAtkExcludeRB().addItemListener( rbListener_r);
		container.getRBContainer().getDefIncludeRB().addItemListener( rbListener_r);
		container.getRBContainer().getDefOptionalRB().addItemListener( rbListener_r);
		container.getRBContainer().getDefExcludeRB().addItemListener( rbListener_r);
		container.getRBContainer().getSpAIncludeRB().addItemListener( rbListener_r);
		container.getRBContainer().getSpAOptionalRB().addItemListener( rbListener_r);
		container.getRBContainer().getSpAExcludeRB().addItemListener( rbListener_r);
		container.getRBContainer().getSpDIncludeRB().addItemListener( rbListener_r);
		container.getRBContainer().getSpDOptionalRB().addItemListener( rbListener_r);
		container.getRBContainer().getSpDExcludeRB().addItemListener( rbListener_r);
		container.getRBContainer().getSpeIncludeRB().addItemListener( rbListener_r);
		container.getRBContainer().getSpeOptionalRB().addItemListener( rbListener_r);
		container.getRBContainer().getSpeExcludeRB().addItemListener( rbListener_r);
	}
	
}
