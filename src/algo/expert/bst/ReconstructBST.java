package algo.expert.bst;
import java.util.*;

public class ReconstructBST {
	static class BST {
	    public int value;
	    public BST left = null;
	    public BST right = null;

	    public BST(int value) {
	      this.value = value;
	   }
    }
	
	public static BST reconstructBst(ArrayList<Integer> preOrderTraversalValues) {
	    // Write your code here.
		BST tree = new BST(preOrderTraversalValues.get(0)); 
		if (preOrderTraversalValues.size() == 1) {
			return tree;
		}
		ArrayList<BST> bstTraversal = new ArrayList<BST>();
		bstTraversal.add(tree);
		
		if (preOrderTraversalValues.get(1) < tree.value) { 
			tree.left = new BST(preOrderTraversalValues.get(1));
		} else {
			tree.right = new BST(preOrderTraversalValues.get(1));
		}
		
		if (preOrderTraversalValues.size() == 2) {
			return tree;
		}
		bstTraversal.add(tree.left);
		
		for (int i = 2; i < preOrderTraversalValues.size(); i++) {
			int current = preOrderTraversalValues.get(i);
			
			if (current < preOrderTraversalValues.get(i - 1)) {
				bstTraversal.get(i - 1).left = new BST(current);
				bstTraversal.add(bstTraversal.get(i - 1).left);
			} else if (current < preOrderTraversalValues.get(i - 2)) {
				bstTraversal.get(i - 1).right = new BST(current);
				bstTraversal.add(bstTraversal.get(i - 1).right);
			} else if (current >= preOrderTraversalValues.get(i - 2)) {
				int cursor = i - 2;
				while (cursor > 0) {
					if (cursor >= 1) {
						if (current >= preOrderTraversalValues.get(cursor) &&
							current < preOrderTraversalValues.get(cursor - 1)) {
							break;
						}
					}
					cursor--;
				}
				BST bstCursor = bstTraversal.get(cursor);
				while (bstCursor.right != null) {
					bstCursor = bstCursor.right;
				}
				bstCursor.right = new BST(current);
				bstTraversal.add(bstCursor.right);
			}
		}
		
		
	    return tree;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> preOrderTraversalValues = new ArrayList<Integer>();
		preOrderTraversalValues.add(10);
		preOrderTraversalValues.add(4);
		preOrderTraversalValues.add(2);
		preOrderTraversalValues.add(1);
		preOrderTraversalValues.add(5);
		preOrderTraversalValues.add(17);
		preOrderTraversalValues.add(19);
		preOrderTraversalValues.add(18);
		
		BST myTree = ReconstructBST.reconstructBst(preOrderTraversalValues);
		System.out.println("Result = " + myTree.toString());
	}
}
