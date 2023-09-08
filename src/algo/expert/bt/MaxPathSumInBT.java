package algo.expert.bt;

import java.util.*;

public class MaxPathSumInBT {
	public static int getMaxDepthSumSubtree(BinaryTree tree) {
		if (tree.left == null && tree.right == null) {
			return tree.value;
		} else {
			if (tree.left == null) {
				return tree.value + getMaxDepthSumSubtree(tree.right);
			}
			
			if (tree.right == null) {
				return tree.value + getMaxDepthSumSubtree(tree.left);
			}
			
			int leftSum = getMaxDepthSumSubtree(tree.left);
			int rightSum = getMaxDepthSumSubtree(tree.right);
			if (leftSum > rightSum) {
				return tree.value + leftSum;
			} else {
				return tree.value + rightSum;
			}
		}
	}
	
	public static int maxPathSum(BinaryTree tree) {
		// Init the max value
		int max = tree.value;
		int leftSum = 0;
		int rightSum = 0;
		
		if (tree.left != null) {
			leftSum = getMaxDepthSumSubtree(tree.left);
		}
		
		if (tree.right != null) {
			rightSum = getMaxDepthSumSubtree(tree.right);
		}
		
		if (leftSum > 0) {
			max += leftSum;
		}
		
		if (rightSum > 0) {
			max += rightSum;
		}
		
		// Level order traversal
		Queue<BinaryTree> myQueue = new LinkedList<BinaryTree>();
		BinaryTree current = tree;
		
		myQueue.add(current);
		while (!myQueue.isEmpty()) {
			int sum = current.value;
			leftSum = 0;
			rightSum = 0;
			
			if (current.left != null) {
				leftSum = getMaxDepthSumSubtree(current.left);
			}
			
			if (current.right != null) {
				rightSum = getMaxDepthSumSubtree(current.right);
			}
			
			if (leftSum > 0) {
				sum += leftSum;
			}
			
			if (rightSum > 0) {
				sum += rightSum;
			}
			
			System.out.println("Value: " + current.value + "; Sum = " + sum);
			
			if (sum > max) {
				max = sum;
			}
			
			
			if (current.left != null) {
				myQueue.add(current.left);
			}
			
			if (current.right != null) {
				myQueue.add(current.right);
			}
			
			myQueue.remove();
			current = myQueue.peek();
		}
		
	    return max;
    }
	
	static class BinaryTree {
	    public int value;
	    public BinaryTree left;
	    public BinaryTree right;

	    public BinaryTree(int value) {
	    	this.value = value;
	    }
    }
	
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree(1);
		tree.left = new BinaryTree(2);
		tree.left.left = new BinaryTree(4);
		tree.left.right = new BinaryTree(5);
		tree.right = new BinaryTree(3);
		tree.right.left = new BinaryTree(6);
		tree.right.right = new BinaryTree(7);
		
		int max = maxPathSum(tree);
		System.out.println("Max Path Sum = " + max);
	}
}
