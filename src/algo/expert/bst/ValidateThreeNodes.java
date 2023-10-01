package algo.expert.bst;

import java.util.*;

public class ValidateThreeNodes {
	// This is an input class. Do not edit.
	static class BST {
		public int value;
		public BST left = null;
		public BST right = null;

		public BST(int value) {
			this.value = value;
		}
	}
	
	static class MyChecker {
		public boolean isFoundDown = false;
	}
	
//	public static void createParrentMap(BST current, BST parrent, HashMap<BST, BST> parrentMap) {
//		if (current == null) {
//			return;
//		}
//		
//		parrentMap.put(current, parrent);
//		createParrentMap(current.left, current, parrentMap);
//		createParrentMap(current.right, current, parrentMap);
//	}
	
	public static void findNodeDown(BST current, BST nodeToFind, MyChecker checker) {
		if (current == null) {
			return;
		}
		
		findNodeDown(current.left, nodeToFind, checker);
		
		if (current.equals(nodeToFind)) {
			checker.isFoundDown = true;
		}
		
		findNodeDown(current.right, nodeToFind, checker);
	}
	
	public boolean validateThreeNodes(BST nodeOne, BST nodeTwo, BST nodeThree) {
		// Write your code here.
		MyChecker nodeChecker = new MyChecker();
		
		findNodeDown(nodeOne, nodeTwo, nodeChecker);
		if (nodeChecker.isFoundDown == true) {
			nodeChecker.isFoundDown = false;
			findNodeDown(nodeTwo, nodeThree, nodeChecker);
			if (nodeChecker.isFoundDown == true) {
				return true;
			}
		} else {
			findNodeDown(nodeThree, nodeTwo, nodeChecker);
			if (nodeChecker.isFoundDown == true) {
				nodeChecker.isFoundDown = false;
				findNodeDown(nodeTwo, nodeOne, nodeChecker);
				if (nodeChecker.isFoundDown == true) {
					return true;
				}
			}
		}
		
		return false;
	}
}
