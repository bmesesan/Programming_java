package algo.expert.bst;

import java.util.*;

class MinBSTHeight {
	public static BST minHeightBst(List<Integer> array) {
        // Write your code here.
    	if (array.size() == 1) {
    		return new BST(array.get(0));
    	}
    	
    	if (array.size() == 2) {
    		BST result = new BST(array.get(0));
    		result.insert(array.get(1));
    		return result;
    	}
    	
    	int midIdx = array.size() / 2;
    	BST tree = new BST(array.get(midIdx));
    	
    	tree.populateBST(array, 0, midIdx - 1);
    	tree.populateBST(array, midIdx + 1, array.size() - 1);
    	
        return tree;
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
            left = null;
            right = null;
        }

        public void insert(int value) {
            if (value < this.value) {
                if (left == null) {
                    left = new BST(value);
                } else {
                    left.insert(value);
                }
            } else {
                if (right == null) {
                    right = new BST(value);
                } else {
                    right.insert(value);
                }
            }
        }
        
        public void populateBST(List<Integer> array, int left, int right) {
        	if (left > right) {
        		return;
        	}
        	
        	if (left == right) {
        		this.insert(array.get(left));
        		return;
        	}
        	
        	int current = left + (right - left) / 2;
        	this.insert(array.get(current));
        	populateBST(array, left, current - 1);
        	populateBST(array, current + 1, right);
        }
    }
    
    public static void main(String args[]) {
    	Integer[] myArr = {1, 2, 5, 7, 10, 13, 14, 15, 22};
    	List<Integer> myList = new ArrayList<Integer>(Arrays.asList(myArr));
    	
    	BST resultTree = minHeightBst(myList);
    	
    	System.out.println("Done!");
    }
    
    
}
