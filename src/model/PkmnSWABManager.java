package model;

import model.basic.SWIEManager;
import model.basic.Fraction;

/**
 * Copyright 2016, Stephen Gung, All rights reserved
 * @author Stephen Gung
 */
public class PkmnSWABManager{
	private final SWIEManager manager_a;
	private final SWIEManager manager_b;
	private final SWIEManager manager_ab;
	private final Fraction manager_perfect;

	public PkmnSWABManager(){
		manager_a = new SWIEManager();
		manager_b = new SWIEManager();
		manager_ab = new SWIEManager();
		manager_perfect = new Fraction( 0, 0);
	}

	// Copy Constructor method
	public PkmnSWABManager( SWIEManager a,
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
