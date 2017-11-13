package pokemoniv_4;

import model.PkmnSWABManager;
import model.basic.SWIEManager;
import java.util.HashMap;
import model.basic.Fraction;
import model.PkmnBreedManager;
import combination.Node;
import pokemon.Pokemon;
import pokemon.PokemonHelper;

/**
 * Copyright 2016, Stephen Gung, All rights reserved
 * 
 * @author Stephen Gung
 */
public class PkmnProbManager{
	// flags for informing program which stat to be considered during probability computation
	private boolean flag_hp;
	private boolean flag_atk;
	private boolean flag_def;
	private boolean flag_spa;
	private boolean flag_spd;
	private boolean flag_spe;
	// The breed manager associated with the probabilities we'll be computing
	private final PkmnBreedManager breed;
	// Optimization
	private final HashMap< String, PkmnSWABManager> probabilities;
	private PkmnFractionMode mode;
	private String currentKey;
	
	public PkmnProbManager( PkmnBreedManager b){
		breed = b;
		probabilities = new HashMap();
	}
	
	// Copy Constructor method
	public PkmnProbManager( PkmnProbManager p){
		flag_hp = p.flag_hp;
		flag_atk = p.flag_atk;
		flag_def = p.flag_def;
		flag_spa = p.flag_spa;
		flag_spd = p.flag_spd;
		flag_spe = p.flag_spe;
		breed = p.breed;
		probabilities = new HashMap( p.probabilities);
	}
	
	public void updateProbabilities(){
		// To save computing time, update only if it hasn't yet been evaluated
		if( probabilities.get(currentKey) == null){
			System.out.println( "New key: " + currentKey);
			SWIEManager manager_a = new SWIEManager();
			SWIEManager manager_b = new SWIEManager();
			SWIEManager manager_ab = new SWIEManager();
			Fraction perfect = new Fraction( 0, 0);
			// Iterate through every combination for probability
			for( int i = 0; i < breed.getCalculator().size(); i++){
				Node target = breed.getCalculator().getNode(i);
				Pokemon pokemon_A = breed.getABManager().getPokemonA();
				Pokemon pokemon_B = breed.getABManager().getPokemonB();
				// Consolidate the IV values of the parents
				int[] source_a_ivs = { pokemon_A.getHP_IV(), pokemon_A.getAtk_IV(), pokemon_A.getDef_IV(),
									pokemon_A.getSpA_IV(), pokemon_A.getSpD_IV(), pokemon_A.getSpe_IV()};
				int[] source_b_ivs = { pokemon_B.getHP_IV(), pokemon_B.getAtk_IV(), pokemon_B.getDef_IV(),
									pokemon_B.getSpA_IV(), pokemon_B.getSpD_IV(), pokemon_B.getSpe_IV()};
				// Find the min & max IV values for each stat
				int[] source_max_ivs = max( source_a_ivs, source_b_ivs);
				int[] source_min_ivs = min( source_a_ivs, source_b_ivs);
				mode = PkmnFractionMode.STRONGINCLUSIVE;
				manager_a.updateStrongInclusive( calcFraction( target, source_a_ivs));
				manager_b.updateStrongInclusive( calcFraction( target, source_b_ivs));
				manager_ab.updateStrongInclusive( calcFraction( target, source_max_ivs));
				mode = PkmnFractionMode.STRONGEXCLUSIVE;
				manager_a.updateStrongExclusive( calcFraction( target, source_a_ivs));
				manager_b.updateStrongExclusive( calcFraction( target, source_b_ivs));
				manager_ab.updateStrongExclusive( calcFraction( target, source_max_ivs));
				mode = PkmnFractionMode.WEAKINCLUSIVE;
				manager_a.updateWeakInclusive( calcFraction( target, source_a_ivs));
				manager_b.updateWeakInclusive( calcFraction( target, source_b_ivs));
				manager_ab.updateWeakInclusive( calcFraction( target, source_min_ivs));
				mode = PkmnFractionMode.WEAKEXCLUSIVE;
				manager_a.updateWeakExclusive( calcFraction( target, source_a_ivs));
				manager_b.updateWeakExclusive( calcFraction( target, source_b_ivs));
				manager_ab.updateWeakExclusive( calcFraction( target, source_min_ivs));
				mode = PkmnFractionMode.PERFECT;
				Fraction temp = calcFraction( target, source_a_ivs);
				perfect.addNumerator( temp.getNumerator());
				perfect.addDenominator( temp.getDenominator());
			}
			PkmnSWABManager manager = new PkmnSWABManager( manager_a, manager_b, manager_ab, perfect);
			probabilities.put( currentKey, manager);
		}
		else{
			System.out.println( "Key already exists: " + currentKey);
		}
	}
	
	public void clearProbabilities(){	probabilities.clear();	}
	
	public void updateCurrentKey(){
		String hp = flag_hp ? "t" : "f";
		String atk = flag_atk ? "t" : "f";
		String def = flag_def ? "t" : "f";
		String spa = flag_spa ? "t" : "f";
		String spd = flag_spd ? "t" : "f";
		String spe = flag_spe ? "t" : "f";
		currentKey = hp + atk + def + spa + spd + spe;
	}
	
	public void setFlagHp( boolean flag){	flag_hp = flag;	}
	public void setFlagAtk( boolean flag){	flag_atk = flag;	}
	public void setFlagDef( boolean flag){	flag_def = flag;	}
	public void setFlagSpA( boolean flag){	flag_spa = flag;	}
	public void setFlagSpD( boolean flag){	flag_spd = flag;	}
	public void setFlagSpe( boolean flag){	flag_spe = flag;	}
	
	public boolean flagHp(){	return flag_hp;	}
	public boolean flagAtk(){	return flag_atk;	}
	public boolean flagDef(){	return flag_def;	}
	public boolean flagSpA(){	return flag_spa;	}
	public boolean flagSpD(){	return flag_spd;	}
	public boolean flagSpe(){	return flag_spe;	}
	
	public PkmnBreedManager getBreedManager(){	return breed;	}
	public PkmnSWABManager getSWABAt( String key){	return probabilities.get(key);	}
	public String getCurrentKey(){	return currentKey;	}
	
	// Traverses through a node for computing a Pokemon Fraction
	private Fraction calcFraction( Node target, int[] source_ivs){
		Fraction result;
		if( target.isLeaf()){
			result = calcFraction( breed.getIVs( target), source_ivs);
		}
		else{
			result = calcFraction( target.getLeftChild(), source_ivs);
			Fraction temp = calcFraction( target.getRightChild(), source_ivs);
			result.addNumerator( temp.getNumerator());
			result.addDenominator( temp.getDenominator());
		}
		return result;
	}
	
	// Calculates the fractional probability for this particlar set of IVs (taken into consideration of the user's stat selection)
	private Fraction calcFraction( int[] target, int[] source){
		Fraction prob_hp = flag_hp ?					calcFraction( target[0], source[0]) :
			(target[0] == PokemonHelper.NULL_IV_VALUE) ?	new Fraction( PokemonHelper.NUM_IV_VALUES, PokemonHelper.NUM_IV_VALUES) :
															new Fraction( 1, 1);
		Fraction prob_atk = flag_atk ?					calcFraction( target[1], source[1]) :
			(target[1] == PokemonHelper.NULL_IV_VALUE) ?	new Fraction( PokemonHelper.NUM_IV_VALUES, PokemonHelper.NUM_IV_VALUES) :
															new Fraction( 1, 1);
		Fraction prob_def = flag_def ?					calcFraction( target[2], source[2]) :
			(target[2] == PokemonHelper.NULL_IV_VALUE) ?	new Fraction( PokemonHelper.NUM_IV_VALUES, PokemonHelper.NUM_IV_VALUES) :
															new Fraction( 1, 1);
		Fraction prob_spa = flag_spa ?					calcFraction( target[3], source[3]) :
			(target[3] == PokemonHelper.NULL_IV_VALUE) ?	new Fraction( PokemonHelper.NUM_IV_VALUES, PokemonHelper.NUM_IV_VALUES) :
															new Fraction( 1, 1);
		Fraction prob_spd = flag_spd ?					calcFraction( target[4], source[4]) :
			(target[4] == PokemonHelper.NULL_IV_VALUE) ?	new Fraction( PokemonHelper.NUM_IV_VALUES, PokemonHelper.NUM_IV_VALUES) :
															new Fraction( 1, 1);
		Fraction prob_spe = flag_spe ?					calcFraction( target[5], source[5]) :
			(target[5] == PokemonHelper.NULL_IV_VALUE) ?	new Fraction( PokemonHelper.NUM_IV_VALUES, PokemonHelper.NUM_IV_VALUES) :
															new Fraction( 1, 1);
		return prob_hp.multiply(prob_atk).multiply(prob_def).multiply(prob_spa).multiply(prob_spd).multiply(prob_spe);
	}
	
	// Calculates the fractional probability for this IV 
	private Fraction calcFraction( int target, int source){
		int num = 0;
		int den = (target == PokemonHelper.NULL_IV_VALUE) ? PokemonHelper.NUM_IV_VALUES : 1;
		if( mode == PkmnFractionMode.STRONGINCLUSIVE){
			num = (target == PokemonHelper.NULL_IV_VALUE)	? PokemonHelper.findNumStrongInclusive(source) :
										(target >= source)	? 1 : 0;
		}
		else if( mode == PkmnFractionMode.STRONGEXCLUSIVE){
			num = (target == PokemonHelper.NULL_IV_VALUE)	? PokemonHelper.findNumStrongExclusive(source) :
										(target > source)	? 1 : 0;
		}
		else if( mode == PkmnFractionMode.WEAKINCLUSIVE){
			num = (target == PokemonHelper.NULL_IV_VALUE)	? PokemonHelper.findNumWeakInclusive(source) :
										(target <= source)	? 1 : 0;		
		}
		else if( mode == PkmnFractionMode.WEAKEXCLUSIVE){
			num = (target == PokemonHelper.NULL_IV_VALUE)	? PokemonHelper.findNumWeakExclusive(source) :
										(target < source)	? 1 : 0;
		}
		else if( mode == PkmnFractionMode.PERFECT){
			num = (target == PokemonHelper.NULL_IV_VALUE)	? 1 :
					( PokemonHelper.isPerfectIV(target))	? 1 : 0;
		}
		return new Fraction( num, den);
	}
	
	// Returns an array, of the same length as a & b, containing the maximum values between a & b
	private int[] max( int[] a, int[] b){
		int[] result = new int[a.length];
		for( int i = 0; i < result.length; i++){
			result[i] = (a[i] >= b[i]) ? a[i] : b[i];
		}
		return result;
	}
	
	// Returns an array, of the same length as a & b, containing the minimum values between a & b
	private int[] min( int[] a, int[] b){
		int[] result = new int[a.length];
		for( int i = 0; i < result.length; i++){
			result[i] = (a[i] <= b[i]) ? a[i] : b[i];
		}
		return result;
	}
	
	public enum PkmnFractionMode{
		DEFAULT,
		STRONGINCLUSIVE,
		STRONGEXCLUSIVE,
		WEAKINCLUSIVE,
		WEAKEXCLUSIVE,
		PERFECT
	}
	

	
}