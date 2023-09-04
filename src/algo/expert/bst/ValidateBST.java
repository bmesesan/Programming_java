package algo.expert.bst;

import java.util.Stack;



public class ValidateBST {
	public interface ValidationFunction {
		boolean isValid(BST poppedElem, int value);
	}
	
	public static boolean validateSubtree(BST tree, int value, ValidationFunction valFunc) {
		Stack<BST> myStack = new Stack<>();
		
		BST current = tree;
	    
	    myStack.push(current);
	    current = current.left;
	    while (!(current == null && myStack.empty())) {
	    	if (current == null && !myStack.empty()) {
	    		BST poppedElem = myStack.pop();
	    		
	    		if (!valFunc.isValid(poppedElem, value)) {
	    			return false;
	    		}
	    		current = poppedElem.right;
	    	} else {
	    		myStack.push(current);
	    		current = current.left;
	    	}
	    }
	    
	    return true;
	}
	
	public static boolean validateBst(BST tree) {
	    Stack<BST> myStack = new Stack<>();
	    BST current = tree;
	    
	    // In order traversal using a stack
	    myStack.push(current);
	    current = current.left;
	    while (!(current == null && myStack.empty())) {
	    	if (current == null && !myStack.empty()) {
	    		BST poppedElem = myStack.pop();
	    		
	    		// Node validation - possible left leaf
	    		if (poppedElem.left != null) {
	    			if (!validateSubtree(poppedElem.left,
	    								 poppedElem.value,
	    								 (BST pElem, int val) -> {
	    							  	    	return pElem.value < val;
	    								 }))
	    			{
	    				return false;
	    			}
	    		}
	    		
	    		// Node validation - possible left leaf
	    		if (poppedElem.right != null) {
	    			if (!validateSubtree(poppedElem.right,
							 			 poppedElem.value,
										 (BST pElem, int val) -> {
									  	    	return pElem.value >= val;
										 })) {
						return false;
					}
	    		}
	    		current = poppedElem.right;
	    		
	    	} else {
	    		myStack.push(current);
	    		current = current.left;
	    	}
	    }
		
		
		return true;
	}

	static class BST {
	    public int value;
	    public BST left;
	    public BST right;

	    public BST(int value) {
	      this.value = value;
	    }
	}
	
	public static void runTest6 () {
		BST bst = new BST(10);
	    bst.left = new BST(5);
//	    bst.left.left = new BST(2);
//	    bst.left.left.left = new BST(1);
//	    bst.left.right = new BST(5);
	    bst.left.right = new BST(11);
	    
//	    bst.right = new BST(15);
//	    bst.right.right = new BST(22);
	    
	    boolean result = validateBst(bst);
	    
	    System.out.println("Validation result = " + result);
	}
	
	public static void main(String[] args) {
		runTest6();
//		BST bst = new BST(10);
//	    bst.left = new BST(5);
//	    bst.left.left = new BST(2);
//	    bst.left.left.left = new BST(1);
//	    bst.left.right = new BST(5);
//	    
//	    bst.right = new BST(15);
//	    bst.right.left = new BST(13);
//	    bst.right.left.right = new BST(14);
//	    bst.right.right = new BST(22);
//	    
//	    boolean result = validateBst(bst);
//	    
//	    System.out.println("Validation result = " + result);
	}
}
