/**
 * Copyright 2016, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator;

import org.epifany.pokemonbreedprobabilitycalculator.model.PokemonBreedManager;

/**
 * This is a "super" manager.
 * 
 * @author Stephen Gung
 */
public class PokemonManager {
	private final PokemonBreedManager breedManager;
	private final PokemonProbManager probManager;
	private final PokemonProbManager2 probManager2;
	private final PokemonCalcManager calcManager;
	
	public PokemonManager(){
		breedManager = new PokemonBreedManager();
		probManager = new PokemonProbManager( breedManager);
		probManager2 = new PokemonProbManager2( breedManager);
		calcManager = new PokemonCalcManager();
	}
	
	public PokemonBreedManager getBreedManager(){
		return breedManager;
	}
	
	public PokemonProbManager getProbManager(){
		return probManager;
	}
	
	public PokemonProbManager2 getProbManager2(){
		return probManager2;
	}
	
	public PokemonCalcManager getCalcManager(){
		return calcManager;
	}
}