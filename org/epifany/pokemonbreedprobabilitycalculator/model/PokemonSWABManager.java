/**
 * Copyright 2016, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator.model;

import org.epifany.pokemonbreedprobabilitycalculator.model.basic.*;

/**
 * @author Stephen Gung
 */
public class PokemonSWABManager {
	private final SWIEManager manager_a;
	private final SWIEManager manager_b;
	private final SWIEManager manager_ab;
	private final Fraction manager_perfect;

	public PokemonSWABManager(){
		manager_a = new SWIEManager();
		manager_b = new SWIEManager();
		manager_ab = new SWIEManager();
		manager_perfect = new Fraction( 0, 0);
	}

	// Copy Constructor method
	public PokemonSWABManager( SWIEManager a,
							SWIEManager b,
							SWIEManager max,
							Fraction perfect){
		manager_a = new SWIEManager( a);
		manager_b = new SWIEManager( b);
		manager_ab = new SWIEManager( max);
		manager_perfect = new Fraction( perfect);
	}

	public SWIEManager getManagerA(){	return manager_a;	}
	public SWIEManager getManagerB(){	return manager_b;	}
	public SWIEManager getManagerAB(){	return manager_ab;	}
	public Fraction getManagerPerfect(){	return manager_perfect;	}
}
