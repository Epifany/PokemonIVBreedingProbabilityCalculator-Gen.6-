/**
 * Copyright 2016, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator;

import java.util.ArrayList;
import java.util.HashMap;
import org.epifany.combination.NodeCombinationCalculator;

/**
 * This class handles, and saves,
 * all the different types of calculations that occurs while the program is in use
 * 
 * @author Stephen Gung
 */
public class PokemonCalcManager {
	private CalcType type_a;
	private CalcType type_b;
	
	private final HashMap<String, NodeCombinationCalculator> calculators;
	private String currentKey;
	
	public PokemonCalcManager(){
		calculators = new HashMap();
	}
	
	public void updateCalculators(){
		// To save computing time, update only if it hasn't yet been evaluated
		if( calculators.get( currentKey) == null){
			System.out.println( "New key: " + currentKey);
			// Generate a new calculator here
			NodeCombinationCalculator calc_new = createNewCalculator();
			calculators.put( currentKey, calc_new);
		}/*
		else{
			System.out.println( "Key already exists: " + currentKey);
		}*/
	}
	
	private NodeCombinationCalculator createNewCalculator(){
		NodeCombinationCalculator calculator;
		int[] indices = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 , 10, 11};
		int[] split_a = { 0, 1, 2, 3, 4, 5};
		int[] split_b = { 6, 7, 8, 9, 10, 11};
		int numElements;
		// Two power items
		if( currentKey.matches("[0-5][0-5]")){
			numElements = 2;
			int priority_a = Character.getNumericValue( currentKey.charAt(0));
			int priority_b = Character.getNumericValue( currentKey.charAt(1)) + 6;
			calculator = new NodeCombinationCalculator( indices, numElements);
			calculator.splitNodes( split_a, split_b);
			for( int i = 0; i < calculator.size(); i++){
				ArrayList<Integer> combination = calculator.get(i);
				if( combination.contains( priority_a) && combination.contains( priority_b)){
					calculator.remove(i--);
				}
				else if( combination.contains( priority_a)){
					calculator.appendElement( calculator.getNode(i), priority_b);
				}
				else if( combination.contains( priority_b)){
					calculator.appendElement( calculator.getNode(i), priority_a);
				}
				else{
					calculator.appendElement( calculator.getNode(i), priority_a, priority_b);
				}
			}
		}	
		// Power item held by first Pokemon or held by second Pokemon
		else if( currentKey.matches("[0-5]d")
		|| currentKey.matches("[0-5]n")
		|| currentKey.matches("d[0-5]")
		|| currentKey.matches("n[0-5]")){
			int priority;
			// Was Power item held by first Pokemon?
			if( currentKey.matches("[0-5]d") || currentKey.matches("[0-5]n")){
				priority = Character.getNumericValue( currentKey.charAt(0));
				// Is second Pokemon holding Destiny Knot or No Item?
				numElements = (currentKey.charAt(1) == 'd') ? 4 : 2;
			}
			else{
				priority = Character.getNumericValue( currentKey.charAt(1)) + 6;
				// Is first Pokemon holding Destiny Knot or No Item?
				numElements = (currentKey.charAt(0) == 'd') ? 4 : 2;
			}
			int[] indices_priority = new int[indices.length-1];
			int count = 0;
			// Copy all elements except for the master element
			for( int i = 0; i < indices.length; i++){
				if( i != priority){
					indices_priority[count++] = i;
				}
			}
			// New calculator, with gauranteed element associated with the Power Item
			calculator = new NodeCombinationCalculator( indices_priority, numElements);
			calculator.splitNodes( split_a, split_b);
			for( int i = 0; i < calculator.size(); i++){
				calculator.appendElement( calculator.getNode(i), priority);
			}
		}
		// One of the Pokemon is holding Destiny Knot
		else if( currentKey.equals("dd")
		|| currentKey.equals("dn")
		|| currentKey.equals("nd")){
			calculator = new NodeCombinationCalculator( indices, 5);
			calculator.splitNodes( split_a, split_b);
		}
		else{
			calculator = new NodeCombinationCalculator( indices, 3);
			calculator.splitNodes( split_a, split_b);
		}
		return calculator;
	}
	
	public void updateCurrentKey(){
		String a = (type_a == CalcType.DESTINYKNOT) ?	"d" :
					(type_a == CalcType.POWERHP)	?	"0" :
					(type_a == CalcType.POWERATK)	?	"1" :
					(type_a == CalcType.POWERDEF)	?	"2" :
					(type_a == CalcType.POWERSPA)	?	"3" :
					(type_a == CalcType.POWERSPD)	?	"4" :
					(type_a == CalcType.POWERSPE)	?	"5" : "n";
		String b = (type_b == CalcType.DESTINYKNOT) ?	"d" :
					(type_b == CalcType.POWERHP)	?	"0" :
					(type_b == CalcType.POWERATK)	?	"1" :
					(type_b == CalcType.POWERDEF)	?	"2" :
					(type_b == CalcType.POWERSPA)	?	"3" :
					(type_b == CalcType.POWERSPD)	?	"4" :
					(type_b == CalcType.POWERSPE)	?	"5" : "n";
		System.out.println( currentKey);
		currentKey = a + b;
	}
	
	public void setCaclTypeA( CalcType type){
		type_a = type;
	}
	
	public void setCaclTypeB( CalcType type){
		type_b = type;
	}
	
	public NodeCombinationCalculator getCalculatorAt( String key){
		return calculators.get(key);
	}
	
	public String getCurrentKey(){
		return currentKey;
	}
	
	public enum CalcType{
		DEFAULT,
		DESTINYKNOT,
		POWERHP,
		POWERATK,
		POWERDEF,
		POWERSPA,
		POWERSPD,
		POWERSPE
	}
	
		/*
		// Power item held by second Pokemon
		else if( currentKey.matches("d[0-5]")
		|| currentKey.matches("n[0-5]")){
			int priority = Character.getNumericValue( currentKey.charAt(1)) + 6;
			// Is first Pokemon holding Destiny Knot or No Item?
			numElements = (currentKey.charAt(0) == 'd') ? 4 : 2;
	
			int[] indices_priority = new int[indices.length-1];
			int count = 0;
			// Copy all elements except for the master element
			for( int i = 0; i < indices.length; i++){
				if( i != priority){
					indices_priority[count++] = i;
				}
			}
			// New calculator, with gauranteed element associated with the Power Item
			calculator = new NodeCombinationCalculator( indices_priority, numElements);
			calculator.splitNodes( split_a, split_b);
			for( int i = 0; i < calculator.size(); i++){
				calculator.appendElement( calculator.getNode(i), priority);
			}
		}
		// Power item held by first Pokemon
		else if( currentKey.matches("[0-5]d")
		|| currentKey.matches("[0-5]n")){
			int priority = Character.getNumericValue( currentKey.charAt(0));
			// Is second Pokemon holding Destiny Knot or No Item?
			numElements = (currentKey.charAt(1) == 'd') ? 4 : 2;
	
			int[] indices_priority = new int[indices.length-1];
			int count = 0;
			// Copy all elements except for the master element
			for( int i = 0; i < indices.length; i++){
				if( i != priority){
					indices_priority[count++] = i;
				}
			}
			// New calculator, with gauranteed element associated with the Power Item
			calculator = new NodeCombinationCalculator( indices_priority, numElements);
			calculator.splitNodes( split_a, split_b);
			for( int i = 0; i < calculator.size(); i++){
				calculator.appendElement( calculator.getNode(i), priority);
			}
		}
		*/
}
