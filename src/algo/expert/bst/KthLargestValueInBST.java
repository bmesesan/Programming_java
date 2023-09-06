package algo.expert.bst;

import java.util.*;

public class KthLargestValueInBST {
	
	static class BST {
	    public int value;
	    public BST left = null;
	    public BST right = null;

	    public BST(int value) {
	      this.value = value;
	   }
    }

	public static int findKthLargestValueInBst(BST tree, int k) {
		Stack<BST> myStack = new Stack<BST>();
		myStack.push(tree);
		
		BST current = tree.right;
		
		// Non-recursive in-order traversal
		while (current != null || !myStack.isEmpty()) {
			if (current == null && !myStack.isEmpty()) {
				BST poppedElem = myStack.pop();
				
				k -= 1;
				if (k == 0) {
					return poppedElem.value;
				}
				current = poppedElem.left;
			} else {
				myStack.push(current);
				current = current.right;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		BST tree = new BST(15);
		
		tree.left = new BST(5);
		tree.left.left = new BST(2);
		tree.left.left.left = new BST(1);
		tree.left.left.right = new BST(3);
		tree.left.right = new BST(5);
		
		tree.right = new BST(20);
		tree.right.left = new BST(17);
		tree.right.right = new BST(22);
		
		int value = KthLargestValueInBST.findKthLargestValueInBst(tree, 3);
		
		System.out.println("Value is = " + value);
	}
}
