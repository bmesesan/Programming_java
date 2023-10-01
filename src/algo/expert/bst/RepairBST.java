package algo.expert.bst;

import java.util.*;

public class RepairBST {
	// This is an input class. Do not edit.
	static class BST {
		public int value;
		public BST left = null;
		public BST right = null;

		public BST(int value) {
			this.value = value;
		}
	}
	
	public static BST checkLeftSubtree(BST leftStartElem, BST root) {
		Stack<BST> myStack = new Stack<>();
		
		if (leftStartElem == null) {
			return null;
		}
		
		myStack.push(leftStartElem);
		BST current = leftStartElem.left;
		
		while (!myStack.isEmpty() || current != null) {
			if (current == null && !myStack.isEmpty()) {
				// pop one elem
				BST poppedElem = myStack.pop();
				
				if (poppedElem.value >= root.value) {
					return poppedElem;
				}
				
				current = poppedElem.right;
			} else {
				// push one elem
				myStack.push(current);
				current = current.left;
			}	
		}
		
		return null;
	}
	
	public static BST checkRightSubtree(BST rightStartElem, BST root) {
		Stack<BST> myStack = new Stack<>();
		
		if (rightStartElem == null) {
			return null;
		}
		
		myStack.push(rightStartElem);
		BST current = rightStartElem.left;
		while (!myStack.isEmpty() || current != null) {
			if (current == null && !myStack.isEmpty()) {
				// pop one elem
				BST poppedElem = myStack.pop();
				
				if (poppedElem.value < root.value) {
					return poppedElem;
				}
				
				current = poppedElem.right;
			} else {
				// push one elem
				myStack.push(current);
				current = current.left;
			}	
		}
		
		return null;
	}
	
	public BST repairBst(BST tree) {
		Queue<BST> myQueue = new LinkedList<>();
		
		myQueue.add(tree);
		BST current = tree.left;
		while (!myQueue.isEmpty()) {
			// pop one elem
			BST poppedElem = myQueue.poll();
			
			if (poppedElem.left != null) {
				myQueue.add(poppedElem.left);
			}
			
			if (poppedElem.right != null) {
				myQueue.add(poppedElem.right);
			}
				
			BST leftCheck = checkLeftSubtree(poppedElem.left, poppedElem);
			BST rightCheck = checkRightSubtree(poppedElem.right, poppedElem);
			
			if (leftCheck != null && rightCheck != null) {
				int aux = leftCheck.value;
				leftCheck.value = rightCheck.value;
				rightCheck.value = aux;
				
				return tree;
			} else if (leftCheck != null && rightCheck == null) {
				int aux = leftCheck.value;
				leftCheck.value = poppedElem.value;
				poppedElem.value = aux;
				
				return tree;
			} else if (rightCheck != null && leftCheck == null) {
				int aux = rightCheck.value;
				rightCheck.value = poppedElem.value;
				poppedElem.value = aux;
				
				return tree;
			}
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		RepairBST.BST tree = new RepairBST.BST(2);
	    tree.left = new RepairBST.BST(1);
	    tree.right = new RepairBST.BST(3);
	    tree.left.left = new RepairBST.BST(4);
	    tree.right.right = new RepairBST.BST(0);
	    List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4);
	    BST repaired = new RepairBST().repairBst(tree);
	}
}
