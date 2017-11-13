package model.basic;

/**
 * Copyright 2016, Stephen Gung, All rights reserved
 * 
 * Class for handling
 * Strong Inclusiveness,
 * Strong Exclusiveness,
 * Weak Inclusiveness,
 * Weak Exclusiveness
 * 
 * @author Stephen Gung
 */
public class SWIEManager{
	private final Fraction strongInclusive;
	private final Fraction strongExclusive;
	private final Fraction weakInclusive;
	private final Fraction weakExclusive;
	
	public SWIEManager(){
		strongInclusive = new Fraction( 0, 0);
		strongExclusive = new Fraction( 0, 0);
		weakInclusive = new Fraction( 0, 0);
		weakExclusive = new Fraction( 0, 0);
	}
	
	// Copy Constructor method
	public SWIEManager( SWIEManager p){
		strongInclusive = new Fraction( p.strongInclusive);
		strongExclusive = new Fraction( p.strongExclusive);
		weakInclusive = new Fraction( p.weakInclusive);
		weakExclusive = new Fraction( p.weakExclusive);
	}
	
	public void updateStrongInclusive( Fraction p){
		strongInclusive.addNumerator( p.getNumerator());
		strongInclusive.addDenominator( p.getDenominator());
	}
	
	public void updateStrongExclusive( Fraction p){
		strongExclusive.addNumerator( p.getNumerator());
		strongExclusive.addDenominator( p.getDenominator());
	}
	
	public void updateWeakInclusive( Fraction p){
		weakInclusive.addNumerator( p.getNumerator());
		weakInclusive.addDenominator( p.getDenominator());
	}
	
	public void updateWeakExclusive( Fraction p){
		weakExclusive.addNumerator( p.getNumerator());
		weakExclusive.addDenominator( p.getDenominator());
	}
	
	public Fraction getStrongInclusive(){	return strongInclusive;	}
	public Fraction getStrongExclusive(){	return strongExclusive;	}
	public Fraction getWeakInclusive(){	return weakInclusive;	}
	public Fraction getWeakExclusive(){	return weakExclusive;	}
}