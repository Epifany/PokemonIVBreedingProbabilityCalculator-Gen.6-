package pokemoniv_4;

import gui.CheckBoxContainer;
import gui.InputContainer;
import gui.PkmnGUIContainer;
import gui.RadioButtonContainer;
import gui.StrongWeakContainer;
import model.PkmnABManager;
import combination.NodeCombinationCalculator;
import model.PkmnSWABManager;
import model.basic.SWIEManager;
import model.basic.Fraction;
import pokemon.Gender;
import pokemon.Nature;
import pokemon.Pokemon;
import pokemon.PokemonHelper;
import pokemoniv_4.PkmnCalcManager.CalcType;

/**
 * Copyright 2016, Stephen Gung, All rights reserved
 * 
 * @author Stephen Gung
 */
public class PkmnPresenter{
	private boolean started;
	// Our model
	private final PkmnManager manager;
	// Our view
	private final PkmnGUIContainer view;
	
	public PkmnPresenter( PkmnManager m, PkmnGUIContainer v){
		manager = m;
		view = v;
	}
	
	public void calculate(){
		PkmnCalcManager calcManager = manager.getCalcManager();
		// Obtain the values for A
		InputContainer input = view.getInputContainer_A();
		int hp_a = Integer.parseInt( input.getHPSpinner().getValue().toString());
		int atk_a = Integer.parseInt( input.getAtkSpinner().getValue().toString());
		int def_a = Integer.parseInt( input.getDefSpinner().getValue().toString());
		int spa_a = Integer.parseInt( input.getSpASpinner().getValue().toString());
		int spd_a = Integer.parseInt( input.getSpDSpinner().getValue().toString());
		int spe_a = Integer.parseInt( input.getSpeSpinner().getValue().toString());
		String list_a = input.getItemCB().getSelectedItem().toString();
		// Obtain the values for B
		input = view.getInputContainer_B();
		int hp_b = Integer.parseInt( input.getHPSpinner().getValue().toString());
		int atk_b = Integer.parseInt( input.getAtkSpinner().getValue().toString());
		int def_b = Integer.parseInt( input.getDefSpinner().getValue().toString());
		int spa_b = Integer.parseInt( input.getSpASpinner().getValue().toString());
		int spd_b = Integer.parseInt( input.getSpDSpinner().getValue().toString());
		int spe_b = Integer.parseInt( input.getSpeSpinner().getValue().toString());
		String list_b = input.getItemCB().getSelectedItem().toString();
		
		CalcType type = list_a.equals( PkmnCmd.ITEM_DESTINYKNOT_TEXT)	? CalcType.DESTINYKNOT :
						list_a.equals( PkmnCmd.ITEM_POWERWEIGHT_TEXT)	? CalcType.POWERHP :
						list_a.equals( PkmnCmd.ITEM_POWERBRACER_TEXT)	? CalcType.POWERATK :
						list_a.equals( PkmnCmd.ITEM_POWERBELT_TEXT)		? CalcType.POWERDEF :
						list_a.equals( PkmnCmd.ITEM_POWERLENS_TEXT)		? CalcType.POWERSPA :
						list_a.equals( PkmnCmd.ITEM_POWERBAND_TEXT)		? CalcType.POWERSPD :
						list_a.equals( PkmnCmd.ITEM_POWERANKLET_TEXT)	? CalcType.POWERSPE : CalcType.DEFAULT;
		calcManager.setCaclTypeA( type);
		
		type = list_b.equals( PkmnCmd.ITEM_DESTINYKNOT_TEXT)	? CalcType.DESTINYKNOT :
				list_b.equals( PkmnCmd.ITEM_POWERWEIGHT_TEXT)	? CalcType.POWERHP :
				list_b.equals( PkmnCmd.ITEM_POWERBRACER_TEXT)	? CalcType.POWERATK :
				list_b.equals( PkmnCmd.ITEM_POWERBELT_TEXT)		? CalcType.POWERDEF :
				list_b.equals( PkmnCmd.ITEM_POWERLENS_TEXT)		? CalcType.POWERSPA :
				list_b.equals( PkmnCmd.ITEM_POWERBAND_TEXT)		? CalcType.POWERSPD :
				list_b.equals( PkmnCmd.ITEM_POWERANKLET_TEXT)	? CalcType.POWERSPE : CalcType.DEFAULT;
		calcManager.setCaclTypeB( type);
		
		calcManager.updateCurrentKey();
		calcManager.updateCalculators();
		NodeCombinationCalculator currentCalculator = calcManager.getCalculatorAt( calcManager.getCurrentKey());
		manager.getBreedManager().setCalculator( currentCalculator);
		
		Pokemon pokemon_A =	new Pokemon( 99, "A", Gender.MALE, Nature.MODEST,
							hp_a, atk_a, def_a, spa_a, spd_a, spe_a);
		Pokemon pokemon_B = new Pokemon( 99, "B", Gender.FEMALE, Nature.MODEST,
							hp_b, atk_b, def_b, spa_b, spd_b, spe_b);
		
		manager.getBreedManager().setManager( new PkmnABManager( pokemon_A, pokemon_B));
		manager.getBreedManager().evaluateIVMapping();
		started = true;
	}
	
	// Reset everything; clean slate
	public void reset(){
		manager.getProbManager().clearProbabilities();
		manager.getProbManager2().clearProbabilities();
		started = false;
	}
	
	// This method will reset all the GUI values. Calling this method will not affect the internal data.
	public void resetGUI(){
		InputContainer input_a = view.getInputContainer_A();
		InputContainer input_b = view.getInputContainer_B();
		CheckBoxContainer cbContainer = view.getCheckBoxContainer();
		RadioButtonContainer rbContainer = view.getRBContainer();
		// Reset spinner values
		input_a.getHPSpinner().setValue("31");
		input_a.getAtkSpinner().setValue("31");
		input_a.getDefSpinner().setValue("31");
		input_a.getSpASpinner().setValue("31");
		input_a.getSpDSpinner().setValue("31");
		input_a.getSpeSpinner().setValue("31");
		// Reset list
		input_a.getItemCB().setSelectedIndex(1);
		// Reset spinner values
		input_b.getHPSpinner().setValue("31");
		input_b.getAtkSpinner().setValue("31");
		input_b.getDefSpinner().setValue("31");
		input_b.getSpASpinner().setValue("31");
		input_b.getSpDSpinner().setValue("31");
		input_b.getSpeSpinner().setValue("31");
		// Reset list
		input_b.getItemCB().setSelectedIndex(0);
		// Reset checkboxes
		cbContainer.getHPCheckBox().setSelected(false);
		cbContainer.getAtkCheckBox().setSelected( false);
		cbContainer.getDefCheckBox().setSelected( false);
		cbContainer.getSpACheckBox().setSelected( false);
		cbContainer.getSpDCheckBox().setSelected( false);
		cbContainer.getSpeCheckBox().setSelected( false);
		// Reset labels
		view.getStrongWeakA().setStrongInclusiveText( "0 / 0");
		view.getStrongWeakA().setStrongExclusiveText( "0 / 0");
		view.getStrongWeakA().setWeakInclusiveText( "0 / 0");
		view.getStrongWeakA().setWeakExclusiveText( "0 / 0");
		view.getStrongWeakB().setStrongInclusiveText( "0 / 0");
		view.getStrongWeakB().setStrongExclusiveText( "0 / 0");
		view.getStrongWeakB().setWeakInclusiveText( "0 / 0");
		view.getStrongWeakB().setWeakExclusiveText( "0 / 0");
		view.getStrongWeakAB().setStrongInclusiveText( "0 / 0");
		view.getStrongWeakAB().setStrongExclusiveText( "0 / 0");
		view.getStrongWeakAB().setWeakInclusiveText( "0 / 0");
		view.getStrongWeakAB().setWeakExclusiveText( "0 / 0");
		view.getStrongWeakPerfect().setText( "0 / 0");
		// Reset RadioButtons
		rbContainer.getHPIncludeRB().setSelected( true);
		rbContainer.getAtkIncludeRB().setSelected( true);
		rbContainer.getDefIncludeRB().setSelected( true);
		rbContainer.getSpAIncludeRB().setSelected( true);
		rbContainer.getSpDIncludeRB().setSelected( true);
		rbContainer.getSpeIncludeRB().setSelected( true);
		// Reset label
		view.getRBLabel().setText( "0 / 0");
	}
	
	// This method is called whenever a flag state is changed
	public void updateFlagState( String text, boolean flag){
		switch (text) {
			case PokemonHelper.HP_TEXT:
				manager.getProbManager().setFlagHp(flag);
				break;
			case PokemonHelper.ATK_TEXT:
				manager.getProbManager().setFlagAtk(flag);
				break;
			case PokemonHelper.DEF_TEXT:
				manager.getProbManager().setFlagDef(flag);
				break;
			case PokemonHelper.SPA_TEXT:
				manager.getProbManager().setFlagSpA(flag);
				break;
			case PokemonHelper.SPD_TEXT:
				manager.getProbManager().setFlagSpD(flag);
				break;
			case PokemonHelper.SPE_TEXT:
				manager.getProbManager().setFlagSpe(flag);
				break;
			default:
				break;
		}
	}
	
	public void updateFlagStateKey(){	manager.getProbManager().updateCurrentKey();	}
	
	// Called whenever a flag state is changed, or when calculate is done
	public void updateFlagStateProbability(){
		if( started){
			PkmnProbManager probManager = manager.getProbManager();
			probManager.updateProbabilities();
			PkmnSWABManager swab = probManager.getSWABAt( probManager.getCurrentKey());
			
			StrongWeakContainer swContainer = view.getStrongWeakA();
			SWIEManager swie = swab.getManagerA();
			Fraction fraction = swie.getStrongInclusive();
			swContainer.setStrongInclusiveText( fraction.getNumerator() + " / " + fraction.getDenominator());
			fraction = swie.getStrongExclusive();
			swContainer.setStrongExclusiveText( fraction.getNumerator() + " / " + fraction.getDenominator());
			fraction = swie.getWeakInclusive();
			swContainer.setWeakInclusiveText( fraction.getNumerator() + " / " + fraction.getDenominator());
			fraction = swie.getWeakExclusive();
			swContainer.setWeakExclusiveText( fraction.getNumerator() + " / " + fraction.getDenominator());
			
			swContainer = view.getStrongWeakB();
			swie = swab.getManagerB();
			fraction = swie.getStrongInclusive();
			swContainer.setStrongInclusiveText( fraction.getNumerator() + " / " + fraction.getDenominator());
			fraction = swie.getStrongExclusive();
			swContainer.setStrongExclusiveText( fraction.getNumerator() + " / " + fraction.getDenominator());
			fraction = swie.getWeakInclusive();
			swContainer.setWeakInclusiveText( fraction.getNumerator() + " / " + fraction.getDenominator());
			fraction = swie.getWeakExclusive();
			swContainer.setWeakExclusiveText( fraction.getNumerator() + " / " + fraction.getDenominator());

			swContainer = view.getStrongWeakAB();
			swie = swab.getManagerAB();
			fraction = swie.getStrongInclusive();
			swContainer.setStrongInclusiveText( fraction.getNumerator() + " / " + fraction.getDenominator());
			fraction = swie.getStrongExclusive();
			swContainer.setStrongExclusiveText( fraction.getNumerator() + " / " + fraction.getDenominator());
			fraction = swie.getWeakInclusive();
			swContainer.setWeakInclusiveText( fraction.getNumerator() + " / " + fraction.getDenominator());
			fraction = swie.getWeakExclusive();
			swContainer.setWeakExclusiveText( fraction.getNumerator() + " / " + fraction.getDenominator());

			fraction = swab.getManagerPerfect();
			view.getStrongWeakPerfect().setText( fraction.getNumerator() + " / " + fraction.getDenominator());
		}
	}
	
	// This method is called whenever a radiobutton state is changed
	public void updateRBState( String text, boolean flag){
		if( flag){
			switch (text) {
				case PkmnCmd.HP_INC:
					manager.getProbManager2().setNeedHp( true);
					manager.getProbManager2().setFlagHp( true);
					break;
				case PkmnCmd.HP_OPT:
					manager.getProbManager2().setNeedHp( false);
					manager.getProbManager2().setFlagHp( true);
					break;
				case PkmnCmd.HP_EXC:
					manager.getProbManager2().setNeedHp( true);
					manager.getProbManager2().setFlagHp( false);
					break;
				case PkmnCmd.ATK_INC:
					manager.getProbManager2().setNeedAtk( true);
					manager.getProbManager2().setFlagAtk( true);
					break;
				case PkmnCmd.ATK_OPT:
					manager.getProbManager2().setNeedAtk( false);
					manager.getProbManager2().setFlagAtk( true);
					break;
				case PkmnCmd.ATK_EXC:
					manager.getProbManager2().setNeedAtk( true);
					manager.getProbManager2().setFlagAtk( false);
					break;
				case PkmnCmd.DEF_INC:
					manager.getProbManager2().setNeedDef( true);
					manager.getProbManager2().setFlagDef( true);
					break;
				case PkmnCmd.DEF_OPT:
					manager.getProbManager2().setNeedDef( false);
					manager.getProbManager2().setFlagDef( true);
					break;
				case PkmnCmd.DEF_EXC:
					manager.getProbManager2().setNeedDef( true);
					manager.getProbManager2().setFlagDef( false);
					break;
				case PkmnCmd.SPA_INC:
					manager.getProbManager2().setNeedSpA( true);
					manager.getProbManager2().setFlagSpA( true);
					break;
				case PkmnCmd.SPA_OPT:
					manager.getProbManager2().setNeedSpA( false);
					manager.getProbManager2().setFlagSpA( true);
					break;
				case PkmnCmd.SPA_EXC:
					manager.getProbManager2().setNeedSpA( true);
					manager.getProbManager2().setFlagSpA( false);
					break;
				case PkmnCmd.SPD_INC:
					manager.getProbManager2().setNeedSpD( true);
					manager.getProbManager2().setFlagSpD( true);
					break;
				case PkmnCmd.SPD_OPT:
					manager.getProbManager2().setNeedSpD( false);
					manager.getProbManager2().setFlagSpD( true);
					break;
				case PkmnCmd.SPD_EXC:
					manager.getProbManager2().setNeedSpD( true);
					manager.getProbManager2().setFlagSpD( false);
					break;
				case PkmnCmd.SPE_INC:
					manager.getProbManager2().setNeedSpe( true);
					manager.getProbManager2().setFlagSpe( true);
					break;
				case PkmnCmd.SPE_OPT:
					manager.getProbManager2().setNeedSpe( false);
					manager.getProbManager2().setFlagSpe( true);
					break;
				case PkmnCmd.SPE_EXC:
					manager.getProbManager2().setNeedSpe( true);
					manager.getProbManager2().setFlagSpe( false);
					break;
				default:
					break;
			}
		}
	}

	public void updateRBStateKey(){	manager.getProbManager2().updateCurrentKey();	}
	
	// Called whenever a radio button state is changed, or when calculate is done
	public void updateRBStateProbability(){
		if( started){
			PkmnProbManager2 probManager2 = manager.getProbManager2();
			probManager2.updateProbabilities();
			Fraction perfect = probManager2.getFractionAt( probManager2.getCurrentKey());
			view.getRBLabel().setText( perfect.getNumerator() + " / " + perfect.getDenominator());
		}
	}
}