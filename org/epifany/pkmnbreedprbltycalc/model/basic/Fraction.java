/**
 * Copyright 2016, Stephen Gung, All rights reserved
 */

package org.epifany.pkmnbreedprbltycalc.model.basic;

/**
 * This class represents a special type of fraction:
 * the numerator will denote the number of combinations that satisfy the user condition
 * the denominator will denote the total number of combinations
 * 
 * @author Stephen Gung
 */
public class Fraction{
	protected int numerator;
	protected int denominator;
	
	public Fraction( int num){
		numerator = num;
		denominator = num;
	}
	
	public Fraction( int numtr, int dentr){
		numerator = numtr;
		denominator = dentr;
	}
	
	// Copy Constructor method
	public Fraction( Fraction p){
		numerator = p.numerator;
		denominator = p.denominator;
	}
	
	public void addNumerator( int num){	numerator += num;	}
	public void addDenominator( int num){	denominator += num;	}
	public void subtractNumerator( int num){	numerator -= num;	}
	public void subtractDenominator( int num){	denominator -= num;	}
	
	public Fraction append( Fraction fraction){
		return new Fraction( numerator + fraction.numerator,
							denominator + fraction.denominator);
	}
	
	// Return a new object whose value is this x multiplicand 
	public Fraction multiply( Fraction multiplicand){
		return new Fraction( numerator * multiplicand.numerator,
							denominator * multiplicand.denominator);
	}
	
	public int getNumerator(){	return numerator;	}
	public int getDenominator(){	return denominator;	}
}