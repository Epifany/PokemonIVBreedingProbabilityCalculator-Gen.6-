package pokemoniv_4;

import model.PkmnBreedManager;

/**
 * Copyright 2016, Stephen Gung, All rights reserved
 * 
 * This is a "super" manager.
 * 
 * @author Stephen Gung
 */
public class PkmnManager{
	private final PkmnBreedManager breedManager;
	private final PkmnProbManager probManager;
	private final PkmnProbManager2 probManager2;
	private final PkmnCalcManager calcManager;
	
	public PkmnManager(){
		breedManager = new PkmnBreedManager();
		probManager = new PkmnProbManager( breedManager);
		probManager2 = new PkmnProbManager2( breedManager);
		calcManager = new PkmnCalcManager();
	}
	
	public PkmnBreedManager getBreedManager(){	return breedManager;	}
	public PkmnProbManager getProbManager(){	return probManager;	}
	public PkmnProbManager2 getProbManager2(){	return probManager2;	}
	public PkmnCalcManager getCalcManager(){	return calcManager;	}
}