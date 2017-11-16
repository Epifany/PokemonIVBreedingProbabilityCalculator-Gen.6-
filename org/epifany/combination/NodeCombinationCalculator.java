/**
 * Copyright 2016, Stephen Gung, All rights reserved
 */

package org.epifany.combination;

import java.util.ArrayList;

/**
 * This class is an extension of CombinationCalculator.
 * In addition to the functions of CombinationCalculator, this class
 * is capable of acknowledging certain form of splits (vague)
 * 
 * @author Stephen Gung
 */
public class NodeCombinationCalculator extends CombinationCalculator {
	// Our set of nodes. The ith node corresponds with the ith combination
	protected final ArrayList<Node> nodes;
	
	/**
	 * Constructor method with elements from 0 through (elmts - 1)
	 * @param elmts - Signifies the elements to be used
	 * @param lim - Signifies the amount of elements contained per combination
	 */
	public NodeCombinationCalculator( int elmts, int lim){
		super( elmts, lim);
		nodes = new ArrayList( combinations.size());
		for( int i = 0; i < combinations.size(); i++){
			// Copy elements
			ArrayList<Integer> combination = combinations.get(i);
			int[] temp = new int[ combination.size()];
			for( int j = 0; j < temp.length; j++){
				temp[j] = combination.get(j);
			}
			nodes.add( new Node( null, temp));
		}
	}
	
	/**
	 * Constructor method with a specified input of elements
	 * @param elmts - The array of elements
	 * @param lim - Signifies the amount of elements contained per combination
	 */
	public NodeCombinationCalculator( int[] elmts, int lim){
		super( elmts, lim);
		nodes = new ArrayList( combinations.size());
		for( int i = 0; i < combinations.size(); i++){
			// Copy elements
			ArrayList<Integer> combination = combinations.get(i);
			int[] temp = new int[ combination.size()];
			for( int j = 0; j < temp.length; j++){
				temp[j] = combination.get(j);
			}
			nodes.add( new Node( null, temp));
		}
	}

	/**
	 * Copy constructor method
	 * @param ncc - The NodeCombinationCalculator object to be copied
	 */
	public NodeCombinationCalculator( NodeCombinationCalculator ncc){
		super( ncc);
		nodes = new ArrayList( ncc.nodes);
	}
	
	public boolean splitNodes( int[] e1, int[] e2){
		if( e1.length != e2.length){
			return false;
		}
		boolean split = false;
		for( int i = 0; i < e1.length; i++){
			if( splitNodes( e1[i], e2[i])){
				split = true;
			}
		}
		return split;
	}
	
	public boolean splitNodes( int e1, int e2){
		boolean split = false;
		for( int i = 0; i < nodes.size(); i++){
			if( splitNode( nodes.get(i), e1, e2)){
				split = true;
			}
		}
		return split;
	}

	private boolean splitNode( Node target, int e1, int e2){
		if( target.isLeaf()){
			int[] elements_target = target.getElements();
			int index1 = -1;
			int index2 = -1;
			boolean split = false;
			for( int i = 0; i < elements_target.length; i++){
				// If we found the first occurrence of the target value
				if( elements_target[i] == e1 && index1 == -1){
					index1 = i;
				}
				// If we found the first occurrence of the target value
				// And this target value is of a different target
				if( elements_target[i] == e2 && index2 == -1 && elements_target[i] != e1){
					index2 = i;
				}
				// If we found an occurrence to split
				if( index1 != -1 && index2 != -1){
					split = true;
					break;
				}
			}
			if( split){
				// We want to split them into left and right nodes
				int[] elements_child = new int[ elements_target.length - 1];
				int count = 1;
				// Copy each stat
				for( int n = 0; n < elements_target.length; n++){
					if( n != index1 && n != index2){
						elements_child[count] = elements_target[n];
						count++;
					}
				}
				// Create child nodes. Head of the list contain the split value
				elements_child[0] = elements_target[index1];
				target.setLeftChild( new Node( target, elements_child));
				elements_child[0] = elements_target[index2];
				target.setRightChild( new Node( target, elements_child));
			}
			return split;
		}
		else{
			boolean left = splitNode( target.getLeftChild(), e1, e2);
			boolean right = splitNode( target.getRightChild(), e1, e2);
			return (left || right);
		}
	}
	
	public void appendElement( Node target, int e1, int e2){
		if( target.isLeaf()){
			int[] elements_target = target.getElements();
			int[] elements_child = new int[ elements_target.length + 1];
			System.arraycopy( elements_target, 0, elements_child, 0, elements_target.length);
			// Append elements
			elements_child[ elements_child.length - 1] = e1;
			target.setLeftChild( new Node( target, elements_child));
			elements_child[ elements_child.length - 1] = e2;
			target.setRightChild( new Node( target, elements_child));
		}
		else{
			appendElement( target.getLeftChild(), e1, e2);
			appendElement( target.getRightChild(), e1, e2);
		}
	}
	
	public void appendElement( Node target, int e){
		if( target.isLeaf()){
			int[] elements_target = target.getElements();
			int[] elements_append = new int[ elements_target.length + 1];
			System.arraycopy( elements_target, 0, elements_append, 0, elements_target.length);
			// Append element
			elements_append[ elements_append.length - 1] = e;
			target.setElements( elements_append);
		}
		else{
			appendElement( target.getLeftChild(), e);
			appendElement( target.getRightChild(), e);
		}
	}
	
	// Returns the node associated with the ith combination
	public Node getNode( int index){	return nodes.get(index);	}
	
	@Override
	public ArrayList<Integer> remove( int index){
		nodes.remove(index);
		return super.remove(index);
	}
	
	/*
	public void printNodes(){
		for( int i = 0; i < nodes.size(); i++){
			printNode( nodes.get(i));
			System.out.println("");
		}
	}
	
	private void printNode( Node target){
		if( target.isLeaf()){
			System.out.print( Arrays.toString( target.getElements()));
		}
		else{
			System.out.print( "( ");
			printNode( target.getLeftChild());
			System.out.print( ", ");
			printNode( target.getRightChild());
			System.out.print( " )");
		}
	}
	
	// Expand a combination
	private Node createNode( Node parent, int[] e){
		Node node = new Node( parent, e);
		// Look for duplicate stats
		for( int i = 0; i < e.length; i++){
			for( int j = (i+1); j < e.length; j++){
				// If we found a duplicate stat
				if( spottingSplit( e[i], e[j])){
					// We want to split them into the left and right nodes
					// Setup for copying values
					int[] elements_child = new int[ e.length - 1];
					int count = 1;
					// Copy each stat
					for( int n = 0; n < e.length; n++){
						if( n != j && n != i){
							elements_child[count] = e[n];
							count++;
						}
					}
					// Create child nodes, head of the list carries the duplicate
					elements_child[0] = e[i];
					node.setLeftChild( createNode( node, elements_child));
					elements_child[0] = e[j];
					node.setRightChild( createNode( node, elements_child));
					return node;
				}
			}
		}
		// Reaching here meant there were no splits required
		return node;
	}
	
	// Used to find corresponding "duplicates"
	private boolean spottingSplit( int target_a, int target_b){
		for( int i = 0; i < split_a.length; i++){
			if( target_a == split_a[i] && target_b == split_b[i]){
				return true;
			}
		}
		return false;
	}
	*/
}