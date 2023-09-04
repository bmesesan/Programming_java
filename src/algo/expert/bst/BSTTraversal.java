package algo.expert.bst;
import java.util.*;

public class BSTTraversal {
	public static List<Integer> inOrderTraverse(BST tree, List<Integer> array) {
		if (tree == null) {
			return array;
		}
		
		inOrderTraverse(tree.left, array);
		array.add(tree.value);
		inOrderTraverse(tree.right, array);
		
	    return array;
    }

	public static List<Integer> preOrderTraverse(BST tree, List<Integer> array) {
		if (tree == null) {
			return array;
		}
		
		array.add(tree.value);
		preOrderTraverse(tree.left, array);
		preOrderTraverse(tree.right, array);
		
	    return array;
	}
	
	public static List<Integer> postOrderTraverse(BST tree, List<Integer> array) {
		if (tree == null) {
			return array;
		}
		
		postOrderTraverse(tree.left, array);
		postOrderTraverse(tree.right, array);
		array.add(tree.value);
		
	    return array;
	}

	static class BST {
		public int value;
		public BST left;
		public BST right;

		public BST(int value) {
			this.value = value;
		}
	}
	
	public static void main(String[] args) {
	    System.out.println("Hello Eclipse!");
	    
	    BST bst = new BST(10);
	    bst.left = new BST(5);
	    bst.left.left = new BST(2);
	    bst.left.left.left = new BST(1);
	    bst.left.right = new BST(5);
	    
	    bst.right = new BST(15);
	    bst.right.right = new BST(22);
	    
	    ArrayList<Integer> result_inorder = new ArrayList<Integer>();
	    result_inorder = (ArrayList<Integer>)BSTTraversal.inOrderTraverse(bst, result_inorder);
	    
	    ArrayList<Integer> result_preorder = new ArrayList<Integer>();
	    result_preorder = (ArrayList<Integer>)BSTTraversal.preOrderTraverse(bst, result_preorder);
	    
	    ArrayList<Integer> result_postorder = new ArrayList<Integer>();
	    result_postorder = (ArrayList<Integer>)BSTTraversal.postOrderTraverse(bst, result_postorder);
	    
	    System.out.println("Result inorder: " + result_inorder);
	    System.out.println("Result preorder: " + result_preorder);
	    System.out.println("Result postorder: " + result_postorder);
	}
}
