package algo.expert.bt;
import java.util.*;

public class FindNodesDistanceK {
	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}
	
	public void buildParentsMap(BinaryTree tree, BinaryTree parrent,
								HashMap<BinaryTree, BinaryTree> myMap) {
		if (tree == null) {
			return;
		}
		myMap.put(tree, parrent);
		buildParentsMap(tree.left, tree, myMap);
		buildParentsMap(tree.right, tree, myMap);
	}
	
	public static BinaryTree getTargetNode(BinaryTree tree, int target) {
		Stack<BinaryTree> myStack = new Stack<>();
		
		myStack.push(tree);
		BinaryTree current = tree.left;
		// Non-recursive In-order traversal
		while (current != null || !myStack.isEmpty()) {
			if (current == null && !myStack.isEmpty()) {
				BinaryTree poppedElem = myStack.pop();
				
				if (poppedElem.value == target) {
					return poppedElem;
				}
				current = poppedElem.right;
			} else {
				myStack.push(current);
				current = current.left;
			}
		}
		
		return null;
	}
	
	public void findNodesDistanceKRunner(BinaryTree tree,
										 HashMap<BinaryTree, BinaryTree> parrentsMap,
										 HashMap<BinaryTree, BinaryTree> visitedMap,
										 ArrayList<Integer> result,
										 int k) {
		if (tree == null || visitedMap.containsKey(tree)) {
			return;
		}
		
		if (k == 0) {
			result.add(tree.value);
		}
		visitedMap.put(tree, null);
		findNodesDistanceKRunner(tree.left, parrentsMap, visitedMap, result, k - 1);
		findNodesDistanceKRunner(tree.right, parrentsMap, visitedMap, result, k - 1);
		findNodesDistanceKRunner(parrentsMap.get(tree), parrentsMap, visitedMap, result, k - 1);
	}
	
	public ArrayList<Integer> findNodesDistanceK(BinaryTree tree, int target, int k) {
		// Write your code here.
		HashMap<BinaryTree, BinaryTree> parrentsMap = new HashMap<>();
		
		// Time: O(N); Space: O(N)
		buildParentsMap(tree, null, parrentsMap);
		
		// Time: O(N); Space: O(N)
		BinaryTree targetNode = getTargetNode(tree, target);
		
		HashMap<BinaryTree, BinaryTree> visitedMap = new HashMap<>();
		ArrayList<Integer> result = new ArrayList<>();
		
		// Time: O(N); Space: O(N)
		findNodesDistanceKRunner(targetNode, parrentsMap, visitedMap, result, k);
		
		return result;
	}
	
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree(1);
		
		tree.left = new BinaryTree(2);
		tree.left.left = new BinaryTree(4);
		tree.left.right = new BinaryTree(5);
		
		tree.right = new BinaryTree(3);
		tree.right.right = new BinaryTree(6);
		tree.right.right.left = new BinaryTree(7);
		tree.right.right.right = new BinaryTree(8);
		
		FindNodesDistanceK program = new FindNodesDistanceK();
		ArrayList<Integer> result = program.findNodesDistanceK(tree, 3, 2);
		
		System.out.println(result);
	}
}
