package algo.expert.bst;
import java.util.*;
import java.lang.Math;

public class FindClosestValueInBST {
	static class BST {
	    public int value;
	    public BST left;
	    public BST right;

	    public BST(int value) {
	      this.value = value;
	    }
	}
	
	static class ReturnStruct {
		public int getAbsDiff() {
			return absDiff;
		}

		public void setAbsDiff(int absDiff) {
			this.absDiff = absDiff;
		}

		public int getReturnVal() {
			return returnVal;
		}

		public void setReturnVal(int returnVal) {
			this.returnVal = returnVal;
		}

		public int absDiff;
		public int returnVal;
		
		public ReturnStruct(int f_absDiff, int f_returnVal) {
			this.absDiff = f_absDiff;
			this.returnVal = f_returnVal;
		}
	}
	
	public static void bstTraversal(BST tree, int target, ReturnStruct retStruct) {
		// Check if the current node is NULL
		if (tree == null) {
			return;
		}
		
		// Check if the absolute difference needs to be updated
		int absDiff = Math.abs(target - tree.value);
		if (absDiff < retStruct.getAbsDiff()) {
			retStruct.setAbsDiff(absDiff);
			retStruct.setReturnVal(tree.value);
		}
		
		// Check whether to go through the left or the right node next
		if (target >= tree.value) {
			bstTraversal(tree.right, target, retStruct);
		} else {
			bstTraversal(tree.left, target, retStruct);
		}
		
	}
	
	public static int findClosestValueInBst(BST tree, int target) {
		// Write your code here.
		int absDiff = Math.abs(target - tree.value);
		int returnVal = tree.value;
		ReturnStruct retStruct = new ReturnStruct(absDiff, returnVal);
		
		bstTraversal(tree, target, retStruct);
		
	    return retStruct.getReturnVal();
	}
	
	public static void main(String[] args) {
	    System.out.println("Hello Eclipse!");
	    
	    BST bst = new BST(10);
	    bst.left = new BST(5);
	    bst.left.left = new BST(2);
	    bst.left.left.left = new BST(1);
	    bst.left.right = new BST(5);
	    
	    bst.right = new BST(15);
	    bst.right.left = new BST(13);
	    bst.right.left.right = new BST(14);
	    bst.right.right = new BST(22);
	    
	    int result = findClosestValueInBst(bst, 12);
	    
	    System.out.println("Result is: " + result);
	}
}
